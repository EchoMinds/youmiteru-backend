plugins {
    java
    jacoco
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "ru.youmiteru"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.withType<JacocoReport> {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val mainSrc = sourceSets.getByName("main").output.classesDirs
    classDirectories.setFrom(files(mainSrc).asFileTree.matching {
        exclude("**/dto/**")
        exclude("**/entity/**")
        exclude("ru/youmiteru/backend/BackendApplication.class")
    })
}

val intTestImplementation: Configuration by configurations.creating {
	extendsFrom(configurations.getByName("implementation"))
}
val intTestRuntimeOnly: Configuration by configurations.creating {
	extendsFrom(configurations.getByName("runtimeOnly"))
}

val integrationTest: SourceSet by sourceSets.creating {
	compileClasspath += sourceSets["main"].output + configurations["intTestImplementation"]
	runtimeClasspath += output + compileClasspath + configurations["intTestRuntimeOnly"]
}

tasks.register<Test>("integrationTest") {
	description = "Runs the integration tests."
	group = "verification"
	testClassesDirs = integrationTest.output.classesDirs
	classpath = integrationTest.runtimeClasspath

	useJUnitPlatform()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.flywaydb:flyway-core:10.4.1")
	implementation("org.postgresql:postgresql")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.flywaydb:flyway-database-postgresql:10.4.1")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")

	intTestImplementation("org.springframework.boot:spring-boot-starter-test")
	intTestImplementation("org.testcontainers:junit-jupiter:1.18.3")
	intTestImplementation("org.testcontainers:testcontainers:1.18.3")
	intTestImplementation("org.testcontainers:postgresql:1.18.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
