package com.vbmeo.evolution2.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSessionException;

import com.vbmeo.evolution2.model.Attivita;
import com.vbmeo.evolution2.model.AttivitaDispendio;

public interface AttivitaMapper {
	
	@Insert("INSERT INTO attivita_fisiche(data, id_attivita, data_fine_settimana, quantita_in_ore, dispendio_energetico, note,a_vuoto) VALUES"
			+ "(#{data}, #{id_attivita}, #{data_fine_settimana}, #{quantita_in_ore}, #{dispendio_energetico}, #{note},#{a_vuoto})")
	@Options(useGeneratedKeys = true, keyProperty = "id", flushCache = true, keyColumn = "id")
	public void insert(Attivita attivita);
	
	
	
	@Update("UPDATE attivita_fisiche SET data=#{data} , id_attivita=#{id_attivita}, data_fine_settimana=#{data_fine_settimana}"
			+ ", quantita_in_ore=#{quantita_in_ore}, dispendio_energetico=#{dispendio_energetico}, note=#{note}, a_vuoto=#{a_vuoto} "
			+ " where id=#{id}")
	@Options(useGeneratedKeys = true, keyProperty = "id", flushCache = true, keyColumn = "id")
	public void update(Attivita attivita);
	

	@Select("select attivita_fisiche.id, attivita_fisiche.id_attivita, " //,  as id_attivita
			+ "attivita_fisiche.data, attivita_fisiche.data_fine_settimana, "
			+ "attivita_fisiche.quantita_in_ore, " 
			+ "attivita_fisiche_dispendio.nome_attivita, "
			+ "attivita_fisiche.dispendio_energetico, " 
			+ "attivita_fisiche.note, " 
			+ "attivita_fisiche.a_vuoto "
			+ "from attivita_fisiche "
			+ "join  evolution.attivita_fisiche_dispendio " + 
			"on attivita_fisiche.id_attivita = attivita_fisiche_dispendio.id " 
			+ "order by attivita_fisiche.data_fine_settimana, attivita_fisiche.data")// order by data DESC
	public List<Attivita> getAll() throws PersistenceException,SqlSessionException;

	//va in errore se si menziona il db evolution.
	@Select("select attivita_fisiche.id, attivita_fisiche.id_attivita, " //,  as id_attivita
			+ "attivita_fisiche.data, attivita_fisiche.data_fine_settimana, "
			+ "attivita_fisiche.quantita_in_ore, " 
			+ "attivita_fisiche_dispendio.nome_attivita, "
			+ "attivita_fisiche.dispendio_energetico, " 
			+ "attivita_fisiche.note, " 
			+ "attivita_fisiche.a_vuoto "
			+ "from attivita_fisiche "
			+ "join  evolution.attivita_fisiche_dispendio " + 
			"on attivita_fisiche.id_attivita = attivita_fisiche_dispendio.id " 
			+ " where attivita_fisiche.data =#{data}"
			+ " order by attivita_fisiche.data_fine_settimana, attivita_fisiche.data")
	public List<Attivita> getByDate(Date data);
	
	
	/**
	 * tutte le misure corrispondenti a quella settimana la data we � il luned� successivo
	 * @param data
	 * @return
	 */
	@Select("select attivita_fisiche.id, attivita_fisiche.id_attivita, " //,  as id_attivita
			+ "attivita_fisiche.data, attivita_fisiche.data_fine_settimana, "
			+ "attivita_fisiche.quantita_in_ore, " 
			+ "attivita_fisiche_dispendio.nome_attivita, "
			+ "attivita_fisiche.dispendio_energetico, " 
			+ "attivita_fisiche.note, "
			+ "attivita_fisiche.a_vuoto "
			+ "from attivita_fisiche "
			+ "join  evolution.attivita_fisiche_dispendio " + 
			"on attivita_fisiche.id_attivita = attivita_fisiche_dispendio.id " 
			+ " where attivita_fisiche.data_fine_settimana =#{data}"
			+ " order by attivita_fisiche.data_fine_settimana, attivita_fisiche.data")
	public List<Attivita> getByDateWe(Date data);
	
	
	@Select("select data from attivita_fisiche order by data DESC LIMIT 1")
	public Date getLastDate();

	@Select("select data_fine_settimana from attivita_fisiche order by data DESC LIMIT 1")
	public Date getLastDateWE();
	

