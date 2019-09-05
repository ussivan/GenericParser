package expression.operations;

import expression.exceptions.EvaluationException;
import expression.exceptions.OverflowException;
import expression.exceptions.ParsingException;
import expression.generic.worker.Worker;

public class CheckedAbs<T> extends AbstractUnaryOperation<T> {

	public CheckedAbs(TripleExpression<T> x, Worker<T> worker) {
		super(x, worker);
	}

	@Override
	protected T count(T x) throws ParsingException {
		return worker.abs(x);
	}
}
