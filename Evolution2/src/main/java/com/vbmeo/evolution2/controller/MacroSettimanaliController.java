package com.vbmeo.evolution2.controller;
import java.text.DateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.annotations.Insert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.vbmeo.evolution2.HomeController;
import com.vbmeo.evolution2.manager.ManagerMacroSettimanali;
import com.vbmeo.evolution2.model.MacroSettimanali;
import com.vbmeo.evolution2.service.MacroSettimanaliService;
import com.vbmeo.evolution2.util.Costanti;
import com.vbmeo.evolution2.util.FrasiFatte;
import com.vbmeo.evolution2.util.MyUtil;


//per chiamate rest json
@RestController
public class MacroSettimanaliController {
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	



		@Autowired
		private MacroSettimanaliService macroSettimanaliService;
		
		
		//http://localhost:8080/evolution2/macro
		@GetMapping(value = "/macro")
		public List<MacroSettimanali> list() {
			logger.debug("richiesta lista macro settimanali");
			List<MacroSettimanali> lista = macroSettimanaliService.getAll();			 
			return lista;
		}
		
		
		//con string come parametro intercetto una possibile data
		//http://localhost:8080/evolution2/macro/30 o 2018-10-22
		@GetMapping(value = "/macro/{idOrDate}")
		public ResponseEntity getById(@PathVariable("idOrDate") String idOrDate) {
			if (MyUtil.isNumeric(idOrDate)){
				//richiesta per id
				Integer id=MyUtil.convertStringToInteger(idOrDate);
				MacroSettimanali macro = macroSettimanaliService.getById(id);
				logger.debug(FrasiFatte.HttpRequest.RICHIESTA_ANDATA_A_BUON_FINE + idOrDate);
				return new ResponseEntity(macro, HttpStatus.OK);
			}else if (MyUtil.eUnaData(idOrDate)){
				java.sql.Date sqlDate = MyUtil.convertDateinSqlDate(idOrDate);
				MacroSettimanali macro = macroSettimanaliService.getByDate(sqlDate);
				logger.debug(FrasiFatte.HttpRequest.RICHIESTA_ANDATA_A_BUON_FINE + idOrDate);
				return new ResponseEntity(macro, HttpStatus.OK);
			}else{
				logger.warn(FrasiFatte.HttpRequest.FORMATO_NON_VALIDO + idOrDate);
				return new ResponseEntity(FrasiFatte.HttpRequest.FORMATO_NON_VALIDO + idOrDate, HttpStatus.BAD_REQUEST);
			}
		}

		
		
		
		//con parametri separati
		@PostMapping(value = "/macro")
		public ResponseEntity insert(@RequestParam(name = "dataYYYYMMDD") String dataYYYYMMDD,
				@RequestParam(name = "calorie_sett") Integer calorie_sett,
				@RequestParam(name = "carboidrati_sett") Integer carboidrati_sett,
				@RequestParam(name = "proteine_sett") Integer proteine_sett,
				@RequestParam(name = "grassi_sett") Integer grassi_sett,
				@RequestParam(name = "alcool_sett") Integer alcool_sett
									) {
			macroSettimanaliService.insert(dataYYYYMMDD, calorie_sett, carboidrati_sett, proteine_sett, grassi_sett, alcool_sett);
			return new ResponseEntity("inserimento macro", HttpStatus.OK);
		}
		
		//http://localhost:8080/evolution2/macro/obj
		//con text/json controlla già il formato data che sia di tipo sql
		//con oggetto
		@PostMapping(value = "/macro/obj")
		public ResponseEntity insertO(@RequestBody  MacroSettimanali macro
									) {

			macroSettimanaliService.insert(macro);
			return new ResponseEntity(macro, HttpStatus.OK);//macro ritorna con id appena inserita!!!
		}

	
		
		@DeleteMapping("/macro/{id}")
		public ResponseEntity delete(@PathVariable Integer id) {

			MacroSettimanali macro = macroSettimanaliService.getById(id);
			if (macro==null) {
				return new ResponseEntity(FrasiFatte.HttpRequest.ID_NON_TROVATO + id, HttpStatus.NOT_FOUND);
			}else{
				macroSettimanaliService.delete(id);
			}

			return new ResponseEntity(FrasiFatte.HttpRequest.RECORD_CANCELLATO + id , HttpStatus.OK);

		}
}
