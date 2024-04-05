package fyi.pauli.schuelerzeitung.util

import java.security.SecureRandom
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

private val random: Random = SecureRandom()

object Hasher {

	fun salt(): ByteArray {
		val salt = ByteArray(16)
		random.nextBytes(salt)
		return salt
	}

	fun matchesHash(password: String, salt: ByteArray, hash: ByteArray): Boolean {
		val passwordHash = hash(password, salt)
		if (passwordHash.size != hash.size) return false
		return passwordHash.indices.all { passwordHash[it] == hash[it] }
	}

	fun hash(password: String, salt: ByteArray): ByteArray {
		val spec = PBEKeySpec(password.toCharArray(), salt, 1000, 256)
		try {
			val skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
			return skf.generateSecret(spec).encoded
		} catch (e: Exception) {
			throw AssertionError("Error while hashing a password: " + e.message, e)
		} finally {
			spec.clearPassword()
		}
	}
}
