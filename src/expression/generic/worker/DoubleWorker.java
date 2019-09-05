package expression.generic.worker;

import expression.exceptions.ParsingException;

public class DoubleWorker implements Worker<Double> {
    @Override
    public Double parseNumber(String number) throws ParsingException {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            throw new ParsingException("Incorrect number format: " + number);
        }
    }

    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }

    @Override
    public Double sub(Double a, Double b) {
        return a - b;
    }

    @Override
    public Double mul(Double a, Double b) {
        return a * b;
    }

    @Override
    public Double div(Double a, Double b) {
        return a / b;
    }

    @Override
    public Double abs(Double a) {
        return Math.abs(a);
    }

    @Override
    public Double square(Double a) {
        return a * a;
    }

    @Override
    public Double mod(Double a, Double b) {
        return a % b;
    }

    @Override
    public Double negate(Double a) {
        return -a;
    }
}
