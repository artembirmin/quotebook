/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.common.di.app.module

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Cicerone.Companion.create
import com.github.terrakok.cicerone.NavigatorHolder
import com.incetro.quotebook.common.navigation.AppRouter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppNavigationModule {
    @Provides
    @Singleton
    fun provideAppCicerone(): Cicerone<AppRouter> = create(AppRouter())

    @Provides
    @Singleton
    fun provideAppRouter(appCicerone: Cicerone<AppRouter>): AppRouter = appCicerone.router

    @Provides
    @Singleton
    fun provideNavigationHolder(appCicerone: Cicerone<AppRouter>): NavigatorHolder =
        appCicerone.getNavigatorHolder()
}