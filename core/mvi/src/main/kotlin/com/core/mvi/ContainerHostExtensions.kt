package com.core.mvi

import kotlinx.coroutines.Job
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.IntentContext

fun <S : Any, SE : Any> ContainerHost<S, SE>.emitSideEffect(
    sideEffect: SE,
): Job = intent { postSideEffect(sideEffect) }

fun <S : Any, SE : Any> ContainerHost<S, SE>.reducer(
    block: IntentContext<S>.() -> S,
): Job = intent { reduce(block) }

fun <S : Any, SE : Any> ContainerHost<S, SE>.blockingReducer(
    block: IntentContext<S>.() -> S,
): Unit = blockingIntent { reduce(block) }
