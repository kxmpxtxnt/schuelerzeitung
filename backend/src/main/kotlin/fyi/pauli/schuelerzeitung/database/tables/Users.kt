package fyi.pauli.schuelerzeitung.database.tables

import fyi.pauli.schuelerzeitung.util.Token
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object Users : IdTable<String>("users") {

	override val id: Column<EntityID<String>> = varchar("id", 8).default(Token.getRandomToken(8)).uniqueIndex().entityId()
	val username: Column<String> = text("username")
	val email: Column<String?> = text("email").nullable()
	val password: Column<EntityID<Int>> = reference("password", Passwords)
	val author: Column<Boolean> = bool("author").default(false)

	override val primaryKey: PrimaryKey = PrimaryKey(Comments.id)
}

class User(id: EntityID<String>) : Entity<String>(id) {
	companion object : EntityClass<String, User>(Users)

	var username: String by Users.username
	var email: String by Users.username
	var password: Password by Password referencedOn Users.password
	var author: Boolean by Users.author

}