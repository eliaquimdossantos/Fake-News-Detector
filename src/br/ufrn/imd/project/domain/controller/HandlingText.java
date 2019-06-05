/**
 * Arquivo com a class HandlingText destinada a fazer o tratamento dos textos da notícia
 */
package br.ufrn.imd.project.domain.controller;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.regex.Pattern;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class HandlingText {
	private String text; 

	/**
	 * Construtor da classe
	 * 
	 * @param text Texto para ser tratado
	 */
	public HandlingText(String text) {		
		this.text = text;				
	}
	
	/**
	 * Texto do parágrafo já tratado
	 * 
	 * @return Texto tratado
	 */
	public String getText() {
		return text;
	}

	/**
	 * Remove o caractere informado do texto pela expressão regular
	 * 
	 * @param text  Texto a ser alterado
	 * @param regex Expressão regular a ser removida do texto
	 * @return String com o texto alterado
	 */
	public static String removeCharacter(String text, String regex) {
		/**
		 * Remove o caractere informado do texto
		 */
		String newText = text.replaceAll(regex, "");

		return newText;
	}
	
	/**
	 * Remove o caractere informado do texto pela expressão regular
	 * 
	 * @param regex Expressão regular a ser removida do texto
	 * @return String com o texto alterado
	 */
	public String removeCharacter(String regex) {
		/**
		 * Remove o caractere informado do texto
		 */
		text = text.replaceAll(regex, "");

		return text;
	}

	/**
	 * Remover palavras com tamanho menor que o informado
	 * 
	 * @param size Tamanho informado
	 * @param text Texto informado
	 * @return String com o texto contendo todas as palavras que possuem o tamanho
	 *         mínimo de 'size'
	 */
	public static String removeWordIfLessThan(int size, String text) {
		String[] crackWords = text.split("-");
		text = String.join(" ", crackWords);
		
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
			words[i] = removeCharacter(words[i], "–");

			if (words[i].length() >= size) {
				wordList.add(words[i]);
			}
		}

		return String.join(" ", wordList);
	}
	
	/**
	 * Remover palavras com tamanho menor que o informado
	 * 
	 * @param size Tamanho informado
	 * @return String com o texto contendo todas as palavras que possuem o tamanho
	 *         mínimo de 'size'
	 */
	public String removeWordIfLessThan(int size) {
		String[] crackWords = text.split("-");
		text = String.join(" ", crackWords);
		
		String[] words = text.split(" ");
		ArrayList<String> wordList = new ArrayList<String>();

		int sizeText = words.length;

		for (int i = 0; i < sizeText; i++) {						
			if (words[i].length() >= size) {
				wordList.add(words[i]);
			}
		}

		text = String.join(" ", wordList);
		
		return text;
	}

	/**
	 * Remove todos os caracteres especiais da string/texto
	 * (Se houver uma letra com acento, esta também é removida)
	 * 
	 * @return texto sem caracteres especiais
	 */
	public String removeSpecialCharacter(){
		text = text.replaceAll("[^a-zA-Z\\s]", "");
		return text;
	}
	
	/**
	 * Remove todos os caracteres especiais da string/texto
	 * (Se houver uma letra com acento, esta também é removida)
	 * 
	 * @param text Texto a terem os caracteres especiais removidos 
	 * @return texto sem caracteres especiais
	 */
	public static String removeSpecialCharacter(String text){
		text = text.replaceAll("[^a-zA-Z\\s]", "");
		return text;
	}
	
	/**
	 * Remove os acentos do texto
	 * 
	 * @param text Texto passado por parâmetro
	 * @return Texto sem acentuação
	 */
	public static String removeAccent(String text) {
		String nfdNormalizedString = Normalizer.normalize(text, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
	
	/**
	 * Remove os acentos do texto
	 * 	 
	 * @return Texto sem acentuação
	 */
	public String removeAccent() {
		String nfdNormalizedString = Normalizer.normalize(text, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		
		text = pattern.matcher(nfdNormalizedString).replaceAll("");
		
		return text;
	}

	/**
	 * Transforma o texto informado em minúsculo
	 * 
	 * @param text Texto informado
	 * @return Texto em minúsculo
	 */
	public static String toLowerCaseText(String text) {
		text = text.toLowerCase();

		return text;
	}
	
	/**
	 * Transforma o texto informado em minúsculo
	 * 
	 * @return Texto em minúsculo
	 */
	public String toLowerCaseText() {
		text = text.toLowerCase();

		return text;
	}

	/**
	 * Remove palavras repetidas do texto
	 * 
	 * @param text Texto informado
	 * @return Texto sem palavras repetidas
	 */
	public static String removeRepeatedWords(String text) {
		String[] words = text.split(" ");
		int textSize = words.length;
		HashSet<String> wordList = new HashSet<String>();

		for (int i = 0; i < textSize; i++) {
			wordList.add(words[i]);
		}

		return String.join(" ", wordList);
	}
	
	/**
	 * Remove palavras repetidas do texto
	 * 
	 * @return Texto sem palavras repetidas
	 */
	public String removeRepeatedWords() {
		String[] words = text.split(" ");
		int textSize = words.length;
		HashSet<String> wordList = new HashSet<String>();

		for (int i = 0; i < textSize; i++) {
			wordList.add(words[i]);
		}
		
		text = String.join(" ", wordList);

		return text;
	}

	/**
	 * Ordenar substrings da string 
	 * 
	 * @param text String passada por parâmetro
	 * @return String com substrings em ordem alfabetica
	 */
	public static String sortText(String text) {
		String [] words = text.split(" ");
		int text_size = words.length;
		ArrayList<String> wordList = new ArrayList<String>();
		
		for(int i = 0; i < text_size; i++) {
			wordList.add(words[i]);
		}
		
		Collections.sort(wordList);
		
		return String.join(" ", wordList);
	}
	
	/**
	 * Ordenar substrings da string 
	 * 	 
	 * @return String com substrings em ordem alfabetica
	 */
	public String sortText() {
		String [] words = text.split(" ");
		int text_size = words.length;
		ArrayList<String> wordList = new ArrayList<String>();
		
		for(int i = 0; i < text_size; i++) {
			wordList.add(words[i]);
		}
		
		Collections.sort(wordList);
		
		text = String.join(" ", wordList);
		
		return text;
	}
}
