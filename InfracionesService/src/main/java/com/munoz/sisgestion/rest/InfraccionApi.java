package com.munoz.sisgestion.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




import com.munoz.sisgestion.entity.Infraccion;
import com.munoz.sisgestion.service.InfraccionService;



@RestController
@RequestMapping("/api/Infraccion")
public class InfraccionApi {
	@Autowired
	private InfraccionService service;
	
	@GetMapping()
	public ResponseEntity<List<Infraccion>> getAll(){
		List<Infraccion> Infracciones =  service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(Infracciones);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Infraccion> getById(@PathVariable("id") int id){
		Infraccion Infracciones = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(Infracciones);
	}
	
	@PostMapping
	public ResponseEntity<Infraccion> create(@RequestBody Infraccion Infraccion){
		Infraccion InfraccionDb=service.create(Infraccion);
		return ResponseEntity.status(HttpStatus.CREATED).body(InfraccionDb);
	}
	
	@PutMapping
	public ResponseEntity<Infraccion> update(@RequestBody Infraccion Infraccion){
		Infraccion  InfraccionDb=service.update(Infraccion);
		return ResponseEntity.status(HttpStatus.OK).body(InfraccionDb);
	}
	@DeleteMapping(value="{id}")
	public int delete(@PathVariable("id") int id) {
		return service.delete(id);
	}
	
	
}
