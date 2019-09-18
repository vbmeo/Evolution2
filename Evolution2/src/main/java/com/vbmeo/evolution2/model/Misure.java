package com.vbmeo.evolution2.model;

import java.sql.Date;

public class Misure {
	  private Integer id;
	  private Date data;
	  private float altezza;
	  private float peso;
	  private float ombelico;
	  private float addomesporgente;
	  private float capezzoli;
	  private float toracealto;
	  private float braccisxstesobraccio;
	  private float braccidxstesobraccio;
	  private float bracciosxintiro;
	  private float bracciodxintiro;
	  private float collo;
	  private float plicosxombelico;
	  private float plicodxombelico;
	  private float plicometasx;
	  private float plicometadx;
	  private float plicofiancosx;
	  private float plicofiancodx;
	  private float peso_mattina_a_vuoto;
	  private float addome_mattina_a_vuoto;
	  
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
	public float getAltezza() {
		return altezza;
	}
	public void setAltezza(float altezza) {
		this.altezza = altezza;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public float getOmbelico() {
		return ombelico;
	}
	public void setOmbelico(float ombelico) {
		this.ombelico = ombelico;
	}
	public float getAddomesporgente() {
		return addomesporgente;
	}
	public void setAddomesporgente(float addomesporgente) {
		this.addomesporgente = addomesporgente;
	}
	public float getCapezzoli() {
		return capezzoli;
	}
	public void setCapezzoli(float capezzoli) {
		this.capezzoli = capezzoli;
	}
	public float getToracealto() {
		return toracealto;
	}
	public void setToracealto(float toracealto) {
		this.toracealto = toracealto;
	}
	public float getBraccisxstesobraccio() {
		return braccisxstesobraccio;
	}
	public void setBraccisxstesobraccio(float braccisxstesobraccio) {
		this.braccisxstesobraccio = braccisxstesobraccio;
	}
	public float getBraccidxstesobraccio() {
		return braccidxstesobraccio;
	}
	public void setBraccidxstesobraccio(float braccidxstesobraccio) {
		this.braccidxstesobraccio = braccidxstesobraccio;
	}
	public float getBracciosxintiro() {
		return bracciosxintiro;
	}
	public void setBracciosxintiro(float bracciosxintiro) {
		this.bracciosxintiro = bracciosxintiro;
	}
	public float getBracciodxintiro() {
		return bracciodxintiro;
	}
	public void setBracciodxintiro(float bracciodxintiro) {
		this.bracciodxintiro = bracciodxintiro;
	}
	public float getCollo() {
		return collo;
	}
	public void setCollo(float collo) {
		this.collo = collo;
	}
	public float getPlicosxombelico() {
		return plicosxombelico;
	}
	public void setPlicosxombelico(float plicosxombelico) {
		this.plicosxombelico = plicosxombelico;
	}
	public float getPlicodxombelico() {
		return plicodxombelico;
	}
	public void setPlicodxombelico(float plicodxombelico) {
		this.plicodxombelico = plicodxombelico;
	}
	public float getPlicometasx() {
		return plicometasx;
	}
	public void setPlicometasx(float plicometasx) {
		this.plicometasx = plicometasx;
	}
	public float getPlicometadx() {
		return plicometadx;
	}
	public void setPlicometadx(float plicometadx) {
		this.plicometadx = plicometadx;
	}
	public float getPlicofiancosx() {
		return plicofiancosx;
	}
	public void setPlicofiancosx(float plicofiancosx) {
		this.plicofiancosx = plicofiancosx;
	}
	public float getPlicofiancodx() {
		return plicofiancodx;
	}
	public void setPlicofiancodx(float plicofiancodx) {
		this.plicofiancodx = plicofiancodx;
	}
	public float getPeso_mattina_a_vuoto() {
		return peso_mattina_a_vuoto;
	}
	public void setPeso_mattina_a_vuoto(float peso_mattina_a_vuoto) {
		this.peso_mattina_a_vuoto = peso_mattina_a_vuoto;
	}
	public float getAddome_mattina_a_vuoto() {
		return addome_mattina_a_vuoto;
	}
	public void setAddome_mattina_a_vuoto(float addome_mattina_a_vuoto) {
		this.addome_mattina_a_vuoto = addome_mattina_a_vuoto;
	}
	public Misure(Integer id, Date data, float altezza, float peso, float ombelico, float addomesporgente,
			float capezzoli, float toracealto, float braccisxstesobraccio, float braccidxstesobraccio,
			float bracciosxintiro, float bracciodxintiro, float collo, float plicosxombelico, float plicodxombelico,
			float plicometasx, float plicometadx, float plicofiancosx, float plicofiancodx, float peso_mattina_a_vuoto,
			float addome_mattina_a_vuoto) {
		super();
		this.id = id;
		this.data = data;
		this.altezza = altezza;
		this.peso = peso;
		this.ombelico = ombelico;
		this.addomesporgente = addomesporgente;
		this.capezzoli = capezzoli;
		this.toracealto = toracealto;
		this.braccisxstesobraccio = braccisxstesobraccio;
		this.braccidxstesobraccio = braccidxstesobraccio;
		this.bracciosxintiro = bracciosxintiro;
		this.bracciodxintiro = bracciodxintiro;
		this.collo = collo;
		this.plicosxombelico = plicosxombelico;
		this.plicodxombelico = plicodxombelico;
		this.plicometasx = plicometasx;
		this.plicometadx = plicometadx;
		this.plicofiancosx = plicofiancosx;
		this.plicofiancodx = plicofiancodx;
		this.peso_mattina_a_vuoto = peso_mattina_a_vuoto;
		this.addome_mattina_a_vuoto = addome_mattina_a_vuoto;
	}
	public Misure(Date data, float altezza, float peso, float ombelico, float addomesporgente, float capezzoli,
			float toracealto, float braccisxstesobraccio, float braccidxstesobraccio, float bracciosxintiro,
			float bracciodxintiro, float collo, float plicosxombelico, float plicodxombelico, float plicometasx,
			float plicometadx, float plicofiancosx, float plicofiancodx, float peso_mattina_a_vuoto,
			float addome_mattina_a_vuoto) {
		super();
		this.data = data;
		this.altezza = altezza;
		this.peso = peso;
		this.ombelico = ombelico;
		this.addomesporgente = addomesporgente;
		this.capezzoli = capezzoli;
		this.toracealto = toracealto;
		this.braccisxstesobraccio = braccisxstesobraccio;
		this.braccidxstesobraccio = braccidxstesobraccio;
		this.bracciosxintiro = bracciosxintiro;
		this.bracciodxintiro = bracciodxintiro;
		this.collo = collo;
		this.plicosxombelico = plicosxombelico;
		this.plicodxombelico = plicodxombelico;
		this.plicometasx = plicometasx;
		this.plicometadx = plicometadx;
		this.plicofiancosx = plicofiancosx;
		this.plicofiancodx = plicofiancodx;
		this.peso_mattina_a_vuoto = peso_mattina_a_vuoto;
		this.addome_mattina_a_vuoto = addome_mattina_a_vuoto;
	}
	
	public Misure() {
		super();
		// TODO Auto-generated constructor stub
	}
	  
	  
	  
}
