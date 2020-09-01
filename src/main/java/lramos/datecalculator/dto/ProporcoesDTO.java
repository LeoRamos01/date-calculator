package lramos.datecalculator.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProporcoesDTO {
	
	private String fracao;
	
	private String nomePrimeiro;
	
	private String nomeSegundo;
	
	private LocalDate dataNascimentoPrimeiro;
	
	private LocalDate dataNascimentoSegundo;
	
	private LocalDate dataFutura;
	
}
