package com.vbmeo.evolution2.model;

import java.sql.Date;
import java.util.ArrayList;

public class RigaGraficoCustom {

	String nomeDellaRiga; //il nome dato alla riga richioesta, es. calorie ombelico ecc
	String[] arrayValori ;
	Date[] arrayDateSingliValori;
	String dataDa;
	String dataA;
	long coefficienteDiMoltiplicazioneDeiDati; //indica di quanto sono stati moltiplicati i dati per renderli compatibili nel grafico
	
	public String getDataDa() {
		return dataDa;
	}

	public void setDataDa(String dataDa) {
		this.dataDa = dataDa;
	}

	public String getDataA() {
		return dataA;
	}

	public void setDataA(String dataA) {
		this.dataA = dataA;
	}

	

	public RigaGraficoCustom() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public String getNomeDellaRiga() {
		return nomeDellaRiga;
	}

	public void setNomeDellaRiga(String nomeDellaRiga) {
		this.nomeDellaRiga = nomeDellaRiga;
	}

	public RigaGraficoCustom(Float[] valori, String nomeDellaRiga, String dataDa, String dataA,Date[] listaDateSingliValori) {
		String[] arrayValori = new String[valori.length];
		
		for (int i = 0; i < valori.length; i++) { 
			if (valori[i]!=null)
				arrayValori[i]=(Float.toString(valori[i]));
		}
		
		this.nomeDellaRiga = nomeDellaRiga;
		this.dataDa = dataDa;
		this.dataA = dataA;
		this.arrayValori= arrayValori;
		this.arrayDateSingliValori = listaDateSingliValori;
		
	}

	public long getCoefficienteDiMoltiplicazioneDeiDati() {
		return coefficienteDiMoltiplicazioneDeiDati;
	}

	public void setCoefficienteDiMoltiplicazioneDeiDati(long coefficienteDiMoltiplicazioneDeiDati) {
		this.coefficienteDiMoltiplicazioneDeiDati = coefficienteDiMoltiplicazioneDeiDati;
	}

	public String[] getArrayValori() {
		return arrayValori;
	}

	public void setArrayValori(String[] arrayValori) {
		this.arrayValori = arrayValori;
	}

	public Date[] getArrayDateSingliValori() {
		return arrayDateSingliValori;
	}

	public void setArrayDateSingliValori(Date[] arrayDateSingliValori) {
		this.arrayDateSingliValori = arrayDateSingliValori;
	}

	
	
}
