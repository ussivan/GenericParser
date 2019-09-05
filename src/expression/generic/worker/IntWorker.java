package expression.generic.worker;

import expression.exceptions.ParsingException;

public class IntWorker implements Worker<Integer> {

    private boolean toCheck;

    public IntWorker(boolean toCheck) {
        this.toCheck = toCheck;
    }

    @Override
    public Integer parseNumber(String number) throws ParsingException {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new ParsingException("Incorrect number format: " + number);
        }
    }

    @Override
    public Integer add(Integer a, Integer b) throws ParsingException {
        if(toCheck)
            checkAdd(a, b);
        return a + b;
    }

    private void checkAdd(int x, int y) throws ParsingException {
        if (x > 0 && Integer.MAX_VALUE - x < y) {
            throw new ParsingException("Overflow");
        }
        if (x < 0 && Integer.MIN_VALUE - x > y) {
            throw new ParsingException("Overflow");
        }
    }

    @Override
    public Integer sub(Integer a, Integer b) throws ParsingException {
        if(toCheck)
            checkSub(a, b);
        return a - b;
    }

    private void checkSub(int x, int y) throws ParsingException {
        if (x >= 0 && y < 0 && x - Integer.MAX_VALUE > y) {
            throw new ParsingException("Overflow");
        }
        if (x <= 0 && y > 0 && Integer.MIN_VALUE - x > -y) {
            throw new ParsingException("Overflow");
        }
    }

    @Override
    public Integer mul(Integer a, Integer b) throws ParsingException {
        if(toCheck)
            checkMul(a, b);
        return a * b;
    }

    private void checkMul(int x, int y) throws ParsingException {
        if (x > 0 && y > 0 && Integer.MAX_VALUE / x < y) {
            throw new ParsingException("Overflow");
        }
        if (x > 0 && y < 0 && Integer.MIN_VALUE / x > y) {
            throw new ParsingException("Overflow");
        }
        if (x < 0 && y > 0 && Integer.MIN_VALUE / y > x) {
            throw new ParsingException("Overflow");
        }
        if (x < 0 && y < 0 && Integer.MAX_VALUE / x > y) {
            throw new ParsingException("Overflow");
        }
    }

    @Override
    public Integer div(Integer a, Integer b) throws ParsingException {
        if(b == 0) {
            throw new ParsingException("Division by zero");
        }
        if(toCheck)
            checkDiv(a, b);
        return a / b;
    }

    private void checkDiv(int x, int y) throws ParsingException {
        if(x == Integer.MIN_VALUE && y == -1) {
            throw new ParsingException("Overflow");
        }
    }

    @Override
    public Integer abs(Integer a) throws ParsingException {
        if(toCheck)
            checkAbs(a);
        return Math.abs(a);
    }

    private void checkAbs(int x) throws ParsingException {
        if(x == Integer.MIN_VALUE) {
            throw new ParsingException("Overflow");
        }
    }

    @Override
    public Integer square(Integer a) throws ParsingException {
        return mul(a, a);
    }

    @Override
    public Integer mod(Integer a, Integer b) throws ParsingException {
        if(b == 0) {
            throw new ParsingException("Division by zero");
        }
        return a % b;
    }

    @Override
    public Integer negate(Integer a) throws ParsingException {
        if(toCheck)
            checkAbs(a);
        return -a;
    }
}
