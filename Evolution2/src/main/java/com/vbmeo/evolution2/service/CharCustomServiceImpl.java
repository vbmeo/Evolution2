package com.vbmeo.evolution2.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vbmeo.evolution2.mapper.AttivitaMapper;
import com.vbmeo.evolution2.mapper.MacroSettimanalimapper;
import com.vbmeo.evolution2.mapper.MisureMapper;
import com.vbmeo.evolution2.model.Attivita;
import com.vbmeo.evolution2.model.MacroSettimanali;
import com.vbmeo.evolution2.model.Misure;

@Service("charCustomServiceImpl")
public class CharCustomServiceImpl implements CharCustomService {

	
	@Autowired
	private MacroSettimanalimapper macroSettimanalimapper;
	@Autowired
	private AttivitaMapper attivitaMapper;
	@Autowired
	private MisureMapper misureMapper;
	
	
	
	@Override
	public List<Misure> getMisureTraDueDateCheCompaionoInMacro(Date dataInzio, Date dataFine) {
		return misureMapper.getMisureTraDueDateCheCompaionoInMacro(dataInzio,dataFine);
	}

	@Override
	public List<MacroSettimanali> getAllFromTwoDate(Date dataInzio, Date dataFine) {
		return macroSettimanalimapper.getAllFromTwoDate(dataInzio,dataFine);
	}

	@Override
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliNonAVuotoTraDueDate(Date dataInzio, Date dataFine) {
		return attivitaMapper.getDispendiEnergeticiEOreSettimanaliNonAVuotoTraDueDate(dataInzio, dataFine);
	}

	@Override
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliAVuotoTraDueDate(Date dataInzio, Date dataFine) {
		return attivitaMapper.getDispendiEnergeticiEOreSettimanaliAVuotoTraDueDate(dataInzio, dataFine);
	}

	@Override
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliAerobiciTraDueDate(Date dataInzio, Date dataFine) {
		return  attivitaMapper.getDispendiEnergeticiEOreSettimanaliAerobiciTraDueDate(dataInzio, dataFine);
	}

	@Override
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliNonAerobiciTraDueDate(Date dataInzio, Date dataFine) {
		return attivitaMapper.getDispendiEnergeticiEOreSettimanaliNonAerobiciTraDueDate(dataInzio, dataFine);
	}

	@Override
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliTraDueDateDiviseInSettimane(Date dataInzio,
			Date dataFine) {
		return attivitaMapper.getDispendiEnergeticiEOreSettimanaliTraDueDateDiviseInSettimane(dataInzio, dataFine);
	}

}
