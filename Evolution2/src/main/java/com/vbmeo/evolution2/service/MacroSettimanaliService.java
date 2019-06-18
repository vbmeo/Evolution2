package com.vbmeo.evolution2.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vbmeo.evolution2.model.MacroSettimanali;


@Service
public interface MacroSettimanaliService {
	void insert(MacroSettimanali macroSettimanali);
	 	
	MacroSettimanali getByDate(Date data);
 
	List<MacroSettimanali> getAll();
 
	public void delete(Integer id);
 
	public MacroSettimanali getById(Integer id);
 
	public void update(MacroSettimanali macroSettimanali);
}
