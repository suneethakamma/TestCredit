package com.csuisse.service;

/**
* <h1>Service class Implementation</h1>
* The Service class is required to perform the business logic
* on the data retrieved from the server log file 
*
* @author  Suneetha Kamma
* @version 1.0
* 
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csuisse.LogEvent;
import com.csuisse.parser.CreditParser;
import com.csuisse.persistence.LogEventDAO;

@Service
public class CreditServiceImpl implements CreditService{
	
	private static final Logger LOGGER = LogManager.getLogger(CreditServiceImpl.class.getName());
	
	//HashMap to hold all the LogEvent objects which are extracted from the ConsoleLog text file
	HashMap<String, LogEvent> eventMap = new HashMap<String, LogEvent>();

	//ArrayList to hold all those LogEvents whose processing time is equal or greater than 4ms
	ArrayList<LogEvent> dbLogeventList = null;
	
	@Autowired
	LogEventDAO logEventDAO;
	
	/*
	 * This is a method where the business logic begins and later on followed by
	 * 			-- reading data from the server log file
	 * 			-- creating event model class objects for each entry of the log file
	 * 			-- calculating the execution time for each event and updating the alert field value for any event whose execution time if more than 4ms
	 * 
	 * @param logFile
	 * @return loggedEvents
	 */
	public int process(String logFile)
	{
		LOGGER.info("process method.. started..");
		
		int numberOFEventsToBeSaved = retrieveLogFileData(logFile);
		
		//method which saves LogEvent objects to dB.
	    int dBResult = saveLogEventHavingExcessProcessTime();
	    
	    LOGGER.info(numberOFEventsToBeSaved + " console log events processing time is high.");
	    
	    LOGGER.info(dBResult+" LogEvents saved in the dB.");
		
		LOGGER.info("process method.. finished..");
		
		return dBResult;
				
	}
	
	/*
	 * To retrieve each event and its details from the server log
	 * @param logFile
	 * @return size of the ArrayList which has the LogEvent objects to be saved in the dB.
	 */
	public int retrieveLogFileData(String logFile){
		
		LOGGER.info("retrieveLogFileData method.. started");
		BufferedReader reader;
	    String consoleLog = null;
	    //int dBResult = 0;
	    
	    try{
	    	// Reader for the server log to read each line
	    	reader = new BufferedReader(new FileReader(logFile));
		    consoleLog = reader.readLine();
		    // object of the parser class
		    CreditParser creditParser = new CreditParser();
		        
		    // iterate through the line read and adding it to ArrayList
		    while (consoleLog != null) {
		    	LogEvent eve = creditParser.parseCreditLogFile(consoleLog);
		    	//method which populates the HashMap (eventMap)
		    	populateEventMap(eve);
		    	//reading the next line of the console log file.
		        consoleLog = reader.readLine();
		    }
		    // method which sets the alert of a LogEvent based on process time
		    setAlertForLogEvents();
		    //method which saves LogEvent objects to dB.
		    //dBResult = saveLogEventHavingExcessProcessTime();
	    	
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    
	    LOGGER.info("retrieveLogFileData method.. finished");
	    	
	    return dbLogeventList.size();
	}
	
	/*
	 * To create a HashMap and have its key an event id and value as the correspnding event object
	 * 
	 * @param eve - LogEvent object
	 * @return HashMap with events populate within
	 */
	private void populateEventMap(LogEvent eve){
		
		LOGGER.info("populateEventMap method.. started");
		/*
		 * Check if the LogEvent object is already created if so, set start or end timestamp accordingly
		 * If LogEvent object if not present then add it to the HashMap (eventMap)
		 */
		if(eventMap.containsKey(eve.getId())){
			LogEvent logEvent = eventMap.get(eve.getId());
			if("started".equalsIgnoreCase(eve.getState())){
				logEvent.setStartTimestamp(eve.getStartTimestamp());
			} else if("finished".equalsIgnoreCase(eve.getState())) {
				logEvent.setEndTimestamp(eve.getEndTimestamp());
			}
		} else {
			eventMap.put(eve.getId(), eve);
		}
		
        LOGGER.info("populateEventMap method.. finished");
        
	}
	/*
	 * Check if the processing time of event is more than 4ms if so 
	 * set Alert field with a yes.
	 * Add log events to an ArrayList whose contents would be inserted to the dB.
	 * 
	 */
	private void setAlertForLogEvents(){
		
		LOGGER.info("setAlertForLogEvents method.. started");
		
		for (String key: eventMap.keySet()) {
		    LogEvent eve = eventMap.get(key);
		    
		    BigInteger start = new BigInteger(eve.getStartTimestamp());
		    BigInteger end = new BigInteger(eve.getEndTimestamp());
		    
		    BigInteger processingTime = end.subtract(start);
		    
		    System.out.println("How much is the processing time..."+processingTime);
		    if(processingTime.intValue() >= 4){
		    	eve.setAlert("Y");
		    	if(null == dbLogeventList){
		    		dbLogeventList = new ArrayList<LogEvent>();
		    	}
		    	dbLogeventList.add(eve);		    	
		    } 
		    
		}
		
		LOGGER.info("setAlertForLogEvents method.. finished");
		
	}
	/*
	 * Save console log events whose processing time exceeds 4ms.
	 */
	public int saveLogEventHavingExcessProcessTime(){
		
		LOGGER.info("saveLogEventHavingExcessProcessTime method.. started");
		//Below is the method to go by Hibernate way - Unfortunately didn't work
		//logEventDAO.create(dbLogeventList);
		
		int dBResult = logEventDAO.saveLogEvent(dbLogeventList);
		
		LOGGER.info("saveLogEventHavingExcessProcessTime method.. started");
		
		return dBResult;
		
	}
	

}
