package fyi.pauli.schuelerzeitung.database.tables

import kotlinx.datetime.Instant
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object Posts : IntIdTable("posts") {

	val date: Column<Instant> = timestamp("date")
	val route: Column<String> = text("route")
	val title: Column<String> = text("title")
	val author: Column<EntityID<String>> = reference("author", Users.id)
	val content: Column<String> = largeText("content")
	val views: Column<Int> = integer("views")

}

class Post(id: EntityID<Int>) : IntEntity(id) {
	companion object : IntEntityClass<Post>(Posts)

	var date: Instant by Posts.date
	var route: String by Posts.route
	var title: String by Posts.title
	var author: User by User referencedOn Posts.author
	var content: String by Posts.content
	var views: Int by Posts.views

}