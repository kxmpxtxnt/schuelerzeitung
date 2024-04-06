package fyi.pauli.schuelerzeitung.server.routing.auth

import fyi.pauli.schuelerzeitung.database.tables.User
import fyi.pauli.schuelerzeitung.database.tables.Users
import fyi.pauli.schuelerzeitung.server.Login
import fyi.pauli.schuelerzeitung.server.auth.UserSession
import fyi.pauli.schuelerzeitung.util.Token
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

@Serializable
data class LoginCredentials(
	val email: String,
	val password: String,
)

fun Routing.login() {
	post<Login> {
		val credentials = call.receive<LoginCredentials>()

		val user: User? = newSuspendedTransaction { User.find { Users.email eq credentials.email }.firstOrNull() }

		if (user == null) {
			call.respondRedirect("/register")
			return@post
		}

		if (!user.password.matches(credentials.password)) {
			call.respond(HttpStatusCode.Unauthorized, "error.password.incorrect")
			return@post
		}

		call.sessions.set(UserSession(user.id.value, Token.getRandomToken()))
		call.respondRedirect("/#")
	}
}