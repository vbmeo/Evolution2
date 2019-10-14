package com.vbmeo.evolution2.service;
import java.sql.Date;
import java.util.List;

import com.vbmeo.evolution2.model.Misure;

public interface MisureService extends BaseSql<Misure> {

	
	String insert(Date data, float altezza, float peso, float ombelico, float addomesporgente,
			float capezzoli, float toracealto, float braccisxstesobraccio, float braccidxstesobraccio,
			float bracciosxintiro, float bracciodxintiro, float collo, float plicosxombelico, float plicodxombelico,
			float plicometasx, float plicometadx, float plicofiancosx, float plicofiancodx, float peso_mattina_a_vuoto,
			float addome_mattina_a_vuoto);
	
	String update(Integer id, Date data, float altezza, float peso, float ombelico, float addomesporgente,
			float capezzoli, float toracealto, float braccisxstesobraccio, float braccidxstesobraccio,
			float bracciosxintiro, float bracciodxintiro, float collo, float plicosxombelico, float plicodxombelico,
			float plicometasx, float plicometadx, float plicofiancosx, float plicofiancodx, float peso_mattina_a_vuoto,
			float addome_mattina_a_vuoto);
	
	Misure getLastRecord();
	
	List<Misure> getByDate(Date data);
	Date getLastDate();
	Date getLastDateById();

	double getDifferenzaPesoTraDueDate(Date dataSql, Date dataSettimanaPrecedente);

	double getDifferenzaOmbelicoTraDueDate(Date dataSql, Date dataSettimanaPrecedente);

	double getDifferenzaPesoTradataE4SettimanePrima(Date primaData);

	double getDifferenzaOmbelicoTradataE4SettimanePrima(Date dataRichiesta);
	
	
}
