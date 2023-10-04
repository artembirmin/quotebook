/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.presentation.userstory.quote.di

import com.incetro.quotebook.common.di.activity.ActivityComponent
import com.incetro.quotebook.common.di.componentmanager.ComponentManager
import com.incetro.quotebook.common.di.componentmanager.ComponentsManager
import com.incetro.quotebook.common.di.scope.FeatureScope
import com.incetro.quotebook.presentation.userstory.quote.quote.QuoteFragment
import com.incetro.quotebook.presentation.userstory.quote.quotelist.QuoteListFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [ActivityComponent::class],
    modules = [
        DemoModule::class
    ]
)
interface QuoteComponent {
    fun inject(quoteListFragment: QuoteListFragment)
    fun inject(quoteListFragment: QuoteFragment)

    @Component.Builder
    interface Builder {
        fun activityComponent(component: ActivityComponent): Builder
        fun build(): QuoteComponent
    }

    object Manager : ComponentManager<QuoteComponent>(
        clazz = QuoteComponent::class.java,
        createAndSave = {
            val componentManager = ComponentsManager.getInstance()
            val activityComponent = ActivityComponent.Manager.getComponent()
            val component = DaggerQuoteComponent.builder()
                .activityComponent(activityComponent)
                .build()
            componentManager.addComponent(component)
        })
}