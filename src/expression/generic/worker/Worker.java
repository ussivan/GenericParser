package expression.generic.worker;

import expression.exceptions.ParsingException;

public interface Worker<T> {

    T parseNumber(String number) throws ParsingException;

    T add(T a, T b) throws ParsingException;

    T sub(T a, T b) throws ParsingException;

    T mul(T a, T b) throws ParsingException;

    T div(T a, T b) throws ParsingException;

    T abs(T a) throws ParsingException;

    T square(T a) throws ParsingException;

    T mod(T a, T b) throws ParsingException;

    T negate(T a) throws ParsingException;

}
