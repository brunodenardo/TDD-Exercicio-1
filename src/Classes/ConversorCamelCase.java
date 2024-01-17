package Classes;

import java.util.ArrayList;
import java.util.List;

import Erros.CaracterEspecialException;
import Erros.NaoPodeNumeroComecoCamelCase;

public class ConversorCamelCase {

	public List<String> converterCamelCase(String original){
		if(this.confereNumeroComeco(original)) {
			throw new NaoPodeNumeroComecoCamelCase();
		}
		List<String> listaPalavras = new ArrayList<String>();
		List<String> listaResultado = this.separarPalavras("", listaPalavras, original);
		return listaResultado;
	}
	
	
	
	private List<String> separarPalavras(String palavra, List<String> listaPalavras, String original){
		for (int posicaoLetra = 0; posicaoLetra < original.length(); posicaoLetra++) {
			this.confereCaracterEspecial(original.charAt(posicaoLetra));
			if(this.deveSeparar(original, palavra, posicaoLetra)) {
				listaPalavras.add(this.transformaLowerCaseOuNao(palavra));
				palavra = "" + original.charAt(posicaoLetra);
			}
			else {
				palavra += original.charAt(posicaoLetra);
				listaPalavras = this.fimDaOriginal(palavra, original, posicaoLetra, listaPalavras);
			}
		}
		return listaPalavras;
	}
	
	private List<String> fimDaOriginal(String palavra, String original, int posicao, List<String> listaPalavra){
		Boolean fimDaOriginal = posicao == original.length() - 1;
		if(fimDaOriginal) {
			palavra = this.transformaLowerCaseOuNao(palavra);
			listaPalavra.add(palavra);
		}
		return listaPalavra;
	}
	
	private String transformaLowerCaseOuNao(String palavra) {
		Boolean primeiroCaracterMaiusculo = Character.isUpperCase(palavra.charAt(0));
		Boolean ultimoCaracterMaiusculo = Character.isUpperCase(palavra.charAt(palavra.length()-1));
		Boolean sigla = primeiroCaracterMaiusculo && ultimoCaracterMaiusculo;
		if(sigla) {
			return palavra;
		}
		return palavra.toLowerCase();
	}
	
	private Boolean confereFimPalavra(String original, int posicaoAtual) {
		if(posicaoAtual != 0) {
			Boolean caracterAtualMaiusculo = Character.isUpperCase(original.charAt(posicaoAtual));
			Boolean caracterAtualNumero = Character.isDigit(original.charAt(posicaoAtual));
			Boolean caracterAnteriorMinusculo = Character.isLowerCase(original.charAt(posicaoAtual-1));
			if((caracterAtualMaiusculo || caracterAtualNumero) && caracterAnteriorMinusculo){
				return true;
			}
		}
		return false;
	}
	
	
	private Boolean confereFimSigla(String original, String palavra, int posicao) {
		if(!this.confereUltimaPosicao(original, posicao)) {
			if(Character.isLowerCase(original.charAt(posicao + 1)) && 
			palavra.equals(palavra.toUpperCase()) &&
			palavra.length() > 1) {
				return true;
			}
		}
		return false;
	}
	
	private Boolean confereUltimaPosicao(String original, int posicao) {
		if (posicao == original.length() - 1) {
			return true;
		}
		return false;
	}
	
	private Boolean confereNumeroComeco(String original) {
		if(Character.isDigit(original.charAt(0))) {
			return true;
		}
		return false;
	}
	
	private void confereCaracterEspecial(Character caracter) {
		if (!Character.isLetterOrDigit(caracter)) {
			throw new CaracterEspecialException();
		}
	}
	
	private Boolean deveSeparar(String original, String palavra, int posicao) {
		Boolean fimDeSigla = this.confereFimSigla(original, palavra, posicao);
		Boolean fimDePalavra = this.confereFimPalavra(original, posicao);
		if(fimDePalavra || fimDeSigla) {
			return true;
		}
		return false;
	}

}



