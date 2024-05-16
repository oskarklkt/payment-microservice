package com.griddynamics.gridhub.payment.mapper.modelToDto;

import com.griddynamics.gridhub.payment.dto.PaypalDto;
import com.griddynamics.gridhub.payment.model.Paypal;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PaypalDtoMapper implements Function<Paypal, PaypalDto> {

  @Override
  public PaypalDto apply(Paypal paypal) {
    return PaypalDto.builder()
        .paymentType(paypal.getPaymentType())
        .email(paypal.getEmail())
        .build();
  }
}
