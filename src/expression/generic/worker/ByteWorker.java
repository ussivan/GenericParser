package expression.generic.worker;

import expression.exceptions.ParsingException;

public class ByteWorker implements Worker<Byte> {
    @Override
    public Byte parseNumber(String number) throws ParsingException{
        try {
            return (byte)Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new ParsingException("Incorrect number format: " + number);
        }
    }

    @Override
    public Byte add(Byte a, Byte b) {
        return (byte)(a + b);
    }

    @Override
    public Byte sub(Byte a, Byte b) {
        return (byte)(a - b);
    }

    @Override
    public Byte mul(Byte a, Byte b) {
        return (byte)(a * b);
    }

    @Override
    public Byte div(Byte a, Byte b) throws ParsingException {
        if(b == 0) {
            throw new ParsingException("Division by zero");
        }
        return (byte)(a / b);
    }

    @Override
    public Byte abs(Byte a) {
        return (byte)Math.abs(a);
    }

    @Override
    public Byte square(Byte a) {
        return (byte)(a * a);
    }

    @Override
    public Byte mod(Byte a, Byte b) throws ParsingException {
        if(b == 0) {
            throw new ParsingException("Division by zero");
        }
        return (byte)(a % b);
    }

    @Override
    public Byte negate(Byte a) {
        return (byte)-a;
    }
}
