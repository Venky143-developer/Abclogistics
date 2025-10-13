package com.alpha.ABClogistics.Service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.ABClogistics.DTO.DriverDto;
import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.Entity.Carrier;
import com.alpha.ABClogistics.Entity.Driver;
import com.alpha.ABClogistics.Entity.Truck;
import com.alpha.ABClogistics.Exception.CarrierNotFoundException;
import com.alpha.ABClogistics.Exception.DriverAlreadyFoundException;
import com.alpha.ABClogistics.Exception.DriverNotFoundException;
import com.alpha.ABClogistics.Exception.TrucknotFoundException;
import com.alpha.ABClogistics.Repository.CarrierRepository;
import com.alpha.ABClogistics.Repository.DriverRepository;
import com.alpha.ABClogistics.Repository.Truckrepository;

@Service
public class DriverService {
	@Autowired
	private DriverRepository driverrepository;
	@Autowired
	private Truckrepository truckrepository;
	@Autowired
	private CarrierRepository carrierrepository;
	
	public ResponseEntity<ResponseStructure<Driver>>saveDriver(DriverDto driver) {
		Optional<Driver> driveropt=driverrepository.findById(driver.getId());
		if(driveropt.isPresent()) {
			throw new DriverAlreadyFoundException();
			
		}
		Driver d =new Driver();
		d.setId(driver.getId());
		d.setName(driver.getName());
		d.setContact(driver.getContact());
		Driver savedrDriver = driverrepository.save(d);
		
		ResponseStructure<Driver> responseStructure = new ResponseStructure<Driver>();
		responseStructure.setData(d);
responseStructure.setMeaasage("Driver saved");
responseStructure.setStatuscode(HttpStatus.CREATED.value());
return new ResponseEntity<ResponseStructure<Driver>>(responseStructure, HttpStatus.CREATED);
		
		
		
	}
	public ResponseEntity<ResponseStructure<Driver>> findDiver(int id) {
		Optional<Driver>driveropt=driverrepository.findById(id);
		if(driveropt.isEmpty()){
			throw new DriverNotFoundException();	
		}
		Driver driver =driveropt.get();
		ResponseStructure<Driver> responseStructure= new ResponseStructure<Driver>();
	responseStructure.setData(driver);
responseStructure.setMeaasage("Driver Found");		
responseStructure.setStatuscode(HttpStatus.FOUND.value());
return new ResponseEntity<ResponseStructure<Driver>>(responseStructure, HttpStatus.FOUND);

	}
	public ResponseEntity<ResponseStructure<Driver>> deletedriver(int id) {
		Optional<Driver> driveropt=driverrepository.findById(id);
		if(driveropt.isEmpty()) {
			throw new DriverNotFoundException();
		}
		Driver driver =driveropt.get();
		ResponseStructure<Driver> responseStructure= new ResponseStructure<Driver>();
	responseStructure.setData(driver);
responseStructure.setMeaasage("Driver Deleted");		
responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
return new ResponseEntity<ResponseStructure<Driver>>(responseStructure, HttpStatus.ACCEPTED);
		
		
	
	}
	public ResponseEntity<ResponseStructure<Driver>> updateDriver(int driverid, int carrierid, int truckid) {
		Truck t = truckrepository.findById(truckid).orElseThrow(() -> new TrucknotFoundException("truck with id"+ truckid+"not found"));
		Driver d = driverrepository.findById(driverid).orElseThrow(() -> new DriverNotFoundException());
		Carrier c = carrierrepository.findById(carrierid).orElseThrow(() -> new CarrierNotFoundException());
		d.setTruck(t);
		d.setCarrier(c);
		t.setCarrier(c);
		Driver savedDriver = driverrepository.save(d);
		Truck savetruck=truckrepository.save(t);
		

		ResponseStructure<Driver> responseStructure = new ResponseStructure<>();
		responseStructure.setData(savedDriver);
		responseStructure.setMeaasage("Driver Updated");
		responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Driver>>(responseStructure, HttpStatus.ACCEPTED);
		
	}

}

