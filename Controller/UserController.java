package com.alpha.ABClogistics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.ABClogistics.DTO.OrderDto;
import com.alpha.ABClogistics.DTO.ResponseStructure;
import com.alpha.ABClogistics.Entity.Order;
import com.alpha.ABClogistics.Repository.OrderRepository;
import com.alpha.ABClogistics.Service.OrderService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	OrderService orderservice;
	
	@PostMapping("/saveOrder")
	public ResponseEntity<ResponseStructure<Order>> saveOrder(@RequestBody @Valid OrderDto orderdto ) {
		return orderservice.saveOrder(orderdto);
	

		}
	

}
