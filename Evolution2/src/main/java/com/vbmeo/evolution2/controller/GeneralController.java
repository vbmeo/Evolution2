package com.vbmeo.evolution2.controller;


import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vbmeo.evolution2.manager.ManagerGeneral;
import com.vbmeo.evolution2.model.GraficiDaFare;
import com.vbmeo.evolution2.model.RigaGraficoCustom;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GeneralController {
	private static final Logger logger = LoggerFactory.getLogger(GeneralController.class);
	
	@Autowired
	ManagerGeneral managerGeneral;
	
	@CrossOrigin
	@PostMapping(value = "/customGrafico")
	public RigaGraficoCustom[] list2(//@RequestParam(required = false) String id) se non obligatoria
			@RequestBody  GraficiDaFare[] grafici) {//HttpServletRequest httpServletRequest non riesco a leggerne i valori... @RequestParam List<String> values NO @RequestParam String[] info
		
		if (grafici.length>0) {
			//controllo data
			List<Date> dataDaeA = managerGeneral.controllaDataDeigraficiERimandaDataPrecedenteSeTuttoOk(grafici);
			if (dataDaeA==null)
				return null;//new ResponseEntity("La data è di un formato errato deve essere (es. 2014-12-01)" , HttpStatus.NOT_ACCEPTABLE);//mettendo non acettabile riesco a stampare il messaggio in ajax con quello che volgio dire, diventa errore 406			
			//creazione grafici, manda anche le date precedente e successiva appena fatte
			RigaGraficoCustom[] arrayDiTuttiIRisultati = managerGeneral.interrogaDbPerGrafici(grafici,dataDaeA.get(0),dataDaeA.get(1));
			
			return arrayDiTuttiIRisultati;
		}
		return null;
	}
	
}



