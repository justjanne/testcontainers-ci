/*
 * Testcontainers-CI
 * Copyright (c) 2021 Janne Mareike Koschinski
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at https://mozilla.org/MPL/2.0/.
 */

package de.justjanne.testcontainersci.api

import de.justjanne.testcontainersci.implementation.GitlabCiProvidedContainer
import java.net.InetAddress

/**
 * Build a provided container from an environment variable and a builder for a local container
 */
fun providedContainer(
  envVariable: String,
  containerBuilder: () -> ProvidedContainer
) = when {
  !System.getenv(envVariable).isNullOrEmpty() ->
    GitlabCiProvidedContainer(InetAddress.getByName(System.getenv(envVariable)))
  else -> containerBuilder()
}
