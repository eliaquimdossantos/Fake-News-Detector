/**
 * Arquivo com a class HandlingParagraph destinada a fazer o tratamento dos parágrafos da notícia
 */
package br.ufrn.imd.project.domain;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.regex.Pattern;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class HandlingParagraph {
	private String text; /* Texto do parágrafo já tratado */

	/**
	 * Tratamento do texto do parágrafo
	 * 
	 * @param text Texto para ser tratado
	 */
	public HandlingParagraph(String text) {
		text = removeWordIfLessThan(4, text);
		text = removeAccent(text);
		text = toLowerCaseText(text);
		text = removeRepeatedWords(text);
		text = sortText(text);
		this.text = text;				
	}

	/**
	 * Texto do parágrafo já tratado
	 * 
	 * @return Texto tratado
	 */
	protected String getText() {
		return text;
	}

	/**
	 * Remove o caractere informado do texto pela expressão regular
	 * 
	 * @param text  Texto a ser alterado
	 * @param regex Expressão regular a ser removida do texto
	 * @return String com o texto alterado
	 */
	private String removeCharacter(String text, String regex) {
		/**
		 * Remove o caractere informado do texto
		 */
		String newText = text.replaceAll(regex, "");

		return newText;
	}

	/**
	 * Remover palavras com tamanho menor que o informado
	 * 
	 * @param size Tamanho informado
	 * @param text Texto informado
	 * @return String com o texto contendo todas as palavras que possuem o tamanho
	 *         mínimo de 'size'
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
			words[i] = removeCharacter(words[i], "[(]");
			words[i] = removeCharacter(words[i], "[)]");
			words[i] = removeCharacter(words[i], ";");
			words[i] = removeCharacter(words[i], "-");

			if (words[i].length() >= size) {
				wordList.add(words[i]);
			}
		}

		return String.join(" ", wordList);
	}

	/**
	 * Remove os acentos do texto
	 * 
	 * @param text Texto passado por parâmetro
	 * @return Texto sem acentuação
	 */
	private static String removeAccent(String text) {
		String nfdNormalizedString = Normalizer.normalize(text, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("");
	}

	/**
	 * Transforma o texto informado em minúsculo
	 * 
	 * @param text Texto informado
	 * @return Texto em minúsculo
	 */
	private String toLowerCaseText(String text) {
		text = text.toLowerCase();

		return text;
	}

	/**
	 * Remove palavras repetidas do texto
	 * 
	 * @param text Texto informado
	 * @return Texto (não ordenado) sem palavras repetidas
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

	private String sortText(String text) {
		String [] words = text.split(" ");
		int text_size = words.length;
		ArrayList<String> wordList = new ArrayList<String>();
		
		for(int i = 0; i < text_size; i++) {
			wordList.add(words[i]);
		}
		
		Collections.sort(wordList);
		
		return String.join(" ", wordList);
	}
}
