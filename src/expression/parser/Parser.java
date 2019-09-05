package expression.parser;

import expression.operations.TripleExpression;
import expression.exceptions.ParsingException;

public interface Parser<T> {
	TripleExpression<T> parse(String s) throws ParsingException;
}
