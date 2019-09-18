package com.vbmeo.evolution2.model;

public class Parametri {

	private Integer id;
	private String nomeParametro;
	private String descrizioneparametro;
	private Float valore;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeParametro() {
		return nomeParametro;
	}
	public void setNomeParametro(String nomeParametro) {
		this.nomeParametro = nomeParametro;
	}
	public String getDescrizioneparametro() {
		return descrizioneparametro;
	}
	public void setDescrizioneparametro(String descrizioneparametro) {
		this.descrizioneparametro = descrizioneparametro;
	}
	public Float getValore() {
		return valore;
	}
	public void setValore(Float valore) {
		this.valore = valore;
	}
	public Parametri(String nomeParametro, String descrizioneparametro, Float valore) {
		super();
		this.nomeParametro = nomeParametro;
		this.descrizioneparametro = descrizioneparametro;
		this.valore = valore;
	}
	public Parametri(Integer id, String nomeParametro, String descrizioneparametro, Float valore) {
		super();
		this.id = id;
		this.nomeParametro = nomeParametro;
		this.descrizioneparametro = descrizioneparametro;
		this.valore = valore;
	}
	public Parametri() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
