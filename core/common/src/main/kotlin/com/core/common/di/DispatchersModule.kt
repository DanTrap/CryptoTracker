package com.core.common.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val dispatchersModule = module {

    single { Dispatchers.IO } bind CoroutineDispatcher::class

    provideCoroutineScope()
}

private fun Module.provideCoroutineScope(): KoinDefinition<CoroutineScope> =
    factory { CoroutineScope(get()) }
