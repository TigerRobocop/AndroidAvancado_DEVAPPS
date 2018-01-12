package com.tigerrobocop.liv.xkcd.repository

import android.util.Log
import com.tigerrobocop.liv.xkcd.model.XKCD
import com.tigerrobocop.liv.xkcd.repository.api.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Livia on 06/01/2018.
 */

class XKCDRepository {

    val repo = Service.create()

    // TODO :: refatorar usando lazy or lateinit

    /*  */
    fun getXKCD(): XKCD? {

        var result : XKCD? = null

        repo.getXKCD().enqueue(object : Callback<XKCD>{

            override fun onResponse(call: Call<XKCD>?, response: Response<XKCD>?) {
                Log.d(XKCDRepository::class.java.simpleName, "onResponse:getXKCD - " + response?.body()?.link)
                result = response?.body()
            }

            override fun onFailure(call: Call<XKCD>?, t: Throwable?) {
                Log.d(XKCDRepository::class.java.simpleName, "onFailure:getXKCD")
            }
        })

        return result
    }
}