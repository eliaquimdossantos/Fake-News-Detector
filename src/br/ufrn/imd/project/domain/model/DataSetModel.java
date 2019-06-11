/**
 * Arquivo com a class DataSet para armazenar as FakeNews de comparação
 */
package br.ufrn.imd.project.domain.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import br.ufrn.imd.project.domain.controller.FakeNewsController;
import br.ufrn.imd.project.domain.model.exception.DataSetNoContentException;
import br.ufrn.imd.project.domain.model.exception.DataSetUninformedException;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class DataSetModel {

	private ArrayList<FakeNewsController> dataSet; /* Banco de dados com as fakenews */
	private final String COMMA_DELIMITER = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
	private String fileName;
	private BufferedReader br;

	/**
	 * Novo banco de dados
	 * 
	 */
	public DataSetModel() {
		setFileName("");
		this.dataSet = new ArrayList<FakeNewsController>();
	}

	/**
	 * Novo banco de dados
	 * 
	 * @param fileName Caminho do arquivo de dados
	 */
	public DataSetModel(String fileName) {
		this.fileName = fileName;
		this.dataSet = new ArrayList<FakeNewsController>();
	}

	/**
	 * Lê todo o conteúdo da base de dados e a prepara para operações como ler uma
	 * notícia específica ou a quantidade de notícias armazenadas
	 * 
	 * @throws IOException
	 */
	public void loadDataSet(String fileName)
			throws DataSetUninformedException, DataSetNoContentException, FileNotFoundException, IOException {

		// Variáveis auxiliares
		String newsLink;
		String newsDate;
		String newsText;

		System.out.println("Inicializando dataset"); // LOG

		if (fileName.isEmpty()) {
			throw new DataSetNoContentException("Falha ao ler base de dados");
		}

		this.setFileName(fileName);

		if (fileName == "") {
			throw new DataSetUninformedException(
					"Erro no caminho do DataSet," + "Verifique se o caminho está completo");
		}

		int totalRead = 0;
		br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		String[] columnNames = line.split(",");

		int hoaxIndex = 0;
		int timestampIndex = 0;
		int linkIndex = 0;

		int index = 0;
		for (String columnName : columnNames) {
			switch (columnName) {
			case "hoax":
				hoaxIndex = index;
				break;
			case "timestamp":
				timestampIndex = index;
				break;
			case "link":
				linkIndex = index;
			}
						
			index++;
		}				

		System.out.println("Carregando dados do dataset"); // LOG

		while ((line = br.readLine()) != null) {
			String[] values = line.split(COMMA_DELIMITER);

			try {
				newsLink = values[linkIndex];
			} catch (Exception e) {
				newsLink = "";
			}

			try {
				newsText = values[hoaxIndex];
			} catch (Exception e) {
				newsText = "";
			}

			try {
				newsDate = values[timestampIndex];
			} catch (Exception e) {
				newsDate = "";
			}

			/**
			 * Captura fakenews do dataset
			 */
			dataSet.add(new FakeNewsController(newsLink, newsDate, newsText));

			totalRead++;
		}

		System.out.println(totalRead + " Linhas carregadas do dataset"); // LOG
	}

	/**
	 * Lê todo o conteúdo da base de dados e a prepara para operações como ler uma
	 * notícia específica ou a quantidade de notícias armazenadas
	 * 
	 * @throws IOException
	 */
	public void loadDataSet() throws DataSetUninformedException, DataSetNoContentException, IOException {
		if (fileName.isEmpty()) {
			throw new DataSetNoContentException("Falha ao ler base de dados");
		}

		this.setFileName(fileName);

		if (fileName == "") {
			throw new DataSetUninformedException(
					"Erro no caminho do DataSet," + "Verifique se o caminho está completo");
		}

		br = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = br.readLine()) != null) {
			String[] values = line.split(COMMA_DELIMITER);

			if (values[1] != null) {
				dataSet.add(new FakeNewsController(values[1], values[2], values[3]));
			}

		}
	}

	/**
	 * Número de fakenews
	 * 
	 * @return Quntidade de notícias no banco de dados
	 */
	public int numberOfNews() {
		return dataSet.size();
	}

	/**
	 * Obter uma fakenews
	 * 
	 * @param index Posição da notícia no banco de dados
	 * @return A FakeNews
	 */
	public FakeNewsController readFakeNews(int index) throws DataSetNoContentException {
		if ((index > numberOfNews()) || (index <= 0)) {
			throw new DataSetNoContentException("A base de dados está vazia?");
		} else {
			int numberCorrection = index - 1;
			return dataSet.get(numberCorrection);
		}
	}

	public String getFileName() throws DataSetNoContentException {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
