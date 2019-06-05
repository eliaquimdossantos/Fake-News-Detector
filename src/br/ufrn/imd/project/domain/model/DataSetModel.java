/**
 * Arquivo com a class DataSet para armazenar as FakeNews de comparação
 */
package br.ufrn.imd.project.domain.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import br.ufrn.imd.project.domain.controller.FakeNews;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class DataSetModel {

	private ArrayList<FakeNews> dataSet; /* Banco de dados com as fakenews */
	private String fileName;	

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
	protected DataSetModel(String fileName) {
		this.dataSet = new ArrayList<FakeNews>();
		BufferedReader bufferedReader = null;
		String line = "";
		String csvDivisor = '"' + "";
		try {
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			;
			try {
				while ((line = bufferedReader.readLine()) != null) {
					String[] content = line.split(csvDivisor);

					String newsParagraph = content[1];

					String[] contentLast = content[2].split(",");
					String newsLink = contentLast[1];
					String newsData = contentLast[2];

					FakeNews news = new FakeNews(newsLink, newsData, newsParagraph);
					dataSet.add(news);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Lê todo o conteúdo da base de dados e a prepara 
	 * para operações como ler uma notícia específica ou 
	 * a quantidade de notícias armazenadas
	 */
	protected void loadDataSet(String fileName) {
		this.setFileName(fileName);
		BufferedReader bufferedReader = null;
		String line = "";
		String csvDivisor = '"' + "";
		try {
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			;
			try {
				while ((line = bufferedReader.readLine()) != null) {
					String[] content = line.split(csvDivisor);

					String newsParagraph = content[1];

					String[] contentLast = content[2].split(",");
					String newsLink = contentLast[1];
					String newsData = contentLast[2];

					FakeNews news = new FakeNews(newsLink, newsData, newsParagraph);
					dataSet.add(news);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
	protected FakeNews readFakeNews(int number) {
		if ((number > numberOfNews()) || (number <= 0)) {
			System.err.println("WARNIG: DataSet is empty");
			return null;
		} else {
			int numberCorrection = number - 1;
			return dataSet.get(numberCorrection);			
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}		
	
}
