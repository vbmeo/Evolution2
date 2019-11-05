package com.vbmeo.evolution2.service;

import java.sql.Date;
import java.util.List;

import com.vbmeo.evolution2.model.MacroSettimanali;


public interface MacroSettimanaliService extends BaseSql<MacroSettimanali> {
	
	
	//l'insert vero prende l'oggetto in questione e si affida a quello nell'interfaccia base
	/**
	 * return null tutto ok, altrimenti messaggio del tipo di errore
	 * @param data
	 * @param calorie_sett
	 * @param carboidrati_sett
	 * @param proteine_sett
	 * @param grassi_sett
	 * @param alcool_sett
	 * @return
	 */
	String insert(java.sql.Date data, Integer calorie_sett, Integer carboidrati_sett, Integer proteine_sett, 
			Integer grassi_sett,Integer alcool_sett);
	 
	/**
	 * return null tutto ok, altrimenti messaggio del tipo di errore
	 * @param id
	 * @param data
	 * @param calorie_sett
	 * @param carboidrati_sett
	 * @param proteine_sett
	 * @param grassi_sett
	 * @param alcool_sett
	 * @return
	 */
	String update(Integer id,java.sql.Date data, Integer calorie_sett, Integer carboidrati_sett, Integer proteine_sett, 
			Integer grassi_sett,Integer alcool_sett);
	List<MacroSettimanali> getByDate(Date data);
	
	Date getLastDate();
	
	List<Integer> getLastTwoCalorieSettimanali();

	List<Integer> getLastTwoCarboSettimanali();

	Integer getByDateMese(Date data);

	Date getDateMesePrima(Date data);
}
