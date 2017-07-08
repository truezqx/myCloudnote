package cn.zqx.service;

public class NoteNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 5945504126149082707L;

	public NoteNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoteNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NoteNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoteNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoteNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	

}
