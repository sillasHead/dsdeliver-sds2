package com.myproject.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.myproject.dsdeliver.dto.OrderDTO;
import com.myproject.dsdeliver.dto.ProductDTO;
import com.myproject.dsdeliver.entities.Order;
import com.myproject.dsdeliver.entities.OrderStatus;
import com.myproject.dsdeliver.entities.Product;
import com.myproject.dsdeliver.repositories.OrderRepository;
import com.myproject.dsdeliver.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> list = orderRepository.findOrdersWithProducts();
        return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order(
            null, 
            dto.getAddress(), 
            dto.getLatitude(), 
            dto.getLongitude(), 
            Instant.now(), 
            OrderStatus.PENDING
        );
        for (ProductDTO p : dto.getProducts()) {
            Product product = productRepository.getOne(p.getId());
            order.getProducts().add(product);
        }
        order = orderRepository.save(order);
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO setDelivered(Long id) {
        Order order = orderRepository.getOne(id);
        order.setStatus(OrderStatus.DELIVERED);
        order = orderRepository.save(order);
        return new OrderDTO(order);
    }
}
