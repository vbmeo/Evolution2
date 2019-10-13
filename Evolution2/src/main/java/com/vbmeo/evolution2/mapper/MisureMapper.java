package com.vbmeo.evolution2.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.vbmeo.evolution2.model.Misure;

public interface MisureMapper {
	@Insert("INSERT INTO dati_misure(data, altezza, peso, ombelico, addomesporgente, capezzoli,toracealto,"
			+ "braccisxstesobraccio,braccidxstesobraccio,bracciosxintiro,bracciodxintiro,collo,plicosxombelico,plicodxombelico"
			+ ",plicometasx,plicometadx,plicofiancosx,plicofiancodx,peso_mattina_a_vuoto,addome_mattina_a_vuoto) VALUES"
			+ "(#{data}, #{altezza}, #{peso}, #{ombelico}, #{addomesporgente}, #{capezzoli}, #{toracealto}"
			+ ", #{braccisxstesobraccio}, #{braccidxstesobraccio}, #{bracciosxintiro}, #{bracciodxintiro}, #{collo}"
			+ ", #{plicosxombelico}, #{plicodxombelico}"
			+ ", #{plicometasx}, #{plicometadx}, #{plicofiancosx}, #{plicofiancodx}, #{peso_mattina_a_vuoto}, #{addome_mattina_a_vuoto})")
	@Options(useGeneratedKeys = true, keyProperty = "id", flushCache = true, keyColumn = "id")
	public void insert(Misure misure);

	@Select("SELECT * FROM dati_misure WHERE data = #{data}")
	public List<Misure> getByDate(Date data);

	@Select("select * from dati_misure where id=#{id}")
	public Misure getById(Integer id);

	@Select("select * from dati_misure order by data")// order by data DESC
	public List<Misure> getAll();

	@Delete("delete from dati_misure where id=#{id}")
	public void delete(int id);

	
	//attenzione non usare parentesi () per inserire i valori data=#{data},
	@Update("update dati_misure set "
			+ " data=#{data}, peso=#{peso},altezza=#{altezza},"
			+ " ombelico=#{ombelico},"
			+ " addomesporgente=#{addomesporgente}, capezzoli=#{capezzoli}, toracealto=#{toracealto}"
			+ ", braccisxstesobraccio=#{braccisxstesobraccio}, braccidxstesobraccio=#{braccidxstesobraccio}, bracciosxintiro=#{bracciosxintiro},"
			+ " bracciodxintiro=#{bracciodxintiro}, collo=#{collo}" 
			+ ", plicosxombelico=#{plicosxombelico}, plicodxombelico=#{plicodxombelico}" 
			+ ", plicometasx=#{plicometasx}, plicometadx=#{plicometadx}, plicofiancosx=#{plicofiancosx}, plicofiancodx=#{plicofiancodx},"
			+ " peso_mattina_a_vuoto=#{peso_mattina_a_vuoto}, addome_mattina_a_vuoto=#{addome_mattina_a_vuoto}"
			+ " where id=#{id}")
	public void update(Misure misure);

	@Select("select data from dati_misure order by data DESC LIMIT 1")
	public Date getLastDate();
	
	@Select("select data from dati_misure order by id DESC LIMIT 1")
	public Date getLastDateById();
	
	
	@Select("select * from dati_misure order by data DESC LIMIT 1")
	public Misure getLastRecord();

	@Select("SELECT peso FROM evolution.dati_misure where data = #{data}") 
	public Float getPesoDaDate(Date data);
	
	@Select("SELECT ombelico FROM evolution.dati_misure where data = #{data}") 
	public Float getOmbelicoDaDate(Date data);
	
	/**
	 * per selezionare solo le date del lunedì, l'ho messo in join con le macro, solo le date che corrispondono alle macro vengono prese in considerazione
	 * @param dataInzio
	 * @param dataFine
	 * @return
	 */
	@Select("SELECT dati_misure.* FROM dati_misure "
			+ "join dati_macro on dati_macro.data = dati_misure.data "
			+ "where dati_misure.data >= #{dataInzio} and dati_misure.data <= #{dataFine}")
	public List<Misure> getMisureTraDueDateCheCompaionoInMacro(@Param("dataInzio") Date dataInzio, @Param("dataFine") Date dataFine);

	
}
