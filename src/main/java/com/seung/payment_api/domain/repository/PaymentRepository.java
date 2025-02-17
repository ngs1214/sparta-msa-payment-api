package com.seung.payment_api.domain.repository;

import com.seung.payment_api.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
