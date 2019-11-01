package com.vbmeo.evolution2.model;

import java.sql.Date;

public class Attivita {
	
	 private Integer id;
	 private Integer id_attivita;
	 private Date data;
	 private Date data_fine_settimana;
	 private Float quantita_in_ore;
	 private String nome_attivita;//recuperato in join con chuive esterna da tabella attivita_fisiche_dispendio
	 private Integer dispendio_energetico;
	 private String note;
	 private boolean a_vuoto;
	 
	 
	 
	 
	public boolean isA_vuoto() {
		return a_vuoto;
	}



	public void setA_vuoto(boolean a_vuoto) {
		this.a_vuoto = a_vuoto;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getId_attivita() {
		return id_attivita;
	}



	public void setId_attivita(Integer id_attivita) {
		this.id_attivita = id_attivita;
	}



	public Date getData() {
		return data;
	}



	public void setData(Date data) {
		this.data = data;
	}



	public Date getData_fine_settimana() {
		return data_fine_settimana;
	}



	public void setData_fine_settimana(Date data_fine_settimana) {
		this.data_fine_settimana = data_fine_settimana;
	}



	public Float getQuantita_in_ore() {
		return quantita_in_ore;
	}



	public void setQuantita_in_ore(Float quantita_in_ore) {
		this.quantita_in_ore = quantita_in_ore;
	}



	public String getNome_attivita() {
		return nome_attivita;
	}



	public void setNome_attivita(String nome_attivita) {
		this.nome_attivita = nome_attivita;
	}



	public Integer getDispendio_energetico() {
		return dispendio_energetico;
	}



	public void setDispendio_energetico(Integer dispendio_energetico) {
		this.dispendio_energetico = dispendio_energetico;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}



	public Attivita() {
		super();
		// TODO Auto-generated constructor stub
	}





	public Attivita(Integer id, Integer id_attivita, Date data, Date data_fine_settimana, float quantita_in_ore,
			Integer dispendio_energetico, String note, boolean a_vuoto) {
		super();
		this.id = id;
		this.id_attivita = id_attivita;
		this.data = data;
		this.data_fine_settimana = data_fine_settimana;
		this.quantita_in_ore = quantita_in_ore;
		this.dispendio_energetico = dispendio_energetico;
		this.note = note;
		this.a_vuoto = a_vuoto;
	}



	public Attivita(Integer id_attivita, Date data, Date data_fine_settimana, float quantita_in_ore,
			Integer dispendio_energetico, String note, boolean a_vuoto) {
		super();
		this.id_attivita = id_attivita;
		this.data = data;
		this.data_fine_settimana = data_fine_settimana;
		this.quantita_in_ore = quantita_in_ore;
		this.dispendio_energetico = dispendio_energetico;
		this.note = note;
		this.a_vuoto = a_vuoto;
	}
	 
	 
	

}
