package com.rnd.java21springboot3jpa.user.db;

import com.rnd.java21springboot3jpa.user.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
