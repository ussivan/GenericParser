package expression.parser;

import expression.exceptions.ParsingException;

public class ExpressionSource {

    protected int pos;
    private String source;
    protected boolean EOF;

    public ExpressionSource(String expression) {
        source = expression;
        pos = 0;
        EOF = false;
    }

    public char peekChar() {
        return source.charAt(pos);
    }

    public char nextChar() {
        if(EOF) {
            return 0;
        }
        char c = peekChar();
        pos++;
        if(pos == source.length()) {
            EOF = true;
        }
        return c;
    }

    public ParsingException error(String message) {
        return new ParsingException(pos, String.format("%d: %s", pos, message));
    }
}
