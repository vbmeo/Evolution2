package com.vbmeo.evolution2.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vbmeo.evolution2.mapper.AttivitaMapper;
import com.vbmeo.evolution2.model.Attivita;
import com.vbmeo.evolution2.model.AttivitaDispendio;
import com.vbmeo.evolution2.util.MyUtil;

@Service("attivitaServiceImpl")
public class AttivitaServiceImpl implements AttivitaService {

	@Autowired
	AttivitaMapper attivitaMapper;

	
	@Override
	public Attivita getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Attivita attivita) {
		 attivitaMapper.insert(attivita);
	}

	@Override
	public void update(Attivita object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

/**
 * per correttezza dovrebbe controlare che non ci siano stesse attivitò nello stesso giorno, ma anche questo non è poi così corretto
 */
	@Override
	public String insert(Date data, Integer id_attivita, Date data_fine_settimana, float quantita_in_ore,
			Integer dispendio_energetico, String note, boolean a_vuoto) {
		//crea oggetto senza id
		Attivita attivita = new Attivita(id_attivita, data, data_fine_settimana, quantita_in_ore, dispendio_energetico, note,a_vuoto);
		insert(attivita);
		return null;
	}

	@Override
	public String update(Integer id, Integer id_attivita, Date data, Date data_fine_settimana, float quantita_in_ore,
			Integer dispendio_energetico, String note, boolean a_vuoto) {
		Attivita attivita = new Attivita(id, id_attivita, data, data_fine_settimana, quantita_in_ore, dispendio_energetico, note,a_vuoto);	
		attivitaMapper.update(attivita);
		return null;
	}

	@Override
	public List<Attivita> getByDate(Date data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attivita> getByDateWe(Date data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attivita> getAll() {
		return attivitaMapper.getAll();
	}

	@Override
	public Date getLastDate() {
		return attivitaMapper.getLastDate();
	}

	@Override
	public Date getLastDateWE() {
		return attivitaMapper.getLastDateWE();
	}
	
	@Override
	public List<AttivitaDispendio> getAllDispendi() {
		return attivitaMapper.getAllDispendi();
	}

	@Override
	public List<Attivita> getDispendiEnergeticiSettimanaliAVuoto() {
		return attivitaMapper.getDispendiEnergeticiSettimanaliAVuoto();
	}

	@Override
	public List<Attivita> getDispendiEnergeticiSettimanaliNonAVuoto() {
		return attivitaMapper.getDispendiEnergeticiSettimanaliNonAVuoto();
	}

	@Override
	public List<Attivita> getDispendiEnergeticiSettimanaliTotali() {
		return attivitaMapper.getDispendiEnergeticiSettimanaliTotali();
	}

	@Override
	public List<Attivita> getDispendiEnergeticiSettimanaliAerobici() {
		return attivitaMapper.getDispendiEnergeticiSettimanaliAerobici();
	}

	@Override
	public List<Attivita> getDispendiEnergeticiSettimanaliNonAerobici() {
		return attivitaMapper.getDispendiEnergeticiSettimanaliNonAerobici();
	}

	
	@Override
	public List<Attivita> getDispendiEnergeticiSettimanaliAVuoto(Date indata) {
		return attivitaMapper.getDispendiEnergeticiSettimanaliAVuotoData(indata);
	}

	@Override
	public List<Attivita> getDispendiEnergeticiSettimanaliNonAVuoto(Date indata) {
		return attivitaMapper.getDispendiEnergeticiSettimanaliNonAVuotoData(indata);
	}

	@Override
	public List<Attivita> getDispendiEnergeticiSettimanaliTotali(Date indata) {
		return attivitaMapper.getDispendiEnergeticiSettimanaliTotaliData(indata);
	}

	@Override
	public List<Attivita> getDispendiEnergeticiSettimanaliAerobici(Date indata) {
		return attivitaMapper.getDispendiEnergeticiSettimanaliAerobiciData(indata);
	}

	@Override
	public List<Attivita> getDispendiEnergeticiSettimanaliNonAerobici(Date indata) {
		return attivitaMapper.getDispendiEnergeticiSettimanaliNonAerobiciData(indata);
	}

	@Override
	public List<Attivita> getDispendiEnergeticiSettimanaliTotaliMensili(Date dataIniziosetimanaRichiesta) {
		Date data4SetimanePrima = MyUtil.less4WeekToDateSQl(dataIniziosetimanaRichiesta);
		return attivitaMapper.getDispendiEnergeticiSettimanaliTotaliMensili(data4SetimanePrima, dataIniziosetimanaRichiesta);		
	}
	
	@Override
	public List<Attivita> getDispendiEnergeticiSettimanaliAVuotoMensili(Date dataIniziosetimanaRichiesta) {
		Date data4SetimanePrima = MyUtil.less4WeekToDateSQl(dataIniziosetimanaRichiesta);
		return attivitaMapper.getDispendiEnergeticiSettimanaliAVuotoMensili(data4SetimanePrima, dataIniziosetimanaRichiesta);		
	}
	
	@Override
	public List<Attivita> getDispendiEnergeticiSettimanaliNonAVuotoMensili(Date dataIniziosetimanaRichiesta) {
		Date data4SetimanePrima = MyUtil.less4WeekToDateSQl(dataIniziosetimanaRichiesta);
		return attivitaMapper.getDispendiEnergeticiSettimanaliNonAVuotoMensili(data4SetimanePrima, dataIniziosetimanaRichiesta);		
	}
	
	
	
}
