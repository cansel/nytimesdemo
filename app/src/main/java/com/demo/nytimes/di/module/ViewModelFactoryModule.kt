package com.demo.nytimes.di.module

import androidx.lifecycle.ViewModelProvider
import com.demo.nytimes.ui.main.viewmodel.viewmodelfactory.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    /**
     * view model factory module
     *
     * @param viewModelFactory @ViewModelProviderFactory
     * @return viewModelFactoryModule
     */
    @Binds
    abstract fun viewModelFactoryModule(viewModelFactory: ViewModelProviderFactory?):
            ViewModelProvider.Factory?
}