package lramos.datecalculator.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lramos.datecalculator.dto.ProporcoesDTO;

@Service
public class CalculoProporcao {
	
	@Autowired
	IdadeCalculator idadeCalculator;
	
	public List<ProporcoesDTO> calcularPadrao(String nomePrimeiro, String nomeSegundo, LocalDate dataAnterior, LocalDate dataPosterior) {

		List<ProporcoesDTO> list = new LinkedList<>();
		
		list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, 0.99          , "99%"         ));
		list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, 0.9           , "90%"         ));
		list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, 3.0 / 4       , "75%"         ));
		list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, 1.0 / 2       , "metade"      ));
		list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, 1.0 / Math.E  , "um sobre e"  ));
		list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, 1.0 / 3       , "um terço"    ));
		list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, 1.0 / Math.PI , "um sobre pi" ));
		list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, 1.0 / 4       , "1/4"         ));
		list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, 1.0 / 5       , "1/5"         ));
		list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, 1.0 / 6       , "1/6"         ));
		list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, 1.0 / 7       , "1/7"         ));
		list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, 1.0 / 8       , "1/8"         ));
		list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, 1.0 / 9       , "1/9"         ));
		list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, 0.1           , "10%"         ));
		list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, 0.05          , "5%"          ));
		list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, 0.02          , "2%"          ));
		list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, 0.01          , "1%"          ));
		
		return list;
	}
	
	public List<ProporcoesDTO> calcular(String nomePrimeiro, String nomeSegundo, LocalDate dataAnterior, LocalDate dataPosterior, List<Double> fracoes) {

		List<ProporcoesDTO> list = new LinkedList<>();
		
		fracoes.forEach(f -> {
			
			list.add(calcularParaFracao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, f, f.toString()));
			
		});
		
		return list;
	}
	
	protected ProporcoesDTO calcularParaFracao(String nomePrimeiro, String nomeSegundo, LocalDate dataAnterior, LocalDate dataPosterior, Double fracao, String nomeBonito) {
		
		Long distancia = Duration.between(LocalDateTime.of(dataAnterior, LocalTime.MIN), LocalDateTime.of(dataPosterior, LocalTime.MIN)).toDays();
		
		Double idadeFuturaDoMaisVelhoEmDias = distancia.doubleValue() / (1 - fracao);
		
		dataAnterior.plusDays(idadeFuturaDoMaisVelhoEmDias.longValue());
		
		return new ProporcoesDTO(nomeBonito, nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, dataAnterior.plusDays(idadeFuturaDoMaisVelhoEmDias.longValue()));
	}

	public String calcularHoje(String nomePrimeiro, String nomeSegundo, LocalDate dataAnterior,
			LocalDate dataPosterior) {
		
		LocalDate hoje = LocalDate.now();
		
		Long idadeEmDiasDoMaisVelho = Duration.between(LocalDateTime.of(dataAnterior, LocalTime.MIN), LocalDateTime.of(hoje, LocalTime.MIN)).toDays();
		
		Long idadeEmDiasDoMaisNovo = Duration.between(LocalDateTime.of(dataPosterior, LocalTime.MIN), LocalDateTime.of(hoje, LocalTime.MIN)).toDays();
		
		Double porcentagem = idadeEmDiasDoMaisNovo.doubleValue() / idadeEmDiasDoMaisVelho.doubleValue();
		
		String idadeHojeDoSegundo = idadeCalculator.entre(dataPosterior, hoje);
		String idadeHojeDoPrimeiro = idadeCalculator.entre(dataAnterior, hoje);
		
		return String
				.format(
						"Hoje, %s, %s(%s) tem %s da idade de %s(%s).",
						hoje,
						nomeSegundo,
						idadeHojeDoSegundo,
						porcentagem,
						nomePrimeiro,
						idadeHojeDoPrimeiro
						);
	}
	
}
