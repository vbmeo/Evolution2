package com.vbmeo.evolution2.controller;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vbmeo.evolution2.model.Attivita;
import com.vbmeo.evolution2.model.AttivitaDispendio;
import com.vbmeo.evolution2.service.AttivitaService;
import com.vbmeo.evolution2.util.MyUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AttivitaController {

private static final Logger logger = LoggerFactory.getLogger(AttivitaController.class);
	
	@Autowired
	private AttivitaService attivitaService;
	
	
	//http://localhost:8080/evolution2/misure
	@CrossOrigin
	@GetMapping(value = "/attivita")
	public List<Attivita> list() {
		logger.debug("richiesta lista attività ");
		List<Attivita> lista = attivitaService.getAll();		
		return lista;
	}

	
	
	
	
	@CrossOrigin
	@GetMapping(value = "/attivita/consumisettimanaliavuoto")
	public List<Attivita> getDispendiEnergeticiSettimanaliAVuoto() {
		List<Attivita> lista = attivitaService.getDispendiEnergeticiSettimanaliAVuoto();		
		return lista;
	}
	
	
	@CrossOrigin
	@GetMapping(value = "/attivita/consumisettimanaliavuoto/{inData}")
	public List<Attivita> getDispendiEnergeticiSettimanaliAVuoto(@PathVariable String inData) {
		Date dataSql = null;
		if (MyUtil.controlloDataSql(inData))
			dataSql = MyUtil.convertDateinSqlDate(inData);
			if (dataSql!=null) {
				List<Attivita> lista = attivitaService.getDispendiEnergeticiSettimanaliAVuoto(dataSql);	
				return lista;
			}
				
			return null;
	}
	
	
	
	
	@CrossOrigin
	@GetMapping(value = "/attivita/consumisettimanalianonvuoto")
	public List<Attivita> getDispendiEnergeticiSettimanaliNonAVuoto() {
		List<Attivita> lista = attivitaService.getDispendiEnergeticiSettimanaliNonAVuoto();		
		return lista;
	}

	
	@CrossOrigin
	@GetMapping(value = "/attivita/consumisettimanalianonvuoto/{inData}")
	public List<Attivita> getDispendiEnergeticiSettimanaliNonAVuoto(@PathVariable String inData) {
		Date dataSql = null;
		if (MyUtil.controlloDataSql(inData))
			dataSql = MyUtil.convertDateinSqlDate(inData);
			if (dataSql!=null) {
				List<Attivita> lista = attivitaService.getDispendiEnergeticiSettimanaliNonAVuoto(dataSql);	
				return lista;
			}
				
			return null;
	}
	
	
	
	
	@CrossOrigin
	@GetMapping(value = "/attivita/consumisettimanaliatotali")
	public List<Attivita> getDispendiEnergeticiSettimanaliTotali() {
		List<Attivita> lista = attivitaService.getDispendiEnergeticiSettimanaliTotali();		
		return lista;
	}

	
	
	@CrossOrigin
	@GetMapping(value = "/attivita/consumisettimanaliatotali/{inData}")
	public List<Attivita> getDispendiEnergeticiSettimanaliTotali(@PathVariable String inData) {
		Date dataSql = null;
		if (MyUtil.controlloDataSql(inData))
			dataSql = MyUtil.convertDateinSqlDate(inData);
			if (dataSql!=null) {
				List<Attivita> lista = attivitaService.getDispendiEnergeticiSettimanaliTotali(dataSql);	
				return lista;
			}
				
			return null;
	}
	
	
	
	/**
	 * deve dare le ultime 4 settimane
	 * @param inData
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/attivita/consumisettimanaliatotalimensili/{inData}")
	public List<Attivita> getDispendiEnergeticiSettimanaliTotalimensilimensili(@PathVariable String inData) {
		Date dataSql = null;
		if (MyUtil.controlloDataSql(inData))
			dataSql = MyUtil.convertDateinSqlDate(inData);
			if (dataSql!=null) {
				List<Attivita> lista = attivitaService.getDispendiEnergeticiSettimanaliTotaliMensili(dataSql);	
				return lista;
			}
			return null;
	}
	
	
	@CrossOrigin
	@GetMapping(value = "/attivita/consumisettimanalianonvuotomensili/{inData}")
	public List<Attivita> getDispendiEnergeticiSettimanaliNonAVuotoMensili(@PathVariable String inData) {
		Date dataSql = null;
		if (MyUtil.controlloDataSql(inData))
			dataSql = MyUtil.convertDateinSqlDate(inData);
			if (dataSql!=null) {
				List<Attivita> lista = attivitaService.getDispendiEnergeticiSettimanaliNonAVuotoMensili(dataSql);	
				return lista;
			}
			return null;
	}
	
	
	
	
	
	
	/**
	 * deve dare le ultime 4 settimane
	 * @param inData
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/attivita/consumisettimanaliavuotomensili/{inData}")
	public List<Attivita> getDispendiEnergeticiSettimanaliAVuotoMensili(@PathVariable String inData) {
		Date dataSql = null;
		if (MyUtil.controlloDataSql(inData))
			dataSql = MyUtil.convertDateinSqlDate(inData);
			if (dataSql!=null) {
				List<Attivita> lista = attivitaService.getDispendiEnergeticiSettimanaliAVuotoMensili(dataSql);	
				return lista;
			}
			return null;
	}
	
	
	
	@CrossOrigin
	@GetMapping(value = "/attivita/consumisettimanaliaerobici")
	public List<Attivita> getDispendiEnergeticiSettimanaliAerobici() {
		List<Attivita> lista = attivitaService.getDispendiEnergeticiSettimanaliAerobici();		
		return lista;
	}

	
	@CrossOrigin
	@GetMapping(value = "/attivita/consumisettimanaliaerobici/{inData}")
	public List<Attivita> getDispendiEnergeticiSettimanaliAerobici(@PathVariable String inData) {
		Date dataSql = null;
		if (MyUtil.controlloDataSql(inData))
			dataSql = MyUtil.convertDateinSqlDate(inData);
			if (dataSql!=null) {
				List<Attivita> lista = attivitaService.getDispendiEnergeticiSettimanaliAerobici(dataSql);	
				return lista;
			}
				
			return null;
	}
	
	
	
	@CrossOrigin
	@GetMapping(value = "/attivita/consumisettimanalinonaerobici")
	public List<Attivita> getDispendiEnergeticiSettimanaliNonAerobici() {
		List<Attivita> lista = attivitaService.getDispendiEnergeticiSettimanaliNonAerobici();		
		return lista;
	}
	
	
	
	@CrossOrigin
	@GetMapping(value = "/attivita/consumisettimanalinonaerobici/{inData}")
	public List<Attivita> getDispendiEnergeticiSettimanaliNonAerobici(@PathVariable String inData) {
		Date dataSql = null;
		if (MyUtil.controlloDataSql(inData))
			dataSql = MyUtil.convertDateinSqlDate(inData);
			if (dataSql!=null) {
				List<Attivita> lista = attivitaService.getDispendiEnergeticiSettimanaliNonAerobici(dataSql);	
				return lista;
			}
				
			return null;
	}
	
	
	
	@CrossOrigin
	@GetMapping(value = "/attivita/lastdate")
	public Date lastData() {
		Date data =  MyUtil.addDayToDateSQl(attivitaService.getLastDate(),1);
		return data;
	}

	
	@CrossOrigin
	@GetMapping(value = "/attivita/lastdateWE")
	public Date lastDataWE() {
		Date data =  MyUtil.nextMonday();
		return data;
	}
	
	
	//ritorna tutti i tipi di attività e loro dispendi energetici
	@CrossOrigin
	@GetMapping(value = "/dispendi")
	public List<AttivitaDispendio> listDispendi() {
		logger.debug("richiesta lista attività ");
		List<AttivitaDispendio> lista = attivitaService.getAllDispendi();		
		return lista;
	}

	
		
	//UPDATE E INSERT
	//con parametri separati
	@CrossOrigin
	@PostMapping(value = "/attivita")
	public ResponseEntity insert(
			@RequestParam(name = "dataYYYYMMDD") String dataYYYYMMDD,
			@RequestParam(name = "dataYYYYMMDDWE") String dataYYYYMMDDWE,
			@RequestParam(name = "id") Integer id,
			@RequestParam(name = "quantita_in_ore") Float quantita_in_ore,
			@RequestParam(name = "dispendio_energeticonote") Integer dispendio_energetico,
			@RequestParam(name = "note") String note,
			@RequestParam(name = "id_attivita") Integer id_attivita,
			@RequestParam(name = "a_vuoto") boolean a_vuoto
			) {
		
		
		
		//controllo dato a vuoto
//		int a_vuotoInt=1;//ovvero true
//		if (a_vuoto==false)
//			a_vuotoInt=0;
			
		
		
		//-----------zona valida sia per update che insert------------
		//controllo data 
		Date dataSqldataYYYYMMDDWE= null;
		Date dataSqldataYYYYMMDD = null;
		
		if (!MyUtil.controlloDataSql(dataYYYYMMDDWE))
			return new ResponseEntity("La data WE è di un formato errato deve essere (es. 2014-12-01)" , HttpStatus.NOT_ACCEPTABLE);//mettendo non acettabile riesco a stampare il messaggio in ajax con quello che volgio dire, diventa errore 406
		else{
			dataSqldataYYYYMMDDWE = MyUtil.convertDateinSqlDate(dataYYYYMMDDWE);
			if (dataSqldataYYYYMMDDWE==null)
				return new ResponseEntity("La data WE è di un formato errato deve essere (es. 2014-12-01)" , HttpStatus.NOT_ACCEPTABLE);//mettendo non acettabile riesco a stampare il messaggio in ajax con quello che volgio dire, diventa errore 406
		}
			
		
		//controllo altra data non necessaria
		//no, può anche non esserci la data giornaliera
		if (dataYYYYMMDD!=null) {
			if (MyUtil.controlloDataSql(dataYYYYMMDD))
				dataSqldataYYYYMMDD = MyUtil.convertDateinSqlDate(dataYYYYMMDD);
//				if (dataSqldataYYYYMMDD==null)
//					return new ResponseEntity("La data è di un formato errato deve essere (es. 2014-12-01)" , HttpStatus.NOT_ACCEPTABLE);//mettendo non acettabile riesco a stampare il messaggio in ajax con quello che volgio dire, diventa errore 406		
		}
		
			
		//---------definisce se insert o update------------
		String risultatoOperazione;
		if (id==0 || id==null)//insert
			risultatoOperazione = attivitaService.insert(dataSqldataYYYYMMDD, id_attivita, dataSqldataYYYYMMDDWE, quantita_in_ore, dispendio_energetico, note,a_vuoto);
		else//update
			risultatoOperazione = attivitaService.update(id,id_attivita,dataSqldataYYYYMMDD, dataSqldataYYYYMMDDWE, quantita_in_ore, dispendio_energetico, note,a_vuoto);

		
		
		//universale
		if (risultatoOperazione==null)
			return new ResponseEntity("Inserimento effettuato", HttpStatus.OK);
		else
			return new ResponseEntity(risultatoOperazione, HttpStatus.NOT_ACCEPTABLE);//mettendo non acettabile riesco a stampare il messaggio in ajax con quello che volgio dire, diventa errore 406

		
	}
	
}
