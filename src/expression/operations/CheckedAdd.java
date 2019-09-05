package expression.operations;

import expression.exceptions.EvaluationException;
import expression.exceptions.OverflowException;
import expression.exceptions.ParsingException;
import expression.generic.worker.Worker;

public class CheckedAdd<T> extends AbstractBinaryOperation<T> {
	
	public CheckedAdd(TripleExpression<T> x, TripleExpression<T> y, Worker<T> worker) {
        super(x, y, worker);
    }
    
	@Override
	protected T count(T x, T y) throws ParsingException {
		return worker.add(x, y);
	}


}
