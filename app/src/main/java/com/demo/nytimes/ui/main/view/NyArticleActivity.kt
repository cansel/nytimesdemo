package com.demo.nytimes.ui.main.view

import android.os.Bundle
import com.demo.nytimes.R
import dagger.android.support.DaggerAppCompatActivity

class NyArticleActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MostPopularArticleFragment.newInstance())
                .commit()
        }
    }
}