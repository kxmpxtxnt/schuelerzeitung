package fyi.pauli.schuelerzeitung.server.routing

import fyi.pauli.schuelerzeitung.database.tables.Post
import fyi.pauli.schuelerzeitung.server.Home
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Routing.home() {
	get<Home> {
		Post.all().map { }
	}
}