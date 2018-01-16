package com.tigerrobocop.liv.xkcd.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "xkcd")
data class XKCD (
        var month: String?
        , var num: String?
        , var link: String?
        , var year: String?
        , var news: String?
        , var safe_title: String?
        , var transcript: String?
        , var alt: String?
        , var img: String?
        , var title: String?
        , var day: String?
){
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}