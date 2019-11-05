package com.vbmeo.evolution2.util;

import java.net.URL;
import java.net.URLClassLoader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;




import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.LoggerFactory;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.vbmeo.evolution2.controller.MisureController;


public class MyUtil {

	
	private static final Logger logger = Logger.getLogger(MyUtil.class.getName());
	private static final String formatoData= Costanti.formati.FORMATO_DATA_STRINGA;
	public static final String A_CAPO = "\r\n";
	private static final String formatoDataRichiestoDaEngin = "dd/MM/yyyy";
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static String absolutePath = new File("").getAbsolutePath();
	static String path = "";
	private static String FORMATO_DATA_MYSQL = "YYYY-MM-dd";

	public enum TipoDiPeriodo {
		millisecondi, secondi, minuti, ore, giorni, mesi, anni
	}
	
	

	public static boolean isNumeric(String stringa) {
		return StringUtils.isNumeric(stringa);
	}

	public static String togliACapoDaStringa(String stringa) {
		if (StringUtils.isBlank(stringa))
			return stringa;
		String str = stringa.replaceAll("\r\n|\r|\n", " ");
		return str;
	}

	public static String aggiustaPercorsoFile(String percorsoDoveMettereIlFile) {

		if (percorsoDoveMettereIlFile != null && !percorsoDoveMettereIlFile.isEmpty()) {
			path = percorsoDoveMettereIlFile.replace("/", "//");
			if (!percorsoDoveMettereIlFile.substring(percorsoDoveMettereIlFile.length() - 1).equals("/")) {
				path = path + "//";
			}
		}
		return percorsoDoveMettereIlFile;
	}

