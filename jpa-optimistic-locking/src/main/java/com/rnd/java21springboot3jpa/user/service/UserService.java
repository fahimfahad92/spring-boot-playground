package com.rnd.java21springboot3jpa.user.service;

import com.rnd.java21springboot3jpa.user.db.AddressRepository;
import com.rnd.java21springboot3jpa.user.db.OrderRepository;
import com.rnd.java21springboot3jpa.user.db.UserRepository;
import com.rnd.java21springboot3jpa.user.entity.Address;
import com.rnd.java21springboot3jpa.user.entity.Order;
import com.rnd.java21springboot3jpa.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    private static final Logger logger =
            LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public UserService(UserRepository userRepository, AddressRepository addressRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.orderRepository = orderRepository;
    }


    public User createUser(String firstName, String lastName) {
        User user = new User(firstName, lastName);
        return userRepository.save(user);
    }

    public Address updateAddress(Long userId, String city, String area) {
        logger.info("Updating address");
        Address address = new Address(city, area, userId);
        return addressRepository.save(address);
    }

    public Order createOrder(String itemName, int quantity, BigDecimal price, BigDecimal totalPrice, Long userId) {
        Order order = new Order(itemName, quantity, price, totalPrice, userId);
        return orderRepository.save(order);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<Order> getOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
