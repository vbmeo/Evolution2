package com.vbmeo.evolution2.manager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vbmeo.evolution2.mapper.AttivitaMapper;
import com.vbmeo.evolution2.mapper.MacroSettimanalimapper;
import com.vbmeo.evolution2.mapper.MisureMapper;
import com.vbmeo.evolution2.model.Attivita;
import com.vbmeo.evolution2.model.GraficiDaFare;
import com.vbmeo.evolution2.model.TipiDiGrafici;
import com.vbmeo.evolution2.util.MyUtil;

public class ManagerGeneral {

	private static final Logger logger = LoggerFactory.getLogger(ManagerGeneral.class);
	@Autowired
	private MacroSettimanalimapper macroSettimanalimapper;
	@Autowired
	private AttivitaMapper attivitaMapper;
	@Autowired
	private MisureMapper misureMapper;
	
	
	
	public List<double[]> interrogaDbPerGrafici(GraficiDaFare[] grafici,Date dataPrecedente) {
		Date dataFineIndicataInArray;
		List<double[]> arrayDiTuttiIRisultati = new ArrayList<>();//teniamo tutto double per comprendere anche loro...
		
			//giro array grafici
			for (GraficiDaFare graficoDaFare : grafici) {
				//solo quelli trovati che corrispondono al title dei tag nel sito html generano grafici
				//e devono corrispondere all'enum citato
				if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Attività_a_stomaco_pieno_calorie.toString())) {
					dataFineIndicataInArray=MyUtil.convertDateinSqlDate(graficoDaFare.getData());
					 List<Attivita> attivita = attivitaMapper.getDispendiEnergeticiSettimanaliNonAVuotoTraDueDate(dataPrecedente,dataFineIndicataInArray);
					//interessa solamente i dati non le date
					double[] datiAttivita = new  double[attivita.size()];
					for (int i = 0; i < attivita.size(); i++) 
						datiAttivita[i] = attivita.get(i).getDispendio_energetico();
					
					//metto in list globale
					arrayDiTuttiIRisultati.add(datiAttivita);
				}else if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Attività_a_stomaco_pieno_ore.toString())) {
					dataFineIndicataInArray=MyUtil.convertDateinSqlDate(graficoDaFare.getData());
					 List<Attivita> attivita = attivitaMapper.getDispendiEnergeticiSettimanaliNonAVuotoTraDueDate(dataPrecedente,dataFineIndicataInArray);
					//interessa solamente i dati non le date
					double[] datiAttivita = new  double[attivita.size()];
					for (int i = 0; i < attivita.size(); i++) 
						datiAttivita[i] = attivita.get(i).getQuantita_in_ore();
					
					//metto in list globale
					arrayDiTuttiIRisultati.add(datiAttivita);
				}
				
				
			}//for
			
			return arrayDiTuttiIRisultati;
	}



	public Date controllaDataDeigraficiERimandaDataPrecedenteSeTuttoOk(GraficiDaFare[] grafici) {
		// basta controllarne una, le altre sono tutte uguali
		for (GraficiDaFare graficoDaFare : grafici) {
			//controllo data 
			Date dataSql;
			if (MyUtil.controlloDataSql(graficoDaFare.getData())) {
				dataSql= MyUtil.convertDateinSqlDate(graficoDaFare.getData());
				Date dataPrecedente = MyUtil.less4WeekToDateSQl(dataSql);
				return dataPrecedente;		
			}
				
			return null;//esce alla prima se tutto ok
		}
		return null;
	}
	
	
}
