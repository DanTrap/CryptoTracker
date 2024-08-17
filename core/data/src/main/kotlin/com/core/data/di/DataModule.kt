package com.core.data.di

import com.core.data.repository.AppReviewManagerImpl
import com.core.data.repository.CoinsRepositoryImpl
import com.core.data.repository.ContactUsManagerImpl
import com.core.data.repository.LanguageRepositoryImpl
import com.core.data.source.local.CoinsLocalDataSource
import com.core.data.source.remote.CoinsRemoteDataSource
import com.core.domain.repository.AppReviewManager
import com.core.domain.repository.CoinsRepository
import com.core.domain.repository.ContactUsManager
import com.core.domain.repository.LanguageRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {

    singleOf(::LanguageRepositoryImpl) bind LanguageRepository::class

    singleOf(::ContactUsManagerImpl) bind ContactUsManager::class

    singleOf(::AppReviewManagerImpl) bind AppReviewManager::class

    singleOf(CoinsLocalDataSource::Base) bind CoinsLocalDataSource::class

    singleOf(CoinsRemoteDataSource::Base) bind CoinsRemoteDataSource::class

    singleOf(::CoinsRepositoryImpl) bind CoinsRepository::class
}
