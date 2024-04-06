package fyi.pauli.schuelerzeitung.database.tables

import fyi.pauli.schuelerzeitung.database.TokenTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column

object Users : TokenTable("users") {

	val username: Column<String> = text("username")
	val email: Column<String?> = text("email").nullable()
	val password: Column<EntityID<Int>> = reference("password", Passwords)
	val author: Column<Boolean> = bool("author").default(false)
}

class User(id: EntityID<String>) : Entity<String>(id) {
	companion object : EntityClass<String, User>(Users)

	var username: String by Users.username
	var email: String by Users.username
	var password: Password by Password referencedOn Users.password
	var author: Boolean by Users.author

}