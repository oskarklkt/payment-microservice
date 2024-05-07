package com.griddynamics.gridhub.payment.repository;

import com.griddynamics.gridhub.payment.model.Paypal;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaypalRepository implements Repository<Paypal> {

  @Getter
  private static final Map<Long, Paypal> db = new ConcurrentHashMap<>();

  @Override
  public Paypal save(Paypal paypal) {
    db.putIfAbsent(paypal.getId(), paypal);
    return paypal;
  }

  @Override
  public void delete(Long paypalId) {
    db.remove(paypalId);
  }

  @Override
  public Paypal update(Paypal paypal) {
    db.put(paypal.getId(), paypal);
    return paypal;
  }

  @Override
  public List<Paypal> get(Long userId) {
    return db.values().stream()
            .filter(paypal -> paypal.getUserId().equals(userId))
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
