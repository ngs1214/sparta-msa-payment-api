package com.seung.payment_api.common.api;

import lombok.Getter;

@Getter
public enum ServiceExceptionCode {

  NOT_FOUND_PAYMENT("NOT_FOUND_PAYMENT", "결제내역을 찾을 수 없습니다"),
  ;

  private final String code;
  private final String message;

  ServiceExceptionCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public String toString() {
    return "code : " + code + ", message :" + message;
  }
}
