package com.demo.nytimes.module

import com.demo.nytimes.di.module.RetrofitNetworkModule
import com.demo.nytimes.network.NYApiInterface
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class TestNetworkModule : RetrofitNetworkModule() {

    override fun getGson(): Gson {
        return super.getGson()
    }

    override fun getHttpClient(): OkHttpClient {
        return super.getHttpClient()
    }

    override fun getNewtWorkInterface(retrofit: Retrofit): NYApiInterface {
        return super.getNewtWorkInterface(retrofit)
    }

    override fun getRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return super.getRetrofit(gson, okHttpClient)
    }
}