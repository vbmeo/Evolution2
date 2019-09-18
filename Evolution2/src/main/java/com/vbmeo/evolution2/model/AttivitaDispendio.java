package com.vbmeo.evolution2.model;

public class AttivitaDispendio {
	
	 private Integer id;
	 private String nome_attivita;
	 private Integer dispedio_energetico_orario;
	 
	 
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome_attivita() {
		return nome_attivita;
	}
	public void setNome_attivita(String nome_attivita) {
		this.nome_attivita = nome_attivita;
	}
	
	public Integer getDispedio_energetico_orario() {
		return dispedio_energetico_orario;
	}
	public void setDispedio_energetico_orario(Integer dispedio_energetico_orario) {
		this.dispedio_energetico_orario = dispedio_energetico_orario;
	}
	public AttivitaDispendio() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AttivitaDispendio(Integer id, String nome_attivita, Integer dispedio_energetico_orario) {
		super();
		this.id = id;
		this.nome_attivita = nome_attivita;
		this.dispedio_energetico_orario = dispedio_energetico_orario;
	}
	public AttivitaDispendio(String nome_attivita, Integer dispedio_energetico_orario) {
		super();
		this.nome_attivita = nome_attivita;
		this.dispedio_energetico_orario = dispedio_energetico_orario;
	}
	 
	 
}
