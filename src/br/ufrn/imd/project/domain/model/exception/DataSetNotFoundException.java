package br.ufrn.imd.project.domain.model.exception;

public class DataSetNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;

	public DataSetNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
