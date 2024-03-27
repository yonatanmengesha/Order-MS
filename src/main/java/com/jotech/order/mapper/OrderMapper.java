package com.jotech.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jotech.order.dto.OrderDTO;
import com.jotech.order.entity.Order;

@Mapper
public interface OrderMapper {
	
	OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
	
	Order mapOrderDtoToOrder(OrderDTO orderDTO);
	OrderDTO mapOrderToOrderDTO(Order order);
	
	
	
	

}
