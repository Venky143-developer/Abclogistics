package com.alpha.ABClogistics.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.Entity.Address;


public class AddressAlreadyPresentException extends RuntimeException {
	

}
