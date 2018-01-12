package com.tigerrobocop.liv.xkcd.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import com.tigerrobocop.liv.xkcd.jobscheduler.scheduleJob

/**
 * Created by Livia on 11/01/2018.
 */
class BootReceiver : BroadcastReceiver()  {

    override fun onReceive(context: Context, intent: Intent) {
        scheduleJob(context) // schedule job
    }

}