/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.common.di.componentmanager

/**
 * Provides Dagger component.
 */
interface ComponentProvider {

    /**
     * Returns [clazz] instance. [clazz] is Dagger component class.
     */
    fun <T> getComponent(clazz: Class<T>): T
}