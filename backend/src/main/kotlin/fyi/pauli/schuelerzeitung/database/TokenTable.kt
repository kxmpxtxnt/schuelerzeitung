package fyi.pauli.schuelerzeitung.database

import fyi.pauli.schuelerzeitung.util.Token
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table.Dual.clientDefault

open class TokenTable(name: String = "") : IdTable<String>(name) {

	final override val id: Column<EntityID<String>> = varchar("id", 8).autoGenerate().entityId()
	final override val primaryKey: PrimaryKey = PrimaryKey(id)
}

fun Column<String>.autoGenerate(): Column<String> = clientDefault { Token.getRandomToken(8) }
