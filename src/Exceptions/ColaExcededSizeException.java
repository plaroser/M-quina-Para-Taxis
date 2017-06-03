package Exceptions;

@SuppressWarnings("serial")
public class ColaExcededSizeException extends RuntimeException {
	public ColaExcededSizeException(String msg) {
		super("EXCEPCION: " + msg);
	}
}
