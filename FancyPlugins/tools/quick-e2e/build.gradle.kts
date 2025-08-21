plugins {
    id("java")
    id("maven-publish")
    id("com.gradleup.shadow")
}

group = "de.oliver"
description = "Tool to setup a complete environment for testing"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.fancyinnovations.com/releases")
}

dependencies {
    compileOnly("com.google.code.gson:gson:2.13.1")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("org.jetbrains:annotations:26.0.2")

    implementation("de.oliver.FancyAnalytics:java-sdk:0.0.2")
    implementation("de.oliver.FancyAnalytics:logger:0.0.6")
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "de.oliver.deployment.Main"
        }
    }

    shadowJar{
        archiveClassifier.set("")
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything

        // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
        // See https://openjdk.java.net/jeps/247 for more information.
        options.release.set(21)
    }

    java {
        withSourcesJar()
        withJavadocJar()
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
}
