package com.griddynamics.gridhub.payment.repository;

import com.griddynamics.gridhub.payment.database.QueryHandler;
import com.griddynamics.gridhub.payment.mapper.resultSetToModel.ResultSetPaypalMapper;
import com.griddynamics.gridhub.payment.model.Paypal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.List;
//@Component will be changed to @Repository when we will be adding spring data
@Component
@AllArgsConstructor
public class PaypalRepository implements Repository<Paypal> {


  QueryHandler<Paypal> queryHandler;
  ResultSetPaypalMapper resultSetPaypalMapper;

  private static final String SAVE_PAYPAL_QUERY = "INSERT INTO paypal_accounts (id, user_id, payment_type, email) VALUES (?, ?, ?, ?)";
  private static final String DELETE_PAYPAL_QUERY = "DELETE FROM paypal_accounts WHERE id = ?";
  private static final String UPDATE_PAYPAL_QUERY = "UPDATE paypal_accounts SET email = ? WHERE id = ?";
  private static final String GET_PAYPAL_BY_ID_QUERY = "SELECT * FROM paypal_accounts WHERE id = ?";
  private static final String GET_PAYPAL_BY_USER_ID_QUERY = "SELECT * FROM paypal_accounts WHERE user_id = ?";
  private static final String GET_NEXT_ID_QUERY = "SELECT MAX(id) FROM paypal_accounts";


  @Override
  public Paypal save(Paypal paypal) {
    queryHandler.execute(SAVE_PAYPAL_QUERY, paypal.getId(), paypal.getUserId(), paypal.getPaymentType().name(), paypal.getEmail());
    return paypal;
  }

  @Override
  public void delete(Long paypalId) {
    queryHandler.execute(DELETE_PAYPAL_QUERY, paypalId);
  }

  @Override
  public Paypal update(Paypal paypal) {
    queryHandler.execute(UPDATE_PAYPAL_QUERY, paypal.getEmail(), paypal.getId());
    return paypal;
  }

  @Override
  public List<Paypal> get(Long userId) {
    return queryHandler.findMany(GET_PAYPAL_BY_USER_ID_QUERY, resultSetPaypalMapper, userId);
  }

  @Override
  public boolean isContains(Long paymentMethodId) {
    return queryHandler.findOne(GET_PAYPAL_BY_ID_QUERY, resultSetPaypalMapper, paymentMethodId) != null;
  }

  public Long getNextId() {
    return queryHandler.findMany(GET_NEXT_ID_QUERY, resultSetPaypalMapper).size() + 1L;
  }
}
