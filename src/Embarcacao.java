public interface Embarcacao {

    String getNome();
    int getTamanho();
    boolean foiAtingida(int linha, int coluna);
    boolean estaAfundada();
}
