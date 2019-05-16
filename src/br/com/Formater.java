package br.com;

import java.util.ArrayList;
import java.util.Collections;

public class TextFormater {	
	public String removeWordLessThan(int size, String text) {
		
		String [] words = text.split(" ");
		int size_texto = words.length;
		ArrayList<String> unorderedText = new ArrayList();		
		
		for(int i = 0; i < size_texto; i++) {
			words[i] = words[i].replaceAll(" ","");
			words[i] = words[i].replaceAll("\n","");
			words[i] = words[i].replaceAll("\t","");
			words[i] = words[i].replaceAll(",","");			
			words[i] = words[i].replaceAll("[.]","");
			
			if(words[i].length() >= size) {
				unorderedText.add(words[i]);
			}
		}
				
		String newText = String.join(" ", unorderedText);	
		
		return newText;
	}
	
	public void toLowerCaseText(String text) {
		text = text.toLowerCase();
		
		return text;
	}
}
