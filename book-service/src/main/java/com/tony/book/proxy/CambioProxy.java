package com.tony.book.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tony.book.response.Cambio;

@FeignClient(name="cambio-service", url="localhost:8000") //informações do seviço que iremos consumir
public interface CambioProxy {

	@GetMapping(value = "/cambio-service/{amount}/{from}/{to}")
	public Cambio getCambio(@PathVariable("amount") Double amount, 
			@PathVariable("from") String from, 
			@PathVariable("to") String to);
}
