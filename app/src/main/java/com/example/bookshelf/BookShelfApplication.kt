package com.example.bookshelf

import android.app.Application
import com.example.bookshelf.data.AppContainer
import com.example.bookshelf.data.DefaultAppContainer




class BookShelfApplication : Application(){

    lateinit var container: AppContainer

    override fun onCreate() {
        //App Starts Here
        //Upon App Startup these resources will be begin initialization (even before any UI Loads)


        super.onCreate()
        container = DefaultAppContainer()
    }
}