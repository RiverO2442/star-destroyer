package org.rivero.roommanager.receipt

import lombok.RequiredArgsConstructor
import org.rivero.roommanager.DBConnectionManager
import org.rivero.roommanager.entities.ReceiptConsumer
import org.springframework.stereotype.Repository
import java.sql.SQLException
import java.util.*

@Repository
@RequiredArgsConstructor
class ReceiptConsumerRepository {
    val dbConnectionManager: DBConnectionManager? = null

    fun getListByUserId(id: String?): List<ReceiptConsumer> {
        try {
            dbConnectionManager!!.connect().use { connection ->
                val preparedStatement =
                    connection!!.prepareStatement("SELECT * FROM receipt_consumer WHERE consumerid = ?")
                preparedStatement.setString(1, id)
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

    fun getListByReceiptId(id: String?): List<ReceiptConsumer> {
        try {
            dbConnectionManager!!.connect().use { connection ->
                val preparedStatement =
                    connection!!.prepareStatement("SELECT * FROM receipt_consumer WHERE receiptid = ?")
                preparedStatement.setString(1, id)
                val rs = preparedStatement.executeQuery()
                val receiptConsumersList: MutableList<ReceiptConsumer> = ArrayList()
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

    fun insert(receiptConsumer: ReceiptConsumer) {
        try {
            dbConnectionManager!!.connect().use { connection ->
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

    fun deleteOne(id: String?) {
        try {
            dbConnectionManager!!.connect().use { connection ->
                val preparedStatement = connection!!.prepareStatement("DELETE FROM receipt_consumer WHERE id = ? ")
                preparedStatement.setString(1, id)
                preparedStatement.execute()
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }
}
