package lramos.datecalculator.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lramos.datecalculator.dto.ProporcoesDTO;
import lramos.datecalculator.exception.InvalidProporcaoParameterException;
import lramos.datecalculator.service.CalculoProporcao;
import lramos.datecalculator.service.MontarMensagem;

@RestController
public class CalculatorRest {
	
	@Autowired
	CalculoProporcao calculoProporcao;
	
	@Autowired
	MontarMensagem montarMensagem;

	@PostMapping("calcular/padrao")
	public String calcularProporcoesPadroes(String nomePrimeiro,
			String nomeSegundo, 
			@RequestParam("dataAnterior") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataAnterior,
			@RequestParam("dataPosterior") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataPosterior) {
		
		validarDatas(dataAnterior, dataPosterior);
		
		List<ProporcoesDTO> list = calculoProporcao.calcularPadrao(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior);
		
		return montarMensagem.montarProporcao(list);
		
	}
	
	@PostMapping("calcular/cutomizado")
	public String calcularProporcoes(String nomePrimeiro,
			String nomeSegundo, 
			@RequestParam("dataAnterior") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataAnterior,
			@RequestParam("dataPosterior") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataPosterior,
			Double[] fracoes) {
		
		validarDatas(dataAnterior, dataPosterior);
		validarFracoes(fracoes);
		
		List<ProporcoesDTO> list = calculoProporcao.calcular(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior, List.of(fracoes));
		
		return montarMensagem.montarProporcao(list);
			
	}
	
	@PostMapping("calcular/porcentagem/hoje")
	public String calcularProporcoesHoje(String nomePrimeiro,
			String nomeSegundo, 
			@RequestParam("dataAnterior") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataAnterior,
			@RequestParam("dataPosterior") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataPosterior) {
		
		validarDatas(dataAnterior, dataPosterior);
		
		return calculoProporcao.calcularHoje(nomePrimeiro, nomeSegundo, dataAnterior, dataPosterior);
			
	}
	
	private void validarFracoes(Double... fracoes) {
		
		for (Double f : fracoes) {
			if(f <= 0 || f >= 1) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "As frações devem ser entre 0 - 1.",
						new InvalidProporcaoParameterException("As frações devem ser entre 0 - 1."));
			}
		}
		
	}

	private void validarDatas(LocalDate dataAnterior, LocalDate dataPosterior) {

		if(dataAnterior.isAfter(dataPosterior)) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "As datas estão invertidas.",
					new InvalidProporcaoParameterException("As datas estão invertidas."));

		} else if (dataAnterior.isEqual(dataPosterior)) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "As datas estão iguais.",
					new InvalidProporcaoParameterException("As datas estão iguais."));
			
		}

	}
	
}
