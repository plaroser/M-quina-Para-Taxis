package Exceptions;

@SuppressWarnings("serial")
public class LlevateTuNullDeAquiException extends RuntimeException {
	public LlevateTuNullDeAquiException(String msg) {
		super("EXCEPCION: " + msg);
	}
}
