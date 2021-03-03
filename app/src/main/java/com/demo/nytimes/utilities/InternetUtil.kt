package com.demo.nytimes.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import androidx.lifecycle.LiveData
import javax.inject.Inject


/**
 * Class is used to detect internet detection.
 */
class InternetUtil @Inject constructor(mContext: Context?) :
    LiveData<Boolean>() {
    private var connectivityManager: ConnectivityManager? = null
    private var networkCallback: NetworkCallback? = null

    override fun onActive() {
        super.onActive()
        connectivityManager!!.registerDefaultNetworkCallback(networkCallback!!)
    }

    init {
        connectivityManager = mContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as
                ConnectivityManager
        networkCallback = InterNetNetworkCallBack()
    }

    inner class InterNetNetworkCallBack : NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            postValue(false)
        }
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager!!.unregisterNetworkCallback(networkCallback!!)
    }
}