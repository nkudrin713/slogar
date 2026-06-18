plugins {
    `java-library`
    kotlin("jvm") version "2.4.0"
}

group = "dev.nkudrin.slogar"
version = "0.1.0-SNAPSHOT"

kotlin {
    jvmToolchain(17)
}

java {
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
