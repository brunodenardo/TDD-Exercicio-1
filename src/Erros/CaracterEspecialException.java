package Erros;

public class CaracterEspecialException extends RuntimeException {

	public CaracterEspecialException() {
		super("Há caracteres especiais na String");
	}
}
