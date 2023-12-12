package org.rivero.roommanagement.repositories;

import org.rivero.roommanagement.request.ReceiptCreateRequest;

import java.sql.*;

public class MoneyConsumeEventRepository {
    public ResultSet getList(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM receipt");
        return rs;
    }

    public ResultSet getOne(Connection connection, String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM receipt WHERE id = ?");
        preparedStatement.setString(1, id);
        ResultSet rs;
        rs = preparedStatement.executeQuery();
        return rs;
    }

    public void insert(Connection connection, ReceiptCreateRequest receipt, String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO receipt VALUES (?, ?, ?, ?, ?)");
        preparedStatement.setString(1, id);
        preparedStatement.setInt(2, receipt.moneyAmount());
        preparedStatement.setString(3, receipt.buyerId());
        preparedStatement.setString(4, receipt.name());
        preparedStatement.setString(5, receipt.description());
        preparedStatement.execute();
    }
}
