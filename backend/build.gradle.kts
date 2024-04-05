import io.ktor.plugin.features.*

plugins {
	alias(ktorio.plugins.ktor)
	alias(jetbrains.plugins.jvm)
	alias(jetbrains.plugins.serialization)
}

repositories {
	mavenCentral()
}

application.mainClass = "fyi.pauli.schuelerzeitung.ApplicationKt"

ktor {
	fatJar.archiveFileName = "schuelerzeitung-fat.jar"

	docker {
		localImageName = "schuelerzeitung-backend"
		imageTag = version.toString()

		portMappings = listOf(
			DockerPortMapping(
				8080, 8080, DockerPortMappingProtocol.TCP
			)
		)
	}
}

kotlin {
	jvmToolchain(21)
}

tasks.test {
	useJUnitPlatform()
}

dependencies {
	implementation(libs.jedis)
	implementation(libs.logback)
	implementation(database.hikari)
	implementation(database.postgres)
	implementation(ktorio.bundles.ktor)
	implementation(kotlinx.bundles.kotlinx)
	implementation(database.bundles.exposed)

	testImplementation(kotlin("test"))
}