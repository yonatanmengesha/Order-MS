package com.jotech.order.service;

import org.springframework.beans.factory.annotation.Autowired;
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
        UserDTO userDTO = null; //fetchUserDetailsFromUserId(orderDetails.getUserId());
        Order orderToBeSaved = new Order(newOrderID, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(), userDTO );
        orderRepo.save(orderToBeSaved);
        return OrderMapper.INSTANCE.mapOrderToOrderDTO(orderToBeSaved);
    }

    private UserDTO fetchUserDetailsFromUserId(Integer userId) {
       return restTemplate.getForObject("http://USER-SERVICE/user/fetchUserById/" + userId, UserDTO.class);
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

}
