package lramos.datecalculator.rest;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lramos.datecalculator.exception.InvalidProporcaoParameterException;
import lramos.datecalculator.service.CalculoProporcao;
import lramos.datecalculator.service.MontarMensagem;

@RestController
public class CalculatorRest {
	
	@Autowired
	CalculoProporcao calculoProporcao;
	
	@Autowired
	MontarMensagem montarMensagem;

	@PostMapping
	public String calcularProporcoesPadroes(String nomePrimeiro,
			String nomeSegundo, 
			@RequestParam("dataAnterior") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataAnterior,
			@RequestParam("dataPosterior") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataPosterior) {
		
		validar(dataAnterior, dataPosterior);
		
		Map<String, LocalDate> map = calculoProporcao.calcularPadrao(dataAnterior, dataPosterior);
		
		return montarMensagem.montarProporcao(nomePrimeiro, nomeSegundo, map);
		
	}
	
	private void validar(LocalDate dataAnterior, LocalDate dataPosterior) {

		if(dataAnterior.isAfter(dataPosterior)) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "As datas estão invertidas.",
					new InvalidProporcaoParameterException("As datas estão invertidas."));

		} else if (dataAnterior.isEqual(dataPosterior)) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "As datas estão iguais.",
					new InvalidProporcaoParameterException("As datas estão iguais."));
			
		}

	}
	
}
