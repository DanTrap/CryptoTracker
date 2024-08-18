package com.core.data.di

import com.core.data.repository.CoinsRepositoryImpl
import com.core.data.source.local.CoinsLocalDataSource
import com.core.data.source.remote.CoinsRemoteDataSource
import com.core.domain.repository.CoinsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {

    singleOf(CoinsLocalDataSource::Base) bind CoinsLocalDataSource::class

    singleOf(CoinsRemoteDataSource::Base) bind CoinsRemoteDataSource::class

    singleOf(::CoinsRepositoryImpl) bind CoinsRepository::class
}
