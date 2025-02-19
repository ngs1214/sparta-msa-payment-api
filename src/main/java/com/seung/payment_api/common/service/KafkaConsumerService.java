package com.seung.payment_api.common.service;

import com.seung.payment_api.domain.dto.PaymentRequestDto;
import com.seung.payment_api.domain.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final PaymentService paymentService;

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "order-group")
    public void listen(String message) {
        try {
            log.info("order-event payload: {}", message);
            //TODO : Order Id에 status에 대한 별도 처리 및 합계 금액 별도 처리 필요
            PaymentRequestDto requestDto = PaymentRequestDto.builder()
                    .amount(BigDecimal.ZERO)
                    .orderId(Long.valueOf(message))
                    .build();
            paymentService.create(requestDto);
        }catch (Exception e) {
            log.error(e.getMessage());
        }

    }

}
