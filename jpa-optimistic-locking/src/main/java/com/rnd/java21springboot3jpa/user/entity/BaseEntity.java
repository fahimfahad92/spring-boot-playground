package com.rnd.java21springboot3jpa.user.entity;

import jakarta.persistence.Column;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.Instant;

public class BaseEntity {

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
}
