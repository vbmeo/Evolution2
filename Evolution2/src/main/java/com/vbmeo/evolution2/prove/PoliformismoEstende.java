package com.vbmeo.evolution2.prove;

import com.vbmeo.evolution2.model.Attivita;

public class PoliformismoEstende extends PoliformismoBase {

//non si pu� modificare perch� final metodo in base
//	public final int nonMiModifichi (int a) {
//		return a*2;
//	}
	
	//questo metodo verr� sovrascritto dal chiamante
	@Override
	public int metodoSommaOSottrae(int i, int j) {
		return i-j;
    }
	
	//questo cambia il tirono con una sottoclasse del ritorno che c'� nella base
	@Override
	public PoliformismoEstende ritornoCambia () {
		return new PoliformismoEstende();
    }
	
	public void metodoSoleEstende() {
		
	}
	
	
}
