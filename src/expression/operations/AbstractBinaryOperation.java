package expression.operations;

import expression.exceptions.EvaluationException;
import expression.exceptions.ParsingException;
import expression.generic.worker.Worker;

public abstract class AbstractBinaryOperation<T> implements TripleExpression<T> {
	TripleExpression<T> x;
	TripleExpression<T> y;
	Worker<T> worker;
	
	protected AbstractBinaryOperation(TripleExpression<T> x, TripleExpression<T> y, Worker<T> worker) {
        this.x = x;
        this.y = y;
        this.worker = worker;
    }
	
	protected abstract T count(T x, T y) throws EvaluationException, ParsingException;
	
	public T evaluate(T x, T y, T z) throws EvaluationException, ParsingException {
		return count(this.x.evaluate(x, y, z), this.y.evaluate(x, y, z));
	}
	
}
