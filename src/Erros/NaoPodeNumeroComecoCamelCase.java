package Erros;


public class NaoPodeNumeroComecoCamelCase extends RuntimeException {

	public NaoPodeNumeroComecoCamelCase() {
		super("não deve começar com números");
	}
}
