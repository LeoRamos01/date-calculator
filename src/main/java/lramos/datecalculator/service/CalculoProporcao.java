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

	public Map<Integer, LocalDate> calcular(LocalDate dataAnterior, LocalDate dataPosterior, Integer dividendoMaximo) {

		validar(dataAnterior, dataPosterior, dividendoMaximo);
		
		Map<Integer, LocalDate> map = new LinkedHashMap<>();
		
		for(int dividendo = dividendoMaximo; dividendo > 1; dividendo--) {
			map.put(dividendo, calcularParaUmDividendo(dataAnterior, dataPosterior, dividendo));
		}
		
		return map;
	}
	
	protected LocalDate calcularParaUmDividendo(LocalDate dataAnterior, LocalDate dataPosterior, Integer dividendo) {
		
		Integer divisor = dividendo - 1;

		Double proporcao = 1.0 / Double.valueOf(divisor.toString());
		
		Long quantidadeDiasDoPrimeiro = Duration.between(LocalDateTime.of(dataAnterior, LocalTime.MIN), LocalDateTime.of(dataPosterior, LocalTime.MIN)).toDays();
		
		Double quantidadeDiasParaProporcaoOcorrerNoFuturo = quantidadeDiasDoPrimeiro * proporcao;
		
		LocalDate dataOcorrencia = dataPosterior.plusDays(quantidadeDiasParaProporcaoOcorrerNoFuturo.longValue());
		
		return dataOcorrencia;
	}
	
	private void validar(LocalDate dataAnterior, LocalDate dataPosterior, Integer dividendoMaximo) {
		if(dataAnterior.isAfter(dataPosterior)) {
			throw new RuntimeException("As datas estão invertidas.");
		} else if (dataAnterior.isEqual(dataPosterior)) {
			throw new RuntimeException("As datas estão iguais.");
		} else if(dividendoMaximo < 2) {
			throw new RuntimeException("O dividendo não pode ser menor que 2.");
		}
	}
	
}
