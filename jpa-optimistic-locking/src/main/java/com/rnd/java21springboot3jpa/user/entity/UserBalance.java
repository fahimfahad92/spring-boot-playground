package com.rnd.java21springboot3jpa.user.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "user_balance")
public class UserBalance extends BaseEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  Long id;

  @Column(name = "balance")
  BigDecimal balance;

  @Column(name = "user_id")
  Long userId;

  @Version
  @Column(name = "VERSION", nullable = false)
  private Long version;

  public UserBalance() {}

  public UserBalance(BigDecimal balance, Long userId) {
    this.balance = balance;
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }
}
