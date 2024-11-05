package com.rnd.java21springboot3jpa.user.service;

import com.rnd.java21springboot3jpa.common.exception.InsufficientBalanceException;
import com.rnd.java21springboot3jpa.user.db.repository.AddressRepository;
import com.rnd.java21springboot3jpa.user.db.repository.BalanceRepository;
import com.rnd.java21springboot3jpa.user.db.repository.OrderRepository;
import com.rnd.java21springboot3jpa.user.db.repository.UserRepository;
import com.rnd.java21springboot3jpa.user.entity.*;
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
  private final BalanceRepository balanceRepository;
  private final AddressRepository addressRepository;
  private final OrderRepository orderRepository;

  @Autowired
  public UserService(
      UserRepository userRepository,
      BalanceRepository balanceRepository,
      AddressRepository addressRepository,
      OrderRepository orderRepository) {
    this.userRepository = userRepository;
    this.balanceRepository = balanceRepository;
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
  public Order createOrderWithBalanceCheck(Long userId, Long orderId) {
    UserBalance userBalance = balanceRepository.findByUserId(userId);
    if (userBalance.getBalance().compareTo(BigDecimal.valueOf(50)) < 0) {
      throw new InsufficientBalanceException();
    }
    User user = getUserById(userId);
    Order created = createOrder(user, orderId);
    userBalance.setBalance(userBalance.getBalance().subtract(BigDecimal.valueOf(50)));
    balanceRepository.save(userBalance);
    return created;
  }

  @Transactional
  public Order createOrderForLoadTest(Long userId, Long orderId) {
    User user = getUserById(userId);
    return createOrder("Item " + orderId, 1, BigDecimal.valueOf(50), BigDecimal.valueOf(50), user);
  }

  public Order createOrder(User user, Long orderId) {
    return createOrder("Item " + orderId, 1, BigDecimal.valueOf(50), BigDecimal.valueOf(50), user);
  }

  public BigDecimal getUserBalance(Long userId) {
    return balanceRepository.getUserBalanceForUser(userId);
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

    orderSummaries.forEach(os -> logger.info("{} {}", os.getUserId(), os.getTotalPurchase()));

    UserOrderSummary userOrderSummary =
        orderRepository.getUserOrderSummaryForUser(updatedUser.getId());

    logger.info(
        "For user {} {}", userOrderSummary.getUserId(), userOrderSummary.getTotalPurchase());

    //    deleteUserOrder(updatedUser.getId());
    //    deleteUser(updatedUser.getId());

    logger.info("DB operation completed");
  }

  @Transactional
  public User prepareData() {
    User user = createUser("Fahim", "Fahad");
    Address address = updateAddress(user, "Dhaka", "Dhaka");
    logger.info("Updating address done");

    createOrderForLoadTest(user.getId(),1L);
    createOrderForLoadTest(user.getId(),2L);

    UserBalance userBalance = new UserBalance(BigDecimal.valueOf(10000), user.getId());
    balanceRepository.save(userBalance);
    return user;
  }
}
