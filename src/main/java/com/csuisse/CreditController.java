package com.csuisse;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.csuisse.service.CreditServiceImpl;


@Controller
public class CreditController {
	
	@Autowired
	CreditServiceImpl service;
	
	private static final Logger LOGGER = LogManager.getLogger(CreditController.class.getName());
	
	@RequestMapping( value = "/Credit")
	public String creditLog(@RequestParam("logFile") String logFile, ModelMap map){
		
        LOGGER.info("Controller Class: myCredit");
        
        int numOfLogEvents = service.process(logFile);
        
        map.put("test", Integer.toString(numOfLogEvents));
	    
	    return "First";
	}

}
