package com.jotech.order.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.jotech.order.dto.OrderDTO;
import com.jotech.order.dto.OrderDTOFromFE;
import com.jotech.order.dto.UserDTO;
import com.jotech.order.entity.Order;
import com.jotech.order.mapper.OrderMapper;
import com.jotech.order.repo.OrderRepo;

@Service
public class OrderService {
	
	@Autowired
    OrderRepo orderRepo;

    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    RestTemplate restTemplate;



    public OrderDTO saveOrderInDb(OrderDTOFromFE orderDetails) {
        Integer newOrderID = sequenceGenerator.generateNextOrderId();
    	//ObjectId newOrderID = sequenceGenerator.generateNextOrderId();
        UserDTO userDTO = fetchUserDetailsFromUserId(orderDetails.getUserId()); // null
        Order orderToBeSaved = new Order(newOrderID, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(), userDTO );
        orderRepo.save(orderToBeSaved);
        return OrderMapper.INSTANCE.mapOrderToOrderDTO(orderToBeSaved);
    }

    public  UserDTO fetchUserDetailsFromUserId(Integer userId) {
       return restTemplate.getForObject("http://UserInformation/user/fetchUserById/" + userId, UserDTO.class);
    }
	/*
	 * @Autowired OrderRepo orderRepo;
	 * 
	 * @Autowired SequenceGenerator sequenceGenerator;
	 * 
	 * @Autowired RestTemplate restTemplate;
	 * 
	 * public OrderDTO saveOrderInDb(OrderDTOFromFE orderDetails) {
	 * 
	 * Integer newOrderId = sequenceGenerator.generateNextOrderId();
	 * 
	 * UserDTO userDTO = fetchUserDetailsFromUserId(orderDetails.getUserId());
	 * 
	 * Order orderToBeSaved = new
	 * Order(newOrderId,orderDetails.getFoodItemList(),orderDetails.getRestaurant(),
	 * userDTO);
	 * 
	 * orderRepo.save(orderToBeSaved);
	 * 
	 * return OrderMapper.INSTANCE.mapOrderToOrderDTO(orderToBeSaved);
	 * 
	 * }
	 * 
	 * private UserDTO fetchUserDetailsFromUserId(Integer userId) {
	 * 
	 * return
	 * restTemplate.getForObject("http://USER-SERVICE/user//fetchUserById/"+userId,
	 * UserDTO.class);
	 * 
	 * }
	 */

	public List<OrderDTO >fetchAllOrdersService() {
		// TODO Auto-generated method stub
		
		
		List<Order> orders = orderRepo.findAll();
	   List<OrderDTO>  allOrderDTOs =  orders.stream().map(order -> OrderMapper.INSTANCE.mapOrderToOrderDTO(order)).collect(Collectors.toList());
	           return allOrderDTOs;		
	
	}

	public ResponseEntity<OrderDTO> fetchOrderByAppliyingId(Integer id) {
		//ObjectId objId = new ObjectId(id);
		Optional<Order> fetchedOrder = orderRepo.findById(id);
		
		if(fetchedOrder.isPresent()) {
			
			Order orderToBeFound = fetchedOrder.get();
			return  new ResponseEntity<>(OrderMapper.INSTANCE.mapOrderToOrderDTO(orderToBeFound),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}

}
