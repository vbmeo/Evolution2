package com.vbmeo.evolution2.model;

import java.sql.Date;

public class Misure {
	  private Integer id;
	  private Date data;
	  private Float altezza;
	  private Float peso;
	  private Float ombelico;
	  private Float addomesporgente;
	  private Float capezzoli;
	  private Float toracealto;
	  private Float braccisxstesobraccio;
	  private Float braccidxstesobraccio;
	  private Float bracciosxintiro;
	  private Float bracciodxintiro;
	  private Float collo;
	  private Float plicosxombelico;
	  private Float plicodxombelico;
	  private Float plicometasx;
	  private Float plicometadx;
	  private Float plicofiancosx;
	  private Float plicofiancodx;
	  private Float peso_mattina_a_vuoto;
	  private Float addome_mattina_a_vuoto;
	  
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
	public Float getAltezza() {
		return altezza;
	}
	public void setAltezza(Float altezza) {
		this.altezza = altezza;
	}
	public Float getPeso() {
		return peso;
	}
	public void setPeso(Float peso) {
		this.peso = peso;
	}
	public Float getOmbelico() {
		return ombelico;
	}
	public void setOmbelico(Float ombelico) {
		this.ombelico = ombelico;
	}
	public Float getAddomesporgente() {
		return addomesporgente;
	}
	public void setAddomesporgente(Float addomesporgente) {
		this.addomesporgente = addomesporgente;
	}
	public Float getCapezzoli() {
		return capezzoli;
	}
	public void setCapezzoli(Float capezzoli) {
		this.capezzoli = capezzoli;
	}
	public Float getToracealto() {
		return toracealto;
	}
	public void setToracealto(Float toracealto) {
		this.toracealto = toracealto;
	}
	public Float getBraccisxstesobraccio() {
		return braccisxstesobraccio;
	}
	public void setBraccisxstesobraccio(Float braccisxstesobraccio) {
		this.braccisxstesobraccio = braccisxstesobraccio;
	}
	public Float getBraccidxstesobraccio() {
		return braccidxstesobraccio;
	}
	public void setBraccidxstesobraccio(Float braccidxstesobraccio) {
		this.braccidxstesobraccio = braccidxstesobraccio;
	}
	public Float getBracciosxintiro() {
		return bracciosxintiro;
	}
	public void setBracciosxintiro(Float bracciosxintiro) {
		this.bracciosxintiro = bracciosxintiro;
	}
	public Float getBracciodxintiro() {
		return bracciodxintiro;
	}
	public void setBracciodxintiro(Float bracciodxintiro) {
		this.bracciodxintiro = bracciodxintiro;
	}
	public Float getCollo() {
		return collo;
	}
	public void setCollo(Float collo) {
		this.collo = collo;
	}
	public Float getPlicosxombelico() {
		return plicosxombelico;
	}
	public void setPlicosxombelico(Float plicosxombelico) {
		this.plicosxombelico = plicosxombelico;
	}
	public Float getPlicodxombelico() {
		return plicodxombelico;
	}
	public void setPlicodxombelico(Float plicodxombelico) {
		this.plicodxombelico = plicodxombelico;
	}
	public Float getPlicometasx() {
		return plicometasx;
	}
	public void setPlicometasx(Float plicometasx) {
		this.plicometasx = plicometasx;
	}
	public Float getPlicometadx() {
		return plicometadx;
	}
	public void setPlicometadx(Float plicometadx) {
		this.plicometadx = plicometadx;
	}
	public Float getPlicofiancosx() {
		return plicofiancosx;
	}
	public void setPlicofiancosx(Float plicofiancosx) {
		this.plicofiancosx = plicofiancosx;
	}
	public Float getPlicofiancodx() {
		return plicofiancodx;
	}
	public void setPlicofiancodx(Float plicofiancodx) {
		this.plicofiancodx = plicofiancodx;
	}
	public Float getPeso_mattina_a_vuoto() {
		return peso_mattina_a_vuoto;
	}
	public void setPeso_mattina_a_vuoto(Float peso_mattina_a_vuoto) {
		this.peso_mattina_a_vuoto = peso_mattina_a_vuoto;
	}
	public Float getAddome_mattina_a_vuoto() {
		return addome_mattina_a_vuoto;
	}
	public void setAddome_mattina_a_vuoto(Float addome_mattina_a_vuoto) {
		this.addome_mattina_a_vuoto = addome_mattina_a_vuoto;
	}
	public Misure(Integer id, Date data, Float altezza, Float peso, Float ombelico, Float addomesporgente,
			Float capezzoli, Float toracealto, Float braccisxstesobraccio, Float braccidxstesobraccio,
			Float bracciosxintiro, Float bracciodxintiro, Float collo, Float plicosxombelico, Float plicodxombelico,
			Float plicometasx, Float plicometadx, Float plicofiancosx, Float plicofiancodx, Float peso_mattina_a_vuoto,
			Float addome_mattina_a_vuoto) {
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
	public Misure(Date data, Float altezza, Float peso, Float ombelico, Float addomesporgente, Float capezzoli,
			Float toracealto, Float braccisxstesobraccio, Float braccidxstesobraccio, Float bracciosxintiro,
			Float bracciodxintiro, Float collo, Float plicosxombelico, Float plicodxombelico, Float plicometasx,
			Float plicometadx, Float plicofiancosx, Float plicofiancodx, Float peso_mattina_a_vuoto,
			Float addome_mattina_a_vuoto) {
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
