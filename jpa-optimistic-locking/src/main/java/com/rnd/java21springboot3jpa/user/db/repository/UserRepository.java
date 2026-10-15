package com.rnd.java21springboot3jpa.user.db.repository;

import com.rnd.java21springboot3jpa.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
