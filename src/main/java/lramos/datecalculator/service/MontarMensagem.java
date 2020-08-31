package lramos.datecalculator.service;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class MontarMensagem {

	public String montarProporcao(String nomePrimeiro, String nomeSegundo, Map<Integer, LocalDate> map) {
		
		StringBuilder sb = new StringBuilder();
		
		Integer max = map.keySet().stream().mapToInt(Integer::intValue).max().getAsInt();
		
		for(int i = 2; i <=max; i++) {
			sb.append(String.format("Na data %s %s tem 1/%d da idade de %s.\n", map.get(i), nomeSegundo, i, nomePrimeiro));
		}
		
		return sb.toString();
	}
	
}
