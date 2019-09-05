package expression.generic.worker;

import expression.exceptions.ParsingException;

import java.math.BigInteger;

public class BigIntegerWorker implements Worker<BigInteger> {
    @Override
    public BigInteger parseNumber(String number) throws ParsingException {
        try {
            return new BigInteger(number);
        } catch (NumberFormatException e) {
            throw new ParsingException("Incorrect number format: " + number);
        }
    }

    @Override
    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger sub(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger mul(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public BigInteger div(BigInteger a, BigInteger b) throws ParsingException {
        if(b.equals(BigInteger.ZERO)) {
            throw new ParsingException("Division by zero");
        }
        return a.divide(b);
    }

    @Override
    public BigInteger abs(BigInteger a) {
        return a.abs();
    }

    @Override
    public BigInteger square(BigInteger a) throws ParsingException {
        return mul(a, a);
    }

    @Override
    public BigInteger mod(BigInteger a, BigInteger b) throws ParsingException {
        if(b.compareTo(BigInteger.ZERO) <= 0) {
            throw new ParsingException("Division by zero");
        }
        return a.mod(b);
    }

    @Override
    public BigInteger negate(BigInteger a) {
        return a.negate();
    }
}
