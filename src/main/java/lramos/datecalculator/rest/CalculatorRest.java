package lramos.datecalculator.rest;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lramos.datecalculator.service.CalculoProporcao;
import lramos.datecalculator.service.MontarMensagem;

@RestController
public class CalculatorRest {
	
	@Autowired
	CalculoProporcao calculoProporcao;
	
	@Autowired
	MontarMensagem montarMensagem;

	@PostMapping
	public String calcularProporcoes(String nomePrimeiro, String nomeSegundo, LocalDate dataAnterior, LocalDate dataPosterior, Integer divisorMaximo) {
		
		Map<Integer, LocalDate> map = calculoProporcao.calcular(dataAnterior, dataPosterior, divisorMaximo);
		
		return montarMensagem.montarProporcao(nomePrimeiro, nomeSegundo, map);
		
	}
	
}
