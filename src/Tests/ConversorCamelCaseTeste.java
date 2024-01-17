package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Classes.ConversorCamelCase;
import Erros.CaracterEspecialException;
import Erros.NaoPodeNumeroComecoCamelCase;

public class ConversorCamelCaseTeste {

	ConversorCamelCase conversor = new ConversorCamelCase();
	
	//Caso nome -> nome
	@Test
	public void ConverterUmaPalavraMinuscula() {
		List<String> respostas = new ArrayList<String>();
		respostas.add("nome");
		assertEquals(respostas, this.conversor.converterCamelCase("nome"));
	}
	
	//Nome -> nome 
	@Test
	public void ConverterUmaPalavraMaiuscula() {
		List<String> respostas = new ArrayList<String>();
		respostas.add("nome");
		assertEquals(respostas, this.conversor.converterCamelCase("Nome"));
	}

	
	// nomeComposto -> “nome”, “composto”
	@Test
	public void ConverterDuasPalavras() {
		List<String> respostas = new ArrayList<String>();
		respostas.add("nome");
		respostas.add("composto");
		assertEquals(respostas, this.conversor.converterCamelCase("nomeComposto"));
	}
	
	// NomeComposto -> “nome”, “composto”
	@Test
	public void ConverterDuasPalavrasMaiuscula() {
		List<String> respostas = new ArrayList<String>();
		respostas.add("nome");
		respostas.add("composto");
		assertEquals(respostas, this.conversor.converterCamelCase("NomeComposto"));
	}
	
	// CPF -> “CPF”
	@Test
	public void ConverterSigla() {
		List<String> respostas = new ArrayList<String>();
		respostas.add("CPF");
		assertEquals(respostas, this.conversor.converterCamelCase("CPF"));
	}
	
	// numeroCPF -> “numero”, “CPF”
	@Test
	public void ConverterSiglaUltimaPalavra() {
		List<String> respostas = new ArrayList<String>();
		respostas.add("numero");
		respostas.add("CPF");
		assertEquals(respostas, this.conversor.converterCamelCase("numeroCPF"));
	}
	
	// numeroCPFContribuinte -> “numero”, “CPF”, “contribuinte”
	@Test
	public void ConverterSiglaNoMeio() {
		List<String> respostas = new ArrayList<String>();
		respostas.add("numero");
		respostas.add("CPF");
		respostas.add("contribuinte");
		assertEquals(respostas, this.conversor.converterCamelCase("numeroCPFContribuinte"));
	}
	
	// recupera10Primeiros -> “recupera”, “10”, “primeiros”
	@Test
	public void ConverterNumeroNoMeio() {
		List<String> respostas = new ArrayList<String>();
		respostas.add("recupera");
		respostas.add("10");
		respostas.add("primeiros");
		assertEquals(respostas, this.conversor.converterCamelCase("recupera10Primeiros"));
	}
	
	// 10Primeiros -> Inválido → não deve começar com números
	@Test
	public void ConverterNumeroNoComeco() {
		try {
			this.conversor.converterCamelCase("10Primeiros");
			fail("Permite números no começo");
		}
		catch (NaoPodeNumeroComecoCamelCase e) {}
	}
	
	// nome#Composto -> Inválido → caracteres especiais não são permitidos, somente letras e números
		@Test
		public void ConverterCaracterEspecial() {
			try {
				this.conversor.converterCamelCase("nome#Composto");
				fail("Há caracteres especiais na string");
			}
			catch (CaracterEspecialException e) {}
		}
	
	
}
