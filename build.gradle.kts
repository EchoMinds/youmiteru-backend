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

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")
    implementation("org.postgresql:postgresql:42.5.4")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    compileOnly("org.projectlombok:lombok:1.18.26")
    runtimeOnly("org.postgresql:postgresql:42.5.4")
    annotationProcessor("org.projectlombok:lombok:1.18.26")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0")
    testImplementation("com.h2database:h2:2.1.214")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
