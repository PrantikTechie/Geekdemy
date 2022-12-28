package com.example.geektrust.exception;

/**
 * @author Prantik Sarkar
 * @date 11/12/22
 */

public class ProgramException  extends RuntimeException{
	
	private static final long serialVersionUID = 5012534211445292246L;

	public ProgramException() {
        super();
    }

    public ProgramException(String message) {
        super(message);
    }

}
