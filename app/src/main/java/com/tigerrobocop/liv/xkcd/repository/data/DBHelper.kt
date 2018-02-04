package com.tigerrobocop.liv.xkcd.repository.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.tigerrobocop.liv.xkcd.model.XKCD
import android.arch.persistence.room.Room
import android.content.Context


@Database(entities = arrayOf(XKCD::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {


    abstract fun xkcdDao(): DAO


}