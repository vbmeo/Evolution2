package poliformismo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vbmeo.evolution2.prove.PoliformismoProva;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/appServlet/servlet-context.xml" })//classpath:spring.appServlet/servlet-context.xml
public class PoliformismoTest {

	PoliformismoProva poli = new PoliformismoProva();
	
	@Test
	  public void testChiamaBasePoli(){
		int ris = 12;
		assertEquals(ris, poli.testBase());
	}
	
	@Test
	  public void testChiamaEstendePoli(){
		int ris = -2;
		assertEquals(ris, poli.testEstesa());
	}
	
	@Test
	  public void testChiamaEstendeBasePoli(){
		int ris = -2;
		assertEquals(ris, poli.testEstesaBase());
	}
	
	
	
}
