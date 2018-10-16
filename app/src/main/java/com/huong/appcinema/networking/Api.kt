package com.huong.appcinema.networking

import com.huong.appcinema.models.Cinema
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


/**
 * Created by HuongPN on 10/15/2018.
 */
interface Api {
    @GET("/posts")
    fun getPosts(): Observable<List<Cinema>>

    @Multipart
    @POST("/")
    fun postImage(@Part image: MultipartBody.Part, @Part("name") name: RequestBody): Call<ResponseBody>
}