package com.baymax.socialfeed.di

import com.baymax.socialfeed.data.FeedApi
import com.baymax.socialfeed.feed.FeedRepository
import com.baymax.socialfeed.feed.FeedRepositoryImpl
import com.baymax.socialfeed.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by abhinav on 18/11/21.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesFeedApi(): FeedApi = Retrofit.Builder()
        .baseUrl(FeedApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(FeedApi::class.java)

    @Singleton
    @Provides
    fun providesFeedRepository(api: FeedApi): FeedRepository = FeedRepositoryImpl(api)

    @Singleton
    @Provides
    fun providesDispatchers(): DispatcherProvider = object : DispatcherProvider{
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }
}