package fyi.pauli.schuelerzeitung.server.routing.auth

import fyi.pauli.schuelerzeitung.database.tables.Password
import fyi.pauli.schuelerzeitung.database.tables.User
import fyi.pauli.schuelerzeitung.database.tables.Users
import fyi.pauli.schuelerzeitung.server.auth.UserSession
import fyi.pauli.schuelerzeitung.server.routing.Register
import fyi.pauli.schuelerzeitung.util.Hasher
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
private data class RegisterCredentials(
	val username: String,
	val email: String,
	val password: String,
)

fun Routing.register() {
	post<Register> {
		val credentials = call.receive<RegisterCredentials>()

		if (!newSuspendedTransaction { User.find { Users.email eq credentials.email }.empty() }) {
			call.respond(HttpStatusCode.Forbidden, "error.email.registered")
			return@post
		}

		val user = newSuspendedTransaction {
			val newPassword = Password.new {
				salt = Hasher.salt()
				hash = Hasher.hash(credentials.password, salt)
			}

			User.new {
				email = credentials.email
				username = credentials.username
				password = newPassword
			}
		}

		val token = Token.getRandomToken()

		call.sessions.set(UserSession(user.id.value, token))
		call.respondRedirect("/#")
	}
}