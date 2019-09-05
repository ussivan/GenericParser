package expression.operations;

import expression.exceptions.EvaluationException;
import expression.exceptions.OverflowException;
import expression.exceptions.ParsingException;
import expression.generic.worker.Worker;

public class CheckedMod<T> extends AbstractBinaryOperation<T> {

    public CheckedMod(TripleExpression<T> x, TripleExpression<T> y, Worker<T> worker) {
        super(x, y, worker);
    }

    @Override
    protected T count(T x, T y) throws ParsingException {
        return worker.mod(x, y);
    }
}