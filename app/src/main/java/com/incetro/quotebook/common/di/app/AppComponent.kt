/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.common.di.app

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.incetro.quotebook.app.App
import com.incetro.quotebook.app.AppLauncher
import com.incetro.quotebook.common.di.app.module.AppModule
import com.incetro.quotebook.common.di.app.module.AppNavigationModule
import com.incetro.quotebook.common.di.app.module.CommonAppModule
import com.incetro.quotebook.common.di.app.module.DatabaseModule
import com.incetro.quotebook.common.di.app.module.NetworkModule
import com.incetro.quotebook.common.manager.ResourcesManager
import com.incetro.quotebook.common.navigation.AppRouter
import com.incetro.quotebook.model.data.database.AppDatabase
import com.incetro.quotebook.model.data.database.quote.QuoteDao
import com.incetro.quotebook.model.data.network.api.CategoryApi
import com.incetro.quotebook.model.repository.quote.QuoteRepository
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.BaseViewModelDependencies
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CommonAppModule::class,
        AppNavigationModule::class,
        DatabaseModule::class,
        NetworkModule::class,
    ]
)
interface AppComponent {

    fun inject(app: App)

    // AppModule
    fun provideContext(): Context
    fun provideAppLauncher(): AppLauncher
    fun provideDeps(): BaseViewModelDependencies

    // CommonAppModule
    fun provideQuoteRepository(): QuoteRepository

    // AppNavigationModule
    fun provideNavigationHolder(): NavigatorHolder
    fun provideAppRouter(): AppRouter

    // Database module
    fun provideAppDatabase(): AppDatabase
    fun provideDemoDao(): QuoteDao

    // Network module
    fun provideDemoApi(): CategoryApi

    // Other
    fun provideResourcesManager(): ResourcesManager

    @Component.Builder
    interface Builder {
        fun appModule(appModule: AppModule): Builder
        fun build(): AppComponent
    }
}