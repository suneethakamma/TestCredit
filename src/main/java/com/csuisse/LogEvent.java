package com.csuisse;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
* <h1>Model class</h1>
* The Model class is required to store the data
* which is picked up from a server log file. 
*
* @author  Suneetha Kamma
* @version 1.0
* 
*/
@Entity
@DynamicUpdate(true)
@Table(name = "LOGEVENT")
public class LogEvent {
	
	/*
	 * id of a console log event
	 */
	@Id
	private String id;
	
	/*
	 * state of a console log event
	 */
	private String state;
	
	/*
	 * type of a console log event
	 */
	private String type;
	
	/*
	 * host of a console log event
	 */	
	private String host;
	
	/*
	 * startTimestamp of a console log event
	 */
	private String startTimestamp;
	
	/*
	 * endTimestamp of a console log event
	 */
	private String endTimestamp;
	
	/*
	 * alert to hold the overtime status of the log event
	 */
	private String alert;
	
	/*
	 * No arguement constructor
	 */
	public LogEvent(){
		
	}
	
	/**
	* Another constructor for model class event 
	* @param id  - to hold the id value from the log
	* @param state  - to hold the state value from the log
	* @param type  - to hold the type value from the log
	* @param host  - to hold the host value from the log
	* @param startTimestamp  - to hold the startTimestamp value from the log
	* @param endTimestamp  - to hold the endTimestamp value from the log
	* @param alert  - to hold the alert value from the log
	* 
	*/
	public LogEvent(String id, String state, String type, String host, String startTimestamp, String endTimestamp, String alert){
		
		this.id = id;
		this.state = state;
		this.type = type;
		this.host = host;
		this.startTimestamp = startTimestamp;
		this.endTimestamp = endTimestamp;
		this.alert = alert;
		
	}
	
	/**
	 * @return The id of an event.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id new value for this event's id.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return The state of an event.
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * @param state new value for this event's state.
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * @return The type of an event.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type new value for this event's type.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return The host of an event.
	 */
	public String getHost() {
		return host;
	}
	
	/**
	 * @param host new value for this event's host.
	 */
	public void setHost(String host) {
		this.host = host;
	}
	
	/**
	 * @return The startTimestamp of an event.
	 */
	public String getStartTimestamp() {
		return startTimestamp;
	}
	
	/**
	 * @param startTimestamp new value for this event's startTimestamp.
	 */
	public void setStartTimestamp(String startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	
	/**
	 * @return The endTimestamp of an event.
	 */
	public String getEndTimestamp() {
		return endTimestamp;
	}
	
	/**
	 * @param endTimestamp new value for this event's endTimestamp.
	 */
	public void setEndTimestamp(String endTimestamp) {
		this.endTimestamp = endTimestamp;
	}
	
	/**
	 * @return The alert of an event.
	 */
	public String getAlert() {
		return alert;
	}
	
	/**
	 * @param alert new value for this event's alert.
	 */
	public void setAlert(String alert) {
		this.alert = alert;
	}

	

}
