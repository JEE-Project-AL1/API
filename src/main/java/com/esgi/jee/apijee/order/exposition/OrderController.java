package com.esgi.jee.apijee.order.exposition;
import com.esgi.jee.apijee.order.application.OrderMapper;
import com.esgi.jee.apijee.order.application.OrderService;
import com.esgi.jee.apijee.order.domain.Order;
import com.esgi.jee.apijee.order.exposition.request.OrderRequest;
import com.esgi.jee.apijee.order.exposition.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping( value = "/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<Void> saveOrder(@RequestBody OrderRequest orderRequest){
        Order order =orderService.saveOrder(orderRequest);
        URI location = URI.create(
                ServletUriComponentsBuilder.fromCurrentRequest().build().toUri() + "/" + order.getId());
        return ResponseEntity.created(location).build();
    }
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(){
        List<Order>  orders =orderService.getAllOrders();
        List<OrderResponse> orderResponses = orders.stream().map(order->orderMapper.mapToOrderResponse(order)).collect(Collectors.toList());
        return ResponseEntity.ok().body(orderResponses);
    }
    @GetMapping(path="/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id){
        Order order = orderService.getOrderById(id);
        OrderResponse orderResponse = orderMapper.mapToOrderResponse(order);
        return ResponseEntity.ok().body(orderResponse);
    }
    @GetMapping(path="/user/{userId}")
    public ResponseEntity<List<OrderResponse>> getOrderByUserId(@PathVariable(value = "userId") Long id){
        List<Order>  orders = orderService.getOrderByUserId(id);
        List<OrderResponse> orderResponses = orders.stream().map(order->orderMapper.mapToOrderResponse(order)).collect(Collectors.toList());
        return ResponseEntity.ok().body(orderResponses);
    }

}
