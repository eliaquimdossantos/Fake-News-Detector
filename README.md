# Projeto FAKE NEWS LP2

Desenvolvido  por [Allan de Miranda](https://github.com/allandemiranda)
                e [Eliaquim dos Santos](https://github.com/eliaquimdossantos)

23 de maio de 2019 - LP2 - IMD

## Introdução

Fake News (…) trata-se literalmente da divulgação de notícias falsas por meio das redes sociais que, com a popularização da internet, podem causar impactos negativos na sociedade (CELI, 2019). Para evitar tais problemas, é necessário a criação de uma ferramenta de verificação de notícias.

Com o conhecimento adquirido na disciplina de Linguagem de Programação 2 do curso de Tecnologia da Informação da UFRN, foi desenvolvido um software em Java para a detecção de fake news. 

## Instalação e execução

Para instalar e executar o programa siga as instruções:

Para Linux:

1. Abra o terminal do seu sistema operacional e digite `cd`
2. `git clone https://github.com/eliaquimdossantos/fake-news-detector`
3. `cd fake-news-detector`
4. `java -jar FakeNewsDetector.jar`

Para Windows:

1. Clone o arquivo compactado do projeto no link `https://github.com/eliaquimdossantos/fake-news-detector`
2. Descompacte o arquivo e abra sua pasta raiz
3. Execute o arquivo `FakeNewsDetector.jar`

## Como utilizar

Ao abrir o programa é possível identificar vários campos:

1. Campo para adicionar link do endereço da notícia a ser analisada:
<img src="https://raw.githubusercontent.com/eliaquimdossantos/fake-news-detector/master/img/img3.jpg" alt="img" />

2. Campo para selecionar algorítimos que podem ser utilizados na análise
<img src="https://raw.githubusercontent.com/eliaquimdossantos/fake-news-detector/master/img/img5.jpg" alt="img" />

3. Campo para adicionar banco de dados de fake news
<img src="https://raw.githubusercontent.com/eliaquimdossantos/fake-news-detector/master/img/img6.jpg" alt="img" />

4. Campo para selecionar quanto de porcentagem de semelhança é uma fake news
<img src="https://raw.githubusercontent.com/eliaquimdossantos/fake-news-detector/master/img/img4.jpg" alt="img" />

5. Ao clicar no campo Verificar e seja exibida a porcentagem de semelhança entre os parágrafos da notícia a ser verificada, com o banco de dados selecionado
<img src="https://raw.githubusercontent.com/eliaquimdossantos/fake-news-detector/master/img/img2.jpg" alt="img" />

## Diagramas

1. Diagrama de casos de uso
<img src="https://raw.githubusercontent.com/eliaquimdossantos/fake-news-detector/master/img/uml/Use%20cases.png" alt="img" />

2. Diagrama de classes
<img src="https://raw.githubusercontent.com/eliaquimdossantos/fake-news-detector/master/img/uml/Class%20Diagram.png" alt="img" />

3. Packages
<img src="https://raw.githubusercontent.com/eliaquimdossantos/fake-news-detector/master/img/uml/Packages.png" alt="img" />

## Conclusão

Para que o propósito de uma ferramenta de detecção de Fake News seja atingido com perfeição, e seguindo a lógica do projeto desenvolvido, temos de adquirir um banco de dados tipo “real time” com notícias já taxadas falsas, o que não é uma tarefa tão simples, visto que identificar se algo é falso ou não pode ser bastante subjetivo, incluindo-se o fator humano (personalidade, carater, ideologia, política, etc.). Além desta existem também a dificuldade da coleta de informações de sites de notícias. Por não seguirem o padrão de tags HTML, é possível que textos que não fazem parte da notícia gere ruídos nas análises. O aperfeiçoamento destes dois pontos precisam ser aprimorados.

Concluímos que é possível, através de algoritmo de similaridade, obter resulto viável de similaridade e detectar o quanto ela é parecida com notícias falsas já identificadas e armazenadas em um banco de dados próprio, sendo assim possível distinguir se ela é falsa ou não (ainda precisa-se incluir o fator humano na decisão final). Porem é necessário que nos aprofundemos cientificamente neste tipo de analise, tanto buscando soluções em TI (para similaridade textual) quando em Psicologia (visto que isso é um dilema social de natureza psíquico), para não taxarmos uma notícia de falsa, simplesmente pela sua similaridade com uma outra já identificada falsa, caído no erro e críticas semelhantes ao Sistema Compas (Maybin, 2016) dos Estados Unidos.

## Referências

CELI, Renata. Fake news: o que é, consequências e redação!. Stood. Disponível em: <https://www.stoodi.com.br/blog/2019/01/03/fake-news-o-que-e/>. Acesso em: 23 mai. 2019.

Maybin, S. Sistema de algoritmo que determina pena de condenados cria polêmica nos EUA. BBC Brasil. 31 out. 2016. Disponível em: <https://www.bbc.com/portuguese/brasil-37677421>. Acesso em: 06 jun. 2019.
