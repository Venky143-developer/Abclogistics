package com.alpha.ABClogistics.Exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.DTO.TruckDto;
import com.alpha.ABClogistics.Entity.Cargo;
import com.alpha.ABClogistics.Entity.Truck;
import com.alpha.ABClogistics.Repository.AddressRepository;
import com.alpha.ABClogistics.Repository.Truckrepository;


@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(AddressAlreadyPresentException.class)
	public ResponseEntity<ResponseStructure<String>> AddressAlreadyPresentException() {
		ResponseStructure<String> rs= new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.CONFLICT.value());
		rs.setMeaasage("Adress alredy existed");
		rs.setData(rs.getData());
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.CONFLICT);
		
	}
	
@ExceptionHandler(addreessNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> addreessNotFoundException() {
	ResponseStructure<String> rs= new ResponseStructure<String>();
	rs.setStatuscode(HttpStatus.NOT_FOUND.value());
	rs.setMeaasage("address not found");
	rs.setData(null);
	return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
		
	}
@ExceptionHandler(TruckalredyExistException.class)
public ResponseEntity<ResponseStructure<String>> TruckalredyExistException() {
	ResponseStructure<String>rs= new ResponseStructure<String>();
	rs.setStatuscode(HttpStatus.CONFLICT.value());
	rs.setMeaasage("Truck details already exist");
	rs.setData(null);
	return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.CONFLICT);
	
}
@ExceptionHandler(CarrierAlreadyPresentException.class)
public ResponseEntity<ResponseStructure<String>> CarriersAlreadyPresentException() {
	ResponseStructure<String> rs= new ResponseStructure<String>();
	rs.setStatuscode(HttpStatus.CONFLICT.value());
	rs.setMeaasage("carrier already existed");
	rs.setData("carrier existed");
	return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.CONFLICT);

}
@ExceptionHandler(DriverAlreadyFoundException.class)
public ResponseEntity<ResponseStructure<String>> DriverAlreadyFoundException() {
	ResponseStructure<String> rs= new ResponseStructure<String>();
	rs.setStatuscode(HttpStatus.CONFLICT.value());
	rs.setMeaasage("driver already existed");
	rs.setData("Driver alredy found");
	return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.CONFLICT);

}
@ExceptionHandler(TrucknotFoundException.class)
public ResponseEntity<ResponseStructure<String>> TrucknotFoundException() {
	ResponseStructure<String> rs= new ResponseStructure<String>();
	rs.setStatuscode(HttpStatus.NOT_FOUND.value());
	rs.setMeaasage("Truck not found");
	rs.setData("Truck not found");
	return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);

}
@ExceptionHandler(CarrierNotFoundException.class)
public ResponseEntity<ResponseStructure<String>>  CarrierNotFoundException() {
	ResponseStructure<String> rs= new ResponseStructure<String>();
	rs.setStatuscode(HttpStatus.NOT_FOUND.value());
	rs.setMeaasage("carrier not found");
	rs.setData("carrier not found");
	return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);
}
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ResponseStructure<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
	List<FieldError> ferror = ex.getFieldErrors();
Map<String, String> validationMap = new HashMap<String, String>();
for(FieldError fieldError : ferror) {
	validationMap.put(fieldError.getField(), fieldError.getDefaultMessage());
}
ResponseStructure<Map<String,String>> responseStructure = new ResponseStructure<Map<String,String>>();
responseStructure.setStatuscode(HttpStatus.NOT_ACCEPTABLE.value());
responseStructure.setMeaasage("Validation Problem");
responseStructure.setData(validationMap);
return new ResponseEntity<ResponseStructure<Map<String,String>>>(responseStructure,HttpStatus.NOT_ACCEPTABLE);
}
@ExceptionHandler(TruckCapacityExceededException.class)
public ResponseEntity<ResponseStructure<String>> TruckCapacityExceededException() {
	ResponseStructure<String> responseStructure= new ResponseStructure<String>();
	responseStructure.setStatuscode(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED.value());
	responseStructure.setMeaasage("Truck capacity Exceeded");
	responseStructure.setData("please provide order less than 1000kg");
	return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
}

@ExceptionHandler(NoResourceFoundException.class)
public ResponseEntity<ResponseStructure<String>> handleNoResourceFoundException(NoResourceFoundException ex) {
    ResponseStructure<String> response = new ResponseStructure<>();
    response.setStatuscode(HttpStatus.NOT_FOUND.value());
    response.setMeaasage(ex.getMessage());
    response.setData("Resource not found");
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
}

@ExceptionHandler(CargoAlreadyExistsException.class)
public ResponseEntity<ResponseStructure<String>> CargoAlreadyExistsException() {
    ResponseStructure<String> response = new ResponseStructure<>();
    response.setStatuscode(HttpStatus.NOT_FOUND.value());
    response.setMeaasage("CargoAlreadyException");
    response.setData(null);
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
}
@ExceptionHandler(MissingServletRequestParameterException.class)
public ResponseEntity<ResponseStructure<String>> MissingServletRequestParameterException() {
	  ResponseStructure<String> response = new ResponseStructure<>();
	    response.setStatuscode(HttpStatus.BAD_REQUEST.value());
	    response.setMeaasage("Missing Request parameter Exception");
	    response.setData(null);
	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
@ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
public ResponseEntity<ResponseStructure<String>> HttpRequestMethodNotSupportedException (){
	ResponseStructure<String> response = new ResponseStructure<>();
    response.setStatuscode(HttpStatus.METHOD_NOT_ALLOWED.value());
    response.setMeaasage("Please provide Correct method type. Method incorrect ");
    response.setData("Method Error");
    return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
}
}



