package org.rivero.roommanagement.repositories;

import org.rivero.roommanagement.entities.MoneyConsumeEvent;
import org.rivero.roommanagement.entities.ReceiptConsumer;
import org.rivero.roommanagement.request.ReceiptCreateRequest;

import java.sql.*;
import java.util.UUID;

public class ReceiptConsumerRepository {
    public ResultSet getListByUserId(Connection connection, String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM receipt_consumer WHERE consumerid = ?");
        preparedStatement.setString(1, id);
        return preparedStatement.executeQuery();
    }

    public ResultSet getListByReceiptId(Connection connection, String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM receipt_consumer WHERE receiptid = ?");
        preparedStatement.setString(1, id);
        return preparedStatement.executeQuery();
    }

    public void insert(Connection connection, ReceiptConsumer receiptConsumer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO receipt_consumer VALUES (?, ?, ?)");
        preparedStatement.setString(1, UUID.randomUUID().toString());
        preparedStatement.setString(2, receiptConsumer.getConsumerId());
        preparedStatement.setString(3, receiptConsumer.getReceiptId());
        preparedStatement.execute();
    }

//    public ResultSet getOne(Connection connection, String id) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM receipt WHERE id = ?");
//        preparedStatement.setString(1, id);
//        ResultSet rs;
//        rs = preparedStatement.executeQuery();
//        return rs;
//    }
//
//    public void insert(Connection connection, MoneyConsumeEvent receipt) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO receipt VALUES (?, ?, ?, ?, ?)");
//        preparedStatement.setString(1, UUID.randomUUID().toString());
//        preparedStatement.setString(2, String.valueOf(receipt.getMoneyAmount()));
//        preparedStatement.setString(2, receipt.getBuyerId());
//        preparedStatement.setString(2, receipt.getName());
//        preparedStatement.setString(2, receipt.getDescription());
//        preparedStatement.execute();
//    }
}
