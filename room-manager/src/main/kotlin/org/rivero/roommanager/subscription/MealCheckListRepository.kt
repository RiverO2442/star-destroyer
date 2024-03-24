package org.rivero.roommanager.subscription

import lombok.RequiredArgsConstructor
import org.rivero.roommanager.DBConnectionManager
import org.springframework.stereotype.Repository
import java.sql.SQLException
import java.sql.Timestamp
import java.time.ZoneId
import java.util.*

@Repository
@RequiredArgsConstructor
class MealCheckListRepository {
    val connectionManager: DBConnectionManager? = null

    fun insert(list: MealCheckListDto) {
        try {
            connectionManager!!.connect().use { connection ->
                val preparedStatement =
                    connection!!.prepareStatement("INSERT INTO mealchecklist VALUES (?, ?, ?, ?, ?)")
                preparedStatement.setString(1, UUID.randomUUID().toString())
                preparedStatement.setString(2, list.month)
                preparedStatement.setString(3, list.checkList)
                preparedStatement.setString(4, list.consumerId)
                preparedStatement.setTimestamp(5, Timestamp(System.currentTimeMillis()))
                preparedStatement.execute()
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    val list: Collection<MealCheckListDto>
        get() {
            try {
                connectionManager!!.connect().use { connection ->
                    val statement = connection!!.createStatement()
                    val rs = statement.executeQuery("SELECT * FROM mealchecklist")
                    val resultList = ArrayList<MealCheckListDto>()
                    while (rs.next()) {
                        val id = rs.getString("id")
                        val month = rs.getString("month")
                        val consumerId = rs.getString("consumerid")
                        val time = rs.getTimestamp("createddate").toLocalDateTime().atZone(ZoneId.systemDefault())
                        val checkList = rs.getString("checklist")
                        resultList.add(MealCheckListDto(id, month, checkList, consumerId, time))
                    }
                    return resultList
                }
            } catch (e: SQLException) {
                throw RuntimeException(e)
            }
        }

    fun getOne(id: String): MealCheckListDto? {
        try {
            connectionManager!!.connect().use { connection ->
                val preparedStatement = connection!!.prepareStatement("SELECT * FROM mealchecklist WHERE id = ?")
                preparedStatement.setString(1, id)
                val rs = preparedStatement.executeQuery()
                while (rs.next()) {
                    val mealid = rs.getString("id")
                    val month = rs.getString("month")
                    val consumerid = rs.getString("consumerid")
                    val checklist = rs.getString("checklist")
                    val time = rs.getTimestamp("createddate").toLocalDateTime().atZone(ZoneId.systemDefault())
                    return MealCheckListDto(id, month, checklist, consumerid, time)
                }
                return null
            }
        } catch (e: SQLException) {
            throw RuntimeException()
        }
    }

    fun updateOne(mealCheckList: MealCheckListDto) {
        try {
            connectionManager!!.connect().use { connection ->
                val preparedStatement =
                    connection!!.prepareStatement("UPDATE mealchecklist SET month = ?, consumerid = ?, checkList = ? WHERE id = ?")
                preparedStatement.setString(1, mealCheckList.month)
                preparedStatement.setString(2, mealCheckList.consumerId)
                preparedStatement.setString(3, mealCheckList.checkList)
                preparedStatement.setString(4, mealCheckList.id)
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }
}