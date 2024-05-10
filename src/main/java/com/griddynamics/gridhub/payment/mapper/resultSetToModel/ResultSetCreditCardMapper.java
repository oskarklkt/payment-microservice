package com.griddynamics.gridhub.payment.mapper.resultSetToModel;

import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import com.griddynamics.gridhub.payment.model.CreditCard;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.function.Function;

public class ResultSetCreditCardMapper implements Function<ResultSet, CreditCard> {
    @SneakyThrows
    @Override
    public CreditCard apply(ResultSet resultSet) {
        return CreditCard.builder()
                .id(resultSet.getLong("id"))
                .userId(resultSet.getLong("user_id"))
                .paymentType(PaymentType.CREDIT_CARD)
                .cardHolderName(resultSet.getString("card_holder_name"))
                .cardNumber(resultSet.getString("card_number"))
                .expirationDate(resultSet.getString("expiration_date"))
                .cvv(resultSet.getString("cvv"))
                .build();
    }
}
