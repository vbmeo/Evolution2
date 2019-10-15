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
import com.vbmeo.evolution2.model.MacroSettimanali;
import com.vbmeo.evolution2.model.Misure;
import com.vbmeo.evolution2.model.RigaGraficoCustom;
import com.vbmeo.evolution2.model.TipiDiGrafici;
import com.vbmeo.evolution2.model.TipiDiGrafici.Tipo;
import com.vbmeo.evolution2.service.CharCustomService;
import com.vbmeo.evolution2.service.CharCustomServiceImpl;
import com.vbmeo.evolution2.util.MyUtil;

public class ManagerGeneral {

	private static final Logger logger = LoggerFactory.getLogger(ManagerGeneral.class);

	
	@Autowired
	CharCustomService charCustomService;
	
	public RigaGraficoCustom[] interrogaDbPerGrafici(GraficiDaFare[] grafici,Date dataPrecedente, Date dataSuccessiva) {
		Date dataFineIndicataInArray;
		//non posso sviluppare subito un array perche grafici contiene anche tag non buoni per richieste grafici come i pulsanti, quindi devo
		//fare prima una lista
		List<RigaGraficoCustom> listDiTuttiIRisultati = new ArrayList<RigaGraficoCustom>();
		Integer contatoreGrafici=0;
		
			//faccio prima le interrogazioni base che possono servire per non ripeterle ad ogni richiesta:
			List<Misure> misure = charCustomService.getMisureTraDueDateCheCompaionoInMacro(dataPrecedente,dataSuccessiva);
			List<MacroSettimanali> macro = charCustomService.getAllFromTwoDate(dataPrecedente,dataSuccessiva);
			//giro array grafici
			for (GraficiDaFare graficoDaFare : grafici) {
				//solo quelli trovati che corrispondono al title dei tag nel sito html generano grafici
				//e devono corrispondere all'enum citato
				if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Attività_a_stomaco_pieno_calorie.toString())) {					
					List<Attivita> attivita = charCustomService.getDispendiEnergeticiEOreSettimanaliNonAVuotoTraDueDate(dataPrecedente,dataSuccessiva);					
					inserisciDatiDispendiEnergeticiInArrayDaListaAttivita(attivita,listDiTuttiIRisultati,contatoreGrafici
							,graficoDaFare.getDataDa(),graficoDaFare.getDataA(),TipiDiGrafici.Tipo.Attività_a_stomaco_pieno_calorie.toString()); 					
					contatoreGrafici++;//va qui, non alla fine del ciclo perchè il ciclo comprende anche tag che non danno risultati, che non riguardano i grafici da fare
				}else if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Attività_a_stomaco_pieno_ore.toString())) {
					 List<Attivita> attivita = charCustomService.getDispendiEnergeticiEOreSettimanaliNonAVuotoTraDueDate(dataPrecedente,dataSuccessiva);
					 inserisciDatiOreInArrayDaListaAttivita(attivita,listDiTuttiIRisultati,contatoreGrafici
								,graficoDaFare.getDataDa(),graficoDaFare.getDataA(),TipiDiGrafici.Tipo.Attività_a_stomaco_pieno_ore.toString()); 	
					 contatoreGrafici++;//va qui, non alla fine del ciclo perchè il ciclo comprende anche tag che non danno risultati, che non riguardano i grafici da fare
				}else if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Alcool.toString())) {
					inserisciDatiAlcoolArrayDaListaMacro(macro, listDiTuttiIRisultati, contatoreGrafici, 
							graficoDaFare.getDataDa(),graficoDaFare.getDataA(), TipiDiGrafici.Tipo.Alcool.toString());
					contatoreGrafici++;//va qui, non alla fine del ciclo perchè il ciclo comprende anche tag che non danno risultati, che non riguardano i grafici da fare				
				}else if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Alcool_e_grassi.toString())) {
					inserisciDatiAlcooEGrassilArrayDaListaMacro(macro, listDiTuttiIRisultati, contatoreGrafici, 
							graficoDaFare.getDataDa(),graficoDaFare.getDataA(), TipiDiGrafici.Tipo.Alcool_e_grassi.toString());
					contatoreGrafici++;//va qui, non alla fine del ciclo perchè il ciclo comprende anche tag che non danno risultati, che non riguardano i grafici da fare					
				}else if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Attività_a_stomaco_vuoto_calorie.toString())) {
					List<Attivita> attivita = charCustomService.getDispendiEnergeticiEOreSettimanaliAVuotoTraDueDate(dataPrecedente,dataSuccessiva);					
					inserisciDatiDispendiEnergeticiInArrayDaListaAttivita(attivita,listDiTuttiIRisultati,contatoreGrafici
							,graficoDaFare.getDataDa(),graficoDaFare.getDataA(),TipiDiGrafici.Tipo.Attività_a_stomaco_vuoto_calorie.toString()); 					
					contatoreGrafici++;//va qui, non alla fine del ciclo perchè il ciclo comprende anche tag che non danno risultati, che non riguardano i grafici da fare
				}else if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Attività_a_stomaco_vuoto_ore.toString())) {
					List<Attivita> attivita = charCustomService.getDispendiEnergeticiEOreSettimanaliAVuotoTraDueDate(dataPrecedente,dataSuccessiva);					
					inserisciDatiOreInArrayDaListaAttivita(attivita,listDiTuttiIRisultati,contatoreGrafici
							,graficoDaFare.getDataDa(),graficoDaFare.getDataA(),TipiDiGrafici.Tipo.Attività_a_stomaco_vuoto_ore.toString()); 					
					contatoreGrafici++;//va qui, non alla fine del ciclo perchè il ciclo comprende anche tag che non danno risultati, che non riguardano i grafici da fare
				}else if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Attività_aerobiche_calorie.toString())) {
					List<Attivita> attivita = charCustomService.getDispendiEnergeticiEOreSettimanaliAerobiciTraDueDate(dataPrecedente,dataSuccessiva);					
					inserisciDatiDispendiEnergeticiInArrayDaListaAttivita(attivita,listDiTuttiIRisultati,contatoreGrafici
							,graficoDaFare.getDataDa(),graficoDaFare.getDataA(),TipiDiGrafici.Tipo.Attività_aerobiche_calorie.toString()); 					
					contatoreGrafici++;//va qui, non alla fine del ciclo perchè il ciclo comprende anche tag che non danno risultati, che non riguardano i grafici da fare
				}else if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Attività_aerobiche_ore.toString())) {
					List<Attivita> attivita = charCustomService.getDispendiEnergeticiEOreSettimanaliAerobiciTraDueDate(dataPrecedente,dataSuccessiva);					
					inserisciDatiOreInArrayDaListaAttivita(attivita,listDiTuttiIRisultati,contatoreGrafici
							,graficoDaFare.getDataDa(),graficoDaFare.getDataA(),TipiDiGrafici.Tipo.Attività_aerobiche_ore.toString()); 					
					contatoreGrafici++;//va qui, non alla fine del ciclo perchè il ciclo comprende anche tag che non danno risultati, che non riguardano i grafici da fare
				}else if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Attività_non_airobiche_calorie_bruciate .toString())) {
					List<Attivita> attivita = charCustomService.getDispendiEnergeticiEOreSettimanaliNonAerobiciTraDueDate(dataPrecedente,dataSuccessiva);					
					inserisciDatiDispendiEnergeticiInArrayDaListaAttivita(attivita,listDiTuttiIRisultati,contatoreGrafici
							,graficoDaFare.getDataDa(),graficoDaFare.getDataA(),TipiDiGrafici.Tipo.Attività_non_airobiche_calorie_bruciate.toString()); 					
					contatoreGrafici++;//va qui, non alla fine del ciclo perchè il ciclo comprende anche tag che non danno risultati, che non riguardano i grafici da fare
				}else if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Attività_non_airobiche_ore.toString())) {
					List<Attivita> attivita = charCustomService.getDispendiEnergeticiEOreSettimanaliNonAerobiciTraDueDate(dataPrecedente,dataSuccessiva);					
					inserisciDatiOreInArrayDaListaAttivita(attivita,listDiTuttiIRisultati,contatoreGrafici
							,graficoDaFare.getDataDa(),graficoDaFare.getDataA(),TipiDiGrafici.Tipo.Attività_non_airobiche_ore.toString()); 					
					contatoreGrafici++;//va qui, non alla fine del ciclo perchè il ciclo comprende anche tag che non danno risultati, che non riguardano i grafici da fare
				}else if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Grassi.toString())) {
					inserisciDatiGrassilArrayDaListaMacro(macro,listDiTuttiIRisultati,contatoreGrafici
							,graficoDaFare.getDataDa(),graficoDaFare.getDataA(),TipiDiGrafici.Tipo.Grassi.toString()); 					
					contatoreGrafici++;//va qui, non alla fine del ciclo perchè il ciclo comprende anche tag che non danno risultati, che non riguardano i grafici da fare
				}else if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Calorie.toString())) {
					inserisciDatiCalorieArrayDaListaMacro(macro,listDiTuttiIRisultati,contatoreGrafici
							,graficoDaFare.getDataDa(),graficoDaFare.getDataA(),TipiDiGrafici.Tipo.Calorie.toString()); 					
					contatoreGrafici++;//va qui, non alla fine del ciclo perchè il ciclo comprende anche tag che non danno risultati, che non riguardano i grafici da fare
				}else if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Carbo.toString())) {
					inserisciDatiCarboArrayDaListaMacro(macro,listDiTuttiIRisultati,contatoreGrafici
							,graficoDaFare.getDataDa(),graficoDaFare.getDataA(),TipiDiGrafici.Tipo.Carbo.toString()); 					
					contatoreGrafici++;//va qui, non alla fine del ciclo perchè il ciclo comprende anche tag che non danno risultati, che non riguardano i grafici da fare
				}else if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Proteine.toString())) {
					inserisciDatiProteineArrayDaListaMacro(macro,listDiTuttiIRisultati,contatoreGrafici
							,graficoDaFare.getDataDa(),graficoDaFare.getDataA(),TipiDiGrafici.Tipo.Proteine.toString()); 					
					contatoreGrafici++;//va qui, non alla fine del ciclo perchè il ciclo comprende anche tag che non danno risultati, che non riguardano i grafici da fare
				}else if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Tutte_le_attività_in_calorie.toString())) {
					List<Attivita> attivita = charCustomService.getDispendiEnergeticiEOreSettimanaliTraDueDateDiviseInSettimane(dataPrecedente,dataSuccessiva);
					inserisciDatiDispendiEnergeticiInArrayDaListaAttivita(attivita,listDiTuttiIRisultati,contatoreGrafici
							,graficoDaFare.getDataDa(),graficoDaFare.getDataA(),TipiDiGrafici.Tipo.Tutte_le_attività_in_calorie.toString()); 					
					contatoreGrafici++;
				}else if (graficoDaFare.getTipoDiGrafico().equals(TipiDiGrafici.Tipo.Tutte_le_attività_in_ore.toString())) {
					List<Attivita> attivita = charCustomService.getDispendiEnergeticiEOreSettimanaliTraDueDateDiviseInSettimane(dataPrecedente,dataSuccessiva);
					inserisciDatiOreInArrayDaListaAttivita(attivita,listDiTuttiIRisultati,contatoreGrafici
							,graficoDaFare.getDataDa(),graficoDaFare.getDataA(),TipiDiGrafici.Tipo.Tutte_le_attività_in_ore.toString()); 					
					contatoreGrafici++;
				}else {
					//qui gestisco tutte le richieste di tipo misure,
					//ma entrano anche quelle non gestite che il metodo qui sotto non gestisce e torna indietro senza fare niente
					boolean tipoConfome = inserisciMisuraDaListaMisure(misure,listDiTuttiIRisultati,contatoreGrafici
							,graficoDaFare.getDataDa(),graficoDaFare.getDataA(),graficoDaFare.getTipoDiGrafico()); 					
					if (tipoConfome)
						contatoreGrafici++;
				}
			}//for
			//trasfomra in array la lista dei dati righe
			RigaGraficoCustom[] arrayDiTuttiIRisultati = getArrayFromList(listDiTuttiIRisultati);
			
			Float[] indiceArrayConDatoPiuElevato = getIndiceDellArrayConDatoPiuAlto(arrayDiTuttiIRisultati);
			
			
			modificaDatiPerRenderliCompatibili(arrayDiTuttiIRisultati,indiceArrayConDatoPiuElevato);
			
			
			
			return arrayDiTuttiIRisultati;
	}


	






	@SuppressWarnings("static-access")
	private void modificaDatiPerRenderliCompatibili(RigaGraficoCustom[] arrayDiTuttiIRisultati, Float[] indiceArrayConDatoPiuElevato) {
		//indiceArray con indice dato più elevato e dato più elevato
		for (int conta=0; conta< arrayDiTuttiIRisultati.length; conta++) {
			if (conta==indiceArrayConDatoPiuElevato[0]) {//se uguale all'indice di quello che non è stato trasformato
				//mette 1 come coefficente
				arrayDiTuttiIRisultati[conta].setCoefficienteDiMoltiplicazioneDeiDati(1);
			}else {
				//trova dato più elevato tra questa riga
				Float valorePiuElevato=(float) 0;
				for (String valoreStringa : arrayDiTuttiIRisultati[conta].getArrayValori()) {
					if (MyUtil.convertStringToFloat(valoreStringa)>valorePiuElevato) {
						valorePiuElevato=MyUtil.convertStringToFloat(valoreStringa);
					}
				}
				
				//trova il coefficente di moltiplicazione per renderlo compatibile con quello più elevato tra tutti
				//tagliando i decimali diventa quindi più piccolo
				int coefficente = (int) (indiceArrayConDatoPiuElevato[1]/valorePiuElevato);
				arrayDiTuttiIRisultati[conta].setCoefficienteDiMoltiplicazioneDeiDati(coefficente);
				//moltiplico tutti i dati per il coefficente appena trovato
				for (int conta1=0; conta1<arrayDiTuttiIRisultati[conta].getArrayValori().length; conta1++) {
					float vecchiovalore= (MyUtil.convertStringToFloat(arrayDiTuttiIRisultati[conta].getArrayValori()[conta1]));
					float nuovoValore=(coefficente*vecchiovalore);
					String nuovoValoreString = Float.toString(nuovoValore);
					arrayDiTuttiIRisultati[conta].getArrayValori()[conta1]= nuovoValoreString;
				}
				
				
			}
		}
		
	}



	private RigaGraficoCustom[] getArrayFromList(List<RigaGraficoCustom> listDiTuttiIRisultati) {
		RigaGraficoCustom[] arrayDiTuttiIRisultati = new RigaGraficoCustom[listDiTuttiIRisultati.size()] ;
		Integer contatore=0;
		for (RigaGraficoCustom riga : listDiTuttiIRisultati) {
			arrayDiTuttiIRisultati[contatore]=riga;
			contatore++;
		}
		return arrayDiTuttiIRisultati;
	}



	private void inserisciDatiDispendiEnergeticiInArrayDaListaAttivita(List<Attivita> attivita, List<RigaGraficoCustom> arrayDiTuttiIRisultati,
			Integer contatoreGrafici, String dataDa, String dataA, String tipoDiGrafico) {
		double[] datiAttivita = new  double[attivita.size()];
		Date[] listaDateSingliValori = new Date[attivita.size()];
		for (int i = 0; i < attivita.size(); i++) {
			datiAttivita[i] = attivita.get(i).getDispendio_energetico();	
			listaDateSingliValori[i]= attivita.get(i).getData_fine_settimana();
		}
		//creo oggetto con i valori
		RigaGraficoCustom rigaGraficoCustom = new RigaGraficoCustom(datiAttivita,tipoDiGrafico,dataDa,dataA,listaDateSingliValori);				
		//metto in list globale
		arrayDiTuttiIRisultati.add(rigaGraficoCustom);	
	}
	
	
	private void inserisciDatiOreInArrayDaListaAttivita(List<Attivita> attivita, List<RigaGraficoCustom> arrayDiTuttiIRisultati,
			Integer contatoreGrafici, String dataDa, String dataA, String tipoDiGrafico) {
		double[] datiAttivita = new  double[attivita.size()];
		Date[] listaDateSingliValori = new Date[attivita.size()];
		for (int i = 0; i < attivita.size(); i++) {
			datiAttivita[i] = attivita.get(i).getQuantita_in_ore();		
			listaDateSingliValori[i]= attivita.get(i).getData_fine_settimana();
		}
		//creo oggetto con i valori
		RigaGraficoCustom rigaGraficoCustom = new RigaGraficoCustom(datiAttivita,tipoDiGrafico,dataDa,dataA,listaDateSingliValori);				
		//metto in list globale
		arrayDiTuttiIRisultati.add(rigaGraficoCustom);	
	}


	
	
	private void inserisciDatiAlcoolArrayDaListaMacro(List<MacroSettimanali> macro, List<RigaGraficoCustom> arrayDiTuttiIRisultati,
			Integer contatoreGrafici, String dataDa, String dataA, String tipoDiGrafico) {
		double[] datiAttivita = new  double[macro.size()];
		Date[] listaDateSingliValori = new Date[macro.size()];
		for (int i = 0; i < macro.size(); i++) {
			datiAttivita[i] = macro.get(i).getAlcool_sett();		
			listaDateSingliValori[i]= macro.get(i).getData();
		}
		//creo oggetto con i valori
		RigaGraficoCustom rigaGraficoCustom = new RigaGraficoCustom(datiAttivita,tipoDiGrafico,dataDa,dataA,listaDateSingliValori);				
		//metto in list globale
		arrayDiTuttiIRisultati.add(rigaGraficoCustom);	
	}
	
	
	private void inserisciDatiAlcooEGrassilArrayDaListaMacro(List<MacroSettimanali> macro, List<RigaGraficoCustom> arrayDiTuttiIRisultati,
			Integer contatoreGrafici, String dataDa, String dataA, String tipoDiGrafico) {
		double[] datiAttivita = new  double[macro.size()];
		Date[] listaDateSingliValori = new Date[macro.size()];
		for (int i = 0; i < macro.size(); i++) {
			datiAttivita[i] = macro.get(i).getAlcool_sett()+macro.get(i).getGrassi_sett();		
			listaDateSingliValori[i]= macro.get(i).getData();
		}
		//creo oggetto con i valori
		RigaGraficoCustom rigaGraficoCustom = new RigaGraficoCustom(datiAttivita,tipoDiGrafico,dataDa,dataA,listaDateSingliValori);				
		//metto in list globale
		arrayDiTuttiIRisultati.add(rigaGraficoCustom);	
	}
	
	
	private void inserisciDatiGrassilArrayDaListaMacro(List<MacroSettimanali> macro, List<RigaGraficoCustom> arrayDiTuttiIRisultati,
			Integer contatoreGrafici, String dataDa, String dataA, String tipoDiGrafico) {
		double[] datiAttivita = new  double[macro.size()];
		Date[] listaDateSingliValori = new Date[macro.size()];
		for (int i = 0; i < macro.size(); i++) {
			datiAttivita[i] = macro.get(i).getGrassi_sett();		
			listaDateSingliValori[i]= macro.get(i).getData();
		}
		//creo oggetto con i valori
		RigaGraficoCustom rigaGraficoCustom = new RigaGraficoCustom(datiAttivita,tipoDiGrafico,dataDa,dataA,listaDateSingliValori);				
		//metto in list globale
		arrayDiTuttiIRisultati.add(rigaGraficoCustom);	
	}
	
	
	private void inserisciDatiCarboArrayDaListaMacro(List<MacroSettimanali> macro, List<RigaGraficoCustom> arrayDiTuttiIRisultati,
			Integer contatoreGrafici, String dataDa, String dataA, String tipoDiGrafico) {
		double[] datiAttivita = new  double[macro.size()];
		Date[] listaDateSingliValori = new Date[macro.size()];
		for (int i = 0; i < macro.size(); i++) {
			datiAttivita[i] = macro.get(i).getCarboidrati_sett();		
			listaDateSingliValori[i]= macro.get(i).getData();
		}
		//creo oggetto con i valori
		RigaGraficoCustom rigaGraficoCustom = new RigaGraficoCustom(datiAttivita,tipoDiGrafico,dataDa,dataA,listaDateSingliValori);				
		//metto in list globale
		arrayDiTuttiIRisultati.add(rigaGraficoCustom);	
	}
	
	private void inserisciDatiProteineArrayDaListaMacro(List<MacroSettimanali> macro, List<RigaGraficoCustom> arrayDiTuttiIRisultati,
			Integer contatoreGrafici, String dataDa, String dataA, String tipoDiGrafico) {
		double[] datiAttivita = new  double[macro.size()];
		Date[] listaDateSingliValori = new Date[macro.size()];
		for (int i = 0; i < macro.size(); i++) {
			datiAttivita[i] = macro.get(i).getProteine_sett();		
			listaDateSingliValori[i]= macro.get(i).getData();
		}
		//creo oggetto con i valori
		RigaGraficoCustom rigaGraficoCustom = new RigaGraficoCustom(datiAttivita,tipoDiGrafico,dataDa,dataA,listaDateSingliValori);				
		//metto in list globale
		arrayDiTuttiIRisultati.add(rigaGraficoCustom);	
	}
	
	
	
	
	
	private void inserisciDatiCalorieArrayDaListaMacro(List<MacroSettimanali> macro, List<RigaGraficoCustom> arrayDiTuttiIRisultati,
			Integer contatoreGrafici, String dataDa, String dataA, String tipoDiGrafico) {
		double[] datiAttivita = new  double[macro.size()];
		Date[] listaDateSingliValori = new Date[macro.size()];
		for (int i = 0; i < macro.size(); i++) {
			datiAttivita[i] = macro.get(i).getCalorie_sett();		
			listaDateSingliValori[i]= macro.get(i).getData();
		}
		//creo oggetto con i valori
		RigaGraficoCustom rigaGraficoCustom = new RigaGraficoCustom(datiAttivita,tipoDiGrafico,dataDa,dataA,listaDateSingliValori);				
		//metto in list globale
		arrayDiTuttiIRisultati.add(rigaGraficoCustom);	
	}
