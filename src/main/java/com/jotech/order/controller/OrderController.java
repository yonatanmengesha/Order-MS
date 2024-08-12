package com.jotech.order.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jotech.order.dto.OrderDTO;
import com.jotech.order.dto.OrderDTOFromFE;
import com.jotech.order.dto.UserDTO;
import com.jotech.order.entity.Order;
import com.jotech.order.service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/saveOrder")
	public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTOFromFE orderDetails){
		
		OrderDTO orderSavedInDb = orderService.saveOrderInDb(orderDetails);
		
		return new ResponseEntity<>(orderSavedInDb,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/fetchOrderById/{id}")
	public ResponseEntity<OrderDTO>fetchOrderByIdFromOrder(@PathVariable Integer id){
		
		//ObjectId objId = new ObjectId(id);
		
		return  orderService.fetchOrderByAppliyingId(id);
		
	}
	@GetMapping("/fetchAllOrders")
	public ResponseEntity<List<OrderDTO>> fetchAllOrders(){
		
		 List<OrderDTO> orederResult =  orderService.fetchAllOrdersService();
		
		return   new ResponseEntity<>(orederResult,HttpStatus.FOUND);         
		
	}
	
	@GetMapping("/fetchUserByIdFromOrder/{id}")
	public ResponseEntity<UserDTO>   fetchUserByID(@PathVariable Integer id ) {
		
		UserDTO  foundUser =  orderService.fetchUserDetailsFromUserId(id);
		
		return new ResponseEntity<>(foundUser,HttpStatus.FOUND);
	}

}
