package com.feature.details.di

import com.feature.details.presentation.details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureDetailsModule = module {

    viewModelOf(::DetailsViewModel)
}
