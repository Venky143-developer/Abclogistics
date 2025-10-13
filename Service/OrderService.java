package com.alpha.ABClogistics.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import com.alpha.ABClogistics.DTO.LoadingDto;
import com.alpha.ABClogistics.DTO.OrderDto;
import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.DTO.UnLoadingDto;
import com.alpha.ABClogistics.Entity.Address;
import com.alpha.ABClogistics.Entity.Cargo;

import com.alpha.ABClogistics.Entity.Loading;
import com.alpha.ABClogistics.Entity.Order;
import com.alpha.ABClogistics.Entity.Truck;
import com.alpha.ABClogistics.Entity.Unloading;
import com.alpha.ABClogistics.Exception.CargoAlreadyExistsException;
import com.alpha.ABClogistics.Exception.OrderAlreadyExistException;
import com.alpha.ABClogistics.Exception.OrderNotFoundException;
import com.alpha.ABClogistics.Exception.TruckCapacityExceededException;
import com.alpha.ABClogistics.Exception.TrucknotFoundException;
import com.alpha.ABClogistics.Exception.addreessNotFoundException;
import com.alpha.ABClogistics.Repository.AddressRepository;
import com.alpha.ABClogistics.Repository.CargoRepository;
import com.alpha.ABClogistics.Repository.OrderRepository;
import com.alpha.ABClogistics.Repository.Truckrepository;

@Service
public class OrderService {
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	OrderRepository orderrepository;
	
	@Autowired
	CargoRepository cargorepository;
	@Autowired
	Truckrepository truckrepository;
	
	@Autowired 
	Mailservice mailservice;
	@Autowired
	JavaMailSender javamailsender;
	
	public  ResponseEntity<ResponseStructure<Order>> saveOrder(OrderDto dto) {
		Order odd= new Order();
	if(orderrepository.existsById(dto.getId())) {
		throw new OrderAlreadyExistException("Order with id"+ dto.getId()+"already exist");
	}
	odd.setId(dto.getId());
	odd.setOrderdate(dto.getOrderdate());
	int cost= 5*(dto.getCargowt()*dto.getCargoCount());
	odd.setCost(cost);
	odd.setEmail(dto.getEmail());
	if(cargorepository.existsById(dto.getCargoID())) {
		throw new CargoAlreadyExistsException("cargo with id"+ dto.getCargoID()+"already existed");
	}
	
	
	Cargo cargo = new Cargo(dto.getCargoID(), dto.getCargoName(), dto.getCargoDescription(), dto.getCargowt(), dto.getCargoCount());
	odd.setCargo(cargo);
	Loading load = new Loading();
	Address loadAdd = addressRepository.findById(dto.getLoadingAddId()).orElseThrow(()->new addreessNotFoundException("Address with id " + dto.getLoadingAddId() + " not found"));
	load.setAddress(loadAdd);
	odd.setLoading(load);
	Unloading unload = new Unloading();
	Address unloadAdd = addressRepository.findById(dto.getUnloadingAddId()).orElseThrow(()->new addreessNotFoundException("Address with id " + dto.getUnloadingAddId() + " not found"));
	unload.setAddress(unloadAdd);
	odd.setUnloading(unload);
	
	Order saved = orderrepository.save(odd);
	String tomail= odd.getEmail();
	String sub ="Order placed successfully";
	String content= "your order is successfully placed from"+" "+ odd.getLoading().getAddress().getCity()+ " "+"TO"+" "+odd.getUnloading().getAddress().getCity()+" "+"with the cost of" +" " +"₹"+odd.getCost();
	mailservice.sendMail(tomail, sub, content);
	ResponseStructure<Order> responseStructure = new ResponseStructure<Order>();
	
	responseStructure.setData(saved);
	responseStructure.setMeaasage("Order saved successfully");
	responseStructure.setStatuscode(HttpStatus.CREATED.value());
	return new ResponseEntity<ResponseStructure<Order>>(responseStructure, HttpStatus.CREATED);
}
	
	
		
	
public ResponseEntity<ResponseStructure<Order>> findOrder(int id) {
	Order order = orderrepository.findById(id).orElseThrow(()->new OrderNotFoundException("Order with id " + id + " not found"));
	ResponseStructure<Order> responseStructure = new ResponseStructure<Order>();
	responseStructure.setData(order);
	responseStructure.setMeaasage("Order found successfully");
	responseStructure.setStatuscode(HttpStatus.FOUND.value());
	return new ResponseEntity<ResponseStructure<Order>>(responseStructure, HttpStatus.FOUND);
}

public ResponseEntity<ResponseStructure<Order>> deleteOrder(int id) {
	Order order = orderrepository.findById(id).orElseThrow(()->new OrderNotFoundException("Order with id " + id + " not found"));
	orderrepository.delete(order);
	ResponseStructure<Order> responseStructure = new ResponseStructure<Order>();
	responseStructure.setData(order);
	responseStructure.setMeaasage("Order deleted successfully");
	responseStructure.setStatuscode(HttpStatus.OK.value());
	return new ResponseEntity<ResponseStructure<Order>>(responseStructure, HttpStatus.OK);
	
}
public ResponseEntity<ResponseStructure<Order>> updateOrder(int orderid, int truckid) throws TruckCapacityExceededException {
	Order ord = orderrepository.findById(orderid).orElseThrow(()->new OrderNotFoundException("Order with id " + orderid + " not found"));
	Truck truck = truckrepository.findById(truckid).orElseThrow(()->new TrucknotFoundException("Truck with id " + truckid + " not found"));
	int totalwtoforder = (ord.getCargo().getWeight()*ord.getCargo().getCount());
	int truckcapacity = truck.getCapacity();
	
	if(truckcapacity>=totalwtoforder) {
		ord.setCarrier(truck.getCarrier());
		truck.setCapacity(truck.getCapacity()-totalwtoforder);
		truckrepository.save(truck);
		orderrepository.save(ord);
		String toemail= ord.getEmail();
		String sub = "Assigned carrier to your order";
		String content = "Assigned carrier to your order "+" "+"Contact number:"+ " " +ord.getCarrier().getContact()+ " "  +" Email:"+ord.getCarrier().getMail()+" "+ "If you have any queries Please contact this person";
		mailservice.sendMail(toemail, sub, content);
		
	}else {
		throw new TruckCapacityExceededException("Order weight "+totalwtoforder+" exceeds the available capacity of truck "+truckcapacity);
	}
	ResponseStructure<Order> responseStructure = new ResponseStructure<Order>();
	responseStructure.setData(ord);
	responseStructure.setMeaasage("Order updated successfully");
	responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
	return new ResponseEntity<ResponseStructure<Order>>(responseStructure, HttpStatus.ACCEPTED);
}


public ResponseEntity<ResponseStructure<Order>> updateCargoWeight(int orderId, int weight) {
	
	   
	    Order order = orderrepository.findById(orderId)
	            .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));

	    
	    Cargo cargo = order.getCargo();
	    if (cargo == null) {
	        throw new RuntimeException("Cargo not found for this order"); 
	    }

	   
	    cargo.setWeight(weight);
	    order.setCargo(cargo); 
	    int newCost = 5 * (cargo.getWeight() * cargo.getCount());
	    order.setCost(newCost);

	    
	    orderrepository.save(order);

	   
	    ResponseStructure<Order> responseStructure = new ResponseStructure<>();
	    responseStructure.setData(order);
	    responseStructure.setMeaasage("Cargo weight updated successfully");
	    responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());

	    return new ResponseEntity<>(responseStructure, HttpStatus.ACCEPTED);
	}

