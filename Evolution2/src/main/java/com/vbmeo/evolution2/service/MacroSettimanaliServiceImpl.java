package com.vbmeo.evolution2.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vbmeo.evolution2.manager.ManagerMacroSettimanali;
import com.vbmeo.evolution2.mapper.MacroSettimanalimapper;
import com.vbmeo.evolution2.model.MacroSettimanali;
import com.vbmeo.evolution2.util.MyUtil;



@Service("macroSettimanaliService")
public class MacroSettimanaliServiceImpl implements MacroSettimanaliService {

	@Autowired
	private MacroSettimanalimapper macroSettimanalimapper;
	@Autowired
	ManagerMacroSettimanali managerMacroSettimanali;
	
	
	
	
	
	/**
	 * ritorna macro inserita
	 * la data vole in formato YYYY-MM-DD
	 * @param macroSettimanali
	 */
	@Override
	@Transactional
	public void insert(MacroSettimanali macroSettimanali) {
		macroSettimanalimapper.insert(macroSettimanali);
	}

	/**
	 * return null tutto ok
	 * @param data
	 * @param calorie_sett
	 * @param carboidrati_sett
	 * @param proteine_sett
	 * @param grassi_sett
	 * @param alcool_sett
	 * @return
	 */
	//questo non va nel mapper, chiamerà poi insert con oggetto
	@Override
	public String insert(String dataYyyyMMdd, Integer calorie_sett,
			Integer carboidrati_sett, Integer proteine_sett,
			Integer grassi_sett, Integer alcool_sett) {
		
		
		//controllo data 
		Date dataSql = MyUtil.convertDateinSqlDate(dataYyyyMMdd);
		if (dataSql==null)
			return "Errore nella conversione data occorre che sia nel formato 2018-12-31";
		
		
		MacroSettimanali macro = new MacroSettimanali(dataSql,
				calorie_sett,carboidrati_sett,proteine_sett,grassi_sett,alcool_sett);
		
	
		
		
		if (alcool_sett==0){
			System.out.println("alcool settimanali nullo, procedo col calcolo " + calorie_sett);
			int apportoAlcool = managerMacroSettimanali.calcolaApportoCaloricoAlcoolSettimanale(macro);
			macro.setAlcool_sett(apportoAlcool);
			System.out.println("apporto alcool calcolato in " + apportoAlcool);
		}else
			System.out.println("apporto alcool già immesso " + alcool_sett);
			
		
		insert(macro);
		
		return null;
		
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
