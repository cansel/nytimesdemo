package com.demo.nytimes.network

import androidx.lifecycle.ViewModelProvider
import com.demo.nytimes.di.module.ViewModelFactoryModule
import com.demo.nytimes.ui.main.viewmodel.viewmodelfactory.ViewModelProviderFactory
import io.mockk.mockk

class TestViewModelFactoryModule : ViewModelFactoryModule() {
    override fun viewModelFactoryModule(viewModelFactory: ViewModelProviderFactory?):
            ViewModelProvider.Factory? = mockk()
}