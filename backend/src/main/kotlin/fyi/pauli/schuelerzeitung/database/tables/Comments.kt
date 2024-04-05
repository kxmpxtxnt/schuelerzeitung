package fyi.pauli.schuelerzeitung.database.tables

import fyi.pauli.schuelerzeitung.util.Token
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object Comments : IdTable<String>("comments") {

	val post: Column<EntityID<Int>> = reference("post", Posts.id)
	val commenter: Column<EntityID<String>> = reference("commenter", Users.id)
	val content: Column<String> = text("content")

	override val id: Column<EntityID<String>> = varchar("id", 4).default(Token.getRandomToken(4)).uniqueIndex().entityId()

	override val primaryKey: PrimaryKey = PrimaryKey(id)
}

class Comment(id: EntityID<String>) : Entity<String>(id) {
	companion object : EntityClass<String, Comment>(Comments)

	var post: Post by Post referencedOn Comments.post
	var commenter: User by User referencedOn Comments.commenter
	var content: String by Comments.content
}