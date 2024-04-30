package com.griddynamics.gridhub.payment.mapper;

import com.griddynamics.gridhub.payment.dto.PaypalDto;
import com.griddynamics.gridhub.payment.model.Paypal;
import org.apache.commons.lang3.function.TriFunction;

public class PaypalMapper implements TriFunction<Long, Long, PaypalDto, Paypal> {
  @Override
  public Paypal apply(Long id, Long userId, PaypalDto paypalDto) {
    return Paypal.builder()
        .id(id)
        .userId(userId)
        .paymentType(paypalDto.getPaymentType())
        .email(paypalDto.getEmail())
        .build();
  }
}
