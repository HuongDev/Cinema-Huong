package com.huong.appcinema.app

import android.app.Application

/**
 * Created by HuongPN on 10/15/2018.
 */
class CinemaApp : Application() {

//    lateinit var appComponent: AppComponent
//
//    @Inject
//    lateinit var plugins: MutableSet<AppPlugin>
//
//    val userComponent: UserComponent by lazy {
//        appComponent.plus(UserModule())
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//
//        // create the app component
//        appComponent = DaggerAppComponent.builder()
//                .appModule(AppModule(this))
//                .databaseModule(DatabaseModule())
//                .pluginsModule(PluginsModule())
//                .build()
//
//        // inject
//        appComponent.inject(this)
//
//        setupPlugins()
//    }
//
//    private fun setupPlugins() {
//        plugins.sortedBy { it.order }
//
//        plugins.forEach {
//            it.init(this)
//        }
//    }

}