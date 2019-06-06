package br.ufrn.imd.project.domain.controller;

import br.ufrn.imd.project.domain.controller.exception.DataSetNotFoundException;
import br.ufrn.imd.project.domain.model.DataSetModel;
import br.ufrn.imd.project.domain.model.DataSetNoContentException;

public class DataSetController extends DataSetModel {

	private String fileName;
	private int totalFakeNews; // Quantidade de fakenews na base de dados

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public DataSetController() {
		super();
	}

	public void startDataSet(String fileName) throws DataSetNotFoundException, DataSetNoContentException {
		this.fileName = fileName;
		super.loadDataSet(this.fileName);
		totalFakeNews = super.numberOfNews();
	}

	public FakeNews getFakeNews(int i) throws DataSetNotFoundException, DataSetNoContentException {
		FakeNews f = super.readFakeNews(i);

		return f;
	}

	public int getNumberOfNews() {
		return totalFakeNews;
	}
}
