package com.rnd.java21springboot3jpa.user.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  Long id;

  @Column(name = "first_name")
  String firstName;

  @Column(name = "last_name")
  String lastName;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

  public User(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public User() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  //    public List<Order> getOrders() {
  //        return orders;
  //    }
  //
  //    public void setOrders(List<Order> orders) {
  //        this.orders = orders;
  //    }

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", firstName='" + firstName + ", lastName='" + lastName + '}';
  }
}
