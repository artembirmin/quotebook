/*
 * Quotebook
 *
 * Created by artembirmin on 22/9/2023.
 */

package com.incetro.quotebook.quoteinteractor

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
    fun inject(quoteInteractorInstrumentedTest: QuoteInteractorInstrumentedTest)

    @Component.Builder
    interface Builder {
        fun testModule(component: QuoteTestModule): Builder
        fun build(): QuoteTestComponent
    }
}