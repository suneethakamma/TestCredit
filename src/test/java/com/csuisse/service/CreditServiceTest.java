package com.csuisse.service;

import static org.junit.Assert.assertEquals;

/**
* <h1>Test class for CreditService class</h1>
*
* @author  Suneetha Kamma
* @version 1.0
* 
*/

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.csuisse.persistence.LogEventDAO;


public class CreditServiceTest {
	
	
	String logEventPath = "C:\\Projects\\Workspaces\\CreditSuisse\\TestCredit\\src\\main\\java\\com\\csuisse\\service\\ConsoleLog.txt";
	
	@InjectMocks
    private CreditServiceImpl service;
	
	@Mock
    private LogEventDAO logEventDao;
	
	@Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testProcess(){
		
		int testResult = service.process(logEventPath);
		
		assertNotNull(testResult);
		
		assertEquals(0,testResult); 
	}
	
	@Test
	public void testRetrieveLogFileData() {
		
		int result = service.retrieveLogFileData(logEventPath);
		
		assertNotNull(result);
		
		assertEquals(2,result); 
	}
	
	
}
