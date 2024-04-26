
Para rodar a classe JogoBatalhaNaval siga os seguintes passos:

1 - Certifique-se de usar o IntelliJ

2 - Vá ao menu do Run

3 - Selecione "Edit"

4 - Na janela que se abre, você verá uma lista de configurações de execução no lado esquerdo. Selecione a configuração para a classe em questão

5 - No lado direito, você verá um campo chamado Program arguments. Aqui, você pode inserir os argumentos que o seu programa espera. No seu caso, você deve inserir os caminhos para os arquivos j1.txt e j2.txt.

6 - Como nesse projeto a pasta definida está na raiz, e chama-se arquivos-jogadores, contendo os ditos arquivos, o conteúdo do Program arguments deve ficar assim por exemplo:  
    C:\Disciplina_APS\jogo-batalha-naval\ArquivosJogador\j1.txt C:\Disciplina_APS\jogo-batalha-naval\ArquivosJogador\j2.txt

7 - Certifique-se de que esse é o caminho correto para ambos os arquivos na sua máquina



Falta cumprir os requisitos:

R7)	Caso	o	jogador	afunde	uma	embarcação,	o	jogo	deve	informar	a	mensagem	"AFUNDOU	"+nome
da	embarcação.	Caso	o	jogador	afunde	uma	embarcação,	ele	continua	na	vez.	Veja	o	exemplo
abaixo,	onde	o	jogador	2	afunda	o	Cruzador	do	Jogador	1:

R8)	Caso	um	jogador	entre	com	a	coordenada	de	uma	posição	já	informada	o	jogo	deve	mostrar
uma	mensagem	de	erro:	"TIRO	JA	EXECUTADO"	e	deve	repetir	a	jogada,	exemplo

R9)	Caso	o	jogador	entre	com	uma	coordenada	inválida,	ou	seja,	fora	do	tabuleiro	ou	mal	formatada,
o	jogo	deve	informar	a	mensagem:	"JOGADA	INVALIDA".	Exemplo:

* para "adccd *%#" falta tratar

R10)	Quando	um	jogador	afundar	a	última	embarcação	do	adversário	então	o	jogo	deve	indicar	a
embarcação	que	afundou	e	mostrar	uma	mensagem	que	indica	o	vencedor	e	o	fim	do	jogo.
* (Ainda não foi testado se cumpre esse requisito)