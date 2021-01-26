package com.springboot.rest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public String start() {
		return "Hello Spring Boot";
	}

	// GET搭配網址列傳入參數
	@RequestMapping(path = "/index01/{name}", method = RequestMethod.GET)
	public String index01(@PathVariable("name") String name) {
		return " Hi，" + name;
	}

	// GET搭配RequestParam
	@RequestMapping(path = "/toString", method = RequestMethod.GET)
	public String paramToString(@RequestParam(value = "test") String test) {
		return test;
	}

	// DTO
//	@RequestMapping(path = "/dto", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public String paramToJson(@RequestBody UserDto user) {
//		return user;
//	}

	// JPA1
//	@GetMapping(path = { "/jpa1" })
//	public List<Cars> jpa1() {
//		return carsRepo.findAll();
//	}

	// JPA2
//	@GetMapping(path = { "/jpa2" })
//	public List<Cars> jpa2(@RequestParam("type") String type) {
//		return carsRepo.findByType();
//	}

	// JPA3
//	@PostMapping(path = { "/jpa3" })
//	public List<Cars> jpa3(@RequestBody Map<String, String> map) {
//		return carsRepo.findTest01(map.get("manufacturer"), map.get("type"), new BigDecimal(map.get("minPrice")),
//				new BigDecimal(map.get("price")));
//	}

	// service
//	@GetMapping(path = { "/service1" })
//	public String service1() {
//		return theXXT0_0100_service.test;
//	}
}
