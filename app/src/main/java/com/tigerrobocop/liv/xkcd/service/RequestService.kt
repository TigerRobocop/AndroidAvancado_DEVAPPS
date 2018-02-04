package com.tigerrobocop.liv.xkcd.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.*
import android.os.AsyncTask
import com.tigerrobocop.liv.xkcd.XKCDApp
import com.tigerrobocop.liv.xkcd.model.XKCD
import android.widget.Toast
import android.app.Activity


class RequestService : Service() {

    // tutorial source: http://www.techotopia.com/index.php/Android_Started_Service_%E2%80%93_A_Kotlin_Example
    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        throw UnsupportedOperationException("Not yet implemented")
    }


    private val TAG = RequestService::class.java.simpleName
    private var uuid = UUID.randomUUID().toString()

    val mDAO = XKCDApp.DB_INSTANCE.xkcdDao()

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "START PROCESS uuid -> " + uuid)

        val task = GetComicTask()
        task.execute()

        return Service.START_STICKY
    }


    inner class GetComicTask : AsyncTask<Void, Void, XKCD?>() {

        override fun doInBackground(vararg p0: Void?): XKCD? {

            val repo = APIService.create()
            val response = repo.getXKCD().execute()
            return response.body()
        }

        override fun onPostExecute(result: XKCD?) {
            super.onPostExecute(result)


            if (result != null) {

                Log.d(TAG, "done PROCESS :: " + result.img)

                // run the sentence in a new thread
                Thread(Runnable {
                    mDAO.insertXKCD(result)
                    Log.d(TAG, "item inserted :: " + result.img)
                }).start()

            }
        }

    }
}
