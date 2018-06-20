package com.csuisse.persistence;

import java.util.List;

import com.csuisse.LogEvent;

/**
* <h1>DAO Interface</h1>
* A DAO interface for records to put into the db
*
* @author  Suneetha Kamma
* @version 1.0
* 
*/

public interface LogEventDAO {
	
	/*
	 * method for saving the LogEvent objects into the db
	 * via Hibernate way
	 * @param listLogEvents - list of LogEvents
	 */
	public abstract void create(List<LogEvent> listLogEvents);
	
	/*
	 * method for saving the LogEvent objects into the db
	 * via age old way using JDBC way
	 * @param listLogEvents - list of LogEvents
	 * @return numOfRows
	 */
	public abstract int saveLogEvent(List<LogEvent> listLogEvents);

}
