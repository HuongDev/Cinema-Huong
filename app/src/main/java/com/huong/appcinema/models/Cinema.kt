package com.huong.appcinema.models

import android.arch.persistence.room.Entity

/**
 * Created by HuongPN on 10/15/2018.
 */
@Entity
data class Cinema(val name: String,
                          val ver: String,
                          val api: String)