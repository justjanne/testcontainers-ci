/*
 * Testcontainers-CI
 * Copyright (c) 2021 Janne Mareike Koschinski
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at https://mozilla.org/MPL/2.0/.
 */

package de.justjanne.testcontainersci.extension

import de.justjanne.testcontainersci.api.ProvidedContainer
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * JUnit 5 Extension for testcontainers-ci
 */
class CiContainersExtension : BeforeEachCallback, AfterEachCallback {
  private fun getContainers(context: ExtensionContext?): List<ProvidedContainer> {
    val containers = mutableListOf<ProvidedContainer>()
    context?.requiredTestInstances?.allInstances?.forEach { instance ->
      instance.javaClass.declaredFields.map { field ->
        if (field.type == ProvidedContainer::class.java) {
          field.trySetAccessible()
          containers.add(field.get(instance) as ProvidedContainer)
        }
      }
    }
    return containers
  }

  override fun beforeEach(context: ExtensionContext?) {
    getContainers(context).forEach(ProvidedContainer::start)
  }

  override fun afterEach(context: ExtensionContext?) {
    getContainers(context).forEach(ProvidedContainer::stop)
  }
}
