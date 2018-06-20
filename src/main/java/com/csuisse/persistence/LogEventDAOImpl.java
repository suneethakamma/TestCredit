/**
 * 
 */
package com.csuisse.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csuisse.LogEvent;

/**
 * <h1>DAO Implementation class</h1>
 * A DAO implementation  for records to put into the db
 * 	
 * @author Suneetha Kamma
 * @Version 1.0
 */

@Service
public class LogEventDAOImpl implements LogEventDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger LOGGER = LogManager.getLogger(LogEventDAOImpl.class.getName());
	
	static Connection conn;
	
	static Statement st;
	
	ResultSet rs;	
	
	static {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
        
	        conn = DriverManager.getConnection("jdbc:hsqldb:mem:logeventsdb", "sa","");
	        
	        st = conn.createStatement();
		} 
		catch (Exception e) {
			LOGGER.error("Error occurred while trying to save a LogEvent" + e);
			e.printStackTrace();
		}
	}
	
	/*  method for saving the LogEvent objects into the db
	 * via Hibernate way
	 * @param listLogEvents - list of LogEvents
	 */
	public void create(List<LogEvent> listLogEvent) {
		
		LOGGER.info("create: method execution started..");
		Session session = sessionFactory.openSession();
		session.getTransaction().begin(); 
		 
		for (LogEvent logEve : listLogEvent) {
			session.save(logEve);
		}
		
		session.getTransaction().commit();
		LOGGER.info("create: method execution finished..");
	}
	
	/*
	 * method for saving the LogEvent objects into the db
	 * via age old way using JDBC way
	 * @param listLogEvents - list of LogEvents
	 * @return numOfRows
	 */
	public int saveLogEvent(List<LogEvent> listLogEvents){
		
		LOGGER.info("saveLogEvent: method execution started.. Age old way");
		int rowCount = 0;
		
		try {
	        st.execute("create table LogEvent (id varchar(20), state varchar(10), type varchar(20), host varchar(10), starttimestamp varchar(20), endtimestamp varchar(20), alert varchar(1), PRIMARY KEY (id))");
	        
	        for(LogEvent logE : listLogEvents) {
	        	st.executeUpdate("insert into LogEvent values ('"+logE.getId()+"','"+logE.getState()+"', '"+logE.getType()+"', '"+logE.getHost()+"', '"+logE.getStartTimestamp()+"', '"+logE.getEndTimestamp()+"', '"+logE.getAlert()+"'); ");
	        }
	        
	        rs = st.executeQuery("select count(*) from LogEvent");
	        rs.next();
	        rowCount = rs.getInt(1);
	        
	        LOGGER.info("Total number of rows inserted into the table:::"+rowCount);
	        
	        LOGGER.info("saveLogEvent: method execution finished.. Age old way");
	        
		}
		catch (Exception e) {
			LOGGER.error("Error occurred while trying to save a LogEvent" + e);
			e.printStackTrace();
		}
		/*
		 * Mainly to close any open resources.
		 */
		finally {
			try{
				conn.close();
				st.close();
				rs.close();
			} catch(Exception e){
				e.getMessage();
			}
			
		}
		return rowCount;
		
	}
	
	

}
