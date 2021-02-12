/*
 * Testcontainers-CI
 * Copyright (c) 2021 Janne Mareike Koschinski
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at https://mozilla.org/MPL/2.0/.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.4.21"
  id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
  id("org.jetbrains.dokka") version "1.4.20"
  id("maven-publish")
  id("com.vanniktech.maven.publish") version "0.13.0"
}

repositories {
  mavenCentral()
  exclusiveContent {
    forRepository {
      maven {
        name = "JCenter"
        setUrl("https://jcenter.bintray.com/")
      }
    }
    filter {
      // Required for Dokka
      includeModule("com.soywiz.korlibs.korte", "korte-jvm")
      includeModule("org.jetbrains.kotlinx", "kotlinx-html-jvm")
      includeGroup("org.jetbrains.dokka")
      includeModule("org.jetbrains", "markdown")
    }
  }
}

dependencies {
  testImplementation(kotlin("test-junit5"))

  val testContainersVersion: String by project
  api("org.testcontainers", "testcontainers", testContainersVersion)
  api("org.testcontainers", "junit-jupiter", testContainersVersion)

  val junit5Version: String by project
  testImplementation("org.junit.jupiter", "junit-jupiter-api", junit5Version)
  testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junit5Version)
}

tasks.test {
  useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(8))
  }
}
