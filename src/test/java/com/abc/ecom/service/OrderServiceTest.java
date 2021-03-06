package com.abc.ecom.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.ecom.entity.Order;
import com.abc.ecom.entity.Product;
import com.abc.ecom.repository.OrderRepository;
import com.abc.ecom.repository.ProductRepository;

@SpringBootTest
public class OrderServiceTest {
	@InjectMocks
	OrderService orderService=new OrderServiceImpl();

	@Mock
	OrderRepository orderRepository;

	@Mock
	ProductRepository productRepository;

	@Test
	public void testgetOrderDetails() {
		Order order=new Order();
		order.setOrderId(102);
		order.getOrderAmount();
		order.setOrderDate(LocalDate.now());
		order.setProductId(301);
		order.setCustomerId(301);
		order.setQuantity(3);

		int orderId=102;
		Optional<Order> option=Optional.of(order);

		when(orderRepository.findById(102)).thenReturn(option);

		Order existing=orderService.getOrderDetails(orderId);

		assertEquals(order.getOrderAmount(),existing.getOrderAmount());
		assertEquals(order.getOrderId(),existing.getOrderId());


	}
	@Test
	public void testSaveOrder() {

		Product product=new Product();
		product.setProductCategory("Accessories");
		product.setProductId(1234);
		product.setProductName("Chargers");
		product.setProductPrice(5000);
		product.setCreateOn(LocalDate.of(2022,02,16));

		Optional<Product> optional=Optional.of(product);
		when(productRepository.findById(1234)).thenReturn(optional);

		Order order=new Order();
		order.setOrderId(103);
		order.getOrderAmount();
		order.setOrderDate(LocalDate.of(2022,10,22));
		order.setProductId(product.getProductId());
		order.setCustomerId(301);
		order.setQuantity(4);

		double total=order.getQuantity()*product.getProductPrice();

		when(orderRepository.save(order)).thenReturn(order);
		Order newOrder=orderService.saveOrder(order);
		assertEquals(20000,newOrder.getOrderAmount());

	}

}


