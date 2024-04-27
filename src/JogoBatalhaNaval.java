import java.io.*;
import java.util.Scanner;

public class JogoBatalhaNaval {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java JogoBatalhaNaval <j1.txt> <j2.txt>");
            return;
        }

        String arquivoJogador1 = args[0];
        String arquivoJogador2 = args[1];

        // Carregar tabuleiros dos jogadores
        Tabuleiro tabuleiroJogador1 = carregarTabuleiro(arquivoJogador1);
        Tabuleiro tabuleiroJogador2 = carregarTabuleiro(arquivoJogador2);

        // Verifica se houve erro ao carregar os tabuleiros
        if (tabuleiroJogador1 == null || tabuleiroJogador2 == null) {
            return;
        }

        boolean jogoEmAndamento = true;
        System.out.println("TABULEIROS CARREGADOS COM SUCESSO");
        Tabuleiro tabuleiroAtaqueJogador1 = new Tabuleiro();
        Tabuleiro tabuleiroAtaqueJogador2 = new Tabuleiro();
        Scanner scanner = new Scanner(System.in);
        Tabuleiro tabuleiroAtual = tabuleiroJogador1;
        Tabuleiro tabuleiroAtaqueAtual = tabuleiroAtaqueJogador1;
        Tabuleiro tabuleiroOponente = tabuleiroJogador2;
        String nomeJogadorAtual = "J1";

        while (jogoEmAndamento) {

            System.out.println(nomeJogadorAtual + "> ");

            String entrada = scanner.nextLine();
            String[] coordenadas = entrada.split(" ");
            if (coordenadas.length != 2) {
                System.out.println("JOGADA INVALIDA");
                continue;
            }
            //Verifica as entradas
            if(!entrada.matches("\\d+ \\d+")){
                System.out.println("JOGADA INVALIDA");
                continue;
            }

            int linha = Integer.parseInt(coordenadas[0]) - 1;
            int coluna = Integer.parseInt(coordenadas[1]) - 1;

            // Verifica se as coordenadas estão dentro dos limites do tabuleiro
            if (linha < 0 || linha >= Tabuleiro.getTamanho() || coluna < 0 || coluna >= Tabuleiro.getTamanho()) {
                System.out.println("JOGADA INVALIDA");
                continue;
            }

            //Verifica as jogadas
            if(!tabuleiroAtaqueJogador1.verificarJogada(linha,coluna)){
                System.out.println("JOGADA INVALIDA");
                continue;
            }

            if(!tabuleiroAtaqueJogador2.verificarJogada(linha,coluna)){
                System.out.println("JOGADA INVALIDA");
                continue;
            }

            if(!tabuleiroAtual.verificarJogada(linha,coluna)){
                System.out.println("JOGADA INVALIDA");
                continue;
            }

            if(!tabuleiroAtaqueAtual.verificarJogada(linha,coluna)){
                System.out.println("JOGADA INVALIDA");
                continue;
            }

            if(!tabuleiroOponente.verificarJogada(linha,coluna)){
                System.out.println("JOGADA INVALIDA");
                continue;
            }

            String resultadoAtaque = tabuleiroOponente.atacar(linha, coluna);
            if (resultadoAtaque.equals("TIRO JA EXECUTADO")) {
                System.out.println(resultadoAtaque);
                continue;
            }

            System.out.println(resultadoAtaque);

            if (tabuleiroOponente.fimDeJogo()) {
                System.out.println("FIM DE JOGO");
                System.out.println("VENCEDOR: " + nomeJogadorAtual);
                jogoEmAndamento = false;
            } else {
                // Troca de jogador
                if (tabuleiroAtual == tabuleiroJogador1) {
                    tabuleiroAtual = tabuleiroJogador2;
                    tabuleiroAtaqueAtual = tabuleiroAtaqueJogador2;
                    tabuleiroOponente = tabuleiroJogador1;
                    nomeJogadorAtual = "J2";
                } else {
                    tabuleiroAtual = tabuleiroJogador1;
                    tabuleiroAtaqueAtual = tabuleiroAtaqueJogador1;
                    tabuleiroOponente = tabuleiroJogador2;
                    nomeJogadorAtual = "J1";
                }
            }
        }
    }

    private static Tabuleiro carregarTabuleiro(String arquivo) {
        Tabuleiro tabuleiro = new Tabuleiro();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int numLinha = 1;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(" ");
                if (partes.length != 5) {
                    System.out.println("ERRO LINHA " + numLinha + ": " + arquivo);
                    return null;
                }
                String nomeEmbarcacao = partes[0];
                int linhaIni = Integer.parseInt(partes[1]) - 1;
                int colunaIni = Integer.parseInt(partes[2]) - 1;
                int linhaFim = Integer.parseInt(partes[3]) - 1;
                int colunaFim = Integer.parseInt(partes[4]) - 1;

                EmbarcacaoStrategy embarcacao;
                switch (nomeEmbarcacao) {
                    case "PORTA_AVIOES":
                        embarcacao = new PortaAvioes(linhaIni, colunaIni, linhaFim, colunaFim);
                        break;
                    case "DESTROYER":
                        embarcacao = new Destroyer(linhaIni, colunaIni, linhaFim, colunaFim);
                        break;
                    case "CRUZADOR":
                        embarcacao = new Cruzador(linhaIni, colunaIni, linhaFim, colunaFim);
                        break;
                    case "SUBMARINO":
                        embarcacao = new Submarino(linhaIni, colunaIni, linhaFim, colunaFim);
                        break;
                    case "PATRULHA":
                        embarcacao = new Patrulha(linhaIni, colunaIni, linhaFim, colunaFim);
                        break;
                    default:
                        System.out.println("ERRO: Embarcação inválida no arquivo " + arquivo);
                        return null;
                }

                if (!tabuleiro.posicionarEmbarcacao(embarcacao, linhaIni, colunaIni, linhaFim, colunaFim)) {
                    File f = new File(arquivo);
                    System.out.println("ERRO LINHA " + numLinha + ": " + f.getName());
                    return null;
                }

                numLinha++;
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("ERRO ao ler o arquivo " + arquivo);
            e.printStackTrace();
            return null;
        }
        return tabuleiro;
    }
}
