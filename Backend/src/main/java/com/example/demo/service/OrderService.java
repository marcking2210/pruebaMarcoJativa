package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Order;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.OrderRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RequestMapping("/api/orders")
public class OrderService {
	
	 @Autowired
	    private OrderRepository orderRepository;

	    @PostMapping("/create")
	    private Order create( @RequestBody Order order) {
	    	orderRepository.save(order);
	        return order;
	    }

	    @GetMapping("/{order}")
	    private Order read(@PathVariable(value = "order") Integer orderId) {
	        return orderRepository.findById(orderId)
	                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
	    }

	    @GetMapping("/all")
	    private List<Order> all() {
	        List<Order> orders = (List<Order>) orderRepository.findAll();
	        return orders;
	    }

	    @PutMapping("/update")
	    private ResponseEntity<?> udpate(@PathVariable(value = "order") Integer orderId, @RequestBody Order orderDetail) {
	        Order order = orderRepository.findById(orderId)
	                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
	        order.setOrderCode(orderDetail.getOrderCode());
	        order.setClient(orderDetail.getClient());
	        order.setProduct(orderDetail.getProduct());
	        orderRepository.save(order);
	        return ResponseEntity.ok().build();
	    }

	    @DeleteMapping("/delete/{order}")
	    private ResponseEntity<?> delete(@PathVariable(value = "order") Integer orderId) {
	        Order order = orderRepository.findById(orderId)
	                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
	        orderRepository.delete(order);
	        return ResponseEntity.ok().build();
	    }

}
