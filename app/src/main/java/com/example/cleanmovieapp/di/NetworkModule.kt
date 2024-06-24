package com.example.cleanmovieapp.di

import com.example.cleanmovieapp.common.Constants.BASE_URL
import com.example.cleanmovieapp.data.repository.MoviesRepositoryImpl
import com.example.cleanmovieapp.data.service.MovieApi
import com.example.cleanmovieapp.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Provides
    @Singleton
    fun provideRetrofitOutfitsApiService(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(
        api: MovieApi,
    ): MoviesRepository {
        return MoviesRepositoryImpl(api)
    }


}