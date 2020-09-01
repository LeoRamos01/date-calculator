package lramos.datecalculator.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdadeDTO {

	private String nome;
	
	private LocalDate dataNascimento;
	
}
