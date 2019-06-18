package com.vbmeo.evolution2.controller;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;







import org.springframework.web.bind.annotation.RestController;

import com.vbmeo.evolution2.HomeController;
import com.vbmeo.evolution2.model.MacroSettimanali;
import com.vbmeo.evolution2.service.MacroSettimanaliService;

//per chiamate rest json
@RestController
public class MacroSettimanaliController {
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
		@Autowired
		private MacroSettimanaliService macroSettimanaliService;

		
		@GetMapping(value = "/macro")
		public List<MacroSettimanali> list() {
			List<MacroSettimanali> lista = macroSettimanaliService.getAll();			 
			return lista;
		}
		
		@GetMapping(value = "/macro/{id}")
		public MacroSettimanali getById(@PathVariable("id") Integer id) {
			MacroSettimanali macro = macroSettimanaliService.getById(id);			 
			return macro;
		}

}
