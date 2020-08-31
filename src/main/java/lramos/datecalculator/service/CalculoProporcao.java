package lramos.datecalculator.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class CalculoProporcao {

	public Map<String, LocalDate> calcularPadrao(LocalDate dataAnterior, LocalDate dataPosterior) {

		Map<String, LocalDate> map = new LinkedHashMap<>();
		
		map.put("99%", calcularParaFracao(dataAnterior, dataPosterior, 0.99));
		map.put("90%", calcularParaFracao(dataAnterior, dataPosterior, 0.9));
		map.put("75%", calcularParaFracao(dataAnterior, dataPosterior, 3.0 / 4));
		map.put("metade", calcularParaFracao(dataAnterior, dataPosterior, 1.0 / 2));
		map.put("um sobre e", calcularParaFracao(dataAnterior, dataPosterior, 1.0 / Math.E));
		map.put("um terço", calcularParaFracao(dataAnterior, dataPosterior, 1.0 / 3));
		map.put("um sobre pi", calcularParaFracao(dataAnterior, dataPosterior, 1.0 / Math.PI));
		map.put("1/4", calcularParaFracao(dataAnterior, dataPosterior, 1.0 / 4));
		map.put("1/5", calcularParaFracao(dataAnterior, dataPosterior, 1.0 / 5));
		map.put("1/6", calcularParaFracao(dataAnterior, dataPosterior, 1.0 / 6));
		map.put("1/7", calcularParaFracao(dataAnterior, dataPosterior, 1.0 / 7));
		map.put("1/8", calcularParaFracao(dataAnterior, dataPosterior, 1.0 / 8));
		map.put("1/9", calcularParaFracao(dataAnterior, dataPosterior, 1.0 / 9));
		map.put("10%", calcularParaFracao(dataAnterior, dataPosterior, 0.1));
		map.put("5%", calcularParaFracao(dataAnterior, dataPosterior, 0.05));
		map.put("2%", calcularParaFracao(dataAnterior, dataPosterior, 0.02));
		map.put("1%", calcularParaFracao(dataAnterior, dataPosterior, 0.01));
		
		return map;
	}
	
	public Map<String, LocalDate> calcular(LocalDate dataAnterior, LocalDate dataPosterior, List<Double> fracoes) {

		Map<String, LocalDate> map = new LinkedHashMap<>();
		
		fracoes.forEach(f -> {
			
			map.put(f.toString(), calcularParaFracao(dataAnterior, dataPosterior, f));
			
		});
		
		return map;
	}
	
	protected LocalDate calcularParaFracao(LocalDate dataAnterior, LocalDate dataPosterior, Double fracao) {
		
		Long distancia = Duration.between(LocalDateTime.of(dataAnterior, LocalTime.MIN), LocalDateTime.of(dataPosterior, LocalTime.MIN)).toDays();
		
		Double idadeFuturaDoMaisVelhoEmDias = distancia.doubleValue() / (1 - fracao);
		
		return dataAnterior.plusDays(idadeFuturaDoMaisVelhoEmDias.longValue());
	}
	
}
