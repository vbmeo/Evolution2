package com.vbmeo.evolution2.service;

/**
 * esempio metodo default in interfaccia
 * @author vbmeo
 *
 */
public interface InterfacciaProvaConDefault {

	default int getNumeroPiuBassoIntero(int numero) {
		return 0;
	}
	
	int metodoNonDefault();
}
