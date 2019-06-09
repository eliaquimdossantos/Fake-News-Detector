/**
 * Arquivo com a class DataSet para armazenar as FakeNews de comparação
 */
package br.ufrn.imd.project.domain.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import br.ufrn.imd.project.domain.controller.FakeNews;
import br.ufrn.imd.project.domain.model.exception.DataSetNoContentException;
import br.ufrn.imd.project.domain.model.exception.DataSetUninformedException;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class DataSetModel {

	private ArrayList<FakeNews> dataSet; /* Banco de dados com as fakenews */
	private final String COMMA_DELIMITER = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
	private String fileName;
	private BufferedReader br;

	/**
	 * Novo banco de dados
	 * 
	 */
	public DataSetModel() {
		setFileName("");
		this.dataSet = new ArrayList<FakeNews>();
	}

	/**
	 * Novo banco de dados
	 * 
	 * @param fileName Caminho do arquivo de dados
	 */
	public DataSetModel(String fileName) {
		this.fileName = fileName;
		this.dataSet = new ArrayList<FakeNews>();
	}

	/**
	 * Lê todo o conteúdo da base de dados e a prepara para operações como ler uma
	 * notícia específica ou a quantidade de notícias armazenadas
	 * @throws IOException 
	 */
	protected void loadDataSet(String fileName)
			throws DataSetUninformedException, DataSetNoContentException, FileNotFoundException, IOException {
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
				System.out.println(values[1] +" "+ values[2] +" "+ values[3]);
				dataSet.add(new FakeNews(values[1], values[2], values[3]));
			}

		}
	}
	
	/**
	 * Lê todo o conteúdo da base de dados e a prepara para operações como ler uma
	 * notícia específica ou a quantidade de notícias armazenadas
	 * @throws IOException 
	 */
	protected void loadDataSet()
			throws DataSetUninformedException, DataSetNoContentException, IOException {
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
				dataSet.add(new FakeNews(values[1], values[2], values[3]));
			}

		}
	}

	/**
	 * Número de fakenews
	 * 
	 * @return Quntidade de notícias no banco de dados
	 */
	protected int numberOfNews() {
		return dataSet.size();
	}

	/**
	 * Obter uma fakenews
	 * 
	 * @param number Posição da notícia no banco de dados
	 * @return A FakeNews
	 */
	protected FakeNews readFakeNews(int number) throws DataSetNoContentException {
		if ((number > numberOfNews()) || (number <= 0)) {
			throw new DataSetNoContentException("A base de dados está vazia?");
		} else {
			int numberCorrection = number - 1;
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
