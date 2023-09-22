/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.common.di.activity

import android.content.Context
import com.incetro.quotebook.app.AppActivity
import com.incetro.quotebook.common.di.app.AppComponent
import com.incetro.quotebook.common.di.componentmanager.ComponentManager
import com.incetro.quotebook.common.di.componentmanager.ComponentsManager
import com.incetro.quotebook.common.di.scope.ActivityScope
import com.incetro.quotebook.common.manager.ResourcesManager
import com.incetro.quotebook.common.navigation.AppRouter
import com.incetro.quotebook.model.data.database.AppDatabase
import com.incetro.quotebook.model.data.database.quote.QuoteDao
import com.incetro.quotebook.model.data.network.api.CategoryApi
import com.incetro.quotebook.presentation.base.mvvm.viewmodel.BaseViewModelDependencies
import dagger.Component

@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [
        ActivityModule::class
    ]
)
interface ActivityComponent {
    fun inject(appActivity: AppActivity)

    // AppModule
    fun provideContext(): Context
    fun provideDeps(): BaseViewModelDependencies

    // AppNavigationModule from AppComponent
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
        fun appComponent(component: AppComponent): Builder
        fun activityModule(activityModule: ActivityModule): Builder
        fun build(): ActivityComponent
    }

    object Manager : ComponentManager<ActivityComponent>(
        clazz = ActivityComponent::class.java,
        createAndSave = {
            val componentManager = ComponentsManager.getInstance()
            val activityComponent = DaggerActivityComponent.builder()
                .appComponent(componentManager.applicationComponent)
                .activityModule(ActivityModule())
                .build()

            componentManager.addComponent(activityComponent)
        })
}