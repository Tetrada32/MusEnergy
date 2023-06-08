package com.gahov.musenergy.arch.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gahov.musenergy.arch.di.ViewModelFactory
import com.gahov.musenergy.arch.di.ViewModelKey
import com.gahov.musenergy.feature.articles.details.ArticleDetailsViewModel
import com.gahov.musenergy.feature.articles.list.ArticleListViewModel
import com.gahov.musenergy.feature.favorites.FavoritesViewModel
import com.gahov.musenergy.feature.frontpage.FrontpageViewModel
import com.gahov.musenergy.feature.profile.ProfileViewModel
import com.gahov.musenergy.feature.search.SearchViewModel
import com.gahov.musenergy.feature.stories.StoriesViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FrontpageViewModel::class)
    abstract fun bindFrontpageViewModel(viewModel: FrontpageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleDetailsViewModel::class)
    abstract fun bindArticleDetailsViewModel(viewModel: ArticleDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleListViewModel::class)
    abstract fun bindArticleListViewModel(viewModel: ArticleListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StoriesViewModel::class)
    abstract fun bindStoriesViewModel(viewModel: StoriesViewModel): ViewModel

}