package fyi.pauli.schuelerzeitung.server.auth.storage

import fyi.pauli.schuelerzeitung.redis.jedis
import io.ktor.server.sessions.*

class SessionStorageRedis : SessionStorage {

	override suspend fun invalidate(id: String) {
		jedis.del(id)
	}

	override suspend fun read(id: String): String {
		return jedis.get(id)
	}

	override suspend fun write(id: String, value: String) {
		jedis[id] = value
	}
}