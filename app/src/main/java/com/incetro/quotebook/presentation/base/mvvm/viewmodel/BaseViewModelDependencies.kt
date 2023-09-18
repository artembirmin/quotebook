/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.presentation.base.mvvm.viewmodel

import com.incetro.quotebook.common.manager.ResourcesManager
import com.incetro.quotebook.common.navigation.AppRouter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseViewModelDependencies @Inject constructor(
    val appRouter: AppRouter,
    val resourcesManager: ResourcesManager
)