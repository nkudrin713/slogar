import org.gradle.jvm.tasks.Jar

plugins {
    `java-library`
    id("com.vanniktech.maven.publish") version "0.36.0"
    kotlin("jvm") version "2.4.0"
}

group = "dev.nkudrin"
version = providers.gradleProperty("releaseVersion")
    .orElse("0.1.0-SNAPSHOT")
    .get()

kotlin {
    jvmToolchain(17)
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Jar>().configureEach {
    from(rootProject.file("LICENSE")) {
        into("META-INF")
    }
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()

    coordinates(group.toString(), "slogar", version.toString())

    pom {
        name = "slogar"
        description = "Kotlin library for syllabification of Russian words"
        url = "https://github.com/nkudrin713/slogar"

        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "repo"
            }
        }

        developers {
            developer {
                id = "nkudrin713"
                name = "Nikita Kudrin"
                url = "https://github.com/nkudrin713"
            }
        }

        scm {
            connection = "scm:git:https://github.com/nkudrin713/slogar.git"
            developerConnection = "scm:git:ssh://git@github.com/nkudrin713/slogar.git"
            url = "https://github.com/nkudrin713/slogar"
        }
    }
}
