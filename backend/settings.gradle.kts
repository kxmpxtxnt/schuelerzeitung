rootProject.name = "backend"

dependencyResolutionManagement {
	versionCatalogs {
		create("jetbrains") {
			version("kotlin", "1.9.23")
			plugin("jvm", "org.jetbrains.kotlin.jvm").versionRef("kotlin")
			plugin("serialization", "org.jetbrains.kotlin.plugin.serialization").versionRef("kotlin")
		}

		create("kotlinx") {
			library("io", "org.jetbrains.kotlinx", "kotlinx-io-core").version("0.3.2")
			library("datetime", "org.jetbrains.kotlinx", "kotlinx-datetime").version("0.5.0")
			library("json", "org.jetbrains.kotlinx", "kotlinx-serialization-json").version("1.6.3")
			library("coroutines", "org.jetbrains.kotlinx", "kotlinx-coroutines-core").version("1.8.0")

			bundle("kotlinx", listOf("coroutines", "coroutines", "datetime", "io"))
		}

		create("ktorio") {
			plugin("ktor", "io.ktor.plugin").version("2.3.9")

			library("cio", "io.ktor", "ktor-server-cio").withoutVersion()
			library("network", "io.ktor", "ktor-network").withoutVersion()
			library("core", "io.ktor", "ktor-server-core").withoutVersion()
			library("resources", "io.ktor", "ktor-server-resources").withoutVersion()
			library("locations", "io.ktor", "ktor-server-locations").withoutVersion()
			library("rate-limit", "io.ktor", "ktor-server-rate-limit").withoutVersion()
			library("json", "io.ktor", "ktor-serialization-kotlinx-json").withoutVersion()
			library("server-sessions", "io.ktor", "ktor-server-sessions").withoutVersion()
			library("status-pages", "io.ktor", "ktor-server-status-pages").withoutVersion()
			library("negotiation", "io.ktor", "ktor-server-content-negotiation").withoutVersion()
			library("serialization", "io.ktor", "ktor-serialization-kotlinx-json").withoutVersion()

			bundle(
				"ktor", listOf(
					"cio",
					"core",
					"json",
					"network",
					"resources",
					"locations",
					"rate-limit",
					"negotiation",
					"status-pages",
					"serialization",
					"server-sessions",
				)
			)
		}

		create("database") {
			version("exposed", "0.49.0")

			library("exposed-dao", "org.jetbrains.exposed", "exposed-dao").versionRef("exposed")
			library("exposed-core", "org.jetbrains.exposed", "exposed-core").versionRef("exposed")
			library("exposed-jdbc", "org.jetbrains.exposed", "exposed-jdbc").versionRef("exposed")
			library("exposed-datetime", "org.jetbrains.exposed", "exposed-kotlin-datetime").versionRef("exposed")

			library("postgres", "org.postgresql", "postgresql").version("42.7.3")
			library("hikari", "com.zaxxer", "HikariCP").version("5.1.0")

			bundle(
				"exposed", listOf(
					"exposed-dao",
					"exposed-core",
					"exposed-jdbc",
					"exposed-datetime",
				)
			)
		}

		create("libs") {
			library("jedis", "redis.clients", "jedis").version("5.1.2")
			library("logback", "ch.qos.logback", "logback-classic").version("1.5.3")
		}

		create("redis") {
		}
	}
}