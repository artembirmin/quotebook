/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.presentation.userstory.demo.di

import com.incetro.quotebook.common.di.activity.ActivityComponent
import com.incetro.quotebook.common.di.componentmanager.ComponentManager
import com.incetro.quotebook.common.di.componentmanager.ComponentsManager
import com.incetro.quotebook.common.di.scope.FeatureScope
import com.incetro.quotebook.presentation.userstory.demo.demoscreen.DemoFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [ActivityComponent::class],
    modules = [
        DemoModule::class
    ]
)
interface DemoComponent {
    fun inject(demoFragment: DemoFragment)

    @Component.Builder
    interface Builder {
        fun activityComponent(component: ActivityComponent): Builder
        fun build(): DemoComponent
    }

    object Manager : ComponentManager<DemoComponent>(
        clazz = DemoComponent::class.java,
        createAndSave = {
            val componentManager = ComponentsManager.getInstance()
            val activityComponent = ActivityComponent.Manager.getComponent()
            val component = DaggerDemoComponent.builder()
                .activityComponent(activityComponent)
                .build()
            componentManager.addComponent(component)
        })
}