/*
 * Testcontainers-CI
 * Copyright (c) 2021 Janne Mareike Koschinski
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at https://mozilla.org/MPL/2.0/.
 */

package de.justjanne.testcontainersci.api

import java.net.InetAddress

/**
 * Interface describing a container, can be backed either by a testcontainer or a ci-provided container
 */
interface ProvidedContainer {
  /**
   * Start the container, if applicable
   */
  fun start()

  /**
   * Stop the container, if applicable
   */
  fun stop()

  /**
   * Address for the container
   */
  val address: InetAddress

  /**
   * Get the mapped port for a specific original port
   */
  fun getMappedPort(originalPort: Int): Int
}
