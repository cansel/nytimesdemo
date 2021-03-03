package com.demo.nytimes.di.module

import com.demo.nytimes.network.NYApiInterface
import com.demo.nytimes.utilities.Constants
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class RetrofitNetworkModule {

    /**
     * provides Okhttp obj
     *
     * @return OkHttpClient
     */
    @Provides
    @Singleton
    open fun getHttpClient(): OkHttpClient {
        val client = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        return client.build()
    }

    /**
     * provide gson object
     *
     * @return Gson
     */
    @Provides
    @Singleton
    open fun getGson(): Gson {
        val builder = GsonBuilder().setFieldNamingPolicy(
            FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
        )
        return builder.setLenient().create()
    }

    /**
     * provide retrofit object
     *
     * @param gson Gson
     * @param okHttpClient OkHttpClient
     * @return Retrofit
     */
    @Provides
    @Singleton
    open fun getRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    /**
     * provide https APi interface
     *
     * @param retrofit Retrofit
     * @return Retrofit
     */
    @Provides
    @Singleton
    open fun getNewtWorkInterface(retrofit: Retrofit): NYApiInterface {
        return retrofit.create(NYApiInterface::class.java)
    }
}