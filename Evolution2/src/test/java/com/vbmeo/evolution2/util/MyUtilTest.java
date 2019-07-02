package com.vbmeo.evolution2.util;

import java.sql.Date;

import org.junit.Test;

import junit.framework.TestCase;

public class MyUtilTest extends TestCase {

	
	
	
	@Test
	public void testIsNumeric() {
		String numero = "33";		
		String nonNumero = "3f3";		
		assertEquals("Risultato is numeric ", true, MyUtil.isNumeric(numero));
		assertEquals("Risultato is numeric ", false, MyUtil.isNumeric(nonNumero));
	}

//	@Test
//	public void testTogliACapoDaStringa() {
//		fail("Not yet implemented");
//	}
	
	
	
//
//	@Test
//	public void testAggiustaPercorsoFileString() {
//		fail("Not yet implemented");
//	}
//
	
	
	
	
	
//
//	@Test
//	public void testParseDateStringSimpleDateFormat() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testParseDateConError() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFormatDateToString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFormatDataToStringForMySQL() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFormatDateToDate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetLocalDate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddChatToStartString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddListToListListOfStringListOfString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddListToListListOfStringListOfStringInt() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsInListListOfIntegerInt() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsInListListOfStringString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testContainsInList() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testEUnaData() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testConvertDateinSqlDate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testConvertDateMdbInDateForEngin() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testConvertDataConTempoInSolaDataRitornaDDMMYYYYSeparatiDaSlash() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddToDate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetJusthoursTimeFromTimeStamp() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetJustminutesTimeFromTimeStamp() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetTimeFromTimeStamp() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetTimeFromTimeStampJustHH() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDifferentDaysBeetweenTwoDate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDataDiOggiInFormatoRidotto() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testEUnaDataMaggioreDiOggi() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testEUnaDataMaggioreDiIeri() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testControllaDataSeSuperioreAdOggiRicavaDaCF() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testControllaDataSeSuperioreAIeriRicavaDaCF() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDifferentBeetweenTwoDate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDifferentYearsBeetweenTwoDate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetToday() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetCompleteNameOfClassObject() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAgeFromBirthDay() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetYearsFromDate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetMonthFromDate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetDayFromDate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testConvertIntegerToString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testConvertStringNumberToString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testConvertStringToInteger() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testConvertStringToFloat() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIfIsNumberConvertStringToInteger() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIfIsNumberPassStringNumberElseNULL() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRiordinaMultiMappaInSesnoInverso() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDecodificaStringaConCorrispondenteSeVuotoNullOStringaVuota() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testComponiStringaSoloSeNonVuotiConDescrizioneRitornaNullSeTuttoVuoto() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetParteRimanenteDelPErcorsoFileDopoUnaCertaCartellaCompresaLaCartella() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetCartelleDiPercorsoFIle() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetNameFileFromPath() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAdressFileFromPath() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetRandomCode() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetJsonDestinationPathPaziente() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCopiaFile() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCreateInputStreamUTF_8FromString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCreateFileFromString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCopyFile() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testConcatenaDueStringheSeNonNulle() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testConcatenaDueStringheSeNonNulleEntrambe() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testConcatenaArrayStringheSeNonNulle() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testConcatenaListStringheSeNonNulle() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testEUnCodiceICD9() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testEUnCodiceICPC2() {
//		fail("Not yet implemented");
//	}
//
	
	@Test
	public void testCambiaEstensioneAlNomeFileCon() {
		String path = "nomefile.txt";		
		String estensione = "pdf";	
		String risultato = "nomefile.pdf";	
		assertEquals("Risultato CambiaEstensioneAlNomeFile ", risultato, MyUtil.cambiaEstensioneAlNomeFileCon(path,estensione));
	}
//
//	@Test
//	public void testGetNameFileWithoutExtension() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetExtensionFile() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSplittaIlPunto() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRestituisciParteDellaStringaDopoUnCertoCarttere() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRestituisciParteDellaStringaPrimaDiUnCertoCarttere() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIfIsNotInListAdd() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCreaStringaConElementiACapoDaStringaConCarattereDivisorio() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsBeetweenTwoNumberStringIntInt() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsBeetweenTwoNumberIntIntInt() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsThisObjectOfThisClass() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsStringInRageOfNumber() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testConcatenaArrayStringheSeNonNulleEReplaceVirgolette() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testReplaceVirgolette() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testTogliUltimoCrattereDaUnaStringa() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetLastPathOfUrl() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetPathOfClassInWork() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetPathClass() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetPathClass2() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPrimoCarattereMaiuscolo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testPrimoCarattereminuscolo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetDataInFormatoStringaEnginPerIatros() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetDataInFormatoDataPerIatros() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetFirstDataInseritaInStringa() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAggiustaPercorsoFileStringString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testTogliPrimoCrattereDaUnaStringa() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testTogliPrimoEUltimoCrattereDaUnaStringa() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetUltimiTotCaratteri() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetTrueSeS() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetValoreTAGXMLDaStringa() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCeAlmenoUnTAG() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetStringOnlyWithChar() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRibaltabarre() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testControllaPerEventualePausa() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCleanArray() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsInListSetOfStringString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetDateWithoutTime() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetDateShortFormat() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testReplaceVirgolaTraDueApici() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testReplaceVirgolaDiUnNumeroConPunto() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsNotNullAndMajorOfZero() {
//		fail("Not yet implemented");
//	}

}
