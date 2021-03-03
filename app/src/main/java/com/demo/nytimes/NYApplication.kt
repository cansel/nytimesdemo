package com.demo.nytimes

import com.demo.nytimes.di.component.DaggerNYAppComponent
import com.demo.nytimes.di.component.NYAppComponent
import com.demo.nytimes.di.module.AppModule
import dagger.android.DaggerApplication

/**
 * Application class
 * @DaggerApplication
 *
 */
open class NYApplication : DaggerApplication() {

    override fun applicationInjector(): NYAppComponent? {
        return DaggerNYAppComponent.builder().application(this)!!.context(AppModule(this))!!.build()
    }
}