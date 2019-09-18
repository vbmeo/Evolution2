package com.vbmeo.evolution2.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbmeo.evolution2.model.Parametri;
import com.vbmeo.evolution2.service.ParametriService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ParametriController {

private static final Logger logger = LoggerFactory.getLogger(MisureController.class);
	
	@Autowired
	private ParametriService parametriService;
	
	
	//http://localhost:8080/evolution2/misure
	//@CrossOrigin
	@GetMapping(value = "/parametri")
	public List<Parametri> list() {
		logger.debug("richiesta lista parametri ");
		List<Parametri> lista = parametriService.getAll();		
		return lista;
	}
	
}
