package com.vbmeo.evolution2.service;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import com.vbmeo.evolution2.model.Attivita;
import com.vbmeo.evolution2.model.AttivitaDispendio;

@Service
public interface AttivitaService extends BaseSql<Attivita>{
	
	
	String insert(Date data, Integer id_attivita, Date data_fine_settimana, float quantita_in_ore, Integer dispendio_energetico, String note, boolean a_vuoto);
	
	String update(Integer id,Integer id_attivita, Date data,Date data_fine_settimana, float quantita_in_ore, Integer dispendio_energetico, String note, boolean a_vuoto);
	
//	Misure getLastRecord();
	
	List<Attivita> getByDate(Date data);
	
	/**
	 * tutte le misure corrispondenti a quella settimana la data we è il lunedì successivo
	 * @param data
	 * @return
	 */
	List<Attivita> getByDateWe(Date data);
	Date getLastDate();
	Date getLastDateWE();
	
	List<AttivitaDispendio> getAllDispendi();
	
	/**
	 * FA LA SOMMA DELLE CALORIE SPESE NELLA SETTIMANA PER ATTIVITA' A VUOTO
	 * @return
	 */
    List<Attivita> getDispendiEnergeticiSettimanaliAVuoto(); 
	
    /**
	 * FA LA SOMMA DELLE CALORIE SPESE NELLA SETTIMANA PER ATTIVITA' NON A VUOTO
	 * @return
	 */
    List<Attivita> getDispendiEnergeticiSettimanaliNonAVuoto(); 

    /**
	 * FA LA SOMMA DELLE CALORIE SPESE NELLA SETTIMANA PER tutte le ATTIVITA'
	 * @return
	 */
    List<Attivita> getDispendiEnergeticiSettimanaliTotali(); 
	
	
    /**
	 * FA LA SOMMA DELLE CALORIE SPESE NELLA SETTIMANA PER ATTIVITA' AEROBICHE
	 * @return
	 */
    List<Attivita> getDispendiEnergeticiSettimanaliAerobici(); 
    
    
    /**
	 * FA LA SOMMA DELLE CALORIE SPESE NELLA SETTIMANA PER ATTIVITA' NON AEROBICHE
	 * @return
	 */
    List<Attivita> getDispendiEnergeticiSettimanaliNonAerobici(); 
//	Date getLastDateById();
    
    
    //segue come sopra ma x date
	
    List<Attivita> getDispendiEnergeticiSettimanaliAVuoto(Date indata); 
	
    List<Attivita> getDispendiEnergeticiSettimanaliNonAVuoto(Date indata); 

    List<Attivita> getDispendiEnergeticiSettimanaliTotali(Date indata); 
	
    List<Attivita> getDispendiEnergeticiSettimanaliAerobici(Date indata); 
    
    List<Attivita> getDispendiEnergeticiSettimanaliNonAerobici(Date indata); 
    
    //mensioli
    List<Attivita> getDispendiEnergeticiSettimanaliTotaliMensili(Date dataQualsiasiDelMeseRichiesto);

	List<Attivita> getDispendiEnergeticiSettimanaliAVuotoMensili(Date dataIniziosetimanaRichiesta);

	List<Attivita> getDispendiEnergeticiSettimanaliNonAVuotoMensili(Date dataIniziosetimanaRichiesta); 
    
}
