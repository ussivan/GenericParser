package expression.exceptions;

public class ParsingException extends Exception{

	private int pos = -1;
	
	public ParsingException(final int pos, String message) {
		super(message);
		this.pos = pos;
	}

	public ParsingException(String message) {
		super(message);
	}

	public int getPosition() {
		return pos;
	}
}
