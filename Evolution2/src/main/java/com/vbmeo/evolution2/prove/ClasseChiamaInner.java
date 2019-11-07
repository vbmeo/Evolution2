package com.vbmeo.evolution2.prove;


public class ClasseChiamaInner {
	ClasseEsterna mo = new ClasseEsterna ();
	//così si instanzia una classe interna inner class
    ClasseEsterna.InnerClass inner = mo.new InnerClass();
    
    
    public void usaInner() {
    	inner.stampaX();
    }
    
    
    
}