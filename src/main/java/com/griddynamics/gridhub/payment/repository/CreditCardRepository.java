package com.griddynamics.gridhub.payment.repository;

import com.griddynamics.gridhub.payment.model.CreditCard;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CreditCardRepository implements Repository<CreditCard> {

  @Getter private static final Map<Long, CreditCard> db = new ConcurrentHashMap<>();

  @Override
  public CreditCard save(CreditCard creditCard) {
    db.putIfAbsent(creditCard.getId(), creditCard);
    return creditCard;
  }

  @Override
  public void delete(Long cardId) {
    db.remove(cardId);
  }

  @Override
  public CreditCard update(CreditCard creditCard) {
    db.put(creditCard.getId(), creditCard);
    return creditCard;
  }

  @Override
  public List<CreditCard> get(Long userId) {
    return db.values().stream().
            filter(creditCard -> creditCard.getUserId().equals(userId))
            .toList();
  }

  @Override
  public boolean isContains(Long paymentMethodId) {
      return !db.containsKey(paymentMethodId);
  }

  public static Long getNextId() {
    return db.keySet().stream().max(Long::compareTo).orElse(0L) + 1;
  }
}
