package com.rnd.java21springboot3jpa.user.db;

import com.rnd.java21springboot3jpa.user.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
  Address findByUserId(Long userId);
}
