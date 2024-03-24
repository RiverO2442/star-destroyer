package org.rivero.roommanager

import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.stereotype.Component
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

@Component
@RequiredArgsConstructor
class DBConnectionManager @Autowired constructor(
    val properties: DataSourceProperties
) {

    fun connect(): Connection? {
        var connection: Connection? = null
        try {
            connection = DriverManager.getConnection(
                properties.url,
                properties.username, properties.password
            )
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
        return connection
    }
}
