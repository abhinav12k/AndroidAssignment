package com.baymax.socialfeed.di

import com.baymax.socialfeed.data.FeedApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by abhinav on 18/11/21.
 */

private const val BASE_URL = "https://gorest.co.in/public/v1/"
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesFeedApi(): FeedApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(FeedApi::class.java)

}