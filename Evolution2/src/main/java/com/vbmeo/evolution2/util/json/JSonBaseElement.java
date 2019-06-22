package com.vbmeo.evolution2.util.json;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class JSonBaseElement {

	@JsonIgnore
	private String codiceFiscaleAssistito;
	@JsonIgnore
	private String codiceFiscaleMMG;

	public String getCodiceFiscaleAssistito() {
		return codiceFiscaleAssistito;
	}

	public void setCodiceFiscaleAssistito(String codiceFiscaleAssistito) {
		this.codiceFiscaleAssistito = codiceFiscaleAssistito;
	}

	public String getCodiceFiscaleMMG() {
		return codiceFiscaleMMG;
	}

	public void setCodiceFiscaleMMG(String codiceFiscaleMMG) {
		this.codiceFiscaleMMG = codiceFiscaleMMG;
	}
}
