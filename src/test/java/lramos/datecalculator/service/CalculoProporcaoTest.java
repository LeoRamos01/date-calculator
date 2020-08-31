package lramos.datecalculator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CalculoProporcaoTest {
	
	CalculoProporcao calculoProporcao = new CalculoProporcao();
	
	@Test
	public void testCalcular() {
		Map<Integer, LocalDate> actual = calculoProporcao.calcular(LocalDate.of(2020, 8, 2), LocalDate.of(2020, 8, 7), 3);
		
		Map<Integer, LocalDate> expected = Map.of(3, LocalDate.of(2020, 8, 9), 2, LocalDate.of(2020, 8, 12));
		
		assertEquals(expected, actual);
	}
	
	@ParameterizedTest
	@MethodSource("casos")
	public void testCalcularParaUmDividendo(LocalDate dataAnterior, LocalDate dataPosterior, LocalDate expected, Integer divisor) {
		LocalDate actual = calculoProporcao.calcularParaUmDivisor(dataAnterior, dataPosterior, divisor);
		
		assertEquals(expected, actual);
	}

	private static Stream<Arguments> casos() {
		return Stream.of(
					Arguments.of(LocalDate.of(2020, 8, 2), LocalDate.of(2020, 8, 7), LocalDate.of(2020, 8, 12), 2),
					Arguments.of(LocalDate.of(2020, 8, 2), LocalDate.of(2020, 8, 7), LocalDate.of(2020, 8, 9), 3)
				);
	}

}
