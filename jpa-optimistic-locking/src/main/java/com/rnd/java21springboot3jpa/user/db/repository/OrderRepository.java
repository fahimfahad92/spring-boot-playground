package com.rnd.java21springboot3jpa.user.db.repository;

import com.rnd.java21springboot3jpa.user.entity.Order;
import java.util.List;

import com.rnd.java21springboot3jpa.user.entity.UserOrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByUserId(Long userId);

  void deleteByUserId(Long userId);

  @Query("select userId as userId, sum(totalPrice) as totalPurchase from Order group by userId")
  List<UserOrderSummary> getUserOrderSummary();

  @Query(
      "select userId as userId, sum(totalPrice) as totalPurchase from Order where userId = :userId group by userId")
  UserOrderSummary getUserOrderSummaryForUser(Long userId);
}
