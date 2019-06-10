package br.ufrn.imd.project.domain.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import br.ufrn.imd.project.domain.model.DataSetModel;
import br.ufrn.imd.project.domain.model.exception.DataSetNoContentException;
import br.ufrn.imd.project.domain.model.exception.DataSetUninformedException;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */

public class DataSetController {

	private DataSetModel dataSet;
	private String fileName;
	private int totalFakeNews; // Quantidade de fakenews na base de dados

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public DataSetController() {
		dataSet = new DataSetModel();
	}

	/**
	 * Iniciar comunicação com a base de dados
	 * @param fileName Nome do dataset
	 */
	public void startDataSet(String fileName) {
		this.fileName = fileName;
		try {
			dataSet.loadDataSet(this.fileName);
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
		} 
		totalFakeNews = dataSet.numberOfNews();
	}

	/**
	 * Considerando o dataset como, por exemplo, uma lista telefônica,
	 * retorna a fakenews referente ao número passado
	 * 
	 * @param i Número da notícia na 'lista'
	 * @return Notícia referente ao número passado por parâmetro
	 * @throws DataSetUninformedException Caso filename não informado
	 * @throws DataSetNoContentException Caso não tenham informações no dataset
	 */
	public FakeNewsController getFakeNews(int i) throws DataSetUninformedException, DataSetNoContentException {
		FakeNewsController f = dataSet.readFakeNews(i);		

		return f;
	}

	/**
	 * Quantas notícias estão armazenadas no dataset
	 * 
	 * @return Quantidade de notícias do dataset
	 */
	public int getNumberOfNews() {
		return totalFakeNews;
	}
}
