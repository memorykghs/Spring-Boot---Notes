package com.springboot.rest.controller.advise;

import java.io.IOException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.rest.dto.StoreResponse;
import com.springboot.rest.exception.DataNotFoundException;
import com.springboot.rest.exception.ErrorInputException;

@ControllerAdvice
public class WebExceptionHandler {
	
	@ResponseBody
	@ExceptionHandler(ErrorInputException.class)
	public StoreResponse handleErrorInputException(ErrorInputException eie) {
		StoreResponse storeResponse = new StoreResponse();
		storeResponse.setDatas(null);
		storeResponse.setMessage("ErrorInputException");
		
		return storeResponse;
	}
	
	@ResponseBody
	@ExceptionHandler(DataNotFoundException.class)
	public StoreResponse handleDataNotFoundException(DataNotFoundException dnf) {
		StoreResponse storeResponse = new StoreResponse();
		storeResponse.setDatas(null);
		storeResponse.setMessage("查無資料");
		
		return storeResponse;
	}
	
	@ResponseBody
	@ExceptionHandler(IOException.class)
	public StoreResponse handleDataNotFoundException(IOException ioe) {
		StoreResponse storeResponse = new StoreResponse();
		storeResponse.setDatas(null);
		storeResponse.setMessage("匯出失敗");
		
		return storeResponse;
	}
}
