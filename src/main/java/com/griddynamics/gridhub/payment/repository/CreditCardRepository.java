package com.griddynamics.gridhub.payment.repository;

import com.griddynamics.gridhub.payment.datasource.QueryHandler;
import com.griddynamics.gridhub.payment.mapper.resultSetToModel.ResultSetCreditCardMapper;
import com.griddynamics.gridhub.payment.model.CreditCard;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
//@Component will be changed to @Repository when we will be adding spring data
@Component
@AllArgsConstructor
public class CreditCardRepository implements Repository<CreditCard> {

  private final ResultSetCreditCardMapper resultSetCreditCardMapper;
  QueryHandler<CreditCard> queryHandler;

  private static final String SAVE_CREDIT_CARD_QUERY = "INSERT INTO credit_cards (user_id, payment_type, card_holder_name, card_number, expiration_date, cvv) " +
          "VALUES (?, ?, ?, ?, ?, ?)";
  private static final String DELETE_CREDIT_CARD_QUERY = "DELETE FROM credit_cards WHERE id = ?";
  private static final String UPDATE_CREDIT_CARD_QUERY = "UPDATE credit_cards SET card_holder_name = ?, card_number = ?, expiration_date = ?, cvv = ? WHERE id = ?";
  private static final String GET_CREDIT_CARDS_BY_USER_ID_QUERY = "SELECT * FROM credit_cards WHERE user_id = ?";
    private static final String GET_CREDIT_CARD_BY_ID_QUERY = "SELECT * FROM credit_cards WHERE id = ?";
  private static final String GET_NEXT_ID_QUERY = "SELECT MAX(id) FROM credit_cards";

  @Override
  public CreditCard save(CreditCard creditCard) {
    queryHandler.execute(SAVE_CREDIT_CARD_QUERY,
            creditCard.getUserId(), creditCard.getPaymentType(), creditCard.getCardHolderName(),
            creditCard.getCardNumber(), creditCard.getExpirationDate(), creditCard.getCvv());
    return creditCard;
  }

  @Override
  public void delete(Long cardId) {
    queryHandler.execute(DELETE_CREDIT_CARD_QUERY, cardId);
  }

  @Override
  public CreditCard update(CreditCard creditCard) {
    queryHandler.execute(UPDATE_CREDIT_CARD_QUERY,
            creditCard.getCardHolderName(), creditCard.getCardNumber(), creditCard.getExpirationDate(), creditCard.getCvv(), creditCard.getId());
    return creditCard;
  }

  @Override
  public List<CreditCard> get(Long userId) {
    return queryHandler.findMany(GET_CREDIT_CARDS_BY_USER_ID_QUERY, resultSetCreditCardMapper, userId);
  }

  @Override
  public boolean isContains(Long paymentMethodId) {
    return queryHandler.findOne(GET_CREDIT_CARD_BY_ID_QUERY, resultSetCreditCardMapper, paymentMethodId) != null;
  }

  public Long getNextId() {
    return queryHandler.findMany(GET_NEXT_ID_QUERY, resultSetCreditCardMapper).size() + 1L;
  }
}
