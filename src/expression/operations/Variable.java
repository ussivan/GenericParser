package expression.operations;

import expression.exceptions.EvaluationException;
import expression.exceptions.ParsingException;

public class Variable<T> implements TripleExpression<T> {

	char name;

	public Variable(char name) {
		this.name = name;
	}

	@Override
	public T evaluate(T x, T y, T z) throws EvaluationException, ParsingException {
		if(name == 'x') {
			return x;
		}
		if(name == 'y') {
			return y;
		}
		return z;
	}
}
