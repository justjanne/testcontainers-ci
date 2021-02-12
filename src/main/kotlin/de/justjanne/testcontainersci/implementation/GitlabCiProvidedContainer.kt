/*
 * Testcontainers-CI
 * Copyright (c) 2021 Janne Mareike Koschinski
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at https://mozilla.org/MPL/2.0/.
 */

package de.justjanne.testcontainersci.implementation

import de.justjanne.testcontainersci.api.ProvidedContainer
import java.net.InetAddress

/**
 * Wrapper exposing a ci-provided container
 */
class GitlabCiProvidedContainer(
  override val address: InetAddress
) : ProvidedContainer {
  override fun start() = Unit
  override fun stop() = Unit
  override fun getMappedPort(originalPort: Int) = originalPort
}
