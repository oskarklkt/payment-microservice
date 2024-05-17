package com.griddynamics.gridhub.payment.service.implementation;

import com.griddynamics.gridhub.payment.dto.PaypalDto;
import com.griddynamics.gridhub.payment.exception.NoSuchElementException;
import com.griddynamics.gridhub.payment.exception.PaypalException;
import com.griddynamics.gridhub.payment.mapper.modelToDto.PaypalDtoMapper;
import com.griddynamics.gridhub.payment.mapper.dtoToModel.PaypalMapper;
import com.griddynamics.gridhub.payment.model.Paypal;
import com.griddynamics.gridhub.payment.repository.PaypalRepository;
import com.griddynamics.gridhub.payment.service.PaypalService;
import com.griddynamics.gridhub.payment.util.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaypalServiceImpl implements PaypalService {
  private final PaypalRepository paypalRepository;
  private final PaypalDtoMapper paypalDtoMapper;
  private final ValidationUtil validationUtil;
  private final PaypalMapper paypalMapper;

  @Override
  public PaypalDto save(Long userId, PaypalDto paypalDto) {
    if (!validationUtil.validatePaypal(paypalDto)) {
      throw new PaypalException("Invalid paypal data");
    }
    Paypal paypal = paypalMapper.apply(paypalRepository.getNextId(), userId, paypalDto);
    return paypalDtoMapper.apply(paypalRepository.save(paypal));
  }

  @Override
  public void delete(Long paymentMethodId) {
    if (paypalRepository.isContains(paymentMethodId)) {
      throw new NoSuchElementException("No such element in paypal database");
    }
    paypalRepository.delete(paymentMethodId);
  }

  @Override
  public PaypalDto update(Long paymentMethodId, Long userId, PaypalDto paypalDto) {
    if (paypalRepository.isContains(paymentMethodId)) {
      throw new NoSuchElementException("No such element in paypal database");
    }
    if (!validationUtil.validatePaypal(paypalDto)) {
      throw new PaypalException("Invalid paypal data");
    }
    Paypal paypal = paypalMapper.apply(paymentMethodId, userId, paypalDto);
    return paypalDtoMapper.apply(paypalRepository.update(paypal));
  }

  @Override
  public List<PaypalDto> get(Long userId) {
    return paypalRepository
        .get(userId)
        .stream()
        .map(paypalDtoMapper)
        .toList();
  }
}
