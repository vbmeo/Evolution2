package com.vbmeo.evolution2.prove;


public class PoliformismoBase {

	//uno degli aspetti è rendere un metodo pubblico non modificabile ll'esterno ovvero senza overight
	//es:
	public final int nonMiModifichi (int a) {
		return a*2;
	}
	
	//questo metodo verrà sovrascritto dal chiamante
	public int metodoSommaOSottrae(int i, int j) {
		return i+j;
    }
	
	//altro genere di overight 
	//------------ Covariant return types -------------
	//modifica il tipo di ritorno che dev'essere sottoistanza di questa classe
	public PoliformismoBase ritornoCambia() {
		return new PoliformismoBase();
	}
}
