package com.core.mvi

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

abstract class BaseViewModel<S : Any, SE : Any, E : Any>(
    initialState: S,
) : ViewModel(), ContainerHost<S, SE> {

    override val container = container<S, SE>(initialState = initialState)

    abstract fun onEvent(event: E)
}
