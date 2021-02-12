/*
 * Testcontainers-CI
 * Copyright (c) 2021 Janne Mareike Koschinski
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at https://mozilla.org/MPL/2.0/.
 */

package de.justjanne.testcontainersci.extension

import org.junit.jupiter.api.extension.ExtendWith

/**
 * Annotation to add testcontainers-ci to a JUnit 5 test
 */
@MustBeDocumented
@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ExtendWith(CiContainersExtension::class)
annotation class CiContainers
