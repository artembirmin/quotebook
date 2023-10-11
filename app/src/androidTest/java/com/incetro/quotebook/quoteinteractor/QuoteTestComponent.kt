/*
 * Quotebook
 *
 * Created by artembirmin on 22/9/2023.
 */

package com.incetro.quotebook.quoteinteractor

import com.incetro.quotebook.common.di.app.module.CommonAppModule
import com.incetro.quotebook.common.di.app.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        QuoteTestModule::class,
        CommonAppModule::class,
        NetworkModule::class
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