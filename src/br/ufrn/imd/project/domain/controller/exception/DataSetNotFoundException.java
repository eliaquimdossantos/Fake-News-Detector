package br.ufrn.imd.project.domain.controller.exception;

public class DataSetNotFoundException extends Exception {

	private String message;

	public DataSetNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
