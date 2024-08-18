package com.core.domain.di

import com.core.domain.usecase.GetCoinDetailsUseCase
import com.core.domain.usecase.GetCoinsMarketUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {

    singleOf(::GetCoinsMarketUseCase)

    singleOf(::GetCoinDetailsUseCase)
}
