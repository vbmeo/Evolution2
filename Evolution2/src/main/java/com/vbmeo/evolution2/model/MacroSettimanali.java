package com.vbmeo.evolution2.model;

import java.util.Date;

public class MacroSettimanali {
	  private Integer id;
	  private Date data;
	  private Integer calorie_sett;
	  private Integer carboidrati_sett;
	  private Integer proteine_sett;
	  private Integer grassi_sett;
	  private Integer alcool_sett;
	  
	  
	  
	  
	  
	public MacroSettimanali() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Integer getCalorie_sett() {
		return calorie_sett;
	}
	public void setCalorie_sett(Integer calorie_sett) {
		this.calorie_sett = calorie_sett;
	}
	public Integer getCarboidrati_sett() {
		return carboidrati_sett;
	}
	public void setCarboidrati_sett(Integer carboidrati_sett) {
		this.carboidrati_sett = carboidrati_sett;
	}
	public Integer getProteine_sett() {
		return proteine_sett;
	}
	public void setProteine_sett(Integer proteine_sett) {
		this.proteine_sett = proteine_sett;
	}
	public Integer getGrassi_sett() {
		return grassi_sett;
	}
	public void setGrassi_sett(Integer grassi_sett) {
		this.grassi_sett = grassi_sett;
	}
	public Integer getAlcool_sett() {
		return alcool_sett;
	}
	public void setAlcool_sett(Integer alcool_sett) {
		this.alcool_sett = alcool_sett;
	}
	
	
	public MacroSettimanali(Integer id, Date data, Integer calorie_sett,
			Integer carboidrati_sett, Integer proteine_sett,
			Integer grassi_sett, Integer alcool_sett) {
		super();
		this.id = id;
		this.data = data;
		this.calorie_sett = calorie_sett;
		this.carboidrati_sett = carboidrati_sett;
		this.proteine_sett = proteine_sett;
		this.grassi_sett = grassi_sett;
		this.alcool_sett = alcool_sett;
	}
	  
	  
}
