package com.demo.nytimes.di.module

import androidx.lifecycle.ViewModel
import com.demo.nytimes.ui.main.viewmodel.InternetStatusViewModel
import com.demo.nytimes.ui.main.viewmodel.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * module provide internet connectivity status model
 *
 */
@Module
abstract class InternetStatusViewModelModule {
    /**
     * @MainViewModelModule
     *
     * @param viewModel MainViewModelModule
     * @return ViewModel
     */
    @Binds
    @IntoMap
    @ViewModelKey(InternetStatusViewModel::class)
    abstract fun internetStatusViewModel(viewModel: InternetStatusViewModel): ViewModel?

}