	public static Date parseDate(String dataNelFormatoPrevisto, String formatoDellaData) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatoDellaData);
		Date date = null;
		try {
			date = formatter.parse(dataNelFormatoPrevisto);
		} catch (ParseException e) {
			logger.warning("Errore su parse date = " + dataNelFormatoPrevisto);
			// e.printStackTrace();
		} catch (NullPointerException e) {
			logger.warning("Passata data nulla");
			// e.printStackTrace();
			// return null;
		} catch (Exception e) {
			logger.warning("Altra eccezione generica per data " + dataNelFormatoPrevisto);
			// e.printStackTrace();
			// return null;
		}

		return date;
	}
	
	public static Date parseDate(String dataNelFormatoPrevisto, SimpleDateFormat formatoDellaData)  {	
		Date date = null;
		try {
			date = formatoDellaData.parse(dataNelFormatoPrevisto);
		} catch (ParseException e) {
			logger.warning("Errore su parse date = " + dataNelFormatoPrevisto);
			// e.printStackTrace();
		} catch (NullPointerException e) {
			logger.warning("Passata data nulla");
			// e.printStackTrace();
			// return null;
		} catch (Exception e) {
			logger.warning("Altra eccezione generica per data " + dataNelFormatoPrevisto);
			// e.printStackTrace();
			// return null;
		}

		return date;
	}
	
	

	public static Date parseDateConError(String dataNelFormatoPrevisto, String formatoDellaData) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(formatoDellaData);
		Date date = null;
		date = formatter.parse(dataNelFormatoPrevisto);
		return date;
	}

	public static String formatDateToString(Date date, String formato) {
		if (date == null)
			return null;

		LocalDate localDate = getLocalDate(date);
		DateTimeFormatter formatter = DateTimeFormat.forPattern(formato);
		String f = formatter.print(localDate);
		return f;

	}
	
	
	public static String formatDataToStringForMySQL(Date date) {
		return formatDateToString(date, FORMATO_DATA_MYSQL);
	}

	public static Date formatDateToDate(Date date, String formato) {
		if (date == null)
			return null;

		LocalDate localDate = getLocalDate(date);
		DateTimeFormatter formatter = DateTimeFormat.forPattern(formato);
		String f = formatter.print(localDate);
		Date data = parseDate(f, formato);
		return data;

	}

	public static LocalDate getLocalDate(Date date) {
		return date != null ? new LocalDate(date) : null;
		// return date != null ? new LocalDate(date, DateTimeZone.forID("UTC"))
		// : null;
	}

	public static String addChatToStartString(String stringa, String carattereOStringaDaAggiungereDavanti,
			Integer perArrivareATotCaratteriNullSeNonIndicato) {
		String stringaRisultante = "";

		if (perArrivareATotCaratteriNullSeNonIndicato != null) {
			stringaRisultante = stringa;
			while (stringaRisultante.length() < perArrivareATotCaratteriNullSeNonIndicato)
				stringaRisultante = carattereOStringaDaAggiungereDavanti + stringaRisultante;

		} else {
			if (carattereOStringaDaAggiungereDavanti != null)
				stringaRisultante = stringaRisultante + carattereOStringaDaAggiungereDavanti;
			if (stringa != null)
				stringaRisultante = stringaRisultante + stringa;
		}

		return stringaRisultante;
	}

	public static void addListToList(List<String> listACUiAggiungere, List<String> listDaAggiungere) {

		for (String stringa : listDaAggiungere)
			listACUiAggiungere.add(stringa);

	}

	public static void addListToList(List<String> listACUiAggiungere, List<String> listDaAggiungere,
			int inizioAdAggiungereDalNumeroIniziaDa1) {

		int conta = 1;
		for (String stringa : listDaAggiungere) {
			if (conta >= inizioAdAggiungereDalNumeroIniziaDa1)
				listACUiAggiungere.add(stringa);

			conta++;
		}

	}


	public static boolean isInList(List<Integer> listaIdTerapieAgganciateConicheENon, int numeroDaVerificare) {
		for (Integer elemento : listaIdTerapieAgganciateConicheENon)
			if (elemento == numeroDaVerificare)
				return true;

		return false;

	}

	public static boolean isInList(List<String> listDaCuiControllarePresenza, String stringaDaCercare) {
		for (String elemento : listDaCuiControllarePresenza)
			if (elemento.equals(stringaDaCercare))
				return true;

		return false;
	}

	public static boolean containsInList(List<String> listDaCuiControllarePresenza, String stringaDaCercare) {
		for (String elemento : listDaCuiControllarePresenza)
			if (elemento.contains(stringaDaCercare))
				return true;

		return false;
	}

	public static boolean eUnaData(String dataStringa) {
		if (MyStringUtil.isNotNullOrdEmptyOrZero(dataStringa)) {
			Date data = parseDate(dataStringa, formatoData);
			if (data == null)
				return false;
		} else
			return false;

		return true;
	}
	
	
	/**
	 * ritorna eccezzione in caso non lo sia viene gestita poi 
	 * @param dataStringa
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static void controlloDataSql(String dataStringa) throws IllegalArgumentException {		
			java.sql.Date sqlStartDate = java.sql.Date.valueOf(dataStringa);	
	}
	
	
	public static boolean isDataSql(String dataStringa) {		
		try {
			java.sql.Date sqlStartDate = java.sql.Date.valueOf(dataStringa);
			return true;
		} catch (Exception e) {
			return false;
		}
			
	}
	
	/**
	 * ritorna null se non va bene
	 * @param dataYyyyMMdd
	 * @return
	 */
	public static java.sql.Date convertDateinSqlDate(String dataYyyyMMdd) throws IllegalArgumentException {
		controlloDataSql(dataYyyyMMdd);//gerera eccezzione se non � data
			java.sql.Date sqlStartDate = java.sql.Date.valueOf(dataYyyyMMdd); 
			return sqlStartDate;
	}
	
	
	

	// formato data db 1899-12-30
	public static Date convertDateMdbInDateForEngin(Date dataFormatoMdb) {
		if (dataFormatoMdb != null) {
			String datastring = dataFormatoMdb.toString();
			String nData = datastring.substring(8, 10) + "/" + datastring.substring(5, 7) + "/"
					+ datastring.substring(0, 4);
			Date data = parseDate(nData, formatoData);
			return data;
		}

		return null;
	}

	public static Date convertDataConTempoInSolaDataRitornaDDMMYYYYSeparatiDaSlash(Date data) {
		// attenzione il mese passa sotto
		if (data != null) {
			String nData = getGiornoOMeseCon0Davanti(getDayFromDate(data)) + "/"
					+ getGiornoOMeseCon0Davanti(getMonthFromDate(data) + 1) + "/" + getYearsFromDate(data);
			Date dataR = parseDate(nData, formatoData);
			return dataR;
		}

		return null;
	}

	private static String getGiornoOMeseCon0Davanti(int i) {
		if (i < 10)
			return "0" + i;
		return convertIntegerToString(i);
	}

	// esempio: Calendar.HOUR_OF_DAY
	public static Date addToDate(int numberOfYersOrmouthOrDayecc, Date data, int typeOfCalendarToAdd) {
		// String datastringapartenza = data.toString();
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(typeOfCalendarToAdd, numberOfYersOrmouthOrDayecc);
		Date newDate = c.getTime();
		// String datastringa = newDate.toString();
		// logger.debug("datastringapartenza="+datastringapartenza+"
		// datastringa="+datastringa);
		return newDate;

	}

	public static String getJusthoursTimeFromTimeStamp(Date timeStamp) {
		String orario = getTimeFromTimeStamp(timeStamp);
		if (orario != null && !orario.isEmpty()) {
			return orario.substring(0, 2);
		} else {
			return null;
		}
	}

	public static String getJustminutesTimeFromTimeStamp(Date timeStamp) {
		String orario = getTimeFromTimeStamp(timeStamp);
		if (orario != null && !orario.isEmpty()) {
			return orario.substring(3, 5);
		} else {
			return null;
		}
	}

	public static String getTimeFromTimeStamp(Date timeStamp) {
		if (timeStamp != null) {
			SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm:ss");
			sdfTime.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
			SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
			sdfDate.setTimeZone(java.util.TimeZone.getTimeZone("IST"));
			String dateStr = sdfDate.format(timeStamp);
			String timeStr = sdfTime.format(timeStamp);
			return timeStr;
		} else {
			return null;
		}

	}

	public static String getTimeFromTimeStampJustHH(Date timeStamp) {
		if (timeStamp != null) {
			SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm:ss");
			sdfTime.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
			SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
			sdfDate.setTimeZone(java.util.TimeZone.getTimeZone("IST"));
			String dateStr = sdfDate.format(timeStamp);
			String timeStr = sdfTime.format(timeStamp);
			String[] timeSplic = timeStr.split(":");
			if (timeSplic.length > 0)
				return timeSplic[0];

			return null;
		} else {
			return null;
		}

	}

	// restituisce giorni
	public static long differentDaysBeetweenTwoDate(Date maiorDate, Date minorDate) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(maiorDate);
		c2.setTime(minorDate);
		long giorni = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
		return giorni;

	}

	public static Date dataDiOggiInFormatoRidotto() {
		Date today = getToday();
		Date todayRid = convertDataConTempoInSolaDataRitornaDDMMYYYYSeparatiDaSlash(today);
		return todayRid;
	}

	public static boolean eUnaDataMaggioreDiOggi(Date dataDaConfrontare) {
		Date today = getToday();
		long giorni = differentDaysBeetweenTwoDate(dataDaConfrontare, today);
		if (giorni > 0)
			return false;
		return true;
	}

	public static boolean eUnaDataMaggioreDiIeri(Date dataDaConfrontare) {
		Date today = getToday();
		today = addToDate(-1, today, Calendar.DAY_OF_MONTH);
		long giorni = differentDaysBeetweenTwoDate(dataDaConfrontare, today);
		if (giorni > 0)
			return false;
		return true;
	}

	public static Date controllaDataSeSuperioreAdOggiRicavaDaCF(Date dataNascita, String codiceFiscale) {
		boolean maggioreDiOggi = eUnaDataMaggioreDiOggi(dataNascita);
		if (maggioreDiOggi) {
			Date dataDaCF = null;
			try {
				dataDaCF = CodiceFiscaleChecker.getDataDiNascita(codiceFiscale);
			} catch (Exception e) {
				logger.warning("Probabile cf non valido {}" + codiceFiscale);
			}

			if (dataDaCF == null) {
				logger.warning(
						"Data di nascita del paziente con cf:{} più alta di oggi, e dal CF non è stato possibile ricavare una data corretta. Data proposta {}"+
						codiceFiscale + dataNascita);
				return null;
			} else {
				logger.warning("Data di nascita del paziente con cf:{} più alta di oggi. Data proposta {} tramite CF."+
						codiceFiscale+ dataNascita);
				return dataDaCF;
			}
		}
		return dataNascita;
	}

	public static Date controllaDataSeSuperioreAIeriRicavaDaCF(Date dataNascita, String codiceFiscale) {
		boolean maggioreDiOggi = eUnaDataMaggioreDiIeri(dataNascita);
		if (maggioreDiOggi) {

			Date dataDaCF = null;
			try {
				dataDaCF = CodiceFiscaleChecker.getDataDiNascita(codiceFiscale);
			} catch (Exception e) {
				logger.warning("Probabile cf non valido {}"+ codiceFiscale);
			}

			if (dataDaCF == null) {
				logger.warning(
						"Data di nascita del paziente con cf:{} più alta di ieri, e dal CF non è stato possibile ricavare una data corretta. Data proposta {}"+
						codiceFiscale+dataNascita);
				return null;
			} else {
				logger.warning("Data di nascita del paziente con cf:{} più alta di ieri. Data proposta {} tramite CF."+
						codiceFiscale+ dataNascita);
				return dataDaCF;
			}
		}
		return dataNascita;
	}

	public static long differentBeetweenTwoDate(Date maiorDate, Date minorDate, TipoDiPeriodo tipoDiPeriodo) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(maiorDate);
		c2.setTime(minorDate);
		long unita = (c1.getTime().getTime() - c2.getTime().getTime());
		long sec = unita / 1000;
		long min = sec / 60;
		long ore = min / 60;
		long giorni = ore / 24;
		long anni = giorni / 365;
		// long giorni = unita / (365 * 24 * 3600 * 1000);

		switch (tipoDiPeriodo) {
		case giorni:
			return giorni;
		case millisecondi:
			return unita;
		case secondi:
			return sec;
		case minuti:
			return min;
		case ore:
			return ore;
		case anni:
			return anni;
		}

		return 0;
	}

	// restituisce anni
	public static long differentYearsBeetweenTwoDate(Date maiorDate, Date minorDate) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(maiorDate);
		c2.setTime(minorDate);
		long unita = (c1.getTime().getTime() - c2.getTime().getTime());
		long sec = unita / 1000;
		long min = sec / 60;
		long ore = min / 60;
		long giorni = ore / 24;
		long anni = giorni / 365;

		return anni;
	}

	public static Date getToday() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0); // same for minutes and seconds
		Date newDate = today.getTime();
		return newDate;
	}

	public static String getCompleteNameOfClassObject(Object oggetto) {
		String urlOggettoENome = oggetto.toString();
		String[] nomeSplittato = urlOggettoENome.split("@");
		if (nomeSplittato.length == 2) {
			String soloclasse = nomeSplittato[0];
			return soloclasse;
		} else
			return null;

	}

	public static int getAgeFromBirthDay(Date birthDay) {
		Date today = getToday();
		long giorni = differentDaysBeetweenTwoDate(today, birthDay);
		return getNumberOfYearsFromTotDays(giorni);

	}

	public static int getYearsFromDate(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	public static int getMonthFromDate(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int month = cal.get(Calendar.MONTH);
		return month;
	}

	public static int getDayFromDate(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	private static int getNumberOfYearsFromTotDays(long numberOfDays) {
		int numeroIntero = (int) (numberOfDays / 365);
		return numeroIntero;

	}

	public static String convertIntegerToString(int numero) {
		return Integer.toString(numero);
	}

	public static String convertStringNumberToString(String numero) {

		return numero.toString();
	}

	public static int convertStringToInteger(String stringaNumero) {
		return Integer.parseInt(StringUtils.trim(stringaNumero));
	}

	public static Float convertStringToFloat(String stringaNumero) {
		if (MyStringUtil.isNotNullOrdEmptyOrZero(stringaNumero)) {
			float f;
			try {
				f = Float.parseFloat(stringaNumero);
				return f;
			} catch (Exception ex) {
				logger.warning(stringaNumero + " non convertito in float");
				return Float.parseFloat("0");
			}
		}
		return Float.parseFloat("0");
	}

	public static int ifIsNumberConvertStringToInteger(String stringaNumero) {
		if (isNumeric(stringaNumero))
			return convertStringToInteger(stringaNumero);
		return 0;
	}

	public static String ifIsNumberPassStringNumberElseNULL(String stringaNumero) {
		if (isNumeric(stringaNumero))
			return stringaNumero;
		return null;
	}

	public static Multimap<String, String> riordinaMultiMappaInSesnoInverso(
			Multimap<String, String> mappaConDatiInseritiDalPrimoAllUltimo) {

		Multimap<String, String> mappaOrdinata = HashMultimap.create();
		for (Map.Entry<String, String> elemenoDellaMappa : mappaConDatiInseritiDalPrimoAllUltimo.entries()) {
			mappaOrdinata.put(elemenoDellaMappa.getKey(), elemenoDellaMappa.getValue());
		}

		return mappaOrdinata;
	}

	// torna null se vuoto o non trova
	public static String decodificaStringaConCorrispondenteSeVuotoNullOStringaVuota(
			Map<String, String> mappaValoreTrovatoECorrispondenteDaInserire, String stringaDaCercareInMappa,
			boolean restituiscoNullAltrimentiStringaVuota) {

		String stringa = "";
		if (mappaValoreTrovatoECorrispondenteDaInserire.size() == 0)
			return "";

		stringa = mappaValoreTrovatoECorrispondenteDaInserire.get(stringaDaCercareInMappa);

		if (restituiscoNullAltrimentiStringaVuota && (stringa == null || stringa.isEmpty()))
			return "";
		else if (!restituiscoNullAltrimentiStringaVuota && (stringa == null || stringa.isEmpty()))
			return "";

		return stringa;

	}

	/**
	 * la mappa deve avere nel primo campo la descrizione da inserire prima del
	 * valore del campo che sta nella seconda posizione
	 * 
	 * attenzione, le mappe vengono ciclate dall'ultimo al primo quindi c'è la
	 * possivbilità di riordinarle in senso inverso
	 */
	public static String componiStringaSoloSeNonVuotiConDescrizioneRitornaNullSeTuttoVuoto(
			Multimap<String, String> descrizioneDaInserireDavantiCampiEValoriDeiCampi, String carattereDiConcatenzaione,
			boolean riordinoMappaInSensoInverso) {
		String stringa = "";

		if (descrizioneDaInserireDavantiCampiEValoriDeiCampi.size() == 0)
			return null;

		if (riordinoMappaInSensoInverso) {
			Multimap<String, String> mappaOrdinata = riordinaMultiMappaInSesnoInverso(
					descrizioneDaInserireDavantiCampiEValoriDeiCampi);
			for (Map.Entry<String, String> elemenoDellaMappa : mappaOrdinata.entries()) {
				if (elemenoDellaMappa.getValue() != null && !elemenoDellaMappa.getValue().isEmpty()) {
					if (!stringa.isEmpty())
						stringa = stringa + carattereDiConcatenzaione;

					if (elemenoDellaMappa.getKey() != null && !elemenoDellaMappa.getKey().isEmpty())
						stringa = stringa + elemenoDellaMappa.getKey() + " " + elemenoDellaMappa.getValue();
					else
						stringa = stringa + elemenoDellaMappa.getValue();
				}
			}

		} else {
			for (Map.Entry<String, String> elemenoDellaMappa : descrizioneDaInserireDavantiCampiEValoriDeiCampi
					.entries()) {
				if (elemenoDellaMappa.getValue() != null && !elemenoDellaMappa.getValue().isEmpty()) {
					if (!stringa.isEmpty())
						stringa = stringa + carattereDiConcatenzaione;

					if (elemenoDellaMappa.getKey() != null && !elemenoDellaMappa.getKey().isEmpty())
						stringa = stringa + elemenoDellaMappa.getKey() + " " + elemenoDellaMappa.getValue();
					else
						stringa = stringa + elemenoDellaMappa.getValue();
				}
			}
		}

		if (stringa.isEmpty())
			return null;

		return stringa;
	}

	// es.
	public static String getParteRimanenteDelPErcorsoFileDopoUnaCertaCartellaCompresaLaCartella(String pathCompletaFile,
			String nomeDellaCartella) {
		String percorsoRimanente = "";
		boolean trovataCartella = false;

		if (MyStringUtil.isNotNullOrdEmptyOrZero(pathCompletaFile)
				&& MyStringUtil.isNotNullOrdEmptyOrZero(nomeDellaCartella)) {
			String[] arrayDelleCartelle = getCartelleDiPercorsoFIle(pathCompletaFile);
			for (String cartella : arrayDelleCartelle)
				if (trovataCartella || cartella.equals(nomeDellaCartella)) {
					trovataCartella = true;
					percorsoRimanente += cartella + "/"; // la barra �
															// universale
				}
			return percorsoRimanente.substring(0, percorsoRimanente.length() - 1);
		}
		return null;

	}

	public static String[] getCartelleDiPercorsoFIle(String pathCompletaFile) {
		// nel caso sia un separatore web
		int idx = pathCompletaFile.lastIndexOf("/");
		int idx2 = pathCompletaFile.lastIndexOf("\\");
		if (idx2 > -1) {
			// � non web
			String[] cartelleDelPErcorso = pathCompletaFile.split("\\\\");
			return cartelleDelPErcorso;
		} else if (idx > -1) {
			// web
			String[] cartelleDelPErcorso = pathCompletaFile.split("/");
			return cartelleDelPErcorso;
		}
		return null;
	}

	public static String getNameFileFromPath(String path) {
		// nel caso sia un separatore web
		int idx = path.lastIndexOf("/");
		int idx2 = path.lastIndexOf("\\");

		if (idx > -1)
			return path.substring(idx + 1);

		if (idx2 > -1)
			return path.substring(idx2 + 1);

		return path;

	}

	public static String getAdressFileFromPath(String path) {
		// nel caso sia un separatore web
		int idx = path.lastIndexOf("/");
		int idx2 = path.lastIndexOf("\\");

		if (idx > -1)
			return path.substring(0, idx + 1);

		if (idx2 > -1)
			return path.substring(0, idx2 + 1);

		return path;

	}

	public static String getRandomCode() {
		return UUID.randomUUID().toString();

	}

	/**
	 * directory di destinazione json del paziente <br>
	 * che termina con "/"
	 * 
	 * @param mmgMedico
	 * @param cfSoggetto
	 * @return
	 */
	public static String getJsonDestinationPathPaziente(String mmgMedico, String cfSoggetto) {
		String dirDestination = absolutePath + FILE_SEPARATOR + "json" + FILE_SEPARATOR + mmgMedico + "_" + cfSoggetto
				+ FILE_SEPARATOR;
		return dirDestination;
	}

	/**
	 * come destinazione solo la cartella senza file
	 * 
	 * @param pathname
	 * @param destinationDir
	 */
	public static void copiaFile(String pathname, String destinationDir) {
		File root = new File(pathname);
		if (root == null)
			return;

		if (root.isDirectory()) {// se è una directory copia tutti i file
			for (File file : root.listFiles()) {
				copiaFile(file.getAbsolutePath(), destinationDir);
			}
		} else if (root.isFile()) {
			copyFile(root.getAbsoluteFile(), new File(destinationDir + root.getName()));
			logger.warning("ricercaECopiaDoc, file " + pathname + " OOK COPIATO!");
		} else {
			logger.warning("ricercaECopiaDoc, file " + pathname + " non processato!");
		}
	}

	public static InputStream createInputStreamUTF_8FromString(String stringa) {
		Charset charset = StandardCharsets.UTF_8;
		InputStream stream;
		stream = new ByteArrayInputStream(stringa.getBytes(charset));
		return stream;
	}

	public static void createFileFromString(String stringa, String pathFileDestination) {
		File fileDiDestinazione = new File(pathFileDestination);
		InputStream inputStream = createInputStreamUTF_8FromString(stringa);
		try {
			FileUtils.copyInputStreamToFile(inputStream, fileDiDestinazione);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void copyFile(File sourceFile, File destFile) {
		if (!sourceFile.exists()) {
			return;
		}
		if (!destFile.exists()) {
			try {
				destFile.createNewFile();
			} catch (IOException e) {
				logger.warning("creazione file, file " + sourceFile.getName() + " non processato!");
				// e.printStackTrace();
			}
		}
		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
		} catch (FileNotFoundException e) {
			logger.warning("file non trovato, file " + sourceFile.getName() + " non processato!");
			// e.printStackTrace();
		}
		try {
			destination = new FileOutputStream(destFile).getChannel();
		} catch (FileNotFoundException e) {
			logger.warning("file destinazione trovato, file " + destFile.getName() + " non processato!");
			// e.printStackTrace();
		}
		if (destination != null && source != null) {
			try {
				destination.transferFrom(source, 0, source.size());
			} catch (IOException e) {
				logger.warning("transferFrom file in errore " + destFile.getName() + " non processato!");
				// e.printStackTrace();
			}
		}
		if (source != null) {
			try {
				source.close();
			} catch (IOException e) {
				logger.warning("chiusura file sourceFile errore " + sourceFile.getName() + " non processato!");
				// e.printStackTrace();
			}
		}
		if (destination != null) {
			try {
				destination.close();
			} catch (IOException e) {
				logger.warning("chiusura file destFile in errore " + destFile.getName() + " non processato!");
				// e.printStackTrace();
			}
		}

	}

	/**
	 * IN REALTA' SE NON BLANCK
	 * 
	 * @param carattereConcatenazione
	 * @param primaStringa
	 * @param secondaStringa
	 * @return
	 */
	public static String concatenaDueStringheSeNonNulle(String carattereConcatenazione, String primaStringa,
			String secondaStringa) {

		String newAlvo = "";
		if (StringUtils.isNotBlank(primaStringa))
			newAlvo = newAlvo + primaStringa;

		if (StringUtils.isNotBlank(secondaStringa))
			newAlvo = newAlvo + carattereConcatenazione + secondaStringa;

		if (!newAlvo.isEmpty())
			return newAlvo;

		return null;
	}

	/**
	 * IN REALTA' SE NON BLANCK
	 * 
	 * @param stringaDiConcatenzazione
	 * @param stringa1
	 * @param stringa2
	 * @return
	 */
	public static String concatenaDueStringheSeNonNulleEntrambe(String stringaDiConcatenzazione, String stringa1,
			String stringa2) {
		if (StringUtils.isNotBlank(stringa1) && StringUtils.isNotBlank(stringa2))
			return concatenaDueStringheSeNonNulle(stringaDiConcatenzazione, stringa1, stringa2);
		return null;
	}

	/**
	 * IN REALTA' SE NON BLANCK
	 * 
	 * @param carattereConcatenazione
	 * @param arrayStringhe
	 * @return
	 */

	public static String concatenaArrayStringheSeNonNulle(String carattereConcatenazione, String[] arrayStringhe) {

		String newAlvo = "";
		for (String stringa : arrayStringhe)
			if (StringUtils.isNotBlank(stringa))
				newAlvo = newAlvo + stringa + carattereConcatenazione;

		if (!newAlvo.isEmpty()) {
			newAlvo = TogliUltimoCrattereDaUnaStringa(newAlvo);
			return newAlvo;
		}

		return null;
	}

	/**
	 * IN REALTA' SE NON BLANCK
	 * 
	 * @param carattereConcatenazione
	 * @param arrayStringhe
	 * @return
	 */

	public static String concatenaListStringheSeNonNulle(String carattereConcatenazione, List<String> listStringhe) {

		String newAlvo = "";
		for (String stringa : listStringhe)
			if (StringUtils.isNotBlank(stringa))
				newAlvo = newAlvo + stringa + carattereConcatenazione;

		if (!newAlvo.isEmpty()) {
			newAlvo = TogliUltimoCrattereDaUnaStringa(newAlvo);
			return newAlvo;
		}

		return null;
	}

	public static boolean eUnCodiceICD9(String codice) {
		if (MyStringUtil.isNotNullOrdEmptyOrZero(codice))
			if (isNumeric(codice))
				if (!codice.contains("."))
					return true;
				else
					return false;
			else
				return false;
		else
			return false;
	}

	public static boolean eUnCodiceICPC2(String codice) {
		if (Character.isLetter(codice.charAt(0)))
			if (codice.length() == 3)
				if (isNumeric(codice.substring(1, 2)) && isNumeric(codice.substring(2, 3)))
					return true;
				else
					return false;
			else
				return false;
		else
			return false;
	}

	public static String cambiaEstensioneAlNomeFileCon(String path, String nuovaEstensione) {
		String nuovaNomeFile = path;
		if (MyStringUtil.isNotNullOrdEmptyOrZero(path) && MyStringUtil.isNotNullOrdEmptyOrZero(nuovaEstensione)) {
			int posizionePunto = path.lastIndexOf(".");
			if (posizionePunto > -1) {
				String stringaSenzaEstensione = path.substring(0, posizionePunto);
				nuovaNomeFile = stringaSenzaEstensione + "." + nuovaEstensione;
			}
		}
		return nuovaNomeFile;
	}

	
	public static String getNameFileWithoutExtension(String path) {
		if (MyStringUtil.isNotNullOrdEmptyOrZero(path)) {
			int posizionePunto = path.lastIndexOf(".");
			if (posizionePunto > -1) {
				String stringaSenzaEstensione = path.substring(0, posizionePunto);
				return stringaSenzaEstensione;
			}
		}
		return null;
	}
	
	public static String getExtensionFile(String path) {
		if (MyStringUtil.isNotNullOrdEmptyOrZero(path)) {
			int posizionePunto = path.lastIndexOf(".");
			if (posizionePunto > -1) {
				String stringaSenzaEstensione = path.substring(posizionePunto+1, path.length());
				return stringaSenzaEstensione;
			}
		}
		return null;
	}
	
	
	
	// per ricordare come si plitta il carattere punto
	public static String[] splittaIlPunto(String stringaDaSplittare) {
		return stringaDaSplittare.split("\\.");
	}

	/**
	 * ritorna la parte di una string dopo il carattere che deve trovare, in un
	 * numero di volte<br>
	 * es. passando "2516.AMPN.01", "." , 2 <br>
	 * ritorna 01
	 * 
	 * @param stringa
	 * @param carattereDaCercare
	 * @param numeroDiVolteInCuiDevoTrovareIlCarattere
	 * @return
	 */

	public static String restituisciParteDellaStringaDopoUnCertoCarttere(String stringa, String carattereDaCercare,
			int numeroDiVolteInCuiDevoTrovareIlCarattere) {
		String carattereDaCercareAggiustatoPerSplit = carattereDaCercare.replace(".", "\\.");
		String stringaDiRimando = "";
		if (MyStringUtil.isNotNullOrdEmptyOrZero(stringa)) {
			// Utilizzare solo la parte della descrizione che segue il secondo
			// carattere punto (.) Se il dato � assente non inserire nulla.
			if (StringUtils.contains(stringa, carattereDaCercare)) {
				String[] splitRischio = stringa.split(carattereDaCercareAggiustatoPerSplit);
				if (splitRischio.length > numeroDiVolteInCuiDevoTrovareIlCarattere) {
					int lungArray = splitRischio.length;
					for (int a = numeroDiVolteInCuiDevoTrovareIlCarattere; a < lungArray; a++) {
						stringaDiRimando += splitRischio[a] + carattereDaCercareAggiustatoPerSplit;// 
					}
					return stringaDiRimando.substring(0, stringaDiRimando.length() - carattereDaCercare.length());// 
				}
			}
		}
		return null;
	}

	/**
	 * ritorna la parte di una string prima il carattere che deve trovare, in un
	 * numero di volte<br>
	 * es. passando "2516.AMPN.01", "." , 2 <br>
	 * ritorna 2516.AMPN
	 * 
	 * @param stringa
	 * @param carattereDaCercare
	 * @param numeroDiVolteInCuiDevoTrovareIlCarattere
	 * @return
	 */
	public static String restituisciParteDellaStringaPrimaDiUnCertoCarttere(String stringa, String carattereDaCercare,
			int numeroDiVolteInCuiDevoTrovareIlCarattere) {
		String carattereDaCercareAggiustatoPerSplit = carattereDaCercare.replace(".", "\\.");
		String stringaDiRimando = "";
		if (MyStringUtil.isNotNullOrdEmptyOrZero(stringa)) {
			// Utilizzare solo la parte della descrizione che segue il secondo
			// carattere punto (.) Se il dato � assente non inserire nulla.
			if (StringUtils.contains(stringa, carattereDaCercare)) {
				String[] splitRischio = stringa.split(carattereDaCercareAggiustatoPerSplit);
				if (splitRischio.length >= numeroDiVolteInCuiDevoTrovareIlCarattere) {
					int lungArray = splitRischio.length;
					for (int a = 0; a <= numeroDiVolteInCuiDevoTrovareIlCarattere - 1; a++)
						stringaDiRimando += splitRischio[a] + carattereDaCercareAggiustatoPerSplit;// aggiungo

					return stringaDiRimando.substring(0, stringaDiRimando.length() - carattereDaCercare.length());// tolgie
				}
			}
		}
		return null;
	}

	public static void ifIsNotInListAdd(List<Integer> listaIdTerapieAgganciateConicheENon,
			int numeroDaVerificareEAggiungere) {
		if (!isInList(listaIdTerapieAgganciateConicheENon, numeroDaVerificareEAggiungere))
			listaIdTerapieAgganciateConicheENon.add(numeroDaVerificareEAggiungere);
	}

	public static String creaStringaConElementiACapoDaStringaConCarattereDivisorio(String stringaCF,
			String elementoDivisorio) {
		if (stringaCF != null) {
			String stringaConACapo = stringaCF.replaceAll(elementoDivisorio, A_CAPO);
			return stringaConACapo;
		}
		return null;
	}

	/**
	 * Verifica che una stringa passata sia un numero e che <br>
	 * una volta trasformato in numero sia compreso nell'intervallo proposto
	 * <Br>
	 * quindi >= tra e <= a
	 * 
	 * @param numeroInStringaDaVerificare
	 * @param traQuestoNumero
	 * @param eQuestoNumero
	 * @return
	 */
	public static boolean isBeetweenTwoNumber(String numeroInStringaDaVerificare, int traQuestoNumero,
			int eQuestoNumero) {
		if (isNumeric(numeroInStringaDaVerificare))
			return isBeetweenTwoNumber(convertStringToInteger(numeroInStringaDaVerificare), traQuestoNumero,
					eQuestoNumero);

		return false;
	}

	public static boolean isBeetweenTwoNumber(int numeroDaVerificare, int traQuestoNumero, int eQuestoNumero) {
		if (numeroDaVerificare >= traQuestoNumero && numeroDaVerificare <= eQuestoNumero)
			return true;
		return false;
	}

	public static boolean isThisObjectOfThisClass(Object oggettoDaVerificarrneLaClasse, Class classe) {
		if (classe.isAssignableFrom(oggettoDaVerificarrneLaClasse.getClass()))
			return true;

		return false;
		// opppure molto più pratico e diretto
		// if (oggetto instanceof classe)
	}

	/**
	 * data una stringa che può essere un numero intero <br>
	 * ritorna vero solo se il suo valore è entro i parametri dati <Br>
	 * => da <=a
	 * 
	 * @param stringa
	 * @return
	 */
	public static boolean isStringInRageOfNumber(String stringa, int da, int a) {
		if (isNumeric(stringa)) {
			int numero = convertStringToInteger(stringa);
			if (numero >= da && numero <= a)
				return true;
			return false;
		}
		return false;
	}

	/**
	 * IN REALTA' SE NON BLANCK
	 * 
	 * @param carattereConcatenazione
	 * @param arrayStringhe
	 * @return
	 */
	public static String concatenaArrayStringheSeNonNulleEReplaceVirgolette(String carattereConcatenazione,
			String[] arrayStringhe) {

		String newAlvo = "";
		for (String stringa : arrayStringhe)
			if (StringUtils.isNotBlank(stringa))
				newAlvo = newAlvo + replaceVirgolette(stringa) + carattereConcatenazione;

		if (!newAlvo.isEmpty())
			return newAlvo;

		return null;
	}

	public static String replaceVirgolette(String stringa) {
		return stringa.replace("\"", "");
	}

	public static String TogliUltimoCrattereDaUnaStringa(String stringa) {
		if (stringa != null)
			if (stringa.length() > 1)
				return stringa.substring(0, stringa.length() - 1);
		return null;
	}

	// controlla se sono stati inseriti / 0 \ o doppi
	// "http://example.com/foo/bar/42?param=true restituisce 42?param=true
	// restituisce
	public static String getLastPathOfUrl(String baseDir) {
		int ultimaPos1 = baseDir.lastIndexOf('/');
		int ultimaPos2 = baseDir.lastIndexOf('\\');
		int ultimaPosAssoluta = ultimaPos1;

		if (ultimaPos2 > ultimaPos1)
			ultimaPosAssoluta = ultimaPos2;

		if (ultimaPosAssoluta > -1)
			return baseDir.substring(ultimaPosAssoluta + 1);

		logger.warning("Richiesta ultima path di un indirizzo senza path");
		return null;
	}

	public static String getPathOfClassInWork(Object classe) {
		// String pathfile =
		// (ProfimUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		return null;

	}

	// non buono, fa lista solo dei jar folder
	public static String getPathClass() {
		URL[] url = ((URLClassLoader) (Thread.currentThread().getContextClassLoader())).getURLs();
		for (URL ur : url)
			System.out.println("url=" + ur);
		return null;

	}

	public static void getPathClass2() {
		String strClassPath = System.getProperty("java.class.path");

		System.out.println("Classpath is " + strClassPath);
	}

	public static String primoCarattereMaiuscolo(String stringa) {
		if (MyStringUtil.isNotNullOrdEmptyOrZero(stringa)) {
			char first = Character.toUpperCase(stringa.charAt(0));
			String betterIdea = first + stringa.substring(1);
			return betterIdea;
		}
		return stringa;
	}

	public static String primoCarattereminuscolo(String stringa) {
		if (MyStringUtil.isNotNullOrdEmptyOrZero(stringa)) {
			char first = Character.toLowerCase(stringa.charAt(0));
			String betterIdea = first + stringa.substring(1);
			return betterIdea;
		}
		return stringa;
	}

	public static String getDataInFormatoStringaEnginPerIatros(String numeroDiGiorniDaSommareAInizioData) {
		int vaolreFinaleIntero = 0;
		String valoreNumericoSenzaVirgolaConPunto = numeroDiGiorniDaSommareAInizioData.replace(",", ".");
		// può avere una virgola trasformata o un numero intero
		if (valoreNumericoSenzaVirgolaConPunto.indexOf(".") > -1) {
			// prendo solo parte intera, il resto non serve in termine di giorni
			// da aggiungere
			String[] numeroSplittato = MyUtil.splittaIlPunto(valoreNumericoSenzaVirgolaConPunto);
			if (MyUtil.isNumeric(numeroSplittato[0]))
				vaolreFinaleIntero = MyUtil.convertStringToInteger(numeroSplittato[0]);
		} else {
			vaolreFinaleIntero = MyUtil.convertStringToInteger(numeroDiGiorniDaSommareAInizioData);
		}
		if (vaolreFinaleIntero > 0) {
			Date NewDate = MyUtil.addToDate(vaolreFinaleIntero, MyUtil.parseDate("1901-01-01", "yyyy-MM-dd"),
					Calendar.DAY_OF_MONTH);
			String dataFormatoIatros = MyUtil.formatDateToString(NewDate, formatoDataRichiestoDaEngin);

			return dataFormatoIatros;
		} else {
			return null;
		}
	}

	public static Date getDataInFormatoDataPerIatros(String numeroDiGiorniDaSommareAInizioData) {
		int vaolreFinaleIntero = 0;
		String valoreNumericoSenzaVirgolaConPunto = numeroDiGiorniDaSommareAInizioData.replace(",", ".");
		// può avere una virgola trasformata o un numero intero
		if (valoreNumericoSenzaVirgolaConPunto.indexOf(".") > -1) {
			// prendo solo parte intera, il resto non serve in termine di giorni
			// da aggiungere
			String[] numeroSplittato = MyUtil.splittaIlPunto(valoreNumericoSenzaVirgolaConPunto);
			if (MyUtil.isNumeric(numeroSplittato[0]))
				vaolreFinaleIntero = MyUtil.convertStringToInteger(numeroSplittato[0]);
		} else {
			vaolreFinaleIntero = MyUtil.convertStringToInteger(numeroDiGiorniDaSommareAInizioData);
		}
		if (vaolreFinaleIntero > 0) {
			Date NewDate = MyUtil.addToDate(vaolreFinaleIntero, MyUtil.parseDate("1901-01-01", "yyyy-MM-dd"),
					Calendar.DAY_OF_MONTH);
			return NewDate;
		} else {
			return null;
		}
	}

	

	/**
	 * Estrae la prima Data trovata in una stringa che la contiene al suo
	 * interno
	 * 
	 * @param stringaConteneteData
	 * @param dopoParolaCompresaDiSpaziOppureNULL
	 *            (null se non c'è un punto <br>
	 *            di partenza da dove iniziare a cercare)
	 * @param formatoDataDaTrovare
	 *            (fatto però di spazi al posto delle date e <br>
	 *            carattere di separazione es: "    /  /  "
	 * @return
	 */
	public static String getFirstDataInseritaInStringa(String stringaConteneteData,
			String dopoParolaCompresaDiSpaziOppureNULL, String formatoDataDaTrovare) {
		// cerca carattere di separazione
		if (formatoDataDaTrovare != null && !formatoDataDaTrovare.isEmpty()) {
			int nLettereData = formatoDataDaTrovare.length();
			boolean dataTrovata = false;
			boolean parolaInizialeTrovata = false;
			int largherraFormatoData = formatoDataDaTrovare.length();
			int lunghezzaParolaIniziale = 0;
			boolean haUnaPaorolaIniziale = false;
			String stringaRidottaDaParolaIniziale = stringaConteneteData;
			int lunghezzaStringaConteneteData = stringaConteneteData.length();

			if (dopoParolaCompresaDiSpaziOppureNULL != null && !dopoParolaCompresaDiSpaziOppureNULL.isEmpty()) {
				lunghezzaParolaIniziale = dopoParolaCompresaDiSpaziOppureNULL.length();
				haUnaPaorolaIniziale = true;
			}

			// limito la stringa a dopo la parola iniziale da cercare
			if (haUnaPaorolaIniziale)
				for (int conta = 0; conta < lunghezzaStringaConteneteData; conta++) {
					// controllo che nonn sai oltre la fine della stringa
					if (conta + lunghezzaParolaIniziale >= lunghezzaStringaConteneteData) {
						logger.info(
								"Ricerca data all'inetrno di una stringa: parola iniziale {} non trovata non trovata."+
								dopoParolaCompresaDiSpaziOppureNULL);
						return null;
					}

					String stringaChePotrebbeContenereData = stringaConteneteData
							.substring(conta, conta + lunghezzaParolaIniziale).toLowerCase();
					if (stringaChePotrebbeContenereData.equals(dopoParolaCompresaDiSpaziOppureNULL.toLowerCase())) {
						parolaInizialeTrovata = true;
						stringaRidottaDaParolaIniziale = stringaConteneteData
								.substring(conta + lunghezzaParolaIniziale);
						break;
					}
				} // for ricerca separazione

			// definisce carattere di separazione per la data es./-ecc
			String carattereSeparazione = getFirstCharDifferentFromSpace(formatoDataDaTrovare);
			int lunghezzaStringaRidottaDaParolaIniziale = stringaRidottaDaParolaIniziale.length();
			for (int conta = 0; conta < lunghezzaStringaRidottaDaParolaIniziale; conta++) {

				// controllo che nonn sai oltre la fine della stringa
				if (conta + nLettereData >= lunghezzaStringaRidottaDaParolaIniziale) {
					logger.info("Ricerca data all'inetrno di una stringa: data non trovata.");
					return null;
				}

				// prende prima parte della possibile parte di stringa che
				// contiene la data
				String parteProvvisoria = stringaRidottaDaParolaIniziale.substring(conta, conta + largherraFormatoData);
				// se la parte contiene almeno una separazione della data allora
				// controllo perchè potrebbe essere quella
				if (parteProvvisoria.contains(carattereSeparazione)) {
					String parteProvvisoriaSenzaSeparazione = parteProvvisoria.replaceAll(carattereSeparazione, "");
					// se i rimanenti sono tutti numeri allora è una data
					if (isNumeric(parteProvvisoriaSenzaSeparazione)) {
						// ok è una data
						return parteProvvisoria;
					}
				}
			} // for ricerca separazione

		} // formato stringa data
		return null;
	}

	private static String getFirstCharDifferentFromSpace(String formatoDataDaTrovare) {
		for (int conta = 0; conta < formatoDataDaTrovare.length(); conta++) {
			if (!formatoDataDaTrovare.substring(conta, conta + 1).toLowerCase().equals(" ")) {
				return formatoDataDaTrovare.substring(conta, conta + 1);
			}
		}
		return null;
	}

	public static String aggiustaPercorsoFile(String path, String percorsoDoveMettereIlFile) {
		if (percorsoDoveMettereIlFile != null && !percorsoDoveMettereIlFile.isEmpty()) {
			path = percorsoDoveMettereIlFile.replace("/", "//");
			if (!percorsoDoveMettereIlFile.substring(percorsoDoveMettereIlFile.length() - 1).equals("/")) {
				path = path + "//";
			}
		}
		return percorsoDoveMettereIlFile;
	}

	public static String TogliPrimoCrattereDaUnaStringa(String stringa) {
		if (stringa != null)
			if (stringa.length() > 1)
				return stringa.substring(1, stringa.length());
		return null;
	}

	public static String TogliPrimoEUltimoCrattereDaUnaStringa(String stringa) {
		stringa = MyUtil.TogliUltimoCrattereDaUnaStringa(stringa);
		stringa = MyUtil.TogliPrimoCrattereDaUnaStringa(stringa);
		return stringa;
	}

	/**
	 * restituisce ultimi caratteri di una stringa se la string nopn rispetta i
	 * requisiti minimi viene semplicemente restituita
	 * 
	 * @param nota_cuf
	 * @return
	 */
	public static String getUltimiTotCaratteri(String stringa, int nCaratteriUltimi) {
		if (stringa != null && stringa.length() > (nCaratteriUltimi - 1))
			return stringa.substring(stringa.length() - nCaratteriUltimi);

		return stringa;
	}

	/**
	 * se la stringa è uguale a s o S torna true<br>
	 * in tutti gli altri casi false
	 * 
	 * @param stringa
	 * @return
	 */
	public static Boolean setTrueSeS(String stringa) {
		if (stringa != null)
			if (stringa.toUpperCase().equals("S"))
				return true;
		return false;
	}

	/**
	 * 
	 * @param stringa
	 * @return
	 */
	public static String getValoreTAGXMLDaStringa(String stringa, String nomeDelTagInteressato) {
		String tagVuoto = "<" + nomeDelTagInteressato + " />";
		String tagVuoto2 = "<" + nomeDelTagInteressato + "/>";
		String tagDiApertura = "<" + nomeDelTagInteressato + ">";
		String tagDiChiusura = "</" + nomeDelTagInteressato + ">";

		// se nella stringa c'è questo tag vuoto allora il tag non ha valore
		if (stringa.contains(tagVuoto) || stringa.contains(tagVuoto2))
			return null;

		if (StringUtils.isNotBlank(stringa)) {
			String primaParteDellaStringa = restituisciParteDellaStringaDopoUnCertoCarttere(stringa, tagDiApertura, 1);
			String soloValore = restituisciParteDellaStringaPrimaDiUnCertoCarttere(primaParteDellaStringa,
					tagDiChiusura, 1);
			return soloValore;
		}
		return null;
	}

	public static boolean ceAlmenoUnTAG(String stringa) {
		String tagVuoto = "<";
		String tagVuoto2 = "/>";

		// se nella stringa c'è questo tag vuoto allora il tag non ha valore
		if (stringa.contains(tagVuoto) || stringa.contains(tagVuoto2))
			return true;

		return false;
	}

	/**
	 * ritorna la stringa passata solo se questa contiene il carattere
	 * specificato <br>
	 * altrimenti ritorna ""
	 * 
	 * @param stringa
	 * @param carattere
	 * @return
	 */
	public static String getStringOnlyWithChar(String stringa, String carattere) {
		if (StringUtils.isNotBlank(stringa))
			if (stringa.contains(carattere))
				return stringa;

		return "";
	}

	

	public static String ribaltabarre(String indirizzo) {
		if (indirizzo != null && !indirizzo.isEmpty()) {
			String path = indirizzo.replace("\\", "/");
			return path;
		}
		return indirizzo;

	}

	/**
	 * Messo per lasciare il tempo ad access di liberare memoria nelle interazioni con il DB<br>
	 * ritorna true seè stata almeno tentata la pausa
	 * @param conatatoreInterazioni
	 * @param millisecondiPerTimerSleep
	 * @param dopoQuanteInterazioniDeveFareUnaPausa
	 * @return
	 */
	public static boolean controllaPerEventualePausa(int conatatoreInterazioni, int millisecondiPerTimerSleep,
			int dopoQuanteInterazioniDeveFareUnaPausa,String nomeDelmetodoChiamante) {

		if (conatatoreInterazioni==dopoQuanteInterazioniDeveFareUnaPausa){
			try {
				logger.warning("Inizio pausa di {} secondi "+millisecondiPerTimerSleep/1000);
				Thread.sleep(millisecondiPerTimerSleep);
				logger.warning("Fine pausa");
				return true;
			} catch (InterruptedException e) {
				logger.warning("Timer per fare pausa in {} andato in blocco \n {}" + nomeDelmetodoChiamante+ e.getMessage());				
				return true;
			}
		}
		return false;
	}

	
	/**
	 * ripulisce array mettendo tutto a null
	 * @param codiciPazienti
	 */
	public static void cleanArray(String[] array) {
		Arrays.fill(array, null);
	}

	public static boolean isInList(Set<String> keySet, String soloNomeFileSenzaEstensione) {
		for (String elemento : keySet){
			String soloNomeFileSenzaEstensioneInLista = MyUtil.getNameFileWithoutExtension(elemento);
			if (soloNomeFileSenzaEstensioneInLista.equals(soloNomeFileSenzaEstensione))
				return true;
		}

		return false;
	}
	
	public Date getDateWithoutTime(Date data){
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date todayWithZeroTime = null;
			try {
				todayWithZeroTime = formatter.parse(formatter.format(data));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return todayWithZeroTime;
	}
	
	public static String getDateShortFormat(Date data){
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String shortDate = null;
			shortDate = formatter.format(data);
			return shortDate;
	}
	
	
	
//	/**
//	 * Sostituisce un carattere purch� questo non sia preceduto e seguito da un altro carattere
//	 * @param stringaDaModificare
//	 * @param CarettereCheNonDeveTrovarsiDavantiEDietro
//	 * @return
//	 */
//	public static String replaceIfBeforeAndAfter(String stringaDaModificare, String CarettereCheNonDeveTrovarsiDavanti,
//			String CarettereCheNonDeveTrovarsiDietro, String carattereDaSostituire, String carattereDaInseririe){
//				
//		
//		
//		return carattereDaInseririe;
//	}
	
	/**
	 * Trova le virgole tra apici e le sostituisce con punti
	 * @param stringaDaModificare
	 * @return
	 */
	public static String replaceVirgolaTraDueApici(String stringaDaModificare){
		
		int lunghezza = stringaDaModificare.length();
		//il primo giro lo fa cmq
		for (int con=1;con<lunghezza-2;con++){//va dal secondo al penultimo carattere				
			if (stringaDaModificare.substring(con,con+1).equals(",")){//se � ,
				if (stringaDaModificare.substring(con-1,con).equals("'")){
					if (stringaDaModificare.substring(con+1,con+2).equals("'")){
						String primaparte = stringaDaModificare.substring(0, con);
						String secondaparte = stringaDaModificare.substring(con+1,lunghezza);
						stringaDaModificare = primaparte + "." + secondaparte;
					}
				}
			}
		} 
		return stringaDaModificare;
	}
	
	
	/**
	 * Trova i numeri all'interno di una stringa divisi da una virgola e li sosotituisce con il punto, solo i numeri
	 * @param stringaDaModificare
	 * @return
	 */
	public static String replaceVirgolaDiUnNumeroConPunto(String stringaDaModificare){
		
		int lunghezza = stringaDaModificare.length();

		//il primo giro lo fa cmq
		for (int con=1;con<lunghezza-2;con++){//va dal secondo al penultimo carattere				
			if (stringaDaModificare.substring(con,con+1).equals(",")){//se � ,
				if (MyUtil.isNumeric(stringaDaModificare.substring(con-1,con))){
					if (MyUtil.isNumeric(stringaDaModificare.substring(con+1,con+2))){
						String primaparte = stringaDaModificare.substring(0, con);
						String secondaparte = stringaDaModificare.substring(con+1,lunghezza);
						stringaDaModificare = primaparte + "." + secondaparte;
					}
				}
			}
		} 
		return stringaDaModificare;
	}

	public static boolean isNotNullAndMajorOfZero(Float bracciodxintiro) {
		if (bracciodxintiro!=null)
			if (bracciodxintiro>0)
				return true;
		return false;
	}

	public static java.sql.Date addOneWeekToDateSQl(java.sql.Date data) {
		if (data!=null){
			Calendar c = Calendar.getInstance(); 
			c.setTime(data); 
			c.add(Calendar.DATE, 7);
			java.sql.Date startDate= new java.sql.Date(c.getTimeInMillis());
			return startDate;
		}
		return null;
	}

	public static java.sql.Date add4WeekToDateSQl(java.sql.Date data) {
		if (data!=null){
			Calendar c = Calendar.getInstance(); 
			c.setTime(data); 
			c.add(Calendar.DATE, 28);
			java.sql.Date startDate= new java.sql.Date(c.getTimeInMillis());
			return startDate;
		}
		return null;
	}

	public static java.sql.Date less4WeekToDateSQl(java.sql.Date data) {
		if (data!=null){
			Calendar c = Calendar.getInstance(); 
			c.setTime(data); 
			c.add(Calendar.DATE, -21);
			java.sql.Date startDate= new java.sql.Date(c.getTimeInMillis());
			return startDate;
		}
		return null;
	}
	
	

	/**
	 * restituisce ultimo giorno del mese data una data
	 */
	public static java.sql.Date getLastDateOfMounth(Date data){		
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		java.sql.Date ultimaData = new java.sql.Date(c.getTimeInMillis());
		return ultimaData;
	}
	
	/**
	 * ritorna calendar settato ad una data, con il quale ci si pu� fare i conti
	 * formato esempio "yyyy-MM-dd" x sql server
	 * @param data
	 * @param formato
	 */
	public static Calendar setcalendarToDateString(String dataStringa, String formato) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
		java.util.Date data = null;
		try {
			data = dateFormat.parse(dataStringa);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		return c;
	}

	public static java.sql.Date addDayToDateSQl(java.sql.Date data, Integer numberofDay) {
		if (data!=null){
			Calendar c = Calendar.getInstance(); 
			c.setTime(data); 
			c.add(Calendar.DATE, numberofDay);
			java.sql.Date startDate= new java.sql.Date(c.getTimeInMillis());
			return startDate;
		}
		return null;
	}
	
	
	
	public static double parseDoubleNCifre(Integer numero,Integer nCifreDecimali) {
		double numerodouble = numero;
		numerodouble = MyUtil.troncaCifreDec(numerodouble,nCifreDecimali);
		return numerodouble;
	}
	
	/**
	 * in seguito a problemi con dati float
	 * arrotondamento classico
	 * @return
	 */
	public static double troncaCifreDec(double v, int cifreDecimali) {
		double mult = Math.pow(10, cifreDecimali);
		return Math.round(v*mult) / mult;
	}
	
	public static java.sql.Date nextMonday(){

		Date today = getToday();
		String giornoInLettere = getDayOfTheDate(today);
		int aumentareDiNGiorni=0;
		
		if (giornoInLettere.equals("lun")) {
			aumentareDiNGiorni=0;
		}else if (giornoInLettere.equals("mar")) {
			aumentareDiNGiorni=6;
		}else if (giornoInLettere.equals("mer")) {
			aumentareDiNGiorni=5;
		}else if (giornoInLettere.equals("gio")) {
			aumentareDiNGiorni=4;
		}else if (giornoInLettere.equals("ven")) {
			aumentareDiNGiorni=3;
		}else if (giornoInLettere.equals("sab")) {
			aumentareDiNGiorni=2;
		}else if (giornoInLettere.equals("dom")) {
			aumentareDiNGiorni=1;
		}
		
		
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(today); 
		c.add(Calendar.DATE, aumentareDiNGiorni);
		today = new Date(c.getTimeInMillis());
		java.sql.Date primoLunedi= new java.sql.Date(c.getTimeInMillis());
		return primoLunedi;
	}
	
	
	
	
	
	public static String getDayOfTheDate(Date yourDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(yourDate);
		String dayOfWeek = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ITALY);
		logger.info(dayOfWeek);
		return dayOfWeek;
		
	}
	
}
