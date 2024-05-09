package com.griddynamics.gridhub.payment.database;

import com.griddynamics.gridhub.payment.exception.TooManyResultsException;
import com.griddynamics.gridhub.payment.model.PaymentMethod;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class QueryHandler<T extends PaymentMethod> {
    @SneakyThrows
    public void execute(String query, Object... args) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.execute();
        }
    }

    @SneakyThrows
    public void execute(String query, Consumer<PreparedStatement> statementConsumer) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            statementConsumer.accept(preparedStatement);
            preparedStatement.execute();
        }
    }

    @SneakyThrows
    public T findOne(String query, Function<ResultSet, T> mapper, Object... args) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            T res = resultSet.next() ? mapper.apply(resultSet) : null;
            if (resultSet.next()) {
                throw new TooManyResultsException("More than one result found");
            }
            return res;
        }
    }

    @SneakyThrows
    public List<T> findMany(String query, Function<ResultSet, T> mapper, Object... args) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> res = new ArrayList<>(List.of());
            while (resultSet.next()) {
                res.add(mapper.apply(resultSet));
            }
            return res;
        }
    }
}
