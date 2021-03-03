package com.demo.nytimes.di.component

import com.demo.nytimes.NYApplication
import com.demo.nytimes.di.module.ActivityBuilderModule
import com.demo.nytimes.di.module.AppModule
import com.demo.nytimes.di.module.RetrofitNetworkModule
import com.demo.nytimes.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


/**
 * interface for App component
 */

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, RetrofitNetworkModule::class,
        ActivityBuilderModule::class, ViewModelFactoryModule::class, AppModule::class]
)
interface NYAppComponent : AndroidInjector<NYApplication?> {
    /**
     *
     * builder for dagger 2
     *
     */
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: NYApplication?): Builder?
        fun context(context: AppModule): Builder?
        fun build(): NYAppComponent?
    }
}
