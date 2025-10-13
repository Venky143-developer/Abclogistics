package com.alpha.ABClogistics.Service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.Entity.Carrier;
import com.alpha.ABClogistics.Exception.CarrierAlreadyPresentException;
import com.alpha.ABClogistics.Exception.CarrierNotFoundException;
import com.alpha.ABClogistics.Repository.CarrierRepository;

@Service
public class CarrierService {
	@Autowired
	private CarrierRepository carrierRepository;

	public ResponseEntity<ResponseStructure<Carrier>> saveCarrier(Carrier carrier) {
		
		Optional<Carrier> carrieropt=carrierRepository.findById(carrier.getId());
		if(carrieropt.isPresent()) {
			throw new CarrierAlreadyPresentException("carrier with id"+ carrier.getId()+ "alredy exist");
		}
		
		Carrier savedCarrier = carrierRepository.save(carrier);
		ResponseStructure<Carrier> responseStructure= new ResponseStructure<Carrier>();
		responseStructure.setData(savedCarrier);
        responseStructure.setMeaasage("carrier saved");		
        responseStructure.setStatuscode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Carrier>>(responseStructure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Carrier>> findCarrier(int id) {
		
			Optional<Carrier> carrierOpt =carrierRepository.findById(id);
			if(carrierOpt.isEmpty()) {
				throw new CarrierNotFoundException();
			}
			Carrier carrier = carrierOpt.get();
			ResponseStructure<Carrier> responseStructure = new ResponseStructure<Carrier>();
			responseStructure.setData(carrier);
			responseStructure.setMeaasage("Address Found");
			responseStructure.setStatuscode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Carrier>>(responseStructure, HttpStatus.FOUND);
		}

	public ResponseEntity<ResponseStructure<Carrier>> deleteCarrier(int id) {
		Optional<Carrier> carrierOpt =carrierRepository.findById(id);
		if(carrierOpt.isEmpty()) {
			throw new CarrierNotFoundException();
		}
		Carrier carrier = carrierOpt.get();
		carrierRepository.delete(carrier);
		ResponseStructure<Carrier> responseStructure = new ResponseStructure<Carrier>();
		responseStructure.setData(carrier);
		responseStructure.setMeaasage("Address Deleted");
		responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Carrier>>(responseStructure, HttpStatus.ACCEPTED);
		
	}
	}

	

	
	


