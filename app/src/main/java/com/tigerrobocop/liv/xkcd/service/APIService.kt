package com.tigerrobocop.liv.xkcd.service

import com.tigerrobocop.liv.xkcd.model.XKCD
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Livia on 15/01/2018.
 */
interface APIService {

    @GET("info.0.json")
    fun getXKCD(): Call<XKCD>

    companion object Factory {
        fun create(): APIService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://xkcd.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    // talvez add adapter factory tbm. tem no tutorial mas nao no aoo original
                    .build()

            return retrofit.create(APIService::class.java)
        }
    }
}