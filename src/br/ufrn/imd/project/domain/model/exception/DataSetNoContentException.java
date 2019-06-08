package br.ufrn.imd.project.domain.model;

public class DataSetNoContentException extends Exception {
	private static final long serialVersionUID = 2486670415931121940L;
	private String message;

	public DataSetNoContentException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
