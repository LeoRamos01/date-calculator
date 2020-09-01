package lramos.datecalculator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lramos.datecalculator.dto.ProporcoesDTO;

public class CalculoProporcaoTest {
	
	CalculoProporcao calculoProporcao = new CalculoProporcao();
	
	@ParameterizedTest
	@MethodSource("casos")
	public void testCalcularParaUmDividendo(String nome1, String nome2, LocalDate dataAnterior, LocalDate dataPosterior, LocalDate expected, Double fracao) {
		ProporcoesDTO actual = calculoProporcao.calcularParaFracao(nome1, nome2, dataAnterior, dataPosterior, fracao, fracao.toString());
		
		assertEquals(expected, actual.getDataFutura());
	}

	private static Stream<Arguments> casos() {
		return Stream.of(
					Arguments.of("", "", LocalDate.of(2020, 8, 2), LocalDate.of(2020, 8, 7), LocalDate.of(2020, 8, 12), 1.0 /2),
					Arguments.of("", "", LocalDate.of(2020, 8, 2), LocalDate.of(2020, 8, 7), LocalDate.of(2020, 8, 9), 1.0 /3)
				);
	}

}
