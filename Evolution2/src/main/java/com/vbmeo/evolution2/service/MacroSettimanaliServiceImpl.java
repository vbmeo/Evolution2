package com.vbmeo.evolution2.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public String insert(java.sql.Date dataYyyyMMdd, Integer calorie_sett,
			Integer carboidrati_sett, Integer proteine_sett,
			Integer grassi_sett, Integer alcool_sett) {
		
		
		
		
		
		
		MacroSettimanali macro = new MacroSettimanali(dataYyyyMMdd,
				calorie_sett,carboidrati_sett,proteine_sett,grassi_sett,alcool_sett);
		
		//controllo data doppia
		List<MacroSettimanali> macroGiaPresente = getByDate(dataYyyyMMdd);
		if (macroGiaPresente!=null&&macroGiaPresente.size()>0)
			return "Data già presente nell'archivio...";
		
		
		//controllo tipo di calcolo da effettuare
		//se manca alcool o cal totali
		if (alcool_sett==0){
			System.out.println("alcool settimanali nullo, procedo col calcolo " + calorie_sett);
			int apportoAlcool = managerMacroSettimanali.calcolaApportoGrammiAlcoolSettimanale(macro);
			macro.setAlcool_sett(apportoAlcool);
			System.out.println("apporto alcool calcolato in " + apportoAlcool);
		}else if (calorie_sett==0){
			//da calcolare calorie totali			
			int calTotali = managerMacroSettimanali.calcolaApportoTotaleConAlcool(macro);
			System.out.println("calorie totali non specificate, procedo col calcolo... calcolate in " + calTotali);
			macro.setCalorie_sett(calTotali);
		}else{
			//controllo se i dati corrispondono
			System.out.println("apporto alcool già immesso " + alcool_sett);
			boolean datiAttendibili = managerMacroSettimanali.verificaVeridicitaDatiCalorieConAlcool(macro);
			if (!datiAttendibili){
				System.out.println("apporto alcool errato o dati non attendibili " + alcool_sett);
				return "Dati immessi non sono attendibili, il calcolo dei singoli macro non corrisponde alle calorie totali";
			}
			
			
		}
		insert(macro);
		
		return null;
		
	}
	
	
	@Override
	public List<MacroSettimanali> getByDate(Date data) {
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



	@Override
	public Date getLastDate() {
		return macroSettimanalimapper.getLastDate();
	}

	@Override
	public String update(Integer id, Date data, Integer calorie_sett,
			Integer carboidrati_sett, Integer proteine_sett,
			Integer grassi_sett, Integer alcool_sett) {
		// TODO Auto-generated method stub
		
		MacroSettimanali macoToUpdate = new MacroSettimanali(id, data, calorie_sett, carboidrati_sett, proteine_sett, grassi_sett, alcool_sett);
		//controllo tipo di calcolo da effettuare
				//se manca alcool o cal totali
				if (alcool_sett==0){
					System.out.println("alcool settimanali nullo, procedo col calcolo " + calorie_sett);
					int apportoAlcool = managerMacroSettimanali.calcolaApportoGrammiAlcoolSettimanale(macoToUpdate);
					macoToUpdate.setAlcool_sett(apportoAlcool);
					System.out.println("apporto alcool calcolato in " + apportoAlcool);
				}else if (calorie_sett==0){
					//da calcolare calorie totali			
					int calTotali = managerMacroSettimanali.calcolaApportoTotaleConAlcool(macoToUpdate);
					System.out.println("calorie totali non specificate, procedo col calcolo... calcolate in " + calTotali);
					macoToUpdate.setCalorie_sett(calTotali);
				}else{
					//controllo se i dati corrispondono
					System.out.println("apporto alcool già immesso " + alcool_sett);
					boolean datiAttendibili = managerMacroSettimanali.verificaVeridicitaDatiCalorieConAlcool(macoToUpdate);
					if (!datiAttendibili){
						System.out.println("apporto alcool errato o dati non attendibili " + alcool_sett);
						return "Dati immessi non sono attendibili, il calcolo dei singoli macro non corrisponde alle calorie totali";
					}
					
					
				}
				update(macoToUpdate);
		return null;//tutto ok
	}



	
	
	
	
}