/**
 * tratta solo richieste di tipo misure, altrimenti esce con false
 * diventa true se viene trovao il tipo
 * @param tipoDiGrafico
 * @param misure
 * @param listDiTuttiIRisultati
 * @param contatoreGrafici
 * @param dataDa
 * @param dataA
 * @param tipoDiGrafico
 */
	private boolean inserisciMisuraDaListaMisure(List<Misure> misure, List<RigaGraficoCustom> listDiTuttiIRisultati,
			Integer contatoreGrafici, String dataDa, String dataA, String tipoDiGrafico) {
		double[] datiAttivita = new  double[misure.size()];
		Date[] listaDateSingliValori = new Date[misure.size()];
		
		for (int i = 0; i < misure.size(); i++) {
			//String tipo= tipoDirichiestaMacro.toString();
			switch (tipoDiGrafico) {
			case "Ombelico":
				datiAttivita[i] = misure.get(i).getOmbelico();
				break;
			case "Peso":
				datiAttivita[i] = misure.get(i).getPeso();
				break;
			case "Bracio_DX_in_tiro":
				datiAttivita[i] = misure.get(i).getBracciodxintiro();
				break;
			case "Bracio_DX_steso":
				datiAttivita[i] = misure.get(i).getBraccidxstesobraccio();
				break;
			case "Bracio_SX_in_tiro":
				datiAttivita[i] = misure.get(i).getBracciosxintiro();
				break;
			case "Bracio_SX_steso":
				datiAttivita[i] = misure.get(i).getBraccisxstesobraccio();
				break;
			case "Ombelico_mattina_a_vuoto":
				datiAttivita[i] = misure.get(i).getAddome_mattina_a_vuoto();
				break;
			case "Parte_più_sporgente_addome":
				datiAttivita[i] = misure.get(i).getAddomesporgente();
				break;
			case "Peso_mattina_a_vuoto":
				datiAttivita[i] = misure.get(i).getPeso_mattina_a_vuoto();
				break;
			case "Plico_DX_ombelico":
				datiAttivita[i] = misure.get(i).getPlicodxombelico();
				break;
			case "Plico_SX_ombelico":
				datiAttivita[i] = misure.get(i).getPlicosxombelico();
				break;
			case "Plico_fianco_DX":
				datiAttivita[i] = misure.get(i).getPlicofiancodx();
				break;
			case "Plico_fianco_SX":
				datiAttivita[i] = misure.get(i).getPlicofiancosx();
				break;
			case "Plico_medio_DX":
				datiAttivita[i] = misure.get(i).getPlicometadx();
				break;
			case "Plico_medio_SX":
				datiAttivita[i] = misure.get(i).getPlicometasx();
				break;		
			case "Torace_altezza_capezzoli":
				datiAttivita[i] = misure.get(i).getCapezzoli();
				break;
			case "Torace_altezza_massima":
				datiAttivita[i] = misure.get(i).getToracealto();
				break;		
			default:
				return false; //se non è nessuna di queste richieste esce
			}
			
			
			
			
					
			listaDateSingliValori[i]= misure.get(i).getData();
		}
		//creo oggetto con i valori
		RigaGraficoCustom rigaGraficoCustom = new RigaGraficoCustom(datiAttivita,tipoDiGrafico,dataDa,dataA,listaDateSingliValori);				
		//metto in list globale
		listDiTuttiIRisultati.add(rigaGraficoCustom);	
		return true;
	}

	
	
	
