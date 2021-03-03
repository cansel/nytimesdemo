package com.demo.nytimes.module

import android.content.Context
import com.demo.nytimes.di.module.AppModule
import io.mockk.mockk

class TestAppModule : AppModule(mockk()) {

    override fun provideContext(): Context = mockk()
}