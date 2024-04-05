package fyi.pauli.schuelerzeitung.database

import fyi.pauli.schuelerzeitung.database.datasource.createDatasource
import fyi.pauli.schuelerzeitung.database.tables.Comments
import fyi.pauli.schuelerzeitung.database.tables.Passwords
import fyi.pauli.schuelerzeitung.database.tables.Posts
import fyi.pauli.schuelerzeitung.database.tables.Users
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.database() {
	Database.connect(createDatasource(environment.config))

	transaction {
		SchemaUtils.createMissingTablesAndColumns(
			Users,
			Passwords,
			Posts,
			Comments
		)
	}
}