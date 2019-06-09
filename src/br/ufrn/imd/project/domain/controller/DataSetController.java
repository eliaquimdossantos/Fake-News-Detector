package br.ufrn.imd.project.domain.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import br.ufrn.imd.project.domain.model.DataSetModel;
import br.ufrn.imd.project.domain.model.exception.DataSetNoContentException;
import br.ufrn.imd.project.domain.model.exception.DataSetUninformedException;

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

	public void startDataSet(String fileName) {
		this.fileName = fileName;
		try {
			super.loadDataSet(this.fileName);
		} catch (FileNotFoundException e) {
			MainController.addErrorMessage("Arquivo não encontrado,Verifique o caminho informado.");
		} catch (DataSetUninformedException e) {
			System.err.println(e.getMessage());
		} catch (DataSetNoContentException e) {
			MainController.addErrorMessage("Dados insuficientes,Verifique se está usando a base correta");
			e.printStackTrace();
		} catch (IOException e) {
			MainController.addErrorMessage("Erro ao manipular Base de Dados");
			e.printStackTrace();
		} catch (Exception e) {
			MainController.addErrorMessage("Erro (DataSet)");
		}
		totalFakeNews = super.numberOfNews();
	}

	public FakeNews getFakeNews(int i) throws DataSetUninformedException, DataSetNoContentException {
		FakeNews f = super.readFakeNews(i);

		return f;
	}

	public int getNumberOfNews() {
		return totalFakeNews;
	}
}
