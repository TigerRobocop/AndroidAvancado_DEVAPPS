package com.tigerrobocop.liv.xkcd

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.tigerrobocop.liv.xkcd.repository.data.AppDatabase

/**
 * Created by Livia on 04/02/2018.
 */
class XKCDApp : Application(){

    private val DATABASE_NAME = "dbLocal"

   // private var DB_INSTANCE: AppDatabase? = null
   // private var  APP_INSTANCE: XKCDApp? = null

    companion object {
        lateinit var DB_INSTANCE: AppDatabase
        lateinit var  APP_INSTANCE: XKCDApp
    }

    override fun onCreate() {
        super.onCreate()

        // create database
        DB_INSTANCE =  Room
                .databaseBuilder(applicationContext, AppDatabase::class.java!!, DATABASE_NAME)
                //.allowMainThreadQueries()
                .build()

        APP_INSTANCE = this
    }

    fun getDB(): AppDatabase? {
        return DB_INSTANCE
    }

    fun getApp(): XKCDApp? {
        return APP_INSTANCE
    }








    /*
fun getDBeeee(context: Context): AppDatabase? {

        if (DB_INSTANCE == null) {
            DB_INSTANCE =  Room.databaseBuilder(context, AppDatabase::class.java!!, DATABASE_NAME).build()
        }

        return DB_INSTANCE
    }

    fun destroyInstance() {
        DB_INSTANCE = null
    }
*/



}