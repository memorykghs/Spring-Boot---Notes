package com.springboot.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.service.MagicianService;

@RestController
public class TestController {
	@Autowired
	MagicianService magicianService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testString(){
		return "Hello World!";
	}
	
	/**
	 * 取得魔法師資訊
	 * @return
	 */
	@RequestMapping(value = "/magician/test", method = RequestMethod.GET)
	public ResponseEntity<Object> getMagicianData(){
		System.out.println("-----> from TestController：" + magicianService.toString());
		return new ResponseEntity<>(magicianService.getMagician(), HttpStatus.OK);
	}
}
