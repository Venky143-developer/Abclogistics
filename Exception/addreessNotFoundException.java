package com.alpha.ABClogistics.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alpha.ABClogistics.DTO.ResponseStructure;



public class addreessNotFoundException extends RuntimeException{
	public  addreessNotFoundException(String message) {
		super(message);
		
	}
	public addreessNotFoundException() {
		super("address not found");
	}

}


