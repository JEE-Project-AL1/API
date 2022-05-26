package com.esgi.jee.apijee.order.application;

import com.esgi.jee.apijee.item.domain.Item;
import com.esgi.jee.apijee.item.exposition.payload.ItemDto;
import com.esgi.jee.apijee.item.infrastructure.ItemRepository;
import com.esgi.jee.apijee.order.domain.Order;
import com.esgi.jee.apijee.order.domain.OrderItem;
import com.esgi.jee.apijee.order.exposition.request.OrderRequest;
import com.esgi.jee.apijee.order.infrastructure.OrderRepository;
import com.esgi.jee.apijee.user.domain.User;
import com.esgi.jee.apijee.user.infrastructure.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    
    private final ItemRepository itemRepository;

    public Order saveOrder(OrderRequest orderRequest) {
        Order order = new Order();
        User user =userRepository.findById(orderRequest.getUserId()).orElseThrow(()->new com.esgi.jee.apijee.kernel.exceptions.EntityNotFoundException());
        order.setTotalPrice(orderRequest.getProducts().stream().mapToDouble(value -> value.getPrice().doubleValue() * value.getQuantity()).sum());
        List<OrderItem> orderItems =orderRequest.getProducts().stream().map(this::mapToOrderItem).collect(Collectors.toList());
        order.setOrderItems(orderItems);
        order.setUser(user);
        return orderRepository.save(order);
    }

    private OrderItem mapToOrderItem(ItemDto itemDto){
        OrderItem item = new OrderItem();
        item.setAmount(itemDto.getPrice());
        item.setQuantity(itemDto.getQuantity());
        Item itemFound = itemRepository.findById(itemDto.getId()).orElseThrow(()->new com.esgi.jee.apijee.kernel.exceptions.EntityNotFoundException());
        item.setItem(itemFound);
        return item;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrderByUserId(Long id) {
        User user =userRepository.findById(id).orElseThrow(()->new com.esgi.jee.apijee.kernel.exceptions.EntityNotFoundException());
        return orderRepository.findByUser(user);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(()->new com.esgi.jee.apijee.kernel.exceptions.EntityNotFoundException());
    }
}
