package com.seung.payment_api.domain.controller;

import com.seung.payment_api.common.api.ApiResponse;
import com.seung.payment_api.domain.dto.PaymentRequestDto;
import com.seung.payment_api.domain.entity.Payment;
import com.seung.payment_api.domain.enums.Status;
import com.seung.payment_api.domain.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("payments")
@Tag(name = "Payment API", description = "결제 관련 API")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    @Operation(summary = "결제리스트조회", description = "결제리스트조회")
    public ApiResponse<List<Payment>> findAll() {
        return ApiResponse.success(paymentService.findAll());
    }

    @GetMapping("{id}")
    public ApiResponse<Payment> getPayment(@PathVariable("id") Long id) {
        return ApiResponse.success(paymentService.getPayment(id));
    }

    @PostMapping
    public ApiResponse<Payment> createPayment(@RequestBody PaymentRequestDto dto) {
        return ApiResponse.success(paymentService.create(dto));
    }

    @PatchMapping("{id}")
    public ApiResponse<Boolean> updateStatus(@PathVariable("id") Long id,@RequestParam("status") Status status) {
        return ApiResponse.success(paymentService.updateStatus(id, status));
    }
}
