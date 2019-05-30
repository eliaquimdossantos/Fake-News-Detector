/**
 * Arquivo com a class DataSet para armazenar as FakeNews de comparação
 */
package br.ufrn.imd.project.domain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class DataSet {

	ArrayList<FakeNews> dataBaseFake; /* Banco de dados com as fakenews */

	/**
	 * Novo banco de dados
	 * 
	 * @param fileName Caminho do arquivo de dados
	 */
	public DataSet(String fileName) {
		this.dataBaseFake = new ArrayList<FakeNews>();
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
					dataBaseFake.add(news);
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
	public int numberOfNews() {
		return dataBaseFake.size();
	}

	/**
	 * Obter uma fakenews
	 * 
	 * @param number Posição da notícia no banco de dados
	 * @return A FakeNews
	 */
	public FakeNews getFakeNews(int number) {
		if ((number > numberOfNews()) || (number <= 0)) {
			// retornar erro pois o número é maior que a quantidade de parágrafos
			return null;
		} else {
			int numberCorrection = number - 1;
			return dataBaseFake.get(numberCorrection);
		}
	}

}
