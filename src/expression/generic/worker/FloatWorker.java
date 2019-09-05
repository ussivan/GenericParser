package expression.generic.worker;

import expression.exceptions.ParsingException;

public class FloatWorker implements Worker<Float> {
    @Override
    public Float parseNumber(String number) throws ParsingException {
        try {
            return (float)Double.parseDouble(number);
        } catch (NumberFormatException e) {
            throw new ParsingException("Incorrect number format: " + number);
        }
    }

    @Override
    public Float add(Float a, Float b) {
        return a + b;
    }

    @Override
    public Float sub(Float a, Float b) {
        return a - b;
    }

    @Override
    public Float mul(Float a, Float b) {
        return a * b;
    }

    @Override
    public Float div(Float a, Float b) {
        return a / b;
    }

    @Override
    public Float abs(Float a) {
        return Math.abs(a);
    }

    @Override
    public Float square(Float a) {
        return a * a;
    }

    @Override
    public Float mod(Float a, Float b) throws ParsingException {
        return a % b;
    }

    @Override
    public Float negate(Float a) {
        return -a;
    }
}