public ResponseEntity<ResponseStructure<Order>> updateLoading(int orderId, LoadingDto Ldto) {
	Order ord = orderrepository.findById(orderId).orElseThrow(()->new OrderNotFoundException("Order with id " + orderId + " not found"));
	ord.getLoading().setDate(Ldto.getDate());
	ord.getLoading().setTime(Ldto.getTime());
	ord.setStatus("Pending");
	orderrepository.save(ord);
	
	String toemail= ord.getEmail();
     String sub= "Your Order is pending And successfully loaded from location"+ " "+ord.getLoading().getAddress().getCity();
       String content= "Your order is pending and loaded from:" +" " + ord.getLoading().getAddress().getCity()+ " "+ "Date:"+ " "+ord.getLoading().getDate()+", "+ "Time:"+ " "+ord.getLoading().getTime()+".";
	mailservice.sendMail(toemail, sub, content);
	
	ResponseStructure<Order> rs = new ResponseStructure<Order>();
	rs.setStatuscode(HttpStatus.ACCEPTED.value());
	rs.setMeaasage("updated");
	rs.setData(ord);
	return new ResponseEntity<ResponseStructure<Order>>(rs,HttpStatus.ACCEPTED);
}
public ResponseEntity<ResponseStructure<Order>> updateUnLoading(int orderId, UnLoadingDto unLdto) {
	Order ord = orderrepository.findById(orderId).orElseThrow(()->new OrderNotFoundException("Order with id " + orderId + " not found"));
	ord.getUnloading().setDate(unLdto.getDate());
	ord.getUnloading().setTime(unLdto.getTime());
	ord.setStatus("Delivered");
	orderrepository.save(ord);
	String toemail= ord.getEmail();
	
    String sub= "Your Order is successfullly Delivered at"+ " "+ord.getUnloading().getAddress().getCity();
      String content= "Your order is successfully delivered at:" +" " + ord.getUnloading().getAddress().getCity()+ " "+ "Date:"+ " "+ord.getLoading().getDate()+", "+ "Time:"+ " "+ord.getLoading().getTime()+".";
	mailservice.sendMail(toemail, sub, content);
	
	ResponseStructure<Order> rs = new ResponseStructure<Order>();
	rs.setStatuscode(HttpStatus.ACCEPTED.value());
	rs.setMeaasage("Order deliverd succesfully");
	rs.setData(ord);
	return new ResponseEntity<ResponseStructure<Order>>(rs,HttpStatus.ACCEPTED);
}
}





	










