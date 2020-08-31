package lramos.datecalculator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CalculoProporcaoTest {
	
	CalculoProporcao calculoProporcao = new CalculoProporcao();
	
	@ParameterizedTest
	@MethodSource("casos")
	public void testCalcularParaUmDividendo(LocalDate dataAnterior, LocalDate dataPosterior, LocalDate expected, Double fracao) {
		LocalDate actual = calculoProporcao.calcularParaFracao(dataAnterior, dataPosterior, fracao);
		
		assertEquals(expected, actual);
	}

	private static Stream<Arguments> casos() {
		return Stream.of(
					Arguments.of(LocalDate.of(2020, 8, 2), LocalDate.of(2020, 8, 7), LocalDate.of(2020, 8, 12), 1.0 /2),
					Arguments.of(LocalDate.of(2020, 8, 2), LocalDate.of(2020, 8, 7), LocalDate.of(2020, 8, 9), 1.0 /3)
				);
	}

}
