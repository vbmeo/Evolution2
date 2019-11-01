package com.vbmeo.evolution2.service;

import java.sql.Date;
import java.util.List;

import com.vbmeo.evolution2.model.Attivita;
import com.vbmeo.evolution2.model.MacroSettimanali;
import com.vbmeo.evolution2.model.Misure;

public interface CharCustomService {

	
	//principali
	List<Misure> getMisureTraDueDateCheCompaionoInMacro(Date dataInzio, Date dataFine);
	List<MacroSettimanali> getAllFromTwoDate(Date dataInzio, Date dataFine);
	
	List<Attivita> getDispendiEnergeticiEOreSettimanaliNonAVuotoTraDueDate(Date dataInzio, Date dataFine);
	List<Attivita> getDispendiEnergeticiEOreSettimanaliAVuotoTraDueDate(Date dataInzio, Date dataFine);				
	List<Attivita> getDispendiEnergeticiEOreSettimanaliAerobiciTraDueDate(Date dataInzio, Date dataFine);						
	List<Attivita> getDispendiEnergeticiEOreSettimanaliNonAerobiciTraDueDate(Date dataInzio, Date dataFine);						
	List<Attivita> getDispendiEnergeticiEOreSettimanaliTraDueDateDiviseInSettimane(Date dataInzio, Date dataFine);
	
	
}
