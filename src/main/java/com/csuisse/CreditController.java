package com.csuisse;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csuisse.service.CreditService;


@Controller
public class CreditController {
	
	@Autowired
	CreditService service;
	
	
	
	private static final Logger LOGGER = LogManager.getLogger(CreditController.class.getName());
	
	@RequestMapping( value = "/Credit")
	public String creditLog(@RequestParam("logFile") String logFile){
		
		System.out.println("HElloooooooo...........111111111.."+logFile);
		
		LOGGER.debug("Debug Message Logged !!!");
        LOGGER.info("Controller Class: myCredit");
        //LOGGER.error("Error Message Logged !!!", new NullPointerException("NullError"));		
        
        service.process(logFile);
	    
	    return "First";
	}

}
