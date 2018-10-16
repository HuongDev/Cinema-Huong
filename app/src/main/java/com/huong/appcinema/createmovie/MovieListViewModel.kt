package com.huong.appcinema.createmovie

import com.huong.appcinema.base.BaseViewModel
import com.huong.appcinema.networking.Api
import javax.inject.Inject

/**
 * Created by HuongPN on 10/15/2018.
 */
class MovieListViewModel : BaseViewModel() {
    @Inject
    lateinit var cinemaApi: Api

}