	@Select("select * from attivita_fisiche_dispendio order by nome_attivita")
	public List<AttivitaDispendio> getAllDispendi();
	
	
	/**
	 * FA LA SOMMA DELLE CALORIE SPESE NELLA SETTIMANA PER ATTIVITA' A VUOTO
	 * @return
	 */
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
	+ "FROM evolution.attivita_fisiche "
	+ "WHERE a_vuoto=true and data_fine_settimana <= CURDATE() "
	+ "GROUP BY data_fine_settimana")
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliAVuoto(); 
	
	
	/**
	 * FA LA SOMMA DELLE CALORIE SPESE NELLA SETTIMANA PER ATTIVITA' NON A VUOTO
	 * @return
	 */
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
	+ "FROM evolution.attivita_fisiche "
	+ "WHERE a_vuoto=false and data_fine_settimana <= CURDATE() "
	+ "GROUP BY data_fine_settimana")
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliNonAVuoto(); 
	
	/**
	 * FA LA SOMMA DELLE CALORIE SPESE NELLA SETTIMANA PER tutte le ATTIVITA'
	 * @return
	 */
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
	+ "FROM evolution.attivita_fisiche "
	+ "WHERE data_fine_settimana <= CURDATE() "
	+ "GROUP BY data_fine_settimana")
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliTotali(); 
	
	
	/**
	 * FA LA SOMMA DELLE CALORIE SPESE NELLA SETTIMANA PER ATTIVITA' AEROBICHE
	 * @return
	 */
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
	+ "FROM evolution.attivita_fisiche "
	+ "join evolution.attivita_fisiche_dispendio on evolution.attivita_fisiche.id_attivita = evolution.attivita_fisiche_dispendio.id "
	+ "WHERE attivita_fisiche_dispendio.aerobica = 1 and data_fine_settimana <= CURDATE() "
	+ "GROUP BY data_fine_settimana")
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliAerobici(); 
	
	
	/**
	 * FA LA SOMMA DELLE CALORIE SPESE NELLA SETTIMANA PER ATTIVITA' NON AEROBICHE
	 * @return
	 */
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
	+ "FROM evolution.attivita_fisiche "
	+ "join evolution.attivita_fisiche_dispendio on evolution.attivita_fisiche.id_attivita = evolution.attivita_fisiche_dispendio.id "
	+ "WHERE attivita_fisiche_dispendio.aerobica = 0 and data_fine_settimana <= CURDATE() "
	+ "GROUP BY data_fine_settimana")
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliNonAerobici(); 
	
	
	
	
	/**
	 * FA LA SOMMA DELLE CALORIE SPESE NELLA SETTIMANA PER ATTIVITA' A VUOTO
	 * @return
	 */
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
	+ "FROM evolution.attivita_fisiche "
	+ "WHERE a_vuoto=true and data_fine_settimana = #{inData} "
	+ "GROUP BY data_fine_settimana")
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliAVuotoData(Date inData); 
	
	
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
			+ "FROM evolution.attivita_fisiche "
			+ "WHERE a_vuoto=true and data_fine_settimana >= #{dataInzio} and data_fine_settimana <= #{dataFine} "
			+ "GROUP BY data_fine_settimana")
			public List<Attivita> getDispendiEnergeticiEOreSettimanaliAVuotoTraDueDate(@Param("dataInzio") Date dataInzio, @Param("dataFine") Date dataFine); 
			
	
	/**
	 * FA LA SOMMA DELLE CALORIE SPESE NELLA SETTIMANA PER ATTIVITA' NON A VUOTO
	 * @return
	 */
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
	+ "FROM evolution.attivita_fisiche "
	+ "WHERE a_vuoto=false and data_fine_settimana = #{inData}"
	+ "GROUP BY data_fine_settimana")
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliNonAVuotoData(Date inData); 
	
	/**
	 * FA LA SOMMA DELLE CALORIE SPESE NELLA SETTIMANA PER tutte le ATTIVITA'
	 * @return
	 */
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
	+ "FROM evolution.attivita_fisiche "
	+ "WHERE data_fine_settimana = #{inData} "
	+ "GROUP BY data_fine_settimana")
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliTotaliData(Date inData); 
	
	
	
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
			+ "FROM evolution.attivita_fisiche "
			+ "WHERE data_fine_settimana >= #{dataInzio} and data_fine_settimana <= #{dataFine} ")//
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliTraDueDate(@Param("dataInzio") Date dataInzio, @Param("dataFine") Date dataFine); 
	
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
			+ "FROM evolution.attivita_fisiche "
			+ "WHERE data_fine_settimana >= #{dataInzio} and data_fine_settimana <= #{dataFine} "
			+ "GROUP BY data_fine_settimana")//
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliTraDueDateDiviseInSettimane(@Param("dataInzio") Date dataInzio, @Param("dataFine") Date dataFine); 
	
	
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
			+ "FROM evolution.attivita_fiche "
			+ "WHERE a_vuoto=true and data_fine_settimana >= #{dataInzio} and data_fine_settimana <= #{dataFine} ")
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliAVuotoMensili(@Param("dataInzio") Date dataInzio, @Param("dataFine") Date dataFine)  ; 
			
	
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
			+ "FROM evolution.attivita_fisiche "
			+ "WHERE a_vuoto=false and data_fine_settimana >= #{dataInzio} and data_fine_settimana <= #{dataFine} ")
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliNonAVuotoMensili(@Param("dataInzio") Date dataInzio, @Param("dataFine") Date dataFine); 
			
	
	/**
	 * da lista di attivit� non somma gli energetici o le ore
	 * perch� raggruppa per date fine settimana
	 * @param dataInzio
	 * @param dataFine
	 * @return
	 */
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
			+ "FROM evolution.attivita_fisiche "
			+ "WHERE a_vuoto=false and data_fine_settimana >= #{dataInzio} and data_fine_settimana <= #{dataFine} "
			+ "group by data_fine_settimana ")
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliNonAVuotoTraDueDate(@Param("dataInzio") Date dataInzio, @Param("dataFine") Date dataFine); 
	
	
	
	/**
	 * FA LA SOMMA DELLE CALORIE SPESE NELLA SETTIMANA PER ATTIVITA' AEROBICHE
	 * @return
	 */
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
	+ "FROM evolution.attivita_fisiche "
	+ "join evolution.attivita_fisiche_dispendio on evolution.attivita_fisiche.id_attivita = evolution.attivita_fisiche_dispendio.id "
	+ "WHERE attivita_fisiche_dispendio.aerobica = 1 and data_fine_settimana = #{inData} "
	+ "GROUP BY data_fine_settimana")
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliAerobiciData(Date inData); 
	
	
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
			+ "FROM evolution.attivita_fisiche "
			+ "join evolution.attivita_fisiche_dispendio on evolution.attivita_fisiche.id_attivita = evolution.attivita_fisiche_dispendio.id "
			+ "WHERE attivita_fisiche_dispendio.aerobica = 1 and data_fine_settimana >= #{dataInzio} and data_fine_settimana <= #{dataFine} "
			+ "GROUP BY data_fine_settimana")
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliAerobiciTraDueDate(@Param("dataInzio") Date dataInzio, @Param("dataFine") Date dataFine); 
	
	
	/**
	 * FA LA SOMMA DELLE CALORIE SPESE NELLA SETTIMANA PER ATTIVITA' NON AEROBICHE
	 * @return
	 */
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
	+ "FROM evolution.attivita_fisiche "
	+ "join evolution.attivita_fisiche_dispendio on evolution.attivita_fisiche.id_attivita = evolution.attivita_fisiche_dispendio.id "
	+ "WHERE attivita_fisiche_dispendio.aerobica = 0 and data_fine_settimana = #{inData} "
	+ "GROUP BY data_fine_settimana")
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliNonAerobiciData(Date inData); 
	
	
	@Select("SELECT data_fine_settimana, SUM(dispendio_energetico) as dispendio_energetico, SUM(quantita_in_ore) AS quantita_in_ore "
			+ "FROM evolution.attivita_fisiche "
			+ "join evolution.attivita_fisiche_dispendio on evolution.attivita_fisiche.id_attivita = evolution.attivita_fisiche_dispendio.id "
			+ "WHERE attivita_fisiche_dispendio.aerobica = 0 and data_fine_settimana >= #{dataInzio} and data_fine_settimana <= #{dataFine}"
			+ "GROUP BY data_fine_settimana")
	public List<Attivita> getDispendiEnergeticiEOreSettimanaliNonAerobiciTraDueDate(@Param("dataInzio") Date dataInzio, @Param("dataFine") Date dataFine); 
	
	
	
}
