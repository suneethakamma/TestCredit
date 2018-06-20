/**
 * 
 */
package com.csuisse.service;

/**
* <h1>Service Interface</h1>
* The Service class is required to perform the business logic
* on the data retrieved from the server log file 
*
* @author  Suneetha Kamma
* @version 1.0
* 
*/
public interface CreditService {
	/*
	 * Entry method mainly needs to be created for easy and clean code.
	 */
	public abstract int process(String logFile);
	
	/*
	 * Method which reads line by line information from the console log file 
	 * and sends information to the parser to get LogEvent object
	 */
	public abstract int retrieveLogFileData(String logFile);
	
	/*
	 * Method which calls the DAO to save the needed LogEvents to dB.
	 */
	public abstract int saveLogEventHavingExcessProcessTime();
	
}
