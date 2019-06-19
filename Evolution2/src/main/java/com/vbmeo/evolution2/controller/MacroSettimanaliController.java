package com.vbmeo.evolution2.controller;
import java.text.DateFormat;
import java.util.Date;
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
import com.vbmeo.evolution2.util.FrasiFatte;


//per chiamate rest json
@RestController
public class MacroSettimanaliController {
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
		@Autowired
		private MacroSettimanaliService macroSettimanaliService;

		ManagerMacroSettimanali managerMacroSettimanali = new ManagerMacroSettimanali();
		
		
		@GetMapping(value = "/macro")
		public List<MacroSettimanali> list() {
			List<MacroSettimanali> lista = macroSettimanaliService.getAll();			 
			return lista;
		}
		
		@GetMapping(value = "/macro/{id}")
		public ResponseEntity getById(@PathVariable("id") Integer id) {
			MacroSettimanali macro = macroSettimanaliService.getById(id);	
			if (macro==null)
				return new ResponseEntity(FrasiFatte.HttpRequest.ID_NON_TROVATO + id, HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity(macro, HttpStatus.OK);

		}

		
		@PostMapping(value = "/macro")
		public ResponseEntity insert(@RequestParam(name = "data") Date data,
				@RequestParam(name = "calorie_sett") Integer calorie_sett,
				@RequestParam(name = "carboidrati_sett") Integer carboidrati_sett,
				@RequestParam(name = "proteine_sett") Integer proteine_sett,
				@RequestParam(name = "grassi_sett") Integer grassi_sett,
				@RequestParam(name = "alcool_sett") Integer alcool_sett
									) {

			MacroSettimanali macro = new MacroSettimanali(data,
					calorie_sett,carboidrati_sett,proteine_sett,grassi_sett,alcool_sett);
			
			
			if (alcool_sett==0){
				System.out.println("alcool settimanali nullo, procedo col calcolo " + calorie_sett);
				int apportoAlcool = managerMacroSettimanali.calcolaApportoCaloricoAlcoolSettimanale(macro);
				macro.setAlcool_sett(apportoAlcool);
				System.out.println("apporto alcool calcolato in " + apportoAlcool);
			}else
				System.out.println("apporto alcool già immesso " + alcool_sett);
				
			
			macroSettimanaliService.insert(macro);
			return new ResponseEntity(calorie_sett, HttpStatus.OK);
		}
		
		
//		@DeleteMapping("/macrosettimanali/{id}")
//		public ResponseEntity delete(@PathVariable(value = "id") int id) throws ResourceAccessException{
//			 System.out.println("cancellazione id macro " + id);
//			MacroSettimanali macro = managerMacroSettimanali.getById(id);
//			if (macro == null) 
//				return new ResponseEntity("Nessuna macro trovata con id " + id, HttpStatus.NOT_FOUND);
//			else
//				managerMacroSettimanali.delete(id);
//			
//			return new ResponseEntity(id, HttpStatus.OK);
//
//		}
	
}
