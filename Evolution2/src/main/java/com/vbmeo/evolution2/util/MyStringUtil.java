package com.vbmeo.evolution2.util;

import org.apache.commons.lang3.StringUtils;

public class MyStringUtil {

	
	/**
	 * elimina i caratteri di a capo <br>
	 * tipo: \r\n\r\n\r\n\r\n
	 * @param stringa
	 * @return
	 */
	public static String eliminaCaratteriDiFormattazioneTesto(String stringa){
		if (StringUtils.isNotBlank(stringa))
				stringa = stringa.replaceAll ("\r\n|\r|\n", " ");
		return stringa;
	}
	
	
	public static boolean isNullOrdEmptyOrZero(String stringa){
		if (stringa==null)
			return true;
		if (stringa.isEmpty())
			return true;
		
		return false;
	}
	
	public static boolean isNotNullOrdEmptyOrZero(String stringa){
		if (stringa==null)
			return false;
		if (stringa.isEmpty())
			return false;
		
		return true;
	}
	
	
	public static boolean isNullOrdEmptyOrZero(Integer numeroIntero){
		if (numeroIntero==null)
			return true;
		if (numeroIntero==0)
			return true;
		
		return false;
	}
	
	public static boolean isNotNullOrdEmptyOrZero(Integer numeroIntero){
		if (numeroIntero==null)
			return false;
		if (numeroIntero==0)
			return false;
		
		return true;
	}

	public static String getNameOfFileWithoutExtension(String fileAllegato) {
		if (isNotNullOrdEmptyOrZero(fileAllegato)){
			String[] arrayNomeFile = fileAllegato.split("\\.");
			if (arrayNomeFile.length>1)
				return arrayNomeFile[0];
		}
		
		return null;
	}

	//siccome ci sono spesso attributi del json non opzionali e se viene inserito un null non appaiono,
	// questo metodo restituisce cmq qualcosa
	public static String seENullRestituisciVuotaAltrimentiLasciaComE(String cdescrizione) {
		if (isNullOrdEmptyOrZero(cdescrizione))
			return "";
		return cdescrizione;
	}
	
	
	public static String cercaParteRimanenteDiUnaRigaDiTestoDopoElementoDaCercare(String interoTesto, String elementoDopoIlQualeRitornaIlRestoDellaStringa, boolean trim){
		if (isNullOrdEmptyOrZero(interoTesto))
			return null;
		if (isNullOrdEmptyOrZero(elementoDopoIlQualeRitornaIlRestoDellaStringa))
			return null;
		int posizioneElemento = interoTesto.indexOf(elementoDopoIlQualeRitornaIlRestoDellaStringa);
		
		if (posizioneElemento>-1){
			String stringaRimanente= interoTesto.substring(posizioneElemento+elementoDopoIlQualeRitornaIlRestoDellaStringa.length());
			return stringaRimanente.trim();
		}else
			return null;
	}
	
	
	public static String ricavaSoloLaPrimaParolaDiUnaStringa(String stringa){
		if (isNotNullOrdEmptyOrZero(stringa)){
			String[] splittaStringa = stringa.split(" ");
			if (splittaStringa.length>0)
				return splittaStringa[0];
		}	
		return null;
	}
	
	
	
	
	
	
}
