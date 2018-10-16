package com.huong.appcinema.models.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.huong.appcinema.models.Cinema

/**
 * Created by HuongPN on 10/15/2018.
 */
@Dao
interface CinemaDao {
    @get:Query("SELECT * FROM cinema")
    val all: List<Cinema>
}