package com.alpha.ABClogistics.Service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.DTO.TruckDto;
import com.alpha.ABClogistics.Entity.Carrier;
import com.alpha.ABClogistics.Entity.Truck;
import com.alpha.ABClogistics.Exception.CarrierNotFoundException;
import com.alpha.ABClogistics.Exception.TruckalredyExistException;
import com.alpha.ABClogistics.Exception.TrucknotFoundException;
import com.alpha.ABClogistics.Repository.CarrierRepository;
import com.alpha.ABClogistics.Repository.Truckrepository;

@Service
public class TruckService {
	@Autowired
	Truckrepository truckrepository;
	@Autowired
	CarrierRepository carrierrepository;
	
	
	public ResponseEntity<ResponseStructure<Truck>> saveTruck(@RequestBody TruckDto truckdto) {
		if(truckdto==null) {
			throw  new IllegalArgumentException("TruckDto cannot be null");
		}
		
		if(truckrepository.existsById(truckdto.getId())) {
		Optional<Truck> existingtruck=truckrepository.findById(truckdto.getId());
			throw new TruckalredyExistException("truck alredy exist"+ existingtruck.get());
		}
		
		Truck truck= new Truck();
		truck.setId(truckdto.getId());
		truck.setName(truckdto.getName());
		truck.setNumber(truckdto.getNumber());
		truck.setCapacity(truckdto.getCapacity());
		truck.setStatus(truckdto.getStatus());
			
		 truckrepository.save(truck);
		 ResponseStructure<Truck> responseStructure =new ResponseStructure<Truck>();
		 responseStructure.setData(truck);
		 responseStructure.setMeaasage("truck saved");
		 responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
		 return new ResponseEntity<ResponseStructure<Truck>>(responseStructure, HttpStatus.ACCEPTED);
		
		
	}
	public ResponseEntity<ResponseStructure<Truck>> findTruck(int id) {
		Optional<Truck> truckoptional=truckrepository.findById(id);
		if(truckoptional.isEmpty()) {
			throw new TrucknotFoundException();
		}
		Truck truck= truckoptional.get();
		ResponseStructure<Truck> responseStructure= new ResponseStructure<Truck>();
		responseStructure.setData(truck);
		responseStructure.setMeaasage("Truck found");
		responseStructure.setStatuscode(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<Truck>>(responseStructure, HttpStatus.FOUND);
		
	}
	public ResponseEntity<ResponseStructure<Truck>> deletetruck(int id) {
	Optional<Truck> truckoptional	=truckrepository.findById(id);
		if(truckoptional.isEmpty()) {
			throw new TrucknotFoundException();
		}
		Truck truck = truckoptional.get();
			truckrepository.delete(truck);
			ResponseStructure<Truck> responseStructure = new ResponseStructure<Truck>();
			responseStructure.setData(truck);
			responseStructure.setMeaasage("Truck Deleted");
			responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
			
		return new ResponseEntity<ResponseStructure<Truck>>(responseStructure, HttpStatus.ACCEPTED);
	}
	public ResponseEntity<ResponseStructure<Truck>> updateTruck(int truckid, int carrierid) {
		Truck truck = truckrepository.findById(truckid).orElseThrow(()->new  TrucknotFoundException());
		Carrier carrier = carrierrepository.findById(carrierid).orElseThrow(()-> new CarrierNotFoundException());
		truck.setCarrier(carrier);
		truckrepository.save(truck);
		ResponseStructure<Truck> responseStructure = new ResponseStructure<Truck>();
		responseStructure.setData(truck);
		responseStructure.setMeaasage("Truck Updated");;
		responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Truck>>(responseStructure, HttpStatus.ACCEPTED);
	}
	public ResponseEntity<ResponseStructure<Truck>> updateTruckCapacity(int truckid, int capacity) {
		Truck truck = truckrepository.findById(truckid)
		        .orElseThrow(() -> new TrucknotFoundException("Truck with id " + truckid + " not found"));

		    truck.setCapacity(capacity);
		    truckrepository.save(truck);

		    ResponseStructure<Truck> response = new ResponseStructure<>();
		    response.setData(truck);
		    response.setMeaasage("Truck capacity updated successfully");
		    response.setStatuscode(HttpStatus.ACCEPTED.value());

		    return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		}
	}
	

		
		
	
		
	


	




	
	


