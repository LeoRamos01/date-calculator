package lramos.datecalculator.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

@Service
public class IdadeCalculator {

	public String entre(LocalDate dataNascimento, LocalDate dataFutura) {
		
		Period period = Period.between(dataNascimento, dataFutura);
		
		String ano = period.getYears()  == 1 ? " ano " : " anos ";
		String mes = period.getMonths() == 1 ? " mês " : " meses ";
		String dia = period.getDays()   == 1 ? " dia" : " dias";
		
		return period.getYears() + ano + period.getMonths() + mes + period.getDays() + dia;
	}
	
}
