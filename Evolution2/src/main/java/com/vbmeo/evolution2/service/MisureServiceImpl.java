package com.vbmeo.evolution2.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vbmeo.evolution2.mapper.MisureMapper;
import com.vbmeo.evolution2.model.Misure;
import com.vbmeo.evolution2.util.MyUtil;

@Service("misureServiceImpl")
public class MisureServiceImpl implements MisureService{

	@Autowired
	private MisureMapper misureMapper;
	
	
	/**
	 * ritorna macro inserita
	 * la data vole in formato YYYY-MM-DD
	 * @param Misure
	 */
	@Override
	@Transactional
	public void insert(Misure misure) {
		misureMapper.insert(misure);
	}

	
	//questo non va nel mapper, chiamerà poi insert con oggetto
	@Override
	public String insert(Date data, float altezza, float peso, float ombelico, float addomesporgente, float capezzoli,
			float toracealto, float braccisxstesobraccio, float braccidxstesobraccio, float bracciosxintiro,
			float bracciodxintiro, float collo, float plicosxombelico, float plicodxombelico, float plicometasx,
			float plicometadx, float plicofiancosx, float plicofiancodx, float peso_mattina_a_vuoto,
			float addome_mattina_a_vuoto) {
		
		//crea oggetto senza id
		Misure misure = new Misure(data, altezza, peso, ombelico, addomesporgente, capezzoli, toracealto, braccisxstesobraccio, 
				braccidxstesobraccio, bracciosxintiro, bracciodxintiro, collo, plicosxombelico, plicodxombelico, plicometasx, 
				plicometadx, plicofiancosx, plicofiancodx, peso_mattina_a_vuoto, addome_mattina_a_vuoto);
		
		//controllo data doppia//No! può esserci in tal caso deve fare un upgrade
				List<Misure> macroGiaPresente = getByDate(data);
				if (macroGiaPresente!=null&&macroGiaPresente.size()>0) {
					//data già presente, effettua un upgrade mettendo solo i valori che prima non c'erano
					//verrà rinserita la vecchia musura con i dati aggiuntivi
					Misure misuravecchia = macroGiaPresente.get(0);
					if ((misuravecchia.getAddome_mattina_a_vuoto()==0)||(misuravecchia.getAddome_mattina_a_vuoto()>0&&misure.getAddome_mattina_a_vuoto()>0&&misuravecchia.getAddome_mattina_a_vuoto()!=misure.getAddome_mattina_a_vuoto()))
						misuravecchia.setAddome_mattina_a_vuoto(misure.getAddome_mattina_a_vuoto());
					
					if ((misuravecchia.getAddomesporgente()==0)||(misuravecchia.getAddomesporgente()>0&&misure.getAddomesporgente()>0&&misuravecchia.getAddomesporgente()!=misure.getAddomesporgente()))
						misuravecchia.setAddomesporgente(misure.getAddomesporgente());
					
					if ((misuravecchia.getAltezza()==0)||(misuravecchia.getAltezza()>0&&misure.getAltezza()>0&&misuravecchia.getAltezza()!=misure.getAltezza()))
						misuravecchia.setAltezza(misure.getAltezza());
					
					if ((misuravecchia.getBraccidxstesobraccio()==0)||(misuravecchia.getBraccidxstesobraccio()>0&&misure.getBraccidxstesobraccio()>0&&misuravecchia.getBraccidxstesobraccio()!=misure.getBraccidxstesobraccio()))
						misuravecchia.setBraccidxstesobraccio(misure.getBraccidxstesobraccio());
					
					if ((misuravecchia.getBracciodxintiro()==0)||(misuravecchia.getBracciodxintiro()>0&&misure.getBracciodxintiro()>0&&misuravecchia.getBracciodxintiro()!=misure.getBracciodxintiro()))
						misuravecchia.setBracciodxintiro(misure.getBracciodxintiro());
					
					if ((misuravecchia.getBracciosxintiro()==0)||(misuravecchia.getBracciosxintiro()>0&&misure.getBracciosxintiro()>0&&misuravecchia.getBracciosxintiro()!=misure.getBracciosxintiro()))
						misuravecchia.setBracciosxintiro(misure.getBracciosxintiro());
					
					if ((misuravecchia.getBraccisxstesobraccio()==0)||(misuravecchia.getBraccisxstesobraccio()>0&&misure.getBraccisxstesobraccio()>0&&misuravecchia.getBraccisxstesobraccio()!=misure.getBraccisxstesobraccio()))
						misuravecchia.setBraccisxstesobraccio(misure.getBraccisxstesobraccio());
					
					if ((misuravecchia.getCapezzoli()==0)||(misuravecchia.getCapezzoli()>0&&misure.getCapezzoli()>0&&misuravecchia.getCapezzoli()!=misure.getCapezzoli()))
						misuravecchia.setCapezzoli(misure.getCapezzoli());
					
					if ((misuravecchia.getCollo()==0)||(misuravecchia.getCollo()>0&&misure.getCollo()>0&&misuravecchia.getCollo()!=misure.getCollo()))
						misuravecchia.setCollo(misure.getCollo());
					
					if ((misuravecchia.getOmbelico()==0)||(misuravecchia.getOmbelico()>0&&misure.getOmbelico()>0&&misuravecchia.getOmbelico()!=misure.getOmbelico()))
						misuravecchia.setOmbelico(misure.getOmbelico());
					
					if ((misuravecchia.getPeso()==0)||(misuravecchia.getPeso()>0&&misure.getPeso()>0&&misuravecchia.getPeso()!=misure.getPeso()))
						misuravecchia.setPeso(misure.getPeso());
					
					if ((misuravecchia.getPeso_mattina_a_vuoto()==0)||(misuravecchia.getPeso_mattina_a_vuoto()>0&&misure.getPeso_mattina_a_vuoto()>0&&misuravecchia.getPeso_mattina_a_vuoto()!=misure.getPeso_mattina_a_vuoto()))
						misuravecchia.setPeso_mattina_a_vuoto(misure.getPeso_mattina_a_vuoto());
					
					if ((misuravecchia.getPlicodxombelico()==0)||(misuravecchia.getPlicodxombelico()>0&&misure.getPlicodxombelico()>0&&misuravecchia.getPlicodxombelico()!=misure.getPlicodxombelico()))
						misuravecchia.setPlicodxombelico(misure.getPlicodxombelico());
					
					if ((misuravecchia.getPlicofiancodx()==0)||(misuravecchia.getPlicofiancodx()>0&&misure.getPlicofiancodx()>0&&misuravecchia.getPlicofiancodx()!=misure.getPlicofiancodx()))
						misuravecchia.setPlicofiancodx(misure.getPlicofiancodx());
					
					if ((misuravecchia.getPlicofiancosx()==0)||(misuravecchia.getPlicofiancosx()>0&&misure.getPlicofiancosx()>0&&misuravecchia.getPlicofiancosx()!=misure.getPlicofiancosx()))
						misuravecchia.setPlicofiancosx(misure.getPlicofiancosx());
					
					if ((misuravecchia.getPlicometadx()==0)||(misuravecchia.getPlicometadx()>0&&misure.getPlicometadx()>0&&misuravecchia.getPlicometadx()!=misure.getPlicometadx()))
						misuravecchia.setPlicometadx(misure.getPlicometadx());
					
					if ((misuravecchia.getPlicometasx()==0)||(misuravecchia.getPlicometasx()>0&&misure.getPlicometasx()>0&&misuravecchia.getPlicometasx()!=misure.getPlicometasx()))
						misuravecchia.setPlicometasx(misure.getPlicometasx());
					
					if ((misuravecchia.getPlicosxombelico()==0)||(misuravecchia.getPlicosxombelico()>0&&misure.getPlicosxombelico()>0&&misuravecchia.getPlicosxombelico()!=misure.getPlicosxombelico()))
						misuravecchia.setPlicosxombelico(misure.getPlicosxombelico());
					
					if ((misuravecchia.getToracealto()==0)||(misuravecchia.getToracealto()>0&&misure.getToracealto()>0&&misuravecchia.getToracealto()!=misure.getToracealto()))
						misuravecchia.setToracealto(misure.getToracealto());
					
					update(misuravecchia);
				}else {
					insert(misure);
				}
				
				
		
		return null;
	}
	
