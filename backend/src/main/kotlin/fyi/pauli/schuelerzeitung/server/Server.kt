package fyi.pauli.schuelerzeitung.server

import fyi.pauli.schuelerzeitung.server.auth.sessionTokenAuth
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.resources.*
import kotlinx.serialization.json.Json

fun Application.server() {
	install(Resources)

	install(ContentNegotiation) {
		json(Json {
			prettyPrint = this@server.developmentMode
			encodeDefaults = true
		})
	}

	sessionTokenAuth()
}