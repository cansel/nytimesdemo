package com.demo.nytimes.di.module

import com.demo.nytimes.ui.main.view.NyArticleActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Activity builder module for activity injection
 *
 */
@Module
abstract class ActivityBuilderModule {

    /**
     * Generates an {@link AndroidInjector} for the return type of this method.
     */
    @ContributesAndroidInjector(modules = [FragmentsBuilderModule::class])
    abstract fun contributeActivityAndroidInjector(): NyArticleActivity?

}