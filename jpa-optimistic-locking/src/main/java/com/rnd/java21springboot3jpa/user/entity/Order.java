package com.rnd.java21springboot3jpa.user.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "user_order")
@EntityListeners(AuditingEntityListener.class)
public class Order {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  Long id;

  @Column(name = "item_name")
  String itemName;

  @Column(name = "quantity")
  int quantity;

  @Column(name = "price")
  BigDecimal price;

  @Column(name = "total_price")
  BigDecimal totalPrice;

  @Column(name = "user_id")
  Long userId;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

  @Column(name = "created_by")
  @CreatedBy
  public String createdBy;

  @Column(name = "updated_by")
  @LastModifiedBy
  private String updatedBy;

  public Order(
      String itemName, int quantity, BigDecimal price, BigDecimal totalPrice, Long userId) {
    this.itemName = itemName;
    this.quantity = quantity;
    this.price = price;
    this.totalPrice = totalPrice;
    this.userId = userId;
  }

  public Order() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "Order{"
        + "id="
        + id
        + ", itemName='"
        + itemName
        + '\''
        + ", quantity="
        + quantity
        + ", price="
        + price
        + ", totalPrice="
        + totalPrice
        + '}';
  }
}
