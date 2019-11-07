package com.vbmeo.evolution2.prove;

public class PoliformismoProva {

	PoliformismoBase base = new PoliformismoBase();//chiama il metodo della base
	PoliformismoEstende estende = new PoliformismoEstende();//chiama il metodo della l'estensione
	
	//dichiarato come base ma nuovo estende
	//quindi fa riferimento al metodo di base che però viene sovrascritto da estensione estende
	//quindi prende quello di estende
	//quindi fa sempre riferimento alla base e poi sovrascrive
	//se si chiamasse un metodo di estende darebbe errore perchè non c'è in base
	PoliformismoBase estendeBase = new PoliformismoEstende();//chiama il metodo della estensione
	
	
	public int testBase () {
		int c;
		c = base.metodoSommaOSottrae(5, 7);
		return c;
	}
	
	public int testEstesa () {
		int c;
		c = estende.metodoSommaOSottrae(5, 7);
		estende.metodoSoleEstende();//visibile solo se si instanzia estende
		return c;
	}
	
	public int testEstesaBase () {
		int c;
		c = estendeBase.metodoSommaOSottrae(5, 7);
		//estendeBase.metodoSoleEstende();//errore il metodo è solo di estende	
		return c;
	}
	
	public void test () {
		int c;
		c = base.metodoSommaOSottrae(5, 7);
		System.out.println(c);//darà 12
		c = estende.metodoSommaOSottrae(5, 7);
		System.out.println(c);//darà -2
		c = estendeBase.metodoSommaOSottrae(5, 7);
		System.out.println(c);//darà -2
	}
	
}
