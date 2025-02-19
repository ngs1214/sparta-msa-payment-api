package com.seung.payment_api.domain.entity;

import com.seung.payment_api.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "payments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "order_id", nullable = false)
    Long orderId;

    @Column(name = "amount", nullable = false)
    BigDecimal amount;

    @Setter
    @Column(name = "status", length = 50)
    @Enumerated(EnumType.STRING)
    Status status;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Builder
    public Payment(Long orderId, BigDecimal amount, Status status) {
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
    }
}
