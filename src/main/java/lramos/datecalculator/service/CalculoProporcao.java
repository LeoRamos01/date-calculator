package lramos.datecalculator.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class CalculoProporcao {

	public Map<Integer, LocalDate> calcular(LocalDate dataAnterior, LocalDate dataPosterior, Integer divisorMaximo) {

		Map<Integer, LocalDate> map = new LinkedHashMap<>();
		
		for(int divisor = divisorMaximo; divisor > 1; divisor--) {
			map.put(divisor, calcularParaUmDivisor(dataAnterior, dataPosterior, divisor));
		}
		
		return map;
	}
	
	protected LocalDate calcularParaUmDivisor(LocalDate dataAnterior, LocalDate dataPosterior, Integer divisor) {
		
		Integer divisorAjustado = divisor - 1;

		Double proporcao = 1.0 / Double.valueOf(divisorAjustado.toString());
		
		Long quantidadeDiasDoPrimeiro = Duration.between(LocalDateTime.of(dataAnterior, LocalTime.MIN), LocalDateTime.of(dataPosterior, LocalTime.MIN)).toDays();
		
		Double quantidadeDiasParaProporcaoOcorrerNoFuturo = quantidadeDiasDoPrimeiro * proporcao;
		
		LocalDate dataOcorrencia = dataPosterior.plusDays(quantidadeDiasParaProporcaoOcorrerNoFuturo.longValue());
		
		return dataOcorrencia;
	}
	
}
