package com.huong.appcinema.createmovie

import com.google.gson.annotations.SerializedName
import com.huong.appcinema.models.Cinema
import java.util.*

/**
 * Created by HuongPN on 10/15/2018.
 */
class MovieListResponse(@SerializedName("films")
                         var data: List<Cinema> = ArrayList<Cinema>()){
}