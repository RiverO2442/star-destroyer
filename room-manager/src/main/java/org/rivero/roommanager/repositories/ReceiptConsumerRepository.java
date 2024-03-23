package org.rivero.roommanager.repositories;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanager.entities.ReceiptConsumer;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ReceiptConsumerRepository {
    final DBConnectionManager dbConnectionManager;

    public List<ReceiptConsumer> getListByUserId(String id) {
        try (Connection connection = dbConnectionManager.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM receipt_consumer WHERE consumerid = ?");
            preparedStatement.setString(1, id);
            ArrayList<ReceiptConsumer> receiptConsumersList = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String rc_id = rs.getString("id");
                String consumerId = rs.getString("consumerid");
                String receiptId = rs.getString("receiptid");
                receiptConsumersList.add(new ReceiptConsumer(rc_id, receiptId, consumerId));
            }
            return receiptConsumersList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ReceiptConsumer> getListByReceiptId(String id) {
        try (Connection connection = dbConnectionManager.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM receipt_consumer WHERE receiptid = ?");
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            List<ReceiptConsumer> receiptConsumersList = new ArrayList<>();
            while (rs.next()) {
                String rc_id = rs.getString("id");
                String consumerId = rs.getString("consumerid");
                String receiptId = rs.getString("receiptid");
                receiptConsumersList.add(new ReceiptConsumer(rc_id, receiptId, consumerId));
            }
            return receiptConsumersList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(ReceiptConsumer receiptConsumer) {
        try (Connection connection = dbConnectionManager.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO receipt_consumer VALUES (?, ?, ?)");
            preparedStatement.setString(1, UUID.randomUUID().toString());
            preparedStatement.setString(2, receiptConsumer.getConsumerId());
            preparedStatement.setString(3, receiptConsumer.getReceiptId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOne(String id) {
        try (Connection connection = dbConnectionManager.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM receipt_consumer WHERE id = ? ");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
