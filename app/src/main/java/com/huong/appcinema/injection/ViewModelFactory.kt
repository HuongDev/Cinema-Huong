package com.huong.appcinema.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import com.huong.appcinema.createmovie.MovieListViewModel
import com.huong.appcinema.models.database.AppDatabase

/**
 * Created by HuongPN on 10/15/2018.
 */
class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "cinema").build()
            @Suppress("UNCHECKED_CAST")
            return MovieListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}