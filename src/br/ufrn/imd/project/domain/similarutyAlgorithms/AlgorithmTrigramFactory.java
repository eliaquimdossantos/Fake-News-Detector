package br.ufrn.imd.project.domain.similarutyAlgorithms;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */

public class AlgorithmTrigramFactory extends AlgorithmFactory {

	@Override
	public Algorithm create() {		
		return new AlgorithmTrigram();
	}

}
