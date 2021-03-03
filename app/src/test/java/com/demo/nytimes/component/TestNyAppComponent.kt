package com.demo.nytimes.component

import com.demo.nytimes.di.component.NYAppComponent
import com.demo.nytimes.di.module.AppModule
import com.demo.nytimes.di.module.RetrofitNetworkModule
import com.demo.nytimes.di.module.ViewModelFactoryModule
import com.demo.nytimes.network.TestPopularViewModel
import com.demo.nytimes.network.TestRepository
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, RetrofitNetworkModule::class,
        ViewModelFactoryModule::class, AppModule::class]
)
interface TestNyAppComponent : NYAppComponent {
    fun inject(testRepository: TestRepository)
    fun inject(testPopularViewModel: TestPopularViewModel)
}