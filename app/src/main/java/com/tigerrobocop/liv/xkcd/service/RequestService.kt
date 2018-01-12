package com.tigerrobocop.liv.xkcd.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.AsyncTask.execute
import android.util.Log
import java.util.*
import java.util.UUID.randomUUID
import android.content.ContentValues.TAG
import android.os.AsyncTask





class RequestService : Service() {

    // tutorial source: http://www.techotopia.com/index.php/Android_Started_Service_%E2%80%93_A_Kotlin_Example
    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        throw UnsupportedOperationException("Not yet implemented")
    }


    private val TAG = RequestService::class.java.simpleName
    private var uuid = UUID.randomUUID().toString()



    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "START PROCESS uuid -> " + uuid)

        val task = GetComicTask ()
        task.execute()

        //Log.d(TAG, "START PROCESS")

        return Service.START_STICKY
    }


    inner class GetComicTask : AsyncTask<String, String, String>() {


        override fun doInBackground(vararg p0: String?): String {

            var result = ""

            Log.d(TAG, "done PROCESS + " + uuid)
            return result
        }

        /*

      override fun onPreExecute() {
          super.onPreExecute()
         // MyprogressBar.visibility = View.VISIBLE;
      }

      override fun onPostExecute(result: String?) {
          super.onPostExecute(result)
      }

      */


    }
}
