package com.vbmeo.evolution2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vbmeo.evolution2.model.Parametri;

@Service
public interface ParametriService  extends BaseSql<Parametri>{

	List<Parametri> getAll();
}
