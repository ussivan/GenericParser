package expression.operations;

import expression.exceptions.EvaluationException;
import expression.exceptions.ParsingException;

public interface TripleExpression<T> {
	T evaluate(T x, T y, T z) throws EvaluationException, ParsingException;
}
