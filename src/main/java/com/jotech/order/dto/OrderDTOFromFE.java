package com.jotech.order.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTOFromFE {
	
	private Integer userId;
	private Restaurant restaurant;
	private List<FoodItemDTO> foodItemsList;
	

}
