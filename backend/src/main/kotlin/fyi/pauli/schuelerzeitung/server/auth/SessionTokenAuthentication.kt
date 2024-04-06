package fyi.pauli.schuelerzeitung.server.auth

import fyi.pauli.schuelerzeitung.redis.jedis
import fyi.pauli.schuelerzeitung.server.auth.storage.SessionStorageRedis
import fyi.pauli.schuelerzeitung.server.routing.auth.login
import fyi.pauli.schuelerzeitung.server.routing.auth.register
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.serialization.Serializable
import kotlin.time.Duration.Companion.days

@Serializable
data class UserSession(
	val id: String,
	val token: String,
)

fun Application.sessionTokenAuth() {
	install(Sessions) {
		cookie<UserSession>(
			"schuelerzeitung_session",
			if (jedis.pool.isClosed) SessionStorageMemory() else SessionStorageRedis()
		) {
			cookie.path = "/"
			cookie.maxAge = 30.days
		}
	}

	routing {
		login()
		register()
	}
}