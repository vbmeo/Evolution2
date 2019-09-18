package com.vbmeo.evolution2.controller;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vbmeo.evolution2.model.MacroSettimanali;
import com.vbmeo.evolution2.service.MacroSettimanaliService;
import com.vbmeo.evolution2.util.FrasiFatte;
import com.vbmeo.evolution2.util.MyUtil;

//supporta sitema di richiesta @CrossOrigin che arriva da angular vedi annotation più avanti in get all
//possibile controllo di provenienza richiesta con @CrossOrigin(origins = "http://example.com", maxAge = 3600)
//per chiamate rest json
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MacroSettimanaliController {
private static final Logger logger = LoggerFactory.getLogger(MacroSettimanaliController.class);
	



		@Autowired
		private MacroSettimanaliService macroSettimanaliService;
		
		
		//http://localhost:8080/evolution2/macro
		//@CrossOrigin
		@GetMapping(value = "/macro")
		public List<MacroSettimanali> list() {
			logger.debug("richiesta lista macro settimanali");
			List<MacroSettimanali> lista = macroSettimanaliService.getAll();		
			return lista;
		}
		
		/**
		 * Ritorna ultima data inserita + una settimana, 
		 * @return
		 */
		@GetMapping(value = "/macro/lastdate")
		public Date lastData() {
			Date data =  MyUtil.addOneWeekToDateSQl(macroSettimanaliService.getLastDate());
			return data;
		}
		
		
		
		@GetMapping(value = "/macro/lasttwocal")
		public Integer lastTwoCalorieSettimanali() {
			List<Integer> ultimeDueCal = macroSettimanaliService.getLastTwoCalorieSettimanali();
			if (ultimeDueCal.size()>1){
				int media=(ultimeDueCal.get(0)+ultimeDueCal.get(1))/2;
				return media;
			}
			return null;
		}
		
		
		@GetMapping(value = "/macro/lasttwocarbo")
		public Integer lastTwoCarboSettimanali() {
			List<Integer> ultimeDueCal = macroSettimanaliService.getLastTwoCarboSettimanali();
			if (ultimeDueCal.size()>1){
				int media=(ultimeDueCal.get(0)+ultimeDueCal.get(1))/2;
				return media;
			}
			return null;
		}
		
		
		//con string come parametro intercetto una possibile data
		//http://localhost:8080/evolution2/macro/30 o 2018-10-22
		
		//senza una lista non funziona con solo l'oggetto
		@ResponseBody
		@GetMapping(value = "/macro/")//non aggiungere altro altrimenti non legge il parametro
		public List<MacroSettimanali> getById(@RequestParam(name = "idOrDate") String idOrDate) {
			List<MacroSettimanali> list = new ArrayList();
			
			if (MyUtil.isNumeric(idOrDate)){
				//richiesta per id
				Integer id=MyUtil.convertStringToInteger(idOrDate);
				MacroSettimanali macro = macroSettimanaliService.getById(id);
				logger.debug(FrasiFatte.HttpRequest.RICHIESTA_ANDATA_A_BUON_FINE + idOrDate);
				list.add(macro);
				return list;//new ResponseEntity(macro, HttpStatus.OK);
			}else if (MyUtil.controlloDataSql(idOrDate)){
				java.sql.Date sqlDate = MyUtil.convertDateinSqlDate(idOrDate);
				List<MacroSettimanali> macro = macroSettimanaliService.getByDate(sqlDate);
				logger.debug(FrasiFatte.HttpRequest.RICHIESTA_ANDATA_A_BUON_FINE + idOrDate);
				return macro;//new ResponseEntity(macro, HttpStatus.OK);
			}else{
				logger.warn(FrasiFatte.HttpRequest.FORMATO_NON_VALIDO + idOrDate);
				return null;//new ResponseEntity(FrasiFatte.HttpRequest.FORMATO_NON_VALIDO + idOrDate, HttpStatus.BAD_REQUEST);
			}
		}

		
		@ResponseBody
		@GetMapping(value = "/macro/bydate/{dataInfo}")//non aggiungere altro altrimenti non legge il parametro
		public List<MacroSettimanali> getByDate(@PathVariable String dataInfo) {
			//controllo data 
			Date dataSql = null;
			if (MyUtil.controlloDataSql(dataInfo))
				dataSql = MyUtil.convertDateinSqlDate(dataInfo);
				if (dataSql!=null) {
					List<MacroSettimanali> listaDaUnoSolo = macroSettimanaliService.getByDate(dataSql);
					return listaDaUnoSolo;
				}
				return null;
		}

		
		@CrossOrigin
		@ResponseBody
		@GetMapping(value = "/macro/bydatemese/{dataInfo}")//non aggiungere altro altrimenti non legge il parametro
		public Integer getByDateMesePrima(@PathVariable String dataInfo) {
			//controllo data 
			Date dataSql = null;
			if (MyUtil.controlloDataSql(dataInfo))
				dataSql = MyUtil.convertDateinSqlDate(dataInfo);
				if (dataSql!=null) {
					Integer calorieTotali = macroSettimanaliService.getByDateMese(dataSql);
					return calorieTotali;
				}
				return 0;
		}

		
		
		
		/**
		 * ----------------------delete-----------------------------
		 * @param id
		 * @return
		 */
		@DeleteMapping("/macro/{id}")
		@ResponseBody
		public ResponseEntity delete(@PathVariable Integer id) {
			
			 MacroSettimanali macro = macroSettimanaliService.getById(id);
			if (macro==null) {
				return new ResponseEntity(FrasiFatte.HttpRequest.ID_NON_TROVATO + id, HttpStatus.NOT_ACCEPTABLE);
			}else{
				
				macroSettimanaliService.delete(id);
				return new ResponseEntity(FrasiFatte.HttpRequest.RECORD_CANCELLATO + id , HttpStatus.OK);
			}



		}
		
		//UPDATE E INSERT
		//con parametri separati
		//usato sia per inserimento che per update
		@CrossOrigin
		@PostMapping(value = "/macro")
		public ResponseEntity insert(
				@RequestParam(name = "dataYYYYMMDD") String dataYYYYMMDD,
				@RequestParam(name = "id") Integer id,
				@RequestParam(name = "calorie_sett") Integer calorie_sett,
				@RequestParam(name = "carboidrati_sett") Integer carboidrati_sett,
				@RequestParam(name = "proteine_sett") Integer proteine_sett,
				@RequestParam(name = "grassi_sett") Integer grassi_sett,
				@RequestParam(name = "alcool_sett") Integer alcool_sett
				) {
			
			//-----------zona valida sia per update che insert------------
			//controllo data 
			Date dataSql;
			if (!MyUtil.controlloDataSql(dataYYYYMMDD))
				return new ResponseEntity("La data è di un formato errato deve essere (es. 2014-12-01)" , HttpStatus.NOT_ACCEPTABLE);//mettendo non acettabile riesco a stampare il messaggio in ajax con quello che volgio dire, diventa errore 406
			else{
				dataSql = MyUtil.convertDateinSqlDate(dataYYYYMMDD);
				if (dataSql==null)
					return new ResponseEntity("La data è di un formato errato deve essere (es. 2014-12-01)" , HttpStatus.NOT_ACCEPTABLE);//mettendo non acettabile riesco a stampare il messaggio in ajax con quello che volgio dire, diventa errore 406
			}
				
			
				
			//---------definisce se insert o update------------
			String risultatoOperazione;
			if (id==0 || id==null)//insert
				risultatoOperazione = macroSettimanaliService.insert(dataSql, calorie_sett, carboidrati_sett, proteine_sett, grassi_sett, alcool_sett);
			else//update
				risultatoOperazione = macroSettimanaliService.update(id,dataSql, calorie_sett, carboidrati_sett, proteine_sett, grassi_sett, alcool_sett);
			
			
			
			//universale
			if (risultatoOperazione==null)
				return new ResponseEntity("Inserimento effettuato", HttpStatus.OK);
			else
				return new ResponseEntity(risultatoOperazione, HttpStatus.NOT_ACCEPTABLE);//mettendo non acettabile riesco a stampare il messaggio in ajax con quello che volgio dire, diventa errore 406

			
		}
		
		//http://localhost:8080/evolution2/macro/obj
		//con text/json controlla già il formato data che sia di tipo sql
		//con oggetto
		//@CrossOrigin
		@PostMapping(value = "/macro/obj")
		public ResponseEntity insertO(@ModelAttribute("macroSend") MacroSettimanali macro
									) {//@RequestBody  MacroSettimanali macro

			macroSettimanaliService.insert(macro);
			return new ResponseEntity(macro, HttpStatus.OK);//macro ritorna con id appena inserita!!!
		}

}
