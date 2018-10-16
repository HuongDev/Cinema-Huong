package com.huong.appcinema.models.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.huong.appcinema.models.Cinema

/**
 * Created by HuongPN on 10/15/2018.
 */
@Database(entities = [Cinema::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cinemaDao(): CinemaDao
}