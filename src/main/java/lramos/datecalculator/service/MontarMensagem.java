package lramos.datecalculator.service;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class MontarMensagem {

	public String montarProporcao(String nomePrimeiro, String nomeSegundo, Map<String, LocalDate> map) {
		
		StringBuilder sb = new StringBuilder();
		
		map.forEach((k, v) -> {
			
			sb.append(String.format("Na data %s %s tem %s da idade de %s.\n", v, nomeSegundo, k, nomePrimeiro));
			
		});
		
		return sb.toString();
	}
	
}
