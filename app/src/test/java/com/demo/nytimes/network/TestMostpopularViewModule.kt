package com.demo.nytimes.network

import androidx.lifecycle.ViewModel
import com.demo.nytimes.di.module.MostPopularViewModelModule
import com.demo.nytimes.ui.main.viewmodel.MostPopularListViewModel
import com.demo.nytimes.ui.main.viewmodel.viewmodelfactory.ViewModelKey
import io.mockk.mockk


class TestMostpopularViewModule : MostPopularViewModelModule() {

    @ViewModelKey(MostPopularListViewModel::class)
    override fun popularListViewModel(viewModel: MostPopularListViewModel?): ViewModel? = mockk()

}