	@Override
	public List<Misure> getByDate(Date data) {
		return misureMapper.getByDate(data);
	}
	
	
	
	@Override
	public List<Misure> getAll() {
		return misureMapper.getAll();
	}

	
	@Override
	public void delete(Integer id) {
		misureMapper.delete(id);
	}
	
	@Override
	public Misure getById(Integer id) {
		return misureMapper.getById(id);
	}

	
	@Override
	public void update(Misure misure) {
		misureMapper.update(misure);
	}

	


	//questo non è nel mapper, chiama poi update con parametro oggetto
	@Override
	public String update(Integer id, Date data, float altezza, float peso, float ombelico, float addomesporgente,
			float capezzoli, float toracealto, float braccisxstesobraccio, float braccidxstesobraccio,
			float bracciosxintiro, float bracciodxintiro, float collo, float plicosxombelico, float plicodxombelico,
			float plicometasx, float plicometadx, float plicofiancosx, float plicofiancodx, float peso_mattina_a_vuoto,
			float addome_mattina_a_vuoto) {


		//crea oggetto completo
				Misure misure = new Misure(id,data, altezza, peso, ombelico, addomesporgente, capezzoli, toracealto, braccisxstesobraccio, 
						braccidxstesobraccio, bracciosxintiro, bracciodxintiro, collo, plicosxombelico, plicodxombelico, plicometasx, 
						plicometadx, plicofiancosx, plicofiancodx, peso_mattina_a_vuoto, addome_mattina_a_vuoto);
				
		
		update(misure);		
		return null;
	}

