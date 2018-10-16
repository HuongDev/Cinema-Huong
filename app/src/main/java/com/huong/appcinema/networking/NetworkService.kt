package com.huong.appcinema.networking

import com.huong.appcinema.createmovie.MovieListResponse
import retrofit2.Call
import retrofit2.http.POST

/**
 * Created by HuongPN on 10/15/2018.
 */
interface NetworkService{
//    @GET("api/cinema")
//    fun getAllMovies(): Observable<List<AndroidVersion>>

    @POST("api/cinema")
    fun createMovie(): Call<MovieListResponse>
}