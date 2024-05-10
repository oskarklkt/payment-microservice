package com.griddynamics.gridhub.payment.mapper.resultSetToModel;

import com.griddynamics.gridhub.payment.enumeration.PaymentType;
import com.griddynamics.gridhub.payment.model.Paypal;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.function.Function;

public class ResultSetPaypalMapper implements Function<ResultSet, Paypal> {
    @Override
    @SneakyThrows
    public Paypal apply(ResultSet resultSet) {
        return Paypal.builder()
                .id(resultSet.getLong("id"))
                .userId(resultSet.getLong("user_id"))
                .paymentType(PaymentType.PAYPAL)
                .email(resultSet.getString("email"))
                .build();
    }

}
