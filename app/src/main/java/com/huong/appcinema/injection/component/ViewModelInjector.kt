package com.huong.appcinema.injection.component

import com.huong.appcinema.createmovie.MovieListViewModel
import com.huong.appcinema.networking.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by HuongPN on 10/15/2018.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param movieListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(movieListViewModel: MovieListViewModel)
    /**
     * Injects required dependencies into the specified PostViewModel.
     * @param postViewModel PostViewModel in which to inject the dependencies
     */
//    fun inject(movieViewModel: MovieViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}