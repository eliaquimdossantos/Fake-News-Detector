package br.ufrn.imd.project.domain.similarutyAlgorithms;

import java.util.ArrayList;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */

public class AlgorithmTrigram implements Algorithm {

	@Override
	public double startTest(String string1, String string2) {
		char[] firstAll = string1.toCharArray();
		ArrayList<String> firstWordList = new ArrayList<String>();
		for (int i = 0; i < (firstAll.length - 2); i++) {
			firstWordList.add("" + firstAll[i] + firstAll[i + 1] + firstAll[i + 2]);
		}

		char[] secondAll = string2.toCharArray();
		ArrayList<String> secondWordList = new ArrayList<String>();
		for (int i = 0; i < (secondAll.length - 2); i++) {
			secondWordList.add("" + secondAll[i] + secondAll[i + 1] + secondAll[i + 2]);
		}

		double numberOfCommon = 0;
		for (String slow : firstWordList) {
			for (String fast : secondWordList) {
				if (slow.equals(fast)) {
					numberOfCommon++;
				}
			}
		}
		double numberOfTotal = firstWordList.size() + secondWordList.size() - numberOfCommon;

		double result = numberOfCommon / numberOfTotal;				
		
		return result;
	}

}
