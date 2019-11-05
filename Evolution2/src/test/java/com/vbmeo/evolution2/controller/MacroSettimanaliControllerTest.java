package com.vbmeo.evolution2.controller;

import static org.junit.Assert.*;

import org.junit.Test;





import com.fasterxml.jackson.core.type.TypeReference;
import com.vbmeo.evolution2.model.MacroSettimanali;
import com.vbmeo.evolution2.service.MacroSettimanaliServiceImpl;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MacroSettimanaliControllerTest extends APIBaseTest  {

	@Autowired
	private MacroSettimanaliServiceImpl macroSettimanaliService;
	
	
	 @Before
	    public void init(){
		 String a="";
		 
		 	//macroSettimanaliService ;
	    }
	
	
	@Test
	public void testList()  throws Exception{
		 MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/macro")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andReturn();
	        List<MacroSettimanali> shops = this.objectMapper.readValue(
	                mvcResult.getResponse().getContentAsByteArray(),
	                new TypeReference<List<MacroSettimanali>>(){});
	        assertEquals(1, shops.size());

	        MacroSettimanali shop = shops.get(0);
	        assertEquals("222", shop.getCarboidrati_sett());
		
	}

	@Test
	public void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertO() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}
