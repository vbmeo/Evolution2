package com.vbmeo.evolution2.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import com.vbmeo.evolution2.model.Parametri;

public interface ParametriMapper {

	@Select("select * "
			+ "from parametri "
			+ "order by nomeParametro")
	public List<Parametri> getAll();
}
