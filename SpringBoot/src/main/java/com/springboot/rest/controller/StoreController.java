package com.springboot.rest.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.rest.dto.StoreRequest;
import com.springboot.rest.dto.StoreResponse;
import com.springboot.rest.exception.DataNotFoundException;
import com.springboot.rest.exception.ErrorInputException;
import com.springboot.rest.service.StoreService;

public class StoreController {

	@Autowired
	private StoreService storeService;

	@RequestMapping(value = "/store/{storeName}", method = RequestMethod.GET)
	public StoreResponse queryStoreName(@Valid String storeName) throws ErrorInputException, DataNotFoundException {
		return storeService.queryStore(storeName);
	}

	@RequestMapping(value = "/store", method = RequestMethod.DELETE)
	public StoreResponse deleteStore(@Valid @RequestBody StoreRequest storeRequest) throws ErrorInputException {
		return storeService.deleteStore(storeRequest);
	}

	@RequestMapping(value = "/store", method = RequestMethod.POST)
	public StoreResponse exportStore(@Valid @RequestBody StoreRequest storeRequest) throws ErrorInputException, DataNotFoundException, IOException {
		return storeService.exportStoreRequest(storeRequest);
	}
}
