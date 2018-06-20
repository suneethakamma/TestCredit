package com.csuisse.parser;

import java.io.StringReader;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.csuisse.LogEvent;

/**
* <h1>Parser class</h1>
* A Parser class which performs the JSON format console log
*
* @author  Suneetha Kamma
* @version 1.0
* 
*/

public class CreditParser {
	
	private static final Logger LOGGER = LogManager.getLogger(CreditParser.class.getName());
	
	/*
	 * No arguement constructor
	 */
	public CreditParser(){
		
	}
	
	/*
	 * Takes in a line of log from the server log to populate a LogEvent model object
	 * 
	 * @param consoleLog
	 * @return LogEvent object
	 */
	public LogEvent parseCreditLogFile(String consoleLog)
	{	
		LOGGER.info("parseCreditLogFile method.. started..");
		final JsonParser parser = Json.createParser(new StringReader(consoleLog));
    	
    	String key = null;
        String value = null;
        LogEvent logEve = null;
        while (parser.hasNext()) {
            final Event event = parser.next();
            switch (event) {
            case KEY_NAME:
                key = parser.getString();
                break;
            case VALUE_STRING:
                value = parser.getString();
                break;
            }
            
            if("id".equalsIgnoreCase(key)){
            	logEve = new LogEvent();
            	logEve.setId(value);
            } else if(("state").equalsIgnoreCase(key)) {
            	logEve.setState(value);
			 } else if("type".equalsIgnoreCase(key)) {
				 logEve.setType(value);
			 } else if("host".equalsIgnoreCase(key)) {
				 logEve.setHost(value);
			 } else if("timestamp".equalsIgnoreCase(key) && "started".equalsIgnoreCase(logEve.getState())) {
				 logEve.setStartTimestamp(value);
			 } else if("timestamp".equalsIgnoreCase(key) && "finished".equalsIgnoreCase(logEve.getState())) {
				 logEve.setEndTimestamp(value);
			 }
            	
        }
        parser.close();
        LOGGER.info("parseCreditLogFile method.. finised..");
		return logEve;
	}

}
