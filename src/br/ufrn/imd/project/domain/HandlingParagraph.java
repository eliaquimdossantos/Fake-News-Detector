/**
 * Arquivo com a class HandlingParagraph destinada a fazer o tratamento dos par�grafos da not�cia
 */
package br.ufrn.imd.project.domain;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class HandlingParagraph {
	private String text; /* Texto do par�grafo j� tratado */

	/**
	 * Tratamento do texto do par�grafo
	 * 
	 * @param text Texto para ser tratado
	 */
	public HandlingParagraph(String text) {
		text = removeWordIfLessThan(3, text);
		text = removeAccent(text);
		text = toLowerCaseText(text);
		text = removeRepeatedWords(text);
		this.text = text;
	}

	/**
	 * Texto do par�grafo j� tratado
	 * 
	 * @return Texto tratado
	 */
	protected String getText() {
		return text;
	}

	/**
	 * Remove o caractere informado do texto pela express�o regular
	 * 
	 * @param text  Texto a ser alterado
	 * @param regex Express�o regular a ser removida do texto
	 * @return String com o texto alterado
	 */
	private String removeCharacter(String text, String regex) {
		/**
		 * Remove o caractere informado do texto
		 */
		String newText = text.replaceAll(regex, "");
		// TODO remover espa�os em branco: " "
		// TODO remover quebra de linha: "\n"
		// TODO remover tabula��es: "\t"
		// TODO remover virgulas: ","
		// TODO remover pontos: "[.]"

		return newText;
	}

	/**
	 * Remover palavras com tamanho menor que o informado
	 * 
	 * @param size Tamanho informado
	 * @param text Texto informado
	 * @return String com o texto contendo todas as palavras que possuem o tamanho
	 *         m�nimo de 'size'
	 */
	private String removeWordIfLessThan(int size, String text) {
		String[] words = text.split(" ");
		ArrayList<String> wordList = new ArrayList<String>();

		int sizeText = words.length;

		for (int i = 0; i < sizeText; i++) {
			words[i] = removeCharacter(words[i], " ");
			words[i] = removeCharacter(words[i], "\n");
			words[i] = removeCharacter(words[i], "\t");
			words[i] = removeCharacter(words[i], ",");
			words[i] = removeCharacter(words[i], "[.]");

			if (words[i].length() >= size) {
				wordList.add(words[i]);
			}
		}

		return String.join(" ", wordList);
	}

	/**
	 * Remove os acentos do texto
	 * 
	 * @param text Texto passado por par�metro
	 * @return Texto sem acentua��o
	 */
	private static String removeAccent(String text) {
		String nfdNormalizedString = Normalizer.normalize(text, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("");
	}

	/**
	 * Transforma o texto informado em min�sculo
	 * 
	 * @param text Texto informado
	 * @return Texto em min�sculo
	 */
	private String toLowerCaseText(String text) {
		text = text.toLowerCase();

		return text;
	}

	/**
	 * Remove palavras repetidas do texto
	 * 
	 * @param text Texto informado
	 * @return Texto (n�o ordenado) sem palavras repetidas
	 */
	private String removeRepeatedWords(String text) {
		String[] words = text.split(" ");
		int text_size = words.length;
		HashSet<String> wordList = new HashSet<String>();

		for (int i = 0; i < text_size; i++) {
			wordList.add(words[i]);
		}

		return String.join(" ", wordList);
	}

//	private String sortText(String text) {
//		String [] words = text.split(" ");
//		int text_size = words.length;
//		ArrayList<String> wordList = new ArrayList<String>();
//		
//		for(int i = 0; i < text_size; i++) {
//			wordList.add(words[i]);
//		}
//		
//		Collections.sort(wordList);
//		
//		return String.join(" ", wordList);
//	}
}
