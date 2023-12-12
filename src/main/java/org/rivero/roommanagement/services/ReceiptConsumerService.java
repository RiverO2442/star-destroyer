package org.rivero.roommanagement.services;

import org.rivero.roommanagement.entities.MoneyConsumeEvent;
import org.rivero.roommanagement.entities.ReceiptConsumer;
import org.rivero.roommanagement.repositories.DBConnectionManager;
import org.rivero.roommanagement.repositories.ReceiptConsumerRepository;
import org.rivero.roommanagement.request.ReceiptCreateRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceiptConsumerService {
    ReceiptConsumerRepository receiptConsumerRepository = new ReceiptConsumerRepository();
    DBConnectionManager dbConnectionManager = new DBConnectionManager();
    Connection connection = dbConnectionManager.connect();

    public String create(ReceiptConsumer request) throws SQLException {
        receiptConsumerRepository.insert(connection, request);
            return "New ReceiptConsumer Registered";
    }
    public ArrayList<ReceiptConsumer> getByUserId(String id) throws SQLException {
        ResultSet rs = receiptConsumerRepository.getListByUserId(connection, id);
        ArrayList<ReceiptConsumer> receiptConsumerslist = new ArrayList<ReceiptConsumer>();
        while (rs.next()){
            String rc_id = rs.getString("id");
            String consumerid = rs.getString("consumerid");
            String receiptid = rs.getString("receiptid");
            receiptConsumerslist.add(new ReceiptConsumer(rc_id, receiptid, consumerid));
        }
        return receiptConsumerslist;
    }
    public ArrayList<ReceiptConsumer> getByReceiptId(String id) throws SQLException {
        ResultSet rs = receiptConsumerRepository.getListByReceiptId(connection, id);
        ArrayList<ReceiptConsumer> receiptConsumerslist = new ArrayList<ReceiptConsumer>();
        while (rs.next()){
            String rc_id = rs.getString("id");
            String consumerid = rs.getString("consumerid");
            String receiptid = rs.getString("receiptid");
            receiptConsumerslist.add(new ReceiptConsumer(rc_id, receiptid, consumerid));
        }
        return receiptConsumerslist;
    }
}
