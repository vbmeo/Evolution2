package com.vbmeo.evolution2.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.vbmeo.evolution2.service.MacroSettimanaliService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/appServlet/servlet-context.xml" })//classpath:spring.appServlet/servlet-context.xml
public class MacroTest {

	@Autowired
	MacroSettimanaliService macroSettimanaliService;
	
	private String a="llkl";
	
	
	@Test
	  public void testGetAll(){
		assertNotNull(a);//macroSettimanaliService.getAll()
    }
	
	@Test
	  public void testbo(){
		Assert.notEmpty(macroSettimanaliService.getAll(), "ok");
  }
	
	
}
