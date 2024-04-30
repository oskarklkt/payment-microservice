package com.griddynamics.gridhub.payment.repository;

import com.griddynamics.gridhub.payment.model.Paypal;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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
  public Optional<List<Paypal>> get(Long userId) {
    return Optional.of(
        db.values().stream().filter(paypal -> paypal.getUserId().equals(userId)).toList());
  }

  public static Long getNextId() {
    return db.keySet().stream().max(Long::compareTo).orElse(0L) + 1;
  }
}
