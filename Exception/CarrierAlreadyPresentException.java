package com.alpha.ABClogistics.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alpha.ABClogistics.DTO.ResponseStructure;

public class CarrierAlreadyPresentException extends RuntimeException {

	public CarrierAlreadyPresentException() {
        super("Carrier already exists");
    }

    public CarrierAlreadyPresentException(String message) {
        super(message);
    }
	
	public ResponseEntity<ResponseStructure<String>> CarriersAlreadyPresentException() {
		ResponseStructure<String> rs= new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.CONFLICT.value());
		rs.setMeaasage("carrier already existed");
		rs.setData("carrier existed");
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.CONFLICT);

	}

	
}
