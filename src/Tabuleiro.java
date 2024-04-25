public class Tabuleiro {
    private static final int TAMANHO = 15;
    private static final char AGUA = '~';
    private static final char EMBARCACAO = '#';
    private char[][] matriz;

    public Tabuleiro() {
        this.matriz = new char[TAMANHO][TAMANHO];
        inicializarTabuleiro();
    }

    public static int getTamanho() {
        return TAMANHO;
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                matriz[i][j] = AGUA;
            }
        }
    }

    public boolean posicionarEmbarcacao(Embarcacao embarcacao, int linhaIni, int colunaIni, int linhaFim, int colunaFim) {
        if (linhaIni < 0 || colunaIni < 0 || linhaFim >= TAMANHO || colunaFim >= TAMANHO) {
            return false; // Coordenadas fora do tabuleiro
        }

        int tamanhoEmbarcacao = embarcacao.getTamanho();
        int linhaDif = linhaFim - linhaIni;
        int colunaDif = colunaFim - colunaIni;

        if ((linhaDif != 0 && colunaDif != 0) || (Math.abs(linhaDif) != tamanhoEmbarcacao - 1 && Math.abs(colunaDif) != tamanhoEmbarcacao - 1)) {
            return false; // Embarcação não está na horizontal nem na vertical, ou tamanho inválido
        }

        for (int i = linhaIni; i <= linhaFim; i++) {
            for (int j = colunaIni; j <= colunaFim; j++) {
                if (matriz[i][j] == EMBARCACAO) {
                    return false; // Já há uma embarcação na posição
                }
            }
        }

        for (int i = linhaIni; i <= linhaFim; i++) {
            for (int j = colunaIni; j <= colunaFim; j++) {
                matriz[i][j] = EMBARCACAO;
            }
        }

        return true;
    }

    public String atacar(int linha, int coluna) {
        if (linha < 0 || coluna < 0 || linha >= TAMANHO || coluna >= TAMANHO) {
            return "JOGADA INVALIDA";
        }

        if (matriz[linha][coluna] == EMBARCACAO) {
            matriz[linha][coluna] = AGUA;
            return "ACERTOU";
        } else {
            return "AGUA";
        }
    }
}
