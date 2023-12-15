package org.rivero.roommanagement.repositories;

import org.rivero.roommanagement.entities.MoneyConsumeEvent;
import org.rivero.roommanagement.entities.ReceiptConsumer;
import org.rivero.roommanagement.request.ReceiptCreateRequest;
import org.rivero.roommanagement.request.ReceiptUpdateRequest;
import org.rivero.roommanagement.services.ReceiptConsumerService;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Repository
public class MoneyConsumeEventRepository {
    public List<MoneyConsumeEvent> getList(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM receipt");
            List<MoneyConsumeEvent> receipts = new ArrayList<MoneyConsumeEvent>();
            ArrayList<String> consumerList = new ArrayList<String>();
            while (rs.next()) {
                String receipt_id = rs.getString("id");
                String buyerId = rs.getString("buyerid");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int moneyAmount = Integer.parseInt(rs.getString("moneyamount"));
                receipts.add(new MoneyConsumeEvent(receipt_id, name, moneyAmount, buyerId, consumerList, description));
            }
            return receipts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOne(Connection connection, ReceiptUpdateRequest receipt){
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE receipt SET moneyamount = ? ,buyerid = ?, name = ?, description = ? WHERE id = ?");
            statement.setInt(1, receipt.moneyAmount());
            statement.setString(2, receipt.buyerId());
            statement.setString(3, receipt.name());
            statement.setString(4, receipt.description());
            statement.setString(5, receipt.id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MoneyConsumeEvent getOne(Connection connection, String id, ReceiptConsumerService receiptConsumerService) {
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("SELECT * FROM receipt WHERE id = ?");
            preparedStatement.setString(1, id);
            ResultSet rs;
            rs = preparedStatement.executeQuery();
            ArrayList<String> consumerIds = new ArrayList<>();
            receiptConsumerService.getByReceiptId(id).forEach(data -> {
                consumerIds.add(data.getConsumerId());
            });
            while (rs.next()) {
                String receipt_id = rs.getString("id");
                String buyerId = rs.getString("buyerid");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int moneyAmount = Integer.parseInt(rs.getString("moneyamount"));
                return new MoneyConsumeEvent(receipt_id, name, moneyAmount, buyerId, consumerIds, description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void insert(Connection connection, ReceiptCreateRequest receipt, String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO receipt VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, id);
            preparedStatement.setInt(2, receipt.moneyAmount());
            preparedStatement.setString(3, receipt.buyerId());
            preparedStatement.setString(4, receipt.name());
            preparedStatement.setString(5, receipt.description());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteOne(Connection connection, String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM receipt WHERE id = ? ");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
