package com.demo.nytimes.di.module


import com.demo.nytimes.ui.main.view.MostPopularArticleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Class is used for inject fragment.
 */
@Module
abstract class FragmentsBuilderModule {

    /**
     * Generates an {@link AndroidInjector} for the return type of this method.
     */
    @ContributesAndroidInjector(
        modules = [MostPopularViewModelModule::class, PopularAdapterModule::class,
            InternetStatusViewModelModule::class]
    )
    abstract fun contributeFragmentAndroidInjector(): MostPopularArticleFragment?

}