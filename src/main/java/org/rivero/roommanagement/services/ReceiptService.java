package org.rivero.roommanagement.services;

import org.rivero.roommanagement.entities.MoneyConsumeEvent;
import org.rivero.roommanagement.entities.ReceiptConsumer;
import org.rivero.roommanagement.repositories.DBConnectionManager;
import org.rivero.roommanagement.repositories.MoneyConsumeEventRepository;
import org.rivero.roommanagement.request.ReceiptCreateRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReceiptService {
    MoneyConsumeEventRepository moneyConsumeEventRepository = new MoneyConsumeEventRepository();
    DBConnectionManager dbConnectionManager = new DBConnectionManager();
    Connection connection = dbConnectionManager.connect();

    ReceiptConsumerService receiptConsumerService = new ReceiptConsumerService();

    public MoneyConsumeEvent getReceiptById(String id) throws SQLException {
        ResultSet rs = moneyConsumeEventRepository.getOne(connection, id);
        ArrayList<String> consumerIds = new ArrayList<>();
        receiptConsumerService.getByReceiptId(id).forEach(data -> {
            consumerIds.add(data.getConsumerId());
        });
        while (rs.next()){
            String receipt_id = rs.getString("id");
            String buyerId = rs.getString("buyerid");
            String name = rs.getString("name");
            String description = rs.getString("description");
            int moneyAmount = Integer.parseInt(rs.getString("moneyamount"));
            return  new MoneyConsumeEvent(receipt_id, name, moneyAmount, buyerId, consumerIds, description);
        }
        return null;
    }
    public List<MoneyConsumeEvent> getAllReceipt() throws SQLException {
        ResultSet rs = moneyConsumeEventRepository.getList(connection);
        List<MoneyConsumeEvent> receipts = new ArrayList<MoneyConsumeEvent>();
        ArrayList<String> consumerList = new ArrayList<String>();
        while (rs.next()){
            String receipt_id = rs.getString("id");
            String buyerId = rs.getString("buyerid");
            String name = rs.getString("name");
            String description = rs.getString("description");
            int moneyAmount = Integer.parseInt(rs.getString("moneyamount"));
            receipts.add(new MoneyConsumeEvent(receipt_id,name,moneyAmount, buyerId, consumerList,description));
        }
        return receipts;
    }

    public String create(ReceiptCreateRequest request) throws SQLException {
        String id = UUID.randomUUID().toString();
        moneyConsumeEventRepository.insert(connection, request, id);
        request.consumerList().forEach(consumer -> {
            try {
                receiptConsumerService.create(new ReceiptConsumer(UUID.randomUUID().toString(), id, consumer));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
            return "New Receipt Registered";
    }
}
