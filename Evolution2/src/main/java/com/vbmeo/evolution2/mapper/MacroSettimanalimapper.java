package com.vbmeo.evolution2.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.vbmeo.evolution2.model.MacroSettimanali;




public interface MacroSettimanalimapper {
	/**
	 * Utilizzando l’attributo useGeneratedKeys=true assieme a keyProperty="id" e 
	 * keyColumn="id" all’interno dell’annotazione @Options possiamo ottenere l’id dell’utente appena inserito. 
	 * @param student
	 */
	
	@Insert("INSERT INTO dati_macro(data, calorie_sett, carboidrati_sett, proteine_sett, grassi_sett, alcool_sett) VALUES"
			+ "(#{data}, #{calorie_sett}, #{carboidrati_sett}, #{proteine_sett}, #{grassi_sett}, #{alcool_sett})")
	@Options(useGeneratedKeys = true, keyProperty = "id", flushCache = true, keyColumn = "id")
	public void insert(MacroSettimanali macroSettimanali);

	@Select("SELECT * FROM dati_macro WHERE data = #{data}")
	public List<MacroSettimanali> getByDate(Date data);

	@Select("select * from dati_macro where id=#{id}")
	public MacroSettimanali getById(Integer id);

	@Select("select * from dati_macro order by data DESC")
	public List<MacroSettimanali> getAll();

	@Delete("delete from dati_macro where id=#{id}")
	public void delete(int id);

	@Update("update dati_macro set "
			+ "data=#{data}, calorie_sett=#{calorie_sett}, lastName=#{carboidrati_sett}, userName=#{carboidrati_sett}, "
			+ "proteine_sett=#{proteine_sett}, grassi_sett=#{grassi_sett}, alcool_sett=#{alcool_sett} "
			+ "where id=#{id}")
	public void update(MacroSettimanali macroSettimanali);

	@Select("select data from dati_macro order by data DESC LIMIT 1")
	public Date getLastDate();
}
