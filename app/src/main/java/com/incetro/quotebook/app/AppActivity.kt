/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.incetro.quotebook.R
import com.incetro.quotebook.common.di.activity.ActivityComponent
import com.incetro.quotebook.presentation.base.mvvm.view.BaseComposeFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class AppActivity : AppCompatActivity() {

    @Inject
    lateinit var appLauncher: AppLauncher

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    /**
     * Instance of currently displayed fragment
     */
    private val currentFragment: BaseComposeFragment?
        get() = supportFragmentManager.findFragmentById(R.id.fragment_container) as? BaseComposeFragment

    private val navigator: Navigator = AppNavigator(this, R.id.fragment_container)

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.layout_container)

        if (savedInstanceState == null) {
            appLauncher.start(lifecycleScope)
        }
    }

    private fun inject() {
        ActivityComponent.Manager.getComponent().inject(this)
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            compositeDisposable.clear()
            ActivityComponent.Manager.releaseComponent()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    protected fun Disposable.addDisposable(): Disposable {
        compositeDisposable.add(this)
        return this
    }
}