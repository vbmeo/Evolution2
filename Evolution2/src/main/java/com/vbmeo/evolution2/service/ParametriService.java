package com.vbmeo.evolution2.service;

import java.util.List;

import com.vbmeo.evolution2.model.Parametri;

public interface ParametriService  extends BaseSql<Parametri>{

	List<Parametri> getAll();
}
