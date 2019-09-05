package expression.parser;

import java.util.*;
import expression.exceptions.ParsingException;
import expression.generic.worker.Worker;
import expression.operations.*;

public class ExpressionParser<T> implements Parser<T> {

    private static final Set<String> unaryOperationSet = Set.of(
            "abs", "square", "("
    );
    private static final Set<Character> binaryOperationSet = Set.of(
            '+', '/', '-', '*', ')', '%', (char) 0
    );
    private char curc;
    private ExpressionSource source;
    private T numValue;
    private Worker<T> worker;

    public ExpressionParser(Worker<T> worker) {
        this.worker = worker;
    }

    private void skipSpaces() {
        while (!source.EOF && Character.isWhitespace(source.peekChar())) {
            source.nextChar();
        }
    }

    private String getNextString(char first) {
        StringBuilder ans = new StringBuilder("" + first);
        while (!source.EOF && (Character.isLetterOrDigit(source.peekChar()) || source.peekChar() == '_')) {
            ans.append(source.nextChar());
        }
        ans = new StringBuilder(ans.toString().replaceAll("_", ""));
        return ans.toString();
    }

    private void parseNumber(char first) throws ParsingException {
        StringBuilder num = new StringBuilder("" + first);
        while (!source.EOF && (Character.isDigit(source.peekChar()) || source.peekChar() == '.' || source.peekChar() == ',')) {
            num.append(source.nextChar());
        }
        try {
            numValue = worker.parseNumber(num.toString());
        } catch (Exception e) {
            throw source.error("Illegal argument: " + num);
        }
    }

    private TripleExpression<T> parseString(char first) throws ParsingException {
        String s = getNextString(first);
        if (s.equals("x") || s.equals("y") || s.equals("z")) {
            char varName = s.charAt(0);
            curc = nextOperation();
            return new Variable<>(varName);
        }
        if (!unaryOperationSet.contains(s)) {
            if (binaryOperationSet.contains(first) && s.length() == 1) {
                throw source.error("No argument");
            }
        }
        if (s.equals("square")) {
            return new CheckedSquare<>(unaryOps(), worker);
        }
        if (s.equals("abs")) {
            return new CheckedAbs<>(unaryOps(), worker);
        }
        throw source.error("Unknown operation: " + s);
    }

    private char nextOperation() throws ParsingException {
        skipSpaces();
        char c = source.nextChar();
        if (!binaryOperationSet.contains(c)) {
            throw source.error("Unknown operation: " + c);
        }
        return c;
    }

    private TripleExpression<T> unaryOps() throws ParsingException {
        TripleExpression<T> ans;
        if (source.EOF) {
            throw source.error("No argument");
        }
        skipSpaces();
        curc = source.nextChar();
        switch (curc) {
            case '-':
                if (source.EOF) {
                    throw source.error("No argument");
                }
                skipSpaces();
                if (Character.isDigit(source.peekChar())) {
                    parseNumber(curc);
                    curc = nextOperation();
                    return new Const<>(numValue);
                } else {
                    return new CheckedNegate<>(unaryOps(), worker);
                }
            case '(':
                ans = addSub();
                if (curc != ')') {
                    throw source.error("No closing brace");
                }
                curc = nextOperation();
                return ans;
            default:
                if (Character.isDigit(curc)) {
                    parseNumber(curc);
                    curc = nextOperation();
                    return new Const<>(numValue);
                } else {
                    return parseString(curc);
                }
        }
    }

    private TripleExpression<T> mulDivMod() throws ParsingException {
        TripleExpression<T> ans = unaryOps();
        while (true) {
            switch (curc) {
                case '*':
                    ans = new CheckedMultiply<>(ans, unaryOps(), worker);
                    break;
                case '/':
                    ans = new CheckedDivide<>(ans, unaryOps(), worker);
                    break;
                case '%':
                    ans = new CheckedMod<>(ans, unaryOps(), worker);
                    break;
                default:
                    return ans;
            }
        }
    }

    private TripleExpression<T> addSub() throws ParsingException {
        TripleExpression<T> ans = mulDivMod();
        while (true) {
            switch (curc) {
                case '+':
                    ans = new CheckedAdd<>(ans, mulDivMod(), worker);
                    break;
                case '-':
                    ans = new CheckedSubtract<>(ans, mulDivMod(), worker);
                    break;
                default:
                    return ans;
            }
        }
    }

    @Override
    public TripleExpression<T> parse(String s) throws ParsingException {
        source = new ExpressionSource(s.replaceAll("mod", "%"));
        TripleExpression<T> result = addSub();
        if (curc != 0 || !source.EOF) {
            throw source.error("Excess closing brace");
        }
        return result;
    }
}
