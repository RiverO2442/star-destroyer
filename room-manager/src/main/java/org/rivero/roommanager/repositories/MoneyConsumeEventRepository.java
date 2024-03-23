package org.rivero.roommanager.repositories;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rivero.roommanager.entities.Receipt;
import org.rivero.roommanager.entities.ReceiptConsumer;
import org.rivero.roommanager.receipt.CreateReceiptRequest;
import org.rivero.roommanager.request.ReceiptUpdateRequest;
import org.springframework.stereotype.Repository;
import org.rivero.roommanager.receipt.ReceiptDto;

import java.math.BigDecimal;
import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MoneyConsumeEventRepository {
    final DBConnectionManager connectionManager;

    public String getZonedId(String userId) {
        // TODO: complete me
        return "+07:00";
    }

    public List<Receipt> getList(@Nullable ZonedDateTime from, @Nullable ZonedDateTime to, String userId) {
        try (Connection connection = connectionManager.connect()) {
            ZonedDateTime now = ZonedDateTime.now(ZoneId.of(getZonedId(userId)));
            ZonedDateTime firstOfMonthAtAsia = now.withDayOfMonth(1);
            ZonedDateTime lastOfMonthAtAsia = now.withDayOfMonth(now.getDayOfMonth());
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("""
                    SELECT *
                    FROM receipt left join receipt_consumer rc on receipt.id = rc.receiptid
                    WHERE
                    (?::date is not null and receipt.createddate >= ?)
                    or
                    (?::date is null and receipt.createddate >= ?)
                    """);

            if (Objects.nonNull(from)) {
                preparedStatement.setTimestamp(1, Timestamp.from(Instant.from(from)));
                preparedStatement.setTimestamp(2, Timestamp.from(Instant.from(from)));
                preparedStatement.setTimestamp(3, Timestamp.from(Instant.from(from)));
            } else {
                preparedStatement.setNull(1, Types.TIMESTAMP);
                preparedStatement.setNull(2, Types.TIMESTAMP);
                preparedStatement.setNull(3, Types.TIMESTAMP);
            }
            preparedStatement.setTimestamp(4, Timestamp.from(Instant.from(firstOfMonthAtAsia)));

            ResultSet rs = preparedStatement.executeQuery();
            List<Receipt> receipts = new ArrayList<>();
//            while (rs.next()) {
//                AtomicBoolean isExisted = new AtomicBoolean(false);
//                String receipt_id = rs.getString("id");
//                log.debug(receipt_id);
//                String buyerId = rs.getString("buyerid");
//                String name = rs.getString("name");
//                String description = rs.getString("description");
//                int amount = Integer.parseInt(rs.getString("moneyamount"));
//                String consumerid = rs.getString("consumerid");
//                Timestamp time = rs.getTimestamp("createddate");
//                receipts.forEach(rc -> {
//                    if (rc.getId().equals(receipt_id)) {
//                        rc.getConsumers().add(consumerid);
//                        isExisted.set(true);
//                    }
//                });
//                if (!isExisted.get()) {
//                    receipts.add(new Receipt(receipt_id, name, amount, buyerId, consumerid, description, time.toLocalDateTime().atZone(ZoneId.systemDefault())));
//                }
//            }
            return receipts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public List<MoneyConsumeEvent> getList(Connection connection, String fromDate, String toDate) {
//        try {
//            PreparedStatement preparedStatement = null;
//            preparedStatement = connection.prepareStatement("SELECT * FROM receipt left join receipt_consumer rc on receipt.id = rc.receiptid WHERE (createddate::date >= ?::date and createddate::date <= ?::date)");
//            preparedStatement.setString(1, fromDate);
//            preparedStatement.setString(2, toDate);
//            ResultSet rs = preparedStatement.executeQuery();
//            List<MoneyConsumeEvent> receipts = new ArrayList<MoneyConsumeEvent>();
//            while (rs.next()) {
//                AtomicBoolean isExisted = new AtomicBoolean(false);
//                String receipt_id = rs.getString("id");
//                String buyerId = rs.getString("buyerid");
//                String name = rs.getString("name");
//                String description = rs.getString("description");
//                int amount = Integer.parseInt(rs.getString("moneyamount"));
//                String consumerid = rs.getString("consumerid");
//                Timestamp time = rs.getTimestamp("createddate");
//                ;
//                receipts.forEach(rc -> {
//                    if (rc.getId().equals(receipt_id)) {
//                        rc.getConsumerList().add(consumerid);
//                        isExisted.set(true);
//                    }
//                });
//                if (!isExisted.get()) {
//                    receipts.add(new MoneyConsumeEvent(receipt_id, name, amount, buyerId, consumerid, description, time.toLocalDateTime().atZone(ZoneId.systemDefault())));
//                }
//            }
//            return receipts;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public List<Receipt> getListByUserId(String userId) {
        try (Connection connection = connectionManager.connect()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM receipt left join receipt_consumer rc on receipt.id = rc.receiptid WHERE buyerid = ?");
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            List<Receipt> receipts = new ArrayList<Receipt>();
//            while (rs.next()) {
//                AtomicBoolean isExisted = new AtomicBoolean(false);
//                String receipt_id = rs.getString("id");
//                String buyerId = rs.getString("buyerid");
//                String name = rs.getString("name");
//                String description = rs.getString("description");
//                BigDecimal moneyAmount = new BigDecimal(rs.getString("moneyamount"));
//                String consumerid = rs.getString("consumerid");
//                Timestamp time = rs.getTimestamp("createddate");
//                receipts.forEach(rc -> {
//                    if (rc.getId().equals(receipt_id)) {
//                        rc.getConsumers().add(consumerid);
//                    }
//                    isExisted.set(true);
//                });
//                if (!isExisted.get()) {
//                    receipts.add(new Receipt(receipt_id, name, moneyAmount, buyerId, consumerid, description, time.toLocalDateTime().atZone(ZoneId.systemDefault())));
//                }
//            }
            return receipts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOne(ReceiptUpdateRequest receipt) {
        try (Connection connection = connectionManager.connect()) {
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

    public ReceiptDto getOne(String id) {
        try (Connection connection = connectionManager.connect()) {
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("SELECT * FROM receipt WHERE id = ?");
            preparedStatement.setString(1, id);
            ResultSet rs;
            rs = preparedStatement.executeQuery();
            ArrayList<String> consumerIds = new ArrayList<>();
//            while (rs.next()) {
//                String receipt_id = rs.getString("id");
//                String buyerId = rs.getString("buyerid");
//                String name = rs.getString("name");
//                String description = rs.getString("description");
//                ZonedDateTime time = rs.getTimestamp("createddate").toLocalDateTime().atZone(ZoneId.systemDefault());
//                int amount = Integer.parseInt(rs.getString("moneyamount"));
//                return new ReceiptDto(name, amount, buyerId, consumerIds, receipt_id, description, time);
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void insert(CreateReceiptRequest receipt, String id) {
        try (Connection connection = connectionManager.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO receipt VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, id);
            preparedStatement.setBigDecimal(2, receipt.amount());
            preparedStatement.setString(3, "");
            preparedStatement.setString(4, receipt.name());
            preparedStatement.setString(5, receipt.description());
            preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteOne(String id) {
        try (Connection connection = connectionManager.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM receipt WHERE id = ? ");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<ReceiptConsumer> getListReceiptConsumerByUserId(String id) {
        try (Connection connection = connectionManager.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM receipt_consumer WHERE consumerid = ?");
            preparedStatement.setString(1, id);
//            preparedStatement.setString(2, "2023-12-26");
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

    public ArrayList<ReceiptConsumer> getListReceiptConsumerByReceiptId(String id) {
        try (Connection connection = connectionManager.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM receipt_consumer WHERE receiptid = ?");
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<ReceiptConsumer> receiptConsumersList = new ArrayList<>();
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

    public void insertReceiptConsumer(ReceiptConsumer receiptConsumer) {
        try (Connection connection = connectionManager.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO receipt_consumer VALUES (?, ?, ?)");
            preparedStatement.setString(1, UUID.randomUUID().toString());
            preparedStatement.setString(2, receiptConsumer.getConsumerId());
            preparedStatement.setString(3, receiptConsumer.getReceiptId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOneReceiptConsumer(String id) {
        try (Connection connection = connectionManager.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM receipt_consumer WHERE id = ? ");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}