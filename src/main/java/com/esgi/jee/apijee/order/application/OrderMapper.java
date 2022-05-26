package com.esgi.jee.apijee.order.application;
import com.esgi.jee.apijee.order.domain.Order;
import com.esgi.jee.apijee.order.exposition.response.OrderItemResponse;
import com.esgi.jee.apijee.order.exposition.response.OrderResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final ModelMapper modelMapper;
    @Autowired
    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderResponse mapToOrderResponse(Order order){
        OrderResponse orderResponse= new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setDate(order.getDate());
        orderResponse.setTotalPrice(order.getTotalPrice());
        orderResponse.setFirstName(order.getUser().getFirstName());
        orderResponse.setLastName(order.getUser().getLastName());
        orderResponse.setCity(order.getUser().getCity());
        orderResponse.setAddress(order.getUser().getAddress());
        orderResponse.setEmail(order.getUser().getEmail());
        orderResponse.setPhoneNumber(order.getUser().getPhoneNumber());
        orderResponse.setPostIndex(order.getUser().getPostIndex());
        orderResponse.setOrderItems(order.getOrderItems().stream().map(orderItem -> modelMapper.map(orderItem, OrderItemResponse.class)).collect(Collectors.toList()));
        return orderResponse;
    }
}
