package com.rnd.java21springboot3jpa.user.db;

import com.rnd.java21springboot3jpa.user.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByUserId(Long userId);

  void deleteByUserId(Long userId);

  @Query(
      value =
          "select user_id as userId, sum(total_price) as totalPurchase from user_order group by user_id",
      nativeQuery = true)
  List<UserOrderSummary> getUserOrderSummary();
}
