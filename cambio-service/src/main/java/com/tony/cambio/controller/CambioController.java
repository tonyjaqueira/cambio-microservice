package com.tony.cambio.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tony.cambio.model.Cambio;
import com.tony.cambio.repository.CambioRepository;

@RestController
@RequestMapping("cambio-service")
public class CambioController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CambioRepository repository;

	@GetMapping(value = "/{amount}/{from}/{to}")
	public Cambio getCambio(@PathVariable("amount") BigDecimal amount, 
			@PathVariable("from") String from, 
			@PathVariable("to") String to) {
		
		Optional<Cambio> cambio = repository.findByFromAndTo(from, to);
		if(!cambio.isPresent()) throw new RuntimeException("Conversão não suportada");
		String porta = environment.getProperty("local.server.port");
		BigDecimal conversionFactor = cambio.get().getconversionFactor();
		BigDecimal convertedValue = conversionFactor.multiply(amount); //multiplica o conversionFactor x amount
		cambio.get().setConversuinValue(convertedValue.setScale(2, RoundingMode.CEILING)); //arredonda o valor para duas casas decimais
		cambio.get().setEnvironment(porta);
		return cambio.get();
	}
	
}
