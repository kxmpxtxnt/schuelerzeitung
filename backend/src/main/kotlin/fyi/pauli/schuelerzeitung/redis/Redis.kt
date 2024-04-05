package fyi.pauli.schuelerzeitung.redis

import fyi.pauli.schuelerzeitung.logger
import io.ktor.server.application.*
import redis.clients.jedis.JedisPooled

lateinit var jedis: JedisPooled

fun Application.redis() {
	logger.info("Trying to connect to redis...")

	jedis = JedisPooled(
		environment.config.property("redis.host").getString(),
		environment.config.property("redis.port").getString().toInt(),
	)

	if (!jedis.pool.isClosed)
		logger.info("Connection to redis established...")
}