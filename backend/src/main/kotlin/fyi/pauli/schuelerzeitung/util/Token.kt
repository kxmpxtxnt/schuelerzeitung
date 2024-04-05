package fyi.pauli.schuelerzeitung.util

object Token {

	fun getRandomToken(length: Int = 64): String {
		val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
		return (1..length)
			.map { allowedChars.random() }
			.joinToString("")
	}
}