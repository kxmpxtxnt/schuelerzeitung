package fyi.pauli.schuelerzeitung.server

import fyi.pauli.schuelerzeitung.server.routing.home
import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

@Resource("/login")
class Login

@Resource("/register")
class Register

@Resource("/#")
class Home

fun Application.basicRoutes() {
	routing {
		home()
	}
}