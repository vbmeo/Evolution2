package com.vbmeo.evolution2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vbmeo.evolution2.mapper.ParametriMapper;
import com.vbmeo.evolution2.model.Parametri;

@Service("parametriServiceImpl")
public class ParametriServiceImpl implements ParametriService{

	@Autowired
	private ParametriMapper parametriMapper;
	
	
	@Override
	public Parametri getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Parametri object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Parametri object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Parametri> getAll() {
		return parametriMapper.getAll();
	}

}