/**
 * ritorna indiceArray con indice dato più elevato e dato più elevato
 * @param arrayDiTutteLeRighe
 * @return
 */
	private Float[] getIndiceDellArrayConDatoPiuAlto(RigaGraficoCustom[] arrayDiTutteLeRighe) {
		Float[] arrauDueDati =  new Float[2];
		Float datopiuElevato = (float) 0;
		Float indiceDoveSiTrovaDatoPiuElevato = null;
		for (int conta=0; conta <arrayDiTutteLeRighe.length; conta++) {
			//gira tutte le righe
			if (arrayDiTutteLeRighe[conta].getArrayValori()!=null) {
				for (String valore : arrayDiTutteLeRighe[conta].getArrayValori()) {
					//gira tutti i valori della riga ma sono stringhe
					if (MyUtil.convertStringToFloat(valore)>datopiuElevato) {
						datopiuElevato=MyUtil.convertStringToFloat(valore);
						indiceDoveSiTrovaDatoPiuElevato=(float) conta;
					}
				}
			}
			
		}
		arrauDueDati[0]= indiceDoveSiTrovaDatoPiuElevato;
		arrauDueDati[1]= datopiuElevato;
		return arrauDueDati;
		
	}

	public List<Date> controllaDataDeigraficiERimandaDataPrecedenteSeTuttoOk(GraficiDaFare[] grafici) {
		// basta controllarne una, le altre sono tutte uguali
		for (GraficiDaFare graficoDaFare : grafici) {
			//controllo data 
			Date dataSql;
			Date dataPrecedente;
			Date dataSuccessiva;
			if (MyUtil.controlloDataSql(graficoDaFare.getDataDa())) {
				dataPrecedente= MyUtil.convertDateinSqlDate(graficoDaFare.getDataDa());
				//dataPrecedente = MyUtil.less4WeekToDateSQl(dataSql);
			}else {
				return null;//esce alla prima se tutto ok
			}
			//controllo altra data
			if (MyUtil.controlloDataSql(graficoDaFare.getDataA())) {
				dataSuccessiva= MyUtil.convertDateinSqlDate(graficoDaFare.getDataA());
				//dataSuccessiva = MyUtil.less4WeekToDateSQl(dataSql);
			}else {
				return null;//esce alla prima se tutto ok
			}
			
			
			List<Date> dataDaeA = new ArrayList<Date>(2);
			dataDaeA.add(dataPrecedente);
			dataDaeA.add(dataSuccessiva);
			return dataDaeA;
		}
		return null;
	}
	
	
}
