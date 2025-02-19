package com.seung.payment_api.domain.service;

import com.seung.payment_api.common.api.ServiceException;
import com.seung.payment_api.common.api.ServiceExceptionCode;
import com.seung.payment_api.domain.dto.PaymentRequestDto;
import com.seung.payment_api.domain.entity.Payment;
import com.seung.payment_api.domain.enums.Status;
import com.seung.payment_api.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment getPayment(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PAYMENT));
    }

    public Payment create(PaymentRequestDto dto) {
        Payment payment = Payment.builder()
                .orderId(dto.getOrderId())
                .amount(dto.getAmount())
                .status(Status.PENDING)
                .build();
        return paymentRepository.save(payment);
    }

    public Boolean updateStatus(Long id, Status status) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PAYMENT));
        payment.setStatus(status);
        return true;

    }
}
