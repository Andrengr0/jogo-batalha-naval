
public class PortaAvioes implements Embarcacao {
    private static final String NOME = "PORTA_AVIOES";
    private static final int TAMANHO = 5;
    private int[] posicaoInicio;
    private int[] posicaoFim;
    private boolean[] atingido;

    public PortaAvioes(int linhaIni, int colunaIni, int linhaFim, int colunaFim) {
        this.posicaoInicio = new int[]{linhaIni, colunaIni};
        this.posicaoFim = new int[]{linhaFim, colunaFim};
        this.atingido = new boolean[TAMANHO];
    }

    @Override
    public String getNome() {
        return NOME;
    }

    @Override
    public int getTamanho() {
        return TAMANHO;
    }

    @Override
    public boolean foiAtingida(int linha, int coluna) {
        for (int i = 0; i < TAMANHO; i++) {
            if (!atingido[i] && linha >= posicaoInicio[0] && linha <= posicaoFim[0] && coluna >= posicaoInicio[1] && coluna <= posicaoFim[1]) {
                atingido[i] = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean estaAfundada() {
        for (boolean b : atingido) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public boolean posicaoJaAtacada(int linha, int coluna) {
        return linha >= posicaoInicio[0] && linha <= posicaoFim[0] && coluna >= posicaoInicio[1] && coluna <= posicaoFim[1];
    }
}
