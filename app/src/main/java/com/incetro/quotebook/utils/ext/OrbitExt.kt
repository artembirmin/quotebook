/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.utils.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.incetro.quotebook.presentation.base.messageshowing.SideEffect
import com.incetro.quotebook.presentation.base.mvvm.view.ViewState
import org.orbitmvi.orbit.ContainerHost


@Composable
fun <S : ViewState, E : SideEffect> ContainerHost<S, E>.collectSideEffectsAsState(
    lifecycleState: Lifecycle.State = Lifecycle.State.CREATED
): State<SideEffect> {
    val stateFlow = container.sideEffectFlow
//        .onEach {
//        Timber.e("1" + it.toString())
//        it
//    }
    val lifecycleOwner = LocalLifecycleOwner.current

    val stateFlowLifecycleAware = remember(stateFlow, lifecycleOwner) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, lifecycleState)
    }
//        .onEach {
//        Timber.e("2" + it.toString())
//        it
//    }

    return stateFlowLifecycleAware.collectAsState(SideEffect.None)
}
