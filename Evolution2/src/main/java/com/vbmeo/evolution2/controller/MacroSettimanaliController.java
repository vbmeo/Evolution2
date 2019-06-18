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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;







import com.vbmeo.evolution2.HomeController;
import com.vbmeo.evolution2.model.MacroSettimanali;
import com.vbmeo.evolution2.service.MacroSettimanaliService;


@Controller
public class MacroSettimanaliController {
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
		@Autowired
		private MacroSettimanaliService macroSettimanaliService;


		@RequestMapping(value = "/macro", method = RequestMethod.GET,produces = "application/json")
		public List<MacroSettimanali> list() {
			List<MacroSettimanali> lista = macroSettimanaliService.getAll();			 
			return lista;
		}
		
		
		

}
