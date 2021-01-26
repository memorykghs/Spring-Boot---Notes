package com.springboot.rest.service;

import java.io.IOException;

import com.springboot.rest.dto.StoreRequest;
import com.springboot.rest.dto.StoreResponse;
import com.springboot.rest.exception.DataNotFoundException;
import com.springboot.rest.exception.ErrorInputException;


public interface StoreService {
	
	StoreResponse queryStore(String storeName) throws ErrorInputException, DataNotFoundException;
	
	StoreResponse deleteStore(StoreRequest storeRequest) throws ErrorInputException;
	
	StoreResponse exportStoreRequest(StoreRequest storeRequest) throws ErrorInputException, DataNotFoundException, IOException;
}
