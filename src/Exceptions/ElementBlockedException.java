package Exceptions;

@SuppressWarnings("serial")
public class ElementBlockedException extends RuntimeException {
	public ElementBlockedException(String msg) {
		super("EXCEPCION: " + msg);
	}
}
