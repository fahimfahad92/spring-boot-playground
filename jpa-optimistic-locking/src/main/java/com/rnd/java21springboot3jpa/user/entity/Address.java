package com.rnd.java21springboot3jpa.user.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "address")
@EntityListeners(AuditingEntityListener.class)
public class Address extends BaseEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  Long id;

  @Column(name = "city")
  String city;

  @Column(name = "area")
  String area;

  @Column(name = "user_id")
  Long userId;

  @OneToOne(mappedBy = "address")
  private User user;

  public Address(String city, String area, Long userId) {
    this.city = city;
    this.area = area;
    this.userId = userId;
  }

  public Address() {}

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "Address{"
        + "id="
        + id
        + ", city='"
        + city
        + '\''
        + ", area='"
        + area
        + '\''
        + ", userId="
        + userId
        + '}';
  }
}
