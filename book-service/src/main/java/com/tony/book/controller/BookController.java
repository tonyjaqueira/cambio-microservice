package com.tony.book.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tony.book.model.Book;
import com.tony.book.proxy.CambioProxy;
import com.tony.book.repository.BookRepository;
import com.tony.book.response.Cambio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Book EndePoint") //para documentação Swagger
@RestController
@RequestMapping("book-service")
public class BookController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CambioProxy proxy;
	
	//comunicação de serviço sutilizando restTemplate
//	@GetMapping(value="/{id}/{currency}")
//	public Book findBook(@PathVariable("id") Long id,
//							@PathVariable("currency") String currency) {
//	
//		Optional<Book> book = repository.findById(id);
//		if(!book.isPresent()) throw new RuntimeException("Book não encontrado");
//		String porta = environment.getProperty("local.server.port");
//		
//		
//		HashMap<String, String> params = new HashMap<>();
//		params.put("amount", book.get().getPrice().toString());
//		params.put("from", "USD"); //passando sempre dolar, mas pode ser passado tbm por parametro
//		params.put("to", currency);
//		
//		ResponseEntity<Cambio> response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", Cambio.class, params); //comunicação entre microservices usando resttemplate
//		book.get().setPrice(response.getBody().getConversuinValue());
//		book.get().setEnvironment(porta);
//		return book.get();
//	}
	
	@Operation(summary = "Busca um book específico por seu id") //descrição do end-point para o Swagger
	@GetMapping(value="/feign/{id}/{currency}")	//comunicação de serviço sutilizando Feign
	public Book findBookByFeing(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
	
		Optional<Book> book = repository.findById(id);
		if(!book.isPresent()) throw new RuntimeException("Book não encontrado");
		String porta = environment.getProperty("local.server.port");
		
		Cambio cambio = proxy.getCambio(book.get().getPrice(), "USD", currency); 
	
		book.get().setPrice(cambio.getConversuinValue());
		book.get().setEnvironment("Book Port: "+porta+" Cambio port: "+cambio.getEnvironment());
		return book.get();
	}
	
}
