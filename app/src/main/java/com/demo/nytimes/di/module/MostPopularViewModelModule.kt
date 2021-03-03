package com.demo.nytimes.di.module

import androidx.lifecycle.ViewModel
import com.demo.nytimes.ui.main.viewmodel.MostPopularListViewModel
import com.demo.nytimes.ui.main.viewmodel.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * module provides popular list viewmodel
 *
 */
@Module
abstract class MostPopularViewModelModule {
    /**
     * @MainViewModelModule
     *
     * @param viewModel MainViewModelModule
     * @return ViewModel
     */
    @Binds
    @IntoMap
    @ViewModelKey(MostPopularListViewModel::class)
    abstract fun popularListViewModel(viewModel: MostPopularListViewModel?): ViewModel?
}