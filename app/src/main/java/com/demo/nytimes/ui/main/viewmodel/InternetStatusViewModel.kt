package com.demo.nytimes.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.demo.nytimes.utilities.InternetUtil
import javax.inject.Inject

/**
 * View Model for detect internet status
 */
class InternetStatusViewModel @Inject constructor() : ViewModel() {

    @set:Inject
    var internetUtil: InternetUtil? = null

    fun getInternetStatus(): LiveData<Boolean> {
        return internetUtil!!
    }
}

