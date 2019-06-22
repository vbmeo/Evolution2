package com.vbmeo.evolution2.util;

import java.util.Collections;
import java.util.Date;
import java.util.logging.Logger;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
/**
 * 
 * http://www.agenziaentrate.gov.it/wps/content/Nsilib/Nsi/Home/CosaDeviFare/Richiedere/Codice+fiscale+e+tessera+sanitaria/Richiesta+TS_CF/SchedaI/Informazioni+codificazione+pf/
 * 
 * @author riccardo.zappi
 *
 */
public class CodiceFiscaleChecker {
	private static final Logger logger = Logger.getLogger(CodiceFiscaleChecker.class.getName());
	private static final ImmutableMap<Character,Integer> DISPARI = new ImmutableMap.Builder<Character,Integer>()
			.put('0',1).put('1',0).put('2',5).put('3',7).put('4',9).put('5',13).put('6',15).put('7',17).put('8',19).put('9',21)
			.put('A',1).put('B',0).put('C',5).put('D',7).put('E',9).put('F',13).put('G',15).put('H',17).put('I',19).put('J',21)
			.put('K',2).put('L',4).put('M',18).put('N',20).put('O',11).put('P',3).put('Q',6).put('R',8).put('S',12).put('T',14)
			.put('U',16).put('V',10).put('W',22).put('X',25).put('Y',24).put('Z',23)
			.build();
	
	private static final ImmutableMap<Character,Integer> PARI = new ImmutableMap.Builder<Character,Integer>()
	        .put('0',0).put('1',1).put('2',2).put('3',3).put('4',4).put('5',5).put('6',6).put('7',7).put('8',8).put('9',9)
	        .put('A',0).put('B',1).put('C',2).put('D',3).put('E',4).put('F',5).put('G',6).put('H',7).put('I',8).put('J',9)
	        .put('K',10).put('L',11).put('M',12).put('N',13).put('O',14).put('P',15).put('Q',16).put('R',17).put('S',18)
	        .put('T',19).put('U',20).put('V',21).put('W',22).put('X',23).put('Y',24).put('Z',25)
	        .build();

	// 0 = L; 1 = M; 2 = N; 3 = P; 4 = Q; 5 = R; 6 = S;	 7 = T; 8 = U; 9 = V
	private static final ImmutableMap<Character, Character> OMOCODIE = new ImmutableMap.Builder<Character, Character>()
			.put('L','0').put('M','1').put('N','2').put('P','3').put('Q','4').put('R','5').put('S','6').put('T','7').put('U','8').put('V','9')
			.build();
	
	private static final ImmutableSet<Integer> CHAR_EXPECTED = new ImmutableSet.Builder<Integer>()
			.add(0).add(1).add(2).add(3).add(4).add(5).add(8).add(11).add(15).build();
	
