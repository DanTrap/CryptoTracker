package com.feature.home.di

import com.feature.home.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureHomeModule = module {

    viewModelOf(::HomeViewModel)
}
