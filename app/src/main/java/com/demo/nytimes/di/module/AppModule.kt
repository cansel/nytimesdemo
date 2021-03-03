package com.demo.nytimes.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * provide app context
 *
 * @property context Context
 */
@Module
open class AppModule(private var context: Context) {
    /**
     * method used to provide context
     *
     * @return Context
     */
    @Provides
    @Singleton
    open fun provideContext(): Context {
        return context
    }
}