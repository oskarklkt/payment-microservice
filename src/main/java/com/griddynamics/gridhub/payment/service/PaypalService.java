package com.griddynamics.gridhub.payment.service;

import com.griddynamics.gridhub.payment.dto.PaypalDto;
import com.griddynamics.gridhub.payment.mapper.PaypalDtoMapper;
import com.griddynamics.gridhub.payment.mapper.PaypalMapper;
import com.griddynamics.gridhub.payment.repository.PaypalRepository;
import com.griddynamics.gridhub.payment.util.ValidationUtil;
import lombok.AllArgsConstructor;

import java.util.List;

// todo implement paypal service
@AllArgsConstructor
public class PaypalService implements PaymentService<PaypalDto> {
  private final PaypalRepository paypalRepository;
  private final PaypalDtoMapper paypalDtoMapper;
  private final ValidationUtil validationUtil;
  private final PaypalMapper paypalMapper;

  @Override
  public PaypalDto save(Long userId, PaypalDto paypalDto) {
    return null;
  }

  @Override
  public void delete(Long paymentMethodId) {}

  @Override
  public PaypalDto update(Long paymentId, Long userId, PaypalDto paypalDto) {
    return null;
  }

  @Override
  public List<PaypalDto> get(Long userId) {
    return List.of();
  }
}
