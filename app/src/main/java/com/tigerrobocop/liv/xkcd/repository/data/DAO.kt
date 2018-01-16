package com.tigerrobocop.liv.xkcd.repository.data

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.tigerrobocop.liv.xkcd.model.XKCD


@Dao
interface DAO {
    @Query("select * from xkcd")
    fun getAllXKCD(): List<XKCD>

    @Query("select * from xkcd where id = :p0")
    fun findXKCDById(id: Long): XKCD

    @Insert(onConflict = REPLACE)
    fun insertXKCD(xkcd: XKCD)

    @Update(onConflict = REPLACE)
    fun updateXKCD(xkcd: XKCD)

    @Delete
    fun deleteXKCD(xkcd: XKCD)
}