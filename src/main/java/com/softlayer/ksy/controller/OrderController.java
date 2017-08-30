package com.softlayer.ksy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softlayer.ksy.service.VMService;

/**
 * @author Kim Sae-Young(heehouse1@gmail.com)
 *
 * @FileName OrderController.java
 * @Project Z-opener-java
 * @Date 2017. 8. 24.
 */

@RestController
public class OrderController {
	
	@Autowired
	VMService vms;
	
	@RequestMapping(value = "/order/vm", method = RequestMethod.GET,produces = { MediaType.APPLICATION_JSON_VALUE })
	public String createmeeting() {
		
		vms.OrderVM();
		
		return "\"{\"result\": \"SUCCESS\"}";
	}
}
