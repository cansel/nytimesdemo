package com.demo.nytimes.di.module

import com.demo.nytimes.ui.main.view.MostPopularArticleFragment
import com.demo.nytimes.ui.main.view.MostPopularRecycleViewAdapter
import dagger.Module
import dagger.Provides

/**
 * module provides recycle view adapter and item click dependencies
 *
 */
@Module
class PopularAdapterModule {

    /**
     * mostPopularListAdapter
     *
     * @param mostPopularItemClickListener MostPopularItemClickListener
     * @return MostPopularRecycleViewAdapter
     */
    @Provides
    fun mostPopularListAdapter(
        mostPopularItemClickListener: MostPopularRecycleViewAdapter.
        MostPopularItemClickListener?
    ): MostPopularRecycleViewAdapter {
        return MostPopularRecycleViewAdapter(
            mostPopularItemClickListener
        )
    }

    @Provides
    fun mostPopularItemClickListener(mainFragment: MostPopularArticleFragment):
            MostPopularRecycleViewAdapter.MostPopularItemClickListener {
        return mainFragment
    }
}