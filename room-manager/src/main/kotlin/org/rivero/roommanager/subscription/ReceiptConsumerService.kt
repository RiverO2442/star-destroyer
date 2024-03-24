package org.rivero.roommanager.subscription

import lombok.RequiredArgsConstructor
import org.rivero.roommanager.DBConnectionManager
import org.rivero.roommanager.entities.ReceiptConsumer
import org.rivero.roommanager.receipt.ReceiptConsumerRepository
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class ReceiptConsumerService {
    private val receiptConsumerRepository: ReceiptConsumerRepository? = null
    val dbConnectionManager: DBConnectionManager? = null

    fun create(request: ReceiptConsumer?) {
        receiptConsumerRepository!!.insert(request!!)
    }

    fun getByUserId(id: String?): List<ReceiptConsumer> {
        return receiptConsumerRepository!!.getListByUserId(id)
    }

    fun getByReceiptId(id: String?): List<ReceiptConsumer> {
        return receiptConsumerRepository!!.getListByReceiptId(id)
    }

    fun deleteOne(id: String?): String {
        receiptConsumerRepository!!.deleteOne(id)
        return "Record deleted"
    }
}
