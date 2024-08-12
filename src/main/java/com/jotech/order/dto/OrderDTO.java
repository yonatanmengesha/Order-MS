package com.jotech.order.dto;

import java.util.List;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	
	private Integer orderId;
	private List<FoodItemDTO> foodItemList;
	private Restaurant restaurant;
	private UserDTO userDTO;

}
