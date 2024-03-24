package org.rivero.roommanager.receipt

import jakarta.annotation.Nullable
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.rivero.roommanager.DBConnectionManager
import org.rivero.roommanager.entities.Receipt
import org.rivero.roommanager.entities.ReceiptConsumer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Timestamp
import java.sql.Types
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

@Slf4j
@Repository
@RequiredArgsConstructor
class MoneyConsumeEventRepository @Autowired constructor(
    val connectionManager: DBConnectionManager
) {

    fun getZonedId(userId: String?): String {
        // TODO: complete me
        return "+07:00"
    }

    fun getList(@Nullable from: ZonedDateTime?, @Nullable to: ZonedDateTime?, userId: String?): List<Receipt> {
        try {
            connectionManager.connect().use { connection ->
                val now = ZonedDateTime.now(ZoneId.of(getZonedId(userId)))
                val firstOfMonthAtAsia = now.withDayOfMonth(1)
                val lastOfMonthAtAsia = now.withDayOfMonth(now.dayOfMonth)
                var preparedStatement: PreparedStatement? = null
                preparedStatement = connection!!.prepareStatement(
                    """
                    SELECT *
                    FROM receipt left join receipt_consumer rc on receipt.id = rc.receiptid
                    WHERE
                    (?::date is not null and receipt.createddate >= ?)
                    or
                    (?::date is null and receipt.createddate >= ?)
                    
                    """.trimIndent()
                )

                if (Objects.nonNull(from)) {
                    preparedStatement.setTimestamp(1, Timestamp.from(Instant.from(from)))
                    preparedStatement.setTimestamp(2, Timestamp.from(Instant.from(from)))
                    preparedStatement.setTimestamp(3, Timestamp.from(Instant.from(from)))
                } else {
                    preparedStatement.setNull(1, Types.TIMESTAMP)
                    preparedStatement.setNull(2, Types.TIMESTAMP)
                    preparedStatement.setNull(3, Types.TIMESTAMP)
                }
                preparedStatement.setTimestamp(4, Timestamp.from(Instant.from(firstOfMonthAtAsia)))

                val rs = preparedStatement.executeQuery()
                val receipts: List<Receipt> = ArrayList()
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
                return receipts
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    //    public List<MoneyConsumeEvent> getList(Connection connection, String fromDate, String toDate) {
    //        try {
    //            PreparedStatement preparedStatement = null;
    //            preparedStatement = connection!!.prepareStatement("SELECT * FROM receipt left join receipt_consumer rc on receipt.id = rc.receiptid WHERE (createddate::date >= ?::date and createddate::date <= ?::date)");
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
    fun getListByUserId(userId: String?): List<Receipt> {
        try {
            connectionManager.connect().use { connection ->
                val statement =
                    connection!!.prepareStatement("SELECT * FROM receipt left join receipt_consumer rc on receipt.id = rc.receiptid WHERE buyerid = ?")
                statement.setString(1, userId)
                val rs = statement.executeQuery()
                val receipts: List<Receipt> = ArrayList()
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
                return receipts
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    fun updateOne(receipt: ReceiptUpdateRequest) {
        try {
            connectionManager.connect().use { connection ->
                val statement =
                    connection!!.prepareStatement("UPDATE receipt SET moneyamount = ? ,buyerid = ?, name = ?, description = ? WHERE id = ?")
                statement.setInt(1, receipt.moneyAmount)
                statement.setString(2, receipt.buyerId)
                statement.setString(3, receipt.name)
                statement.setString(4, receipt.description)
                statement.setString(5, receipt.id)
                statement.executeUpdate()
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    fun getOne(id: String?): ReceiptDto? {
        try {
            connectionManager.connect().use { connection ->
                var preparedStatement: PreparedStatement? = null
                preparedStatement = connection!!.prepareStatement("SELECT * FROM receipt WHERE id = ?")
                preparedStatement.setString(1, id)
                val rs = preparedStatement.executeQuery()
                val consumerIds = ArrayList<String>()
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
        return null
    }

    fun insert(receipt: CreateReceiptRequest, id: String?) {
        try {
            connectionManager.connect().use { connection ->
                val preparedStatement = connection!!.prepareStatement("INSERT INTO receipt VALUES (?, ?, ?, ?, ?, ?)")
                preparedStatement.setString(1, id)
                preparedStatement.setBigDecimal(2, receipt.amount)
                preparedStatement.setString(3, "")
                preparedStatement.setString(4, receipt.name)
                preparedStatement.setString(5, receipt.description)
                preparedStatement.setTimestamp(6, Timestamp(System.currentTimeMillis()))
                preparedStatement.execute()
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    fun deleteOne(id: String?) {
        try {
            connectionManager.connect().use { connection ->
                val preparedStatement = connection!!.prepareStatement("DELETE FROM receipt WHERE id = ? ")
                preparedStatement.setString(1, id)
                preparedStatement.execute()
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    fun getListReceiptConsumerByUserId(id: String?): ArrayList<ReceiptConsumer> {
        try {
            connectionManager.connect().use { connection ->
                val preparedStatement =
                    connection!!.prepareStatement("SELECT * FROM receipt_consumer WHERE consumerid = ?")
                preparedStatement.setString(1, id)
                //            preparedStatement.setString(2, "2023-12-26");
                val receiptConsumersList = ArrayList<ReceiptConsumer>()
                val rs = preparedStatement.executeQuery()
                while (rs.next()) {
                    val rc_id = rs.getString("id")
                    val consumerId = rs.getString("consumerid")
                    val receiptId = rs.getString("receiptid")
                    receiptConsumersList.add(ReceiptConsumer(rc_id, receiptId, consumerId))
                }
                return receiptConsumersList
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    fun getListReceiptConsumerByReceiptId(id: String?): ArrayList<ReceiptConsumer> {
        try {
            connectionManager.connect().use { connection ->
                val preparedStatement =
                    connection!!.prepareStatement("SELECT * FROM receipt_consumer WHERE receiptid = ?")
                preparedStatement.setString(1, id)
                val rs = preparedStatement.executeQuery()
                val receiptConsumersList = ArrayList<ReceiptConsumer>()
                while (rs.next()) {
                    val rc_id = rs.getString("id")
                    val consumerId = rs.getString("consumerid")
                    val receiptId = rs.getString("receiptid")
                    receiptConsumersList.add(ReceiptConsumer(rc_id, receiptId, consumerId))
                }
                return receiptConsumersList
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    fun insertReceiptConsumer(receiptConsumer: ReceiptConsumer) {
        try {
            connectionManager.connect().use { connection ->
                val preparedStatement = connection!!.prepareStatement("INSERT INTO receipt_consumer VALUES (?, ?, ?)")
                preparedStatement.setString(1, UUID.randomUUID().toString())
                preparedStatement.setString(2, receiptConsumer.consumerId)
                preparedStatement.setString(3, receiptConsumer.receiptId)
                preparedStatement.execute()
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    fun deleteOneReceiptConsumer(id: String?) {
        try {
            connectionManager.connect().use { connection ->
                val preparedStatement = connection!!.prepareStatement("DELETE FROM receipt_consumer WHERE id = ? ")
                preparedStatement.setString(1, id)
                preparedStatement.execute()
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }
}