package org.rivero.roommanagement.repositories;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ReportRepository {
    void create(Connection connection, String userId){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM receipt_consumer WHERE id = ?");
            preparedStatement.setString(1, userId);
            ResultSet receiptList = preparedStatement.executeQuery();
            while(receiptList.next()){

            }
            PreparedStatement preparedStatementReceipt = connection.prepareStatement("SELECT * FROM receipt WHERE buyerid = ?");
            preparedStatementReceipt.setString(1, userId);
            ResultSet paidReceiptList = preparedStatementReceipt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
