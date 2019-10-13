package com.vbmeo.evolution2.model;


public class GraficiDaFare {

	
	String tipoDiGrafico;
	String dataDa;
	String dataA;
	public String getTipoDiGrafico() {
		return tipoDiGrafico;
	}
	public void setTipoDiGrafico(String tipoDiGrafico) {
		this.tipoDiGrafico = tipoDiGrafico;
	}
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
	public GraficiDaFare(String tipoDiGrafico, String dataDa, String dataA) {
		super();
		this.tipoDiGrafico = tipoDiGrafico;
		this.dataDa = dataDa;
		this.dataA = dataA;
	}
	public GraficiDaFare() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
}
