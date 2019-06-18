package com.vbmeo.evolution2.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vbmeo.evolution2.mapper.MacroSettimanalimapper;
import com.vbmeo.evolution2.model.MacroSettimanali;



@Service("macroSettimanaliService")
public class MacroSettimanaliServiceImpl implements MacroSettimanaliService {

	@Autowired
	private MacroSettimanalimapper macroSettimanalimapper;

	@Override
	@Transactional
	public void insert(MacroSettimanali macroSettimanali) {
		macroSettimanalimapper.insert(macroSettimanali);
	}

	@Override
	public MacroSettimanali getByDate(Date data) {
		return macroSettimanalimapper.getByDate(data);
	}

	@Override
	public List<MacroSettimanali> getAll() {
		return macroSettimanalimapper.getAll();
	}

	@Override
	public void delete(Integer id) {
		macroSettimanalimapper.delete(id);
	}

	@Override
	public MacroSettimanali getById(Integer id) {
		return macroSettimanalimapper.getById(id);
	}

	@Override
	public void update(MacroSettimanali macroSettimanali) {
		macroSettimanalimapper.update(macroSettimanali);
	}
	
	
	
	
}
