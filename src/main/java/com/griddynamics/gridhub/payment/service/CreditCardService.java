package com.griddynamics.gridhub.payment.service;

import com.griddynamics.gridhub.payment.dto.CreditCardDto;
import com.griddynamics.gridhub.payment.exception.CreditCardException;
import com.griddynamics.gridhub.payment.exception.NoSuchElementException;
import com.griddynamics.gridhub.payment.mapper.CreditCardDtoMapper;
import com.griddynamics.gridhub.payment.mapper.CreditCardMapper;
import com.griddynamics.gridhub.payment.model.CreditCard;
import com.griddynamics.gridhub.payment.repository.CreditCardRepository;
import com.griddynamics.gridhub.payment.util.ValidationUtil;
import lombok.AllArgsConstructor;
import java.util.List;


@AllArgsConstructor
public class CreditCardService implements PaymentService<CreditCardDto> {
  private final CreditCardRepository creditCardRepository;
  private final CreditCardDtoMapper creditCardDtoMapper;
  private final ValidationUtil validationUtil;
  private final CreditCardMapper creditCardMapper;

  @Override
  public CreditCardDto save(Long userId, CreditCardDto creditCardDto) {
    if (!validationUtil.validateCreditCard(creditCardDto)) {
      throw new CreditCardException("Invalid credit card data");
    }
    CreditCard creditCard =
        creditCardMapper.apply(CreditCardRepository.getNextId(), userId, creditCardDto);
    creditCardRepository.save(creditCard);
    return creditCardDto;
  }

  @Override
  public void delete(Long cardId) {
    if (!creditCardRepository.isContains(cardId)) {
      throw new NoSuchElementException("No such element in database");
    }
    creditCardRepository.delete(cardId);
  }

  @Override
  public CreditCardDto update(Long cardId, Long userId, CreditCardDto creditCardDto) {
    if (!creditCardRepository.isContains(cardId)) {
      throw new NoSuchElementException("No such payment method in database");
    }
    if (!validationUtil.validateCreditCard(creditCardDto)) {
      throw new CreditCardException("Invalid credit card data");
    }
    CreditCard creditCard = creditCardMapper.apply(cardId, userId, creditCardDto);
    creditCardRepository.update(creditCard);
    return creditCardDto;
  }

  @Override
  public List<CreditCardDto> get(Long userId) {
    return creditCardRepository
            .get(userId)
            .stream()
            .map(creditCardDtoMapper)
            .toList();
  }
}
