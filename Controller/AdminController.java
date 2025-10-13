package com.alpha.ABClogistics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.alpha.ABClogistics.DTO.DriverDto;
import com.alpha.ABClogistics.DTO.LoadingDto;
import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.DTO.TruckDto;
import com.alpha.ABClogistics.DTO.UnLoadingDto;
import com.alpha.ABClogistics.Entity.Address;
import com.alpha.ABClogistics.Entity.Carrier;
import com.alpha.ABClogistics.Entity.Driver;
import com.alpha.ABClogistics.Entity.Order;
import com.alpha.ABClogistics.Entity.Truck;
import com.alpha.ABClogistics.Exception.TruckCapacityExceededException;

import com.alpha.ABClogistics.Service.AddresService;
import com.alpha.ABClogistics.Service.CarrierService;
import com.alpha.ABClogistics.Service.DriverService;
import com.alpha.ABClogistics.Service.OrderService;
import com.alpha.ABClogistics.Service.TruckService;


@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AddresService addresService;
	@Autowired
	CarrierService carrierService;
	@Autowired
	TruckService truckService;
	@Autowired
	DriverService driverservice;
	@Autowired
	OrderService orderservice;
	
//	ADDRESS ROUTES
	@PostMapping("/saveaddress")
	public ResponseEntity<ResponseStructure<Address>> saveaddress(@RequestBody Address address) {
		return addresService.saveAddress(address);
	}
	@GetMapping("/findaddressbyid/{id}")
	public ResponseEntity<ResponseStructure<Address>> findaddress(@PathVariable int id) {
		return addresService.getaddressbyid(id);
	}
	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<String> deleteAddress(@PathVariable int id) {
	    addresService.deleteaddress(id);
	    return ResponseEntity.ok("Address deleted successfully"+""+id);
	}

		
	
//	CARRIER ROUTES
	@PostMapping("/savecarrier")
	public ResponseEntity<ResponseStructure<Carrier>> saveCarrier(@RequestBody Carrier carrier) {
	return carrierService.saveCarrier(carrier);
	}
	
	@GetMapping("/findcarrier")
	public ResponseEntity<ResponseStructure<Carrier>> findCarrier(@RequestParam int id) {
		return carrierService.findCarrier(id);
	}
	@DeleteMapping("/deletecarrier")
	public ResponseEntity<ResponseStructure<Carrier>> deleteCarrier(@RequestParam int id) {
		return carrierService.deleteCarrier(id);
	}
	
	

//TRUCK ROUTES
	@PostMapping("/saveTruck")
	public ResponseEntity<ResponseStructure<Truck>> saveTruck(@RequestBody TruckDto truckDto) {
		return truckService.saveTruck(truckDto);
	}
	@GetMapping("/findtruck")
	public ResponseEntity<ResponseStructure<Truck>> findtruck(@RequestParam int id) {
		return truckService.findTruck(id);
	}
	
	@DeleteMapping("/deletetruck")
	public ResponseEntity<ResponseStructure<Truck>> deletetruck(@RequestParam int id) {
		return truckService.deletetruck(id);
	}
	
	@PutMapping("/updatetruck/{truckid}/assignCarrier/{carrierid}")
	public ResponseEntity<ResponseStructure<Truck>> updateTruck(@PathVariable int truckid,@PathVariable int carrierid) {
		return truckService.updateTruck(truckid,carrierid);
	}
	@PutMapping("/updatetruckcapacity/{truckid}/capacity/{capacity}")
	public ResponseEntity<ResponseStructure<Truck>> updateTruckCapacity(@PathVariable int truckid, @PathVariable int capacity) {
	        
	    return truckService.updateTruckCapacity(truckid, capacity);
	}

	
//	DRIVER ROUTES
	@PostMapping("/saveDriver")
	public  ResponseEntity<ResponseStructure<Driver>> saveDriver(@RequestBody DriverDto driverdto) {
		return driverservice.saveDriver(driverdto);
	}
	
	@GetMapping("/finddriver")
	public  ResponseEntity<ResponseStructure<Driver>>  finddriver(@RequestParam int id) {
		return driverservice.findDiver(id);
	}
	@DeleteMapping("/deleteDriver")
	public ResponseEntity<ResponseStructure<Driver>> deletedriver(@RequestParam int id ) {
		return driverservice.deletedriver(id);
	}
	
	@PutMapping("/updatedriver/{driverid}/assignCarrier/{carrierid}/assigntruck/{truckid}")
	public ResponseEntity<ResponseStructure<Driver>> updateDriver(@PathVariable int driverid,@PathVariable int carrierid,@PathVariable int truckid) {
		return driverservice.updateDriver(driverid,carrierid,truckid);
	}
	
	
	@GetMapping("/findorder")
	public ResponseEntity<ResponseStructure<Order>> findorder(@RequestParam int id) {
		return orderservice.findOrder(id);
	}
	@DeleteMapping("/deleteorder")
	public ResponseEntity<ResponseStructure<Order>> deleteOrder(@RequestParam int id) {
		return orderservice.deleteOrder(id);
	}
	
	
	@PutMapping("/updateOrder/{orderId}/assigncarrier/{truckId}")
	public ResponseEntity<ResponseStructure<Order>> updateOrder(@PathVariable int orderId, @PathVariable int truckId ) throws TruckCapacityExceededException {
		return orderservice.updateOrder(orderId, truckId);
	}
	
//	updating cargo weight seperately
	@PutMapping("/updateOrdercargoweight/{orderId}/cargo/{weight}")
	public ResponseEntity<ResponseStructure<Order>> updateCargoWeight(
	        @PathVariable int orderId,
	        @PathVariable int weight) {
	    return orderservice.updateCargoWeight(orderId, weight);
	}

	@PutMapping("/updateLoading/{orderId}/updateLoading")
	public ResponseEntity<ResponseStructure<Order>> updateLoading(@PathVariable int orderId, @RequestBody LoadingDto Ldto) {
		return orderservice.updateLoading(orderId, Ldto);
	}
	@PutMapping("/updateUnLoading/{orderId}/updateUnLoading")
	public ResponseEntity<ResponseStructure<Order>> updateLoading(@PathVariable int orderId, @RequestBody UnLoadingDto unLdto) {
		return orderservice.updateUnLoading(orderId, unLdto);
	}
	
}
	
	
	
	
	
	