	@Override
	public Misure getLastRecord() {
		return misureMapper.getLastRecord();
	}


	@Override
	public Date getLastDate() {
		return misureMapper.getLastDate();
	}

	
	
	@Override
	public Date getLastDateById() {
		return misureMapper.getLastDate();
	}


	@Override
	public double getDifferenzaPesoTraDueDate(Date primaData, Date menoSecondaData) {
		Float primoPeso = misureMapper.getPesoDaDate(primaData);
		Float secondoPeso = misureMapper.getPesoDaDate(menoSecondaData);
		double primo = MyUtil.troncaCifreDec(primoPeso,3);
		double secondo = MyUtil.troncaCifreDec(secondoPeso,3);
		if (primo>0&& secondo>0) {
			double diff =primo- secondo;
			return diff;
		}
		return 0;//instanza float
	}

	
	@Override
	public double getDifferenzaPesoTradataE4SettimanePrima(Date dataUltima) {
		Date data4SetimanePrima = MyUtil.less4WeekToDateSQl(dataUltima);	
		Float primoPeso = misureMapper.getPesoDaDate(data4SetimanePrima);
		Float secondoPeso = misureMapper.getPesoDaDate(dataUltima);
		double primo = MyUtil.troncaCifreDec(primoPeso,3);
		double secondo = MyUtil.troncaCifreDec(secondoPeso,3);
		if (primo>0&& secondo>0) {
			double diff =secondo- primo;
			return diff;
		}
		return 0;//instanza float
	}

	
	

	@Override
	public double getDifferenzaOmbelicoTraDueDate(Date primaData, Date menoSecondaData) {
		Float primoPeso = misureMapper.getOmbelicoDaDate(primaData);
		Float secondoPeso = misureMapper.getOmbelicoDaDate(menoSecondaData);
		double primo = MyUtil.troncaCifreDec(primoPeso,3);
		double secondo = MyUtil.troncaCifreDec(secondoPeso,3);
		if (primo>0&& secondo>0) {
			double diff = secondo- primo;
			return diff;
		}
		return 0;//instanza float
	}


	@Override
	public double getDifferenzaOmbelicoTradataE4SettimanePrima(Date dataRichiesta) {
		Date data4SetimanePrima = MyUtil.less4WeekToDateSQl(dataRichiesta);	
		Float primoPeso = misureMapper.getOmbelicoDaDate(data4SetimanePrima);
		Float secondoPeso = misureMapper.getOmbelicoDaDate(dataRichiesta);
		double primo = MyUtil.troncaCifreDec(primoPeso,3);
		double secondo = MyUtil.troncaCifreDec(secondoPeso,3);
		if (primo>0&& secondo>0) {
			double diff =secondo - primo;
			return diff;
		}
		return 0;//instanza float
	}

}
