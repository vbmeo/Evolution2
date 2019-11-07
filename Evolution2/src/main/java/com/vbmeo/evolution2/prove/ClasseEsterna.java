package com.vbmeo.evolution2.prove;

public class ClasseEsterna {
	private int x = 9;
	
	
	//isantanza di inner dalla stessa classe è diretta
	public void creaInnerClass() {
		InnerClass in = new InnerClass ();
		in.stampaX();
	}
	
	

	class InnerClass
	{
		public void stampaX()
		{
			//l’Inner Class è in grado di accedere ai membri dell’Outer Class
			System.out.println("Il valore di x è " + x);
			//questo this si riferisce a inner
			System.out.println("Il riferimento della Inner Class " + this);
			//questo this si riferisce a outerclass classe esterna
            System.out.println("Il riferimento della Outer Class " + ClasseEsterna.this);
		}
	}
	
	
	
	
	
	
	//------------------ METODO CHE CONTIENE INNER CLASS---------------------
//	Una Inner Class Method-Local può essere istanziata solo all’interno del metodo dove è definita. 
//	Nessun altro codice che gira all’interno o all’esterno dell’Outer Class può istanziare l’Inner Class.
	//TUTTO CIò CHE è QUI DENTRO NON E' visibile all'esterno, nemmeno alla outer che la contiene, parlo di variabili 
	// e classe ecc
	public void metodoDiTest()
    {
		//PRATICAMENTE:
		//IL METODO DICHIARA UNA CLASSE
		//POI ESEGUE IL CONTENUTO INSTANZIANDOLA
		 
		 String z = "variabile locale";//questa variabile non può essere vista dalla classeInterna
		 //a meno che non sia  abstract e final.
		 
           class ClasseInterna
           {
                 public void stampaX()
                  {
                         System.out.println("Il valore di x è " + x);
                         //System.out.println("Il valore di x è " + z);
                  }
           }  
           
         //NECESSARIO INSTANZIARLA AL SUO INTERNO DOPO PERO' AVERLA DICHIARATA PER ESEGUIRE IL CODICE       
   		ClasseInterna mi = new ClasseInterna ();
        mi.stampaX();
           
    }
	
	
	
	
	//-----------------Anonymous Inner Classes------------------------------
	//praticamente una classe che viene ridefinita nel suo metodo
	class A //non ha ()
	{
	       public void stampa()
	       {
	             System.out.println("A");
	       }
	}
	
	class B//non ha ()
	{
		
		//La classe anonima può essere vista come una sottoclasse di A che esegue un overriding del metodo stampa().
		//questa non è una istanza (non finisce con ; un po' cone js) ma ha le graffe per crearla direttamente
	       A a = new A()
	       {
	             public void stampa()
	             {
	                    System.out.println("anonymous a");
	             }
	       };//nota il ; di chiusura com js
	      
	}
	
	
	
}
