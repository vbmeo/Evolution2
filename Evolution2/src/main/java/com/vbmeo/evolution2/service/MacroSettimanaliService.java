package com.vbmeo.evolution2.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.vbmeo.evolution2.model.MacroSettimanali;


@Service
public interface MacroSettimanaliService {
	void insert(MacroSettimanali macroSettimanali);
	
	//non collegato al mapper
	String insert(String data, Integer calorie_sett, Integer carboidrati_sett, Integer proteine_sett, 
			Integer grassi_sett,Integer alcool_sett);
	 	
	MacroSettimanali getByDate(Date data);
 
	List<MacroSettimanali> getAll();
 
	public void delete(Integer id);
 
	public MacroSettimanali getById(Integer id);
 
	public void update(MacroSettimanali macroSettimanali);
}
