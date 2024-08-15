package com.dantrap.template.di

import android.app.Application
import com.core.common.di.dispatchersModule
import com.core.data.di.dataModule
import com.core.database.di.databaseModule
import com.core.datastore.di.datastoreModule
import com.core.domain.di.domainModule
import com.core.network.di.networkModule
import com.feature.home.di.featureHomeModule
import com.feature.settings.di.featureSettingsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun Application.initKoin() = startKoin {
    androidContext(this@initKoin)
    androidLogger()

    modules(
        dispatchersModule,
        dataModule,
        domainModule,
        networkModule,
        datastoreModule,
        databaseModule,
        featureHomeModule,
        featureSettingsModule,
    )
}
