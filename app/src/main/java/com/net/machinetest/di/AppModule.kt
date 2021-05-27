package com.net.machinetest.di

import android.content.Context
import com.net.machinetest.data.network.NewsApi
import com.net.machinetest.data.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAuthApi(
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): NewsApi {
        return remoteDataSource.buildApi(NewsApi::class.java, context)
    }

}