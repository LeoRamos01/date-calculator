package lramos.datecalculator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lramos.datecalculator.dto.ProporcoesDTO;

@Service
public class MontarMensagem {
	
	@Autowired
	IdadeCalculator idadeCalculator;

	public String montarProporcao(List<ProporcoesDTO> list) {
		
		StringBuilder sb = new StringBuilder();

		list.forEach(el -> {
			
			String idadeFuturaDoSegundo = idadeCalculator.entre(el.getDataNascimentoSegundo(), el.getDataFutura());
			String idadeFuturaDoPrimeiro = idadeCalculator.entre(el.getDataNascimentoPrimeiro(), el.getDataFutura());

			sb.append(
					String
					.format(
							"Na data %s %s(%s) tem %s da idade de %s(%s).\n", 
							el.getDataFutura(),
							el.getNomeSegundo(),
							idadeFuturaDoSegundo,
							el.getFracao(),
							el.getNomePrimeiro()
							, idadeFuturaDoPrimeiro)
					);

		});

		return sb.toString();
	}

}
