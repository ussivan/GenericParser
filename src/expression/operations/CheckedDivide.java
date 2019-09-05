package expression.operations;

import expression.exceptions.ParsingException;
import expression.generic.worker.Worker;

public class CheckedDivide<T> extends AbstractBinaryOperation<T>  {

	public CheckedDivide(TripleExpression<T> x, TripleExpression<T> y, Worker<T> worker) {
		super(x, y, worker);
	}

	@Override
	protected T count(T x, T y) throws ParsingException {
		return worker.div(x, y);
	}
}
