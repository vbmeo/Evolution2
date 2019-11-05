package com.vbmeo.evolution2.controller;

import java.io.Console;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
	
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import com.vbmeo.evolution2.exception.CustomGenericException;


@ControllerAdvice
public class GlobalExceptionController {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);
	
	
	@ExceptionHandler(CustomGenericException.class)
	public String[] handleCustomException(CustomGenericException ex) {
		String[] errori = new String[2];
		errori[0] = ex.getErrCode();
		errori[1] = ex.getErrMsg();
		return errori;

	}
	
	
	/**
	 * gestione data sbagliata o parametro con sintassi sbagliato data YyyyMMdd -
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex,WebRequest request) {
		String descrizione = request.getDescription(true);
		Iterator<String> parametri = request.getParameterNames();
		logger.error("Descrizione request ({})",descrizione);
		while(parametri.hasNext()) {
	         String parametro = parametri.next();
	         logger.error("Con parametro ({})",parametro);
	      }
		Throwable causa = ex.getCause();
		if (causa!=null)
			logger.error(causa.getMessage());
		return new ResponseEntity<String>("Dato passato sbagliato. La data deve essere in formato (es. 2014-12-01)", HttpStatus.BAD_REQUEST);
	}
	

	//throws PersistenceException,SqlSessionException
	@ExceptionHandler(PersistenceException.class)
	public ResponseEntity<String> handleIllegalPersistenceException(PersistenceException ex,WebRequest request) {
		String descrizione = request.getDescription(true);
		Iterator<String> parametri = request.getParameterNames();
		logger.error("Descrizione request ({})",descrizione);
		while(parametri.hasNext()) {
	         String parametro = parametri.next();
	         logger.error("Con parametro ({})",parametro);
	      }
		Throwable causa = ex.getCause();
		if (causa!=null)
			logger.error(causa.toString());
		return new ResponseEntity<String>("Errore nella query al db", HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(SqlSessionException.class)
	public ResponseEntity<String> handleIllegalInvocationSqlSessionException(SqlSessionException ex,WebRequest request) {
		String descrizione = request.getDescription(true);
		Iterator<String> parametri = request.getParameterNames();
		logger.error("Descrizione request ({})",descrizione);
		while(parametri.hasNext()) {
	         String parametro = parametri.next();
	         logger.error("Con parametro ({})",parametro);
	      }
		Throwable causa = ex.getCause();
		if (causa!=null)
			logger.error(causa.toString());
		return new ResponseEntity<String>("Errore nella sintassi della query al db", HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(MySQLSyntaxErrorException.class)
	public ResponseEntity<String> handleIllegalInvocationMySQLSyntaxErrorException(MySQLSyntaxErrorException ex,WebRequest request) {
		String descrizione = request.getDescription(true);
		Iterator<String> parametri = request.getParameterNames();
		logger.error("Descrizione request ({})",descrizione);
		while(parametri.hasNext()) {
	         String parametro = parametri.next();
	         logger.error("Con parametro ({})",parametro);
	      }
		Throwable causa = ex.getCause();
		if (causa!=null)
			logger.error(causa.toString());
		return new ResponseEntity<String>("Errore nella sintassi della query al db MySql ", HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(BadSqlGrammarException.class)
	public ResponseEntity<String> handleIllegalInvocationBadSqlGrammarException(BadSqlGrammarException ex,WebRequest request) {
		String descrizione = request.getDescription(true);
		Iterator<String> parametri = request.getParameterNames();
		logger.error("Descrizione request ({})",descrizione);
		while(parametri.hasNext()) {
	         String parametro = parametri.next();
	         logger.error("Con parametro ({})",parametro);
	      }
		Throwable causa = ex.getCause();
		if (causa!=null)
			logger.error(causa.toString());
		return new ResponseEntity<String>("Errore nella sintassi della query al db MySql ", HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllException(Exception ex) {
		Throwable causa = ex.getCause();
		if (causa!=null)
			logger.error(causa.toString());
		return new ResponseEntity<String>("Errore generico...", HttpStatus.BAD_REQUEST);

	}
	
	
}
