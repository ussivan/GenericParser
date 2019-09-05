package expression.operations;

import expression.exceptions.EvaluationException;
import expression.exceptions.ParsingException;
import expression.generic.worker.Worker;

public abstract class AbstractUnaryOperation<T> implements TripleExpression<T> {
	TripleExpression<T> x;
	Worker<T> worker;
	
	protected AbstractUnaryOperation(TripleExpression<T> x, Worker<T> worker) {
        this.x = x;
        this.worker = worker;
    }
	
	protected abstract T count(T x) throws EvaluationException, ParsingException;
	
	public T evaluate(T x, T y, T z) throws EvaluationException, ParsingException {
		return count(this.x.evaluate(x, y, z));
	}
	
}