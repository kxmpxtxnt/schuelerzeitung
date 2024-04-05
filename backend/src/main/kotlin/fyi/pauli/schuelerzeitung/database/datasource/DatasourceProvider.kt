package fyi.pauli.schuelerzeitung.database.datasource

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*

fun createDatasource(config: ApplicationConfig) = HikariDataSource(
	HikariConfig().apply {
		driverClassName = config.property("database.driverClassName").getString()
		jdbcUrl = config.property("database.jdbcUrl").getString()
		password = config.property("database.password").getString()
		username = config.property("database.username").getString()
		maximumPoolSize = config.property("database.maxPoolSize").getString().toInt()
	}
)