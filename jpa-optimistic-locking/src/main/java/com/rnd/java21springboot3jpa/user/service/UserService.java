package com.rnd.java21springboot3jpa.user.service;

import com.rnd.java21springboot3jpa.user.db.repository.AddressRepository;
import com.rnd.java21springboot3jpa.user.db.repository.OrderRepository;
import com.rnd.java21springboot3jpa.user.entity.UserOrderSummary;
import com.rnd.java21springboot3jpa.user.db.repository.UserRepository;
import com.rnd.java21springboot3jpa.user.entity.Address;
import com.rnd.java21springboot3jpa.user.entity.Order;
import com.rnd.java21springboot3jpa.user.entity.User;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  private final UserRepository userRepository;
  private final AddressRepository addressRepository;
  private final OrderRepository orderRepository;

  @Autowired
  public UserService(
      UserRepository userRepository,
      AddressRepository addressRepository,
      OrderRepository orderRepository) {
    this.userRepository = userRepository;
    this.addressRepository = addressRepository;
    this.orderRepository = orderRepository;
  }

  public User createUser(String firstName, String lastName) {
    User user = new User(firstName, lastName);
    return userRepository.save(user);
  }

  public Address updateAddress(User user, String city, String area) {
    logger.info("Updating address");
    Address address = new Address(city, area, user.getId());
    user.setAddress(address);
    userRepository.save(user);
    return user.getAddress();
  }

  public Order createOrder(
      String itemName, int quantity, BigDecimal price, BigDecimal totalPrice, User user) {
    Order order = new Order(itemName, quantity, price, totalPrice, user.getId());
    return orderRepository.save(order);
  }

  public User getUserById(Long userId) {
    return userRepository.findById(userId).orElse(null);
  }

  public Address getAddressByUserId(Long userId) {
    return addressRepository.findByUserId(userId);
  }

  public List<Order> getOrders(Long userId) {
    return orderRepository.findByUserId(userId);
  }

  public void deleteUser(Long userId) {
    userRepository.deleteById(userId);
  }

  public void deleteUserOrder(Long userId) {
    orderRepository.deleteByUserId(userId);
  }

  @Transactional
  public User prepareData() {
    User user = createUser("Fahim", "Fahad");
    Address address = updateAddress(user, "Dhaka", "Dhaka");
    logger.info("Updating address done");

//    Order order1 = createOrder("Item1", 1, BigDecimal.valueOf(50), BigDecimal.valueOf(50), user);
//    Order order2 = createOrder("Item2", 1, BigDecimal.valueOf(50), BigDecimal.valueOf(50), user);

    return user;
  }

  @Transactional
  public void createOrder(User user, int orderId) {
    createOrder("Item " + orderId, 1, BigDecimal.valueOf(50), BigDecimal.valueOf(50), user);
  }

  @Transactional
  public void getData(User user) {
    User updatedUser = getUserById(user.getId());

    logger.info("Getting address");
    Address updatedAddress = getAddressByUserId(updatedUser.getId());
    logger.info("Getting address done");

    logger.info("Getting orders");
    List<Order> orders = getOrders(updatedUser.getId());
    logger.info("Getting orders done");

    updatedUser.setLastName("Fahad Olein");
    userRepository.save(updatedUser);

    updatedAddress.setArea("Dhaka updated");
    addressRepository.save(updatedAddress);

    Order order1 = orders.getFirst();
    order1.setItemName("Item1 updated");

    List<UserOrderSummary> orderSummaries = orderRepository.getUserOrderSummary();

    orderSummaries.forEach(os -> System.out.println(os.getUserId() + " " + os.getTotalPurchase()));

    UserOrderSummary userOrderSummary = orderRepository.getUserOrderSummaryForUser(updatedUser.getId());

    System.out.println("For user " + userOrderSummary.getUserId() + " " + userOrderSummary.getTotalPurchase());

    //    deleteUserOrder(updatedUser.getId());
    //    deleteUser(updatedUser.getId());

    logger.info("DB operation completed");
  }
}
