package br.ufrn.imd.project.domain.similarutyAlgorithms;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */

public class AlgorithmLevenshteinFactory extends AlgorithmFactory{
	
	public Algorithm create() {
		return new AlgorithmLevenshtein();
	}
	
}
