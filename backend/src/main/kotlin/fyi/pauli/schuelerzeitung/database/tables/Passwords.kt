package fyi.pauli.schuelerzeitung.database.tables

import fyi.pauli.schuelerzeitung.util.Hasher
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column


object Passwords : IntIdTable("passwords") {

	val salt: Column<ByteArray> = binary("salt")
	val hash: Column<ByteArray> = binary("hash")
}

class Password(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<Password>(Passwords)

	var salt: ByteArray by Passwords.salt
	var hash: ByteArray by Passwords.hash

	fun matches(password: String) = Hasher.matchesHash(password, salt, hash)
}