	private static final char[] caratteriDiControllo = new char[] {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		
	private static final ImmutableMap<Character, Integer> MESI = new ImmutableMap.Builder<Character,Integer>()
			.put('A',1).put('B',2).put('C',3).put('D',4).put('E',5).put('H',6).put('L',7).put('M',8).put('P',9).put('R',10).put('S',11).put('T',12).build();
	
	private static final ImmutableBiMap<Character, Integer> reverseMesi = ImmutableBiMap.copyOf(Collections.unmodifiableMap(MESI));
		
	public static boolean isValid(final String codiceFiscale) {
		boolean result = false;
		if (codiceFiscale != null) {
			String cf = normalizeCF(codiceFiscale);
			if (cf.length() == 16) { //CHECK del CF delle persone fisiche
				if (omocodiaValida(cf)) {
					if (cf.charAt(15) == codiceDiControllo(cf)) {
						result = true;
					}	
				}
			} else if (cf.length() == 11) { // CHECK del CF di soggetti diversi dalle persone fisiche
		        //Only numbers
				if (cf.matches("[0-9]+")) {
					int numericCode = Integer.parseInt(""+cf.charAt(10));
				  if ( numericCode == codiceDiControlloNumerico(cf)) {
					  result = true;
				  }
				}
			}
		}
		return result;
	}



	public static boolean matchCognomeCode(String cognome, String cognomeCode) {
		boolean result = false;		
		if ((cognome!=null)&&(cognomeCode!=null)){
		  	if (cognomeCode(cognome).equals(cognomeCode)) {
		  		result = true;
		  	}
		}
		return result;
	}
	
	
	@SuppressWarnings("null")
    public static Date getDataDiNascita(final String codiceFiscale) throws IllegalArgumentException {
		LocalDate dataNascita = null;
		if (isValid(codiceFiscale)) {
			if (codiceFiscale.length() != 16) {
				throw new IllegalArgumentException("Il codice fiscale '"+codiceFiscale+"' deve riferirsi ad una persona per recuperare la Data di Nascita.");
			}
			String annoStr = codiceFiscale.substring(6,8);
			int annoInt = Integer.parseInt(annoStr)+1900;
			int diffYears = DateTime.now().getYear()-annoInt;
			if (diffYears>100) {
				annoInt = annoInt + 100;
			}
			int mese = MESI.get(new Character(codiceFiscale.substring(8,9).charAt(0))).intValue();
			String giornoStr = codiceFiscale.substring(9,11);
			int giorno = Integer.parseInt(giornoStr);
			if (giorno>40) {
				giorno = giorno-40;
			}
			dataNascita = new LocalDate(annoInt, mese, giorno);
		} else {
			throw new IllegalArgumentException("Il codice fiscale '"+codiceFiscale+"' non è valido.");
		}
		
		return dataNascita!=null?dataNascita.toDate():null;

	}
	
	
	public static Boolean isFemale(final String codiceFiscale) throws IllegalArgumentException {
		boolean female = false;
		if (isValid(codiceFiscale)) {
			if (codiceFiscale.length() != 16) {
				logger.warning("Il codice fiscale '"+codiceFiscale+"' deve riferirsi ad una persona per recuperare la Data di Nascita.");
				return null;
			}
			String giornoStr = codiceFiscale.substring(9,11);
			int giorno = Integer.parseInt(giornoStr);
			if (giorno>40) {
				female = true;
			}
		} else {
			logger.warning("Il codice fiscale '"+codiceFiscale+"' non è valido.");
			return null;
		}
		return female;
	}
	
	
	private static String normalizeCF(final String codiceFiscale) {
		String result = null;
		if (codiceFiscale!=null) {
			result = (codiceFiscale.toUpperCase()).replaceAll("\\s","");
		}
		return result;
	}
	
	
	private static char codiceDiControllo(final String codiceFiscale) {
		int totale = 0;
		for (int i = 1; i < codiceFiscale.length(); i++) {
			char carattere = codiceFiscale.charAt(i - 1);
			if ((i % 2) != 0) {
				// dispari
				totale += DISPARI.get(carattere)!=null?DISPARI.get(carattere):0;

			} else {
				// pari
				totale += PARI.get(carattere)!=null?PARI.get(carattere):0;
			}
		}
		int resto = totale % 26;
		return caratteriDiControllo[resto];
	}
	
	
	private static boolean omocodiaValida(final String codiceFiscale) {
		boolean result = true;
		for (int i = 1; i < codiceFiscale.length(); i++) {
			char carattere = codiceFiscale.charAt(i - 1);
			// Gestione delle OMOCODIE		
			if (!(CHAR_EXPECTED.contains(i - 1))) { // Expected a Number
				if (!(Character.isDigit(carattere))) { // but it is something different			
					//ma trova un char 
					if (!(OMOCODIE.containsKey(carattere))) {
						result = false;
					}
				}
			}
		}
		return result;
	}
	
	
	private static String cognomeCode(final String cognome) {
		String result = "";
		if (cognome!=null) {
			String surnameConsonants = cognome.replaceAll("(?i)[^bcdfghjklmnpqrstvwxyz]", "").toUpperCase();
			String surnameVowels = cognome.replaceAll("(?i)[^aeiou]", "").toUpperCase();
		    if (surnameConsonants.length()<3) {
		    	result = surnameConsonants;
		    	if (( surnameConsonants.length() + surnameVowels.length() )<3 ) {
		    		int howManyX = 3 - surnameConsonants.length() - surnameVowels.length();
		    		result = result + surnameVowels;
		    		for (int i = 0; i < howManyX; i++) {
						result = result + "X";
					}
		    	} else {
		    		int howManyVowels = 3 - surnameConsonants.length();
		    		for (int i = 0; i < howManyVowels; i++) {
						result = result + surnameVowels.charAt(i);
					}
		    	}
		    } else {
		    	result = surnameConsonants.substring(0, 3);
		    }
		}
		return result;
	}
	
	
	private static String nomeCode(final String nome) {
		String result = "XXX";  //Caso di persone indiane che non hanno il nome
		if (nome!=null) {
			String nameConsonants = nome.replaceAll("(?i)[^bcdfghjklmnpqrstvwxyz]", "").toUpperCase();
			String nameVowels = nome.replaceAll("(?i)[^aeiou]", "").toUpperCase();
		    if (nameConsonants.length()<4) {
		    	result = nameConsonants;
		    	if (( nameConsonants.length() + nameVowels.length() )<3 ) {
		    		int howManyX = 3 - nameConsonants.length() - nameVowels.length();
		    		result = result + nameVowels;
		    		for (int i = 0; i < howManyX; i++) {
						result = result + "X";
					}
		    	} else {
		    		int howManyVowels = 3 - nameConsonants.length();
		    		for (int i = 0; i < howManyVowels; i++) {
						result = result + nameVowels.charAt(i);
					}
		    	}
		    } else {
		    	result = Character.toString(nameConsonants.charAt(0))+Character.toString(nameConsonants.charAt(2))+Character.toString(nameConsonants.charAt(3));
		    }
		}
		return result;
	}
	
	
	private static int codiceDiControlloNumerico(String cf) {
		
		// 	 Il carattere numerico di controllo viene determinato nel modo seguente:
		//	   - si sommano i valori di ciascuna delle cinque cifre di ordine dispari,partendo da sinistra;
		//	   - si raddoppia ogni cifra di ordine pari e, se il risultato è un numero di due cifre, esso si riduce ad una sola sommando la cifra relativa alle decine e quella relativa alle unità; si sommano quindi tutti i precedenti risultati;
		//	   - si determina il totale delle due somme di cui sopra;
		//	   - si sottrae da dieci la cifra relativa alle unità del precedente totale. Il carattere di controllo è la cifra relativa alle unità del risultato.
		int totale = 0;
		for (int i = 1; i <= 10; i++) {
			String carattere = cf.substring(i - 1,i);
			if ((i % 2) != 0) {
				// dispari
				int dispariValue = Integer.parseInt(carattere);
				//logger.debug("indice : "+i+" (dispari) char:"+carattere+" --> value = "+dispariValue);
				totale += dispariValue;
			} else {
				// pari
				int pariValue = Integer.parseInt(carattere)*2;
				pariValue = pariValue<10?pariValue:((pariValue%10)+1);
				//logger.debug("indice : "+i+" (pari) char:"+carattere+" --> value = "+pariValue);
				totale += pariValue;
			}
		}
		totale = 10 - (totale % 10);
	    return totale;
    }
	
	
	public static String getFirstPartCF(String cognome, String nome, String sex, LocalDate natoIl ) {
		String result = cognomeCode(cognome)+nomeCode(nome);
		if (natoIl!=null) {
			String annoNascita = natoIl.getYearOfCentury()+"";
			Integer mese = new Integer(natoIl.getMonthOfYear());
			Character meseCh = reverseMesi.containsValue(mese) ? reverseMesi.inverse().get(mese) : 'x';
			char sexChar = sex.toUpperCase().charAt(0);
			int giornoNascita = sexChar=='M'?natoIl.getDayOfMonth():natoIl.getDayOfMonth()+40;
			String giornaNascitaStr = String.format("%02d", giornoNascita);
			result += annoNascita + meseCh + giornaNascitaStr;
			return result;
		} else {
			return result+"00X00";
		}
	}
	
	public static String generateCF(String cognome, String nome, String sex, LocalDate natoIl, String codiceComune ) {
		String result = getFirstPartCF(cognome, nome, sex, natoIl);
		result = result + codiceComune+codiceDiControllo(result);
		return result;
	}
}