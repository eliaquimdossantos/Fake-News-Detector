package br.ufrn.imd.lpii.domain;

public class testes {
	public static void main(String[] args) {
		String text = "Delegado da Polícia Federal, eles ocupava eles o posto desde 29 de abril e foi dispensado após 18 dias de trabalho. O motivo da exoneração teria sido uma disputa com a procuradora-chefe do Inep, Carolina Scherer Bicca, segundo relataram ao UOL servidores do órgão. A demissão pegou muitos funcionários do Inep de surpresa. O conflito aconteceu em torno de uma divergência sobre a transparência de dados do Inep sobre os estudantes do ensino básico e superior. Segundo o UOL apurou, Vicenzi era favorável ao uso dessas informações para a formulação de políticas públicas. Já a procuradora é contra. Discordando de pareceres, decidiu retirar a função comissionada de um procurador. A decisão não ... - Veja mais em https://educacao.uol.com.br/noticias/2019/05/16/presidente-do-inep-e-demitido-apos-disputa-com-procuradora-chefe.htm?cmpid=copiaecola";
		
		System.out.println(text);
		System.out.println("");
		TextFormater f = new TextFormater();
		String newString = f.removeWordIfLessThan(4, text);
		newString = f.toLowerCaseText(newString);
		newString = f.removeAccent(newString);
		newString = f.sortText(newString);
		System.out.println(newString);
	}
}
