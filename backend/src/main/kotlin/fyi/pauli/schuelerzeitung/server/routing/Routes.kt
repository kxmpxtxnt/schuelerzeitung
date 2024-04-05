package fyi.pauli.schuelerzeitung.server.routing

import io.ktor.resources.*

@Resource("/login")
class Login

@Resource("/register")
class Register

@Resource("/#")
class Home