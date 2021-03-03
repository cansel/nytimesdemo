package com.demo.nytimes.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.demo.nytimes.R
import com.demo.nytimes.network.model.ResponseType
import com.demo.nytimes.ui.main.viewmodel.InternetStatusViewModel
import com.demo.nytimes.ui.main.viewmodel.MostPopularListViewModel
import com.demo.nytimes.utilities.Constants
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.error_fetch_data.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.no_internet.*
import javax.inject.Inject

const val TAG = "MainFragment"

/**
 * fragment used to displays lost of popular article
 */
class MostPopularArticleFragment : DaggerFragment(),
    MostPopularRecycleViewAdapter.MostPopularItemClickListener {

    @set:Inject
    var mainViewModel: MostPopularListViewModel? = null

    @set:Inject
    var internetStatusViewModel: InternetStatusViewModel? = null

    @set:Inject
    var popularRecycleViewAdapter: MostPopularRecycleViewAdapter? = null

    companion object {
        fun newInstance() = MostPopularArticleFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularList.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
        popularList.adapter = popularRecycleViewAdapter
        getPopularList(Constants.SECTION_VALUE_30)
        tryAgain.setOnClickListener { getPopularList(Constants.SECTION_VALUE_30) }
        checkInternetConnectivity()
    }

    private fun checkInternetConnectivity() {
        internetStatusViewModel!!.getInternetStatus().observe(viewLifecycleOwner, Observer {
            errorLayout.visibility = View.GONE
            when (it) {
                true -> {
                    Log.d(TAG, "internet found")
                    getPopularList(Constants.SECTION_VALUE_30)
                    noInternetLayout.visibility = View.GONE
                }
                false -> {
                    Log.e(TAG, "no internet found")
                    loadData.hide()
                    popularList.visibility = View.GONE
                    noInternetLayout.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun getPopularList(daysValue: String) {
        mainViewModel!!.getMostPopularData(daysValue).observe(viewLifecycleOwner,
            Observer {
                when (it.responseType) {
                    ResponseType.SUCCESS -> {
                        loadData.hide()
                        showOrHideView(View.GONE, View.VISIBLE)
                        popularRecycleViewAdapter!!.setPopularDataList(
                            it.response!!.mostPopularList
                        )
                        Log.d(TAG, "$it.response.mostPopularList.size")
                    }
                    ResponseType.FAILURE -> {
                        loadData.hide()
                        noInternetLayout.visibility = View.GONE
                        showOrHideView(View.VISIBLE, View.GONE)
                        Log.e(TAG, "$it.response.toString()")

                    }
                    ResponseType.LOADING -> {
                        loadData.show()
                        showOrHideView(View.GONE, View.GONE)
                        Log.d(TAG, "loading ")
                    }
                }
            })
    }

    override fun onItemClick(view: View?, position: Int) {
        Log.d(TAG, "position $position")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        requireActivity().menuInflater.inflate(R.menu.day_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.oneDay -> {
                hideLoaderAndList()
                getPopularList(Constants.SECTION_VALUE_1)
            }
            R.id.sevenDay -> {
                hideLoaderAndList()
                getPopularList(Constants.SECTION_VALUE_7)
            }
            R.id.thirtyDay -> {
                hideLoaderAndList()
                getPopularList(Constants.SECTION_VALUE_30)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hideLoaderAndList() {
        popularList.visibility = View.GONE
        loadData.hide()
    }

    private fun showOrHideView(errorLayVisibility: Int, popularListVisibility: Int) {
        errorLayout.visibility = errorLayVisibility
        popularList.visibility = popularListVisibility
    }
}