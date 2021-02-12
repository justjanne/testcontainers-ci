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
import org.testcontainers.containers.GenericContainer
import java.net.InetAddress

/**
 * Wrapper wrapping a local container
 */
class TestContainersProvidedContainer<T : GenericContainer<T>>(
  private val container: T
) : ProvidedContainer {
  override fun start() = container.start()
  override fun stop() = container.stop()
  override val address: InetAddress
    get() = InetAddress.getByName(container.containerIpAddress)
  override fun getMappedPort(originalPort: Int): Int =
    container.getMappedPort(originalPort)
}
