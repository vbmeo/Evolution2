package com.vbmeo.evolution2.controller;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vbmeo.evolution2.model.MacroSettimanali;
import com.vbmeo.evolution2.model.Misure;
import com.vbmeo.evolution2.service.MisureService;
import com.vbmeo.evolution2.util.FrasiFatte;
import com.vbmeo.evolution2.util.MyUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MisureController {
	private static final Logger logger = LoggerFactory.getLogger(MisureController.class);
	
	@Autowired
	private MisureService misureService;
	
			//http://localhost:8080/evolution2/misure
			//@CrossOrigin
			@GetMapping(value = "/misure")
			public List<Misure> list() {
				logger.debug("richiesta lista misure ");
				List<Misure> lista = misureService.getAll();		
				return lista;
			}
	
			
			
			//UPDATE E INSERT
			//con parametri separati
			//usato sia per inserimento che per update
			//21 variabili
			@CrossOrigin
			@PostMapping(value = "/misure")
			public ResponseEntity insert(
					@RequestParam(name = "dataYYYYMMDD") String dataYYYYMMDD,
					@RequestParam(name = "id") Integer id,
					@RequestParam(name = "altezza") Float altezza,
					@RequestParam(name = "peso") Float peso,
					@RequestParam(name = "ombelico") Float ombelico,
					@RequestParam(name = "addomesporgente") Float addomesporgente,
					@RequestParam(name = "capezzoli") Float capezzoli,
					@RequestParam(name = "toracealto") Float toracealto,
					@RequestParam(name = "braccisxstesobraccio") Float braccisxstesobraccio,
					@RequestParam(name = "braccidxstesobraccio") Float braccidxstesobraccio,
					@RequestParam(name = "bracciosxintiro") Float bracciosxintiro,
					@RequestParam(name = "bracciodxintiro") Float bracciodxintiro,
					@RequestParam(name = "collo") Float collo,
					@RequestParam(name = "plicosxombelico") Float plicosxombelico,
					@RequestParam(name = "plicodxombelico") Float plicodxombelico,
					@RequestParam(name = "plicometasx") Float plicometasx,
					@RequestParam(name = "plicometadx") Float plicometadx,
					@RequestParam(name = "plicofiancosx") Float plicofiancosx,
					@RequestParam(name = "plicofiancodx") Float plicofiancodx,
					@RequestParam(name = "peso_mattina_a_vuoto") Float peso_mattina_a_vuoto,
					@RequestParam(name = "addome_mattina_a_vuoto") Float addome_mattina_a_vuoto
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
				if (id==0 || id==null)//insert che ha il controllo anche di aggiornamento su date uguali
					risultatoOperazione = misureService.insert(dataSql, altezza,  peso,  ombelico,  addomesporgente,
							 capezzoli,  toracealto,  braccisxstesobraccio,  braccidxstesobraccio,
							 bracciosxintiro,  bracciodxintiro,  collo,  plicosxombelico,  plicodxombelico,
							 plicometasx,  plicometadx,  plicofiancosx,  plicofiancodx,  peso_mattina_a_vuoto,
							 addome_mattina_a_vuoto);
				else//update
					risultatoOperazione = misureService.update(id,dataSql, altezza,  peso,  ombelico,  addomesporgente,
							 capezzoli,  toracealto,  braccisxstesobraccio,  braccidxstesobraccio,
							 bracciosxintiro,  bracciodxintiro,  collo,  plicosxombelico,  plicodxombelico,
							 plicometasx,  plicometadx,  plicofiancosx,  plicofiancodx,  peso_mattina_a_vuoto,
							 addome_mattina_a_vuoto);
				
				
				//universale
				if (risultatoOperazione==null)
					return new ResponseEntity("Inserimento effettuato", HttpStatus.OK);
				else
					return new ResponseEntity(risultatoOperazione, HttpStatus.NOT_ACCEPTABLE);//mettendo non acettabile riesco a stampare il messaggio in ajax con quello che volgio dire, diventa errore 406

				
			}
			
			/**
			 * ----------------------delete-----------------------------
			 * @param id
			 * @return
			 */
			@DeleteMapping("/misure/{id}")
			@ResponseBody
			public ResponseEntity delete(@PathVariable Integer id) {
				
				 Misure misura = misureService.getById(id);
				if (misura==null) {
					return new ResponseEntity(FrasiFatte.HttpRequest.ID_NON_TROVATO + id, HttpStatus.NOT_ACCEPTABLE);
				}else{
					
					misureService.delete(id);
					return new ResponseEntity(FrasiFatte.HttpRequest.RECORD_CANCELLATO + id , HttpStatus.OK);
				}
			}
			
			
			
			@CrossOrigin
			@GetMapping(value = "/misure/diffPeso/{dataInfo}")
			public double getDifferenzaPesoSettimanaPrecedente(@PathVariable String dataInfo) {
				//controllo data 
				Date dataSql = null;
				if (MyUtil.controlloDataSql(dataInfo))
					dataSql = MyUtil.convertDateinSqlDate(dataInfo);
					if (dataSql!=null) {
						Date dataSettimanaPrecedente=MyUtil.addDayToDateSQl(dataSql,-7);
						double differenza = misureService.getDifferenzaPesoTraDueDate(dataSql,dataSettimanaPrecedente);
						return differenza;
					}
						
					return 0f;//istanza float
			}
					
				
				
			@CrossOrigin
			@GetMapping(value = "/misure/diffPesomese/{dataInfo}")
			public double getDifferenzaPesoMesePrecedente(@PathVariable String dataInfo) {
				//controllo data 
				Date dataSql = null;
				if (MyUtil.controlloDataSql(dataInfo))
					dataSql = MyUtil.convertDateinSqlDate(dataInfo);
					if (dataSql!=null) {
						double differenza = misureService.getDifferenzaPesoTradataE4SettimanePrima(dataSql);
						return  MyUtil.troncaCifreDec(differenza,2);
					}
						
					return 0f;//istanza float
			}
			
			
			
			
			
			@GetMapping(value = "/misure/diffOmbelico/{dataInfo}")
			public double getDifferenzaOmbelicoSettimanaPrecedente(@PathVariable String dataInfo) {
				//controllo data 
				Date dataSql = null;
				if (MyUtil.controlloDataSql(dataInfo))
					dataSql = MyUtil.convertDateinSqlDate(dataInfo);
					if (dataSql!=null) {
						Date dataSettimanaPrecedente=MyUtil.addDayToDateSQl(dataSql,-7);
						double differenza = misureService.getDifferenzaOmbelicoTraDueDate(dataSql,dataSettimanaPrecedente);
						return differenza;
					}
						
					return 0f;//istanza float
			}
					
			
			@GetMapping(value = "/misure/diffOmbelicomese/{dataInfo}")
			public double getDifferenzaOmbelicoTradataE4SettimanePrima(@PathVariable String dataInfo) {
				//controllo data 
				Date dataSql = null;
				if (MyUtil.controlloDataSql(dataInfo))
					dataSql = MyUtil.convertDateinSqlDate(dataInfo);
					if (dataSql!=null) {
						double differenza = misureService.getDifferenzaOmbelicoTradataE4SettimanePrima(dataSql);
						return differenza;
					}
						
					return 0f;//istanza float
			}	
			
			
			
			
			/**
			 * Ritorna ultima data inserita veramente, nel senso di inserimento quindi id, non in ordine di data, per agevolare gli inserimenti sequenziali per tipo di misura, 
			 * @return
			 */
			@CrossOrigin
			@GetMapping(value = "/misure/lastdatedyid")
			public Date lastDataById() {
				Date data =  MyUtil.addOneWeekToDateSQl(misureService.getLastDateById());
				return data;
			}
			
			
}
