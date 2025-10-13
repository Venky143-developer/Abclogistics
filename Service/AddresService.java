package com.alpha.ABClogistics.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.Entity.Address;
import com.alpha.ABClogistics.Exception.AddressAlreadyPresentException;
import com.alpha.ABClogistics.Exception.addreessNotFoundException;
import com.alpha.ABClogistics.Repository.AddressRepository;


@Service
public class AddresService {
	@Autowired
	AddressRepository addressRepository;

	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address) {
		Optional<Address>addoptional=addressRepository.findById(address.getId());
		if(addoptional.isPresent()) {
			throw new AddressAlreadyPresentException();
		}
		Address saveAdress=addressRepository.save(address);
		ResponseStructure<Address> responseStructure= new ResponseStructure<Address>();
		responseStructure.setData(saveAdress);
		responseStructure.setMeaasage("Address saved");
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Address>>(responseStructure,HttpStatus.CREATED);
	}
	

	public  ResponseEntity<ResponseStructure<Address>> getaddressbyid(int id) {
		Optional<Address> addressopt =addressRepository.findById(id);
		if(!addressopt.isPresent()) {
			throw new addreessNotFoundException();
			
		}
		ResponseStructure<Address> rs= new ResponseStructure<Address>();
		rs.setStatuscode(HttpStatus.FOUND.OK.value());
		rs.setMeaasage("address with id: "+id+ "FOUND");
		rs.setData(addressopt.get());
		return new ResponseEntity<ResponseStructure<Address>>(rs, HttpStatus.OK);
	}


	public void deleteaddress(int id) {
	    if (!addressRepository.existsById(id)) {
	        throw new addreessNotFoundException();
	    }
	    addressRepository.deleteById(id);
	}

	}

		
		
	
				
			
	


