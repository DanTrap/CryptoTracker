package com.core.common.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.bind
import org.koin.dsl.module

val dispatchersModule = module {

    single { Dispatchers.IO } bind CoroutineDispatcher::class
}
