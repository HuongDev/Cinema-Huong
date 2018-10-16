package com.huong.appcinema.networking

import android.util.Log
import com.huong.appcinema.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import javax.inject.Singleton

/**
 * Created by HuongPN on 10/15/2018.
 */
@Module
class NetworkModule{
    var cacheFile: File? = null

    fun NetworkModule(cacheFile: File) {
        this.cacheFile = cacheFile
    }

    @Provides
    @Singleton
    internal fun provideCall(): Retrofit {
        var cache: Cache? = null
        try {
            cache = Cache(cacheFile, (10 * 1024 * 1024).toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val httpClient: OkHttpClient
        val httpClientBuilder = OkHttpClient.Builder()

        httpClientBuilder.addInterceptor { chain ->
            val original = chain.request()

            // Build headers
            val builder = Headers.Builder()
            builder.add("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME))
            builder.add("Content-Type", "application/json")
//            val apiToken = SharedPreference.getString(this, "ACCESS_TOKEN")
//            if (apiToken != null) {
//                builder.add("Authorization", apiToken!!)
//            }

            // Customize the request
            val request = original.newBuilder()
                    .headers(builder.build())
                    .removeHeader("Pragma")
                    .build()
            Log.i("request", request.toString() + "")
            val response = chain.proceed(request)
            Log.i("request", response.toString() + "")
            response.cacheResponse()

            // Customize or return the response
            response
        }

        // Logging for dev version.
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(logging)
        }

        httpClient = httpClientBuilder.cache(cache).build()

        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun providesNetworkService(retrofit: Retrofit): NetworkService {
        return retrofit.create<NetworkService>(NetworkService::class.java!!)
    }

    @Provides
    @Singleton
    fun providesService(networkService: NetworkService): Service {
        return Service(networkService)
    }
}