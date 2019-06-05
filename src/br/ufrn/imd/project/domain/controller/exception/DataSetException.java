package br.ufrn.imd.project.domain.controller.exception;

public class DataSetException extends Exception {
	
	private String message;
	
	public DataSetException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
