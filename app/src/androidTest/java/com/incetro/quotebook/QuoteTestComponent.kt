/*
 * Quotebook
 *
 * Created by artembirmin on 20/9/2023.
 */

package com.incetro.quotebook

import com.incetro.quotebook.common.di.app.module.CommonAppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        QuoteTestModule::class,
        CommonAppModule::class
    ]
)
interface QuoteTestComponent {
    fun inject(quoteInstrumentedTest: QuoteInstrumentedTest)

    @Component.Builder
    interface Builder {
        fun testModule(component: QuoteTestModule): Builder
        fun build(): QuoteTestComponent
    }
}