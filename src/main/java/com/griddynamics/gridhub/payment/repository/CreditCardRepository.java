package com.griddynamics.gridhub.payment.repository;

import com.griddynamics.gridhub.payment.model.CreditCard;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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
  public Optional<List<CreditCard>> get(Long userId) {
    return Optional.of(
        db.values().stream().filter(creditCard -> creditCard.getUserId().equals(userId)).toList());
  }

  public static Long getNextId() {
    return db.keySet().stream().max(Long::compareTo).orElse(0L) + 1;
  }
}
