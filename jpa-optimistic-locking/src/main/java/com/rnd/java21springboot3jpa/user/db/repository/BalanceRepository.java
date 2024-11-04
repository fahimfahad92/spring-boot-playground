package com.rnd.java21springboot3jpa.user.db.repository;

import com.rnd.java21springboot3jpa.user.entity.UserBalance;
import jakarta.persistence.LockModeType;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface BalanceRepository extends JpaRepository<UserBalance, Long> {

  @Lock(value = LockModeType.OPTIMISTIC_FORCE_INCREMENT)
  UserBalance findByUserId(Long userId);

  @Lock(value = LockModeType.READ)
  @Query("SELECT b.balance from UserBalance b where b.userId = :userId")
  BigDecimal getUserBalanceForUser(Long userId);
}
