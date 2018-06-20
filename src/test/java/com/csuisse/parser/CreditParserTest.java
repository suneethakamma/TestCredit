package com.csuisse.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.csuisse.LogEvent;

/**
* <h1>Parser test class</h1>
* A Parser  test class
*
* @author  Suneetha Kamma
* @version 1.0
* 
*/


public class CreditParserTest {
	
	String eventInfo = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\", \"host\":\"12345\", \"timestamp\":\"1491377495212\"}";
	
	private CreditParser parser = new CreditParser();
	
	@Test
	public void testparseCreditLogFile(){
		
		LogEvent eve = parser.parseCreditLogFile(eventInfo);
		
		assertNotNull(eve);
		
		assertEquals("scsmbstgra",eve.getId());
		
		assertEquals("APPLICATION_LOG",eve.getType());
		
		
	}

}
