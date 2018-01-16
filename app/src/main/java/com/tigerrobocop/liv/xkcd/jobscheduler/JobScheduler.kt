package com.tigerrobocop.liv.xkcd.jobscheduler

import android.content.Context
import android.app.job.JobScheduler
import android.app.job.JobInfo
import android.content.ComponentName
import android.app.job.JobParameters
import android.content.Intent
import android.app.job.JobService
import android.util.Log
import com.tigerrobocop.liv.xkcd.service.RequestService

////http://www.vogella.com/tutorials/AndroidTaskScheduling/article.html
fun scheduleJob (context : Context){

    val serviceComponent = ComponentName(context, InitJobService::class.java)

    val builder = JobInfo.Builder(0, serviceComponent)
    builder.setMinimumLatency((24 * 60 * 60000).toLong()) // wait at least 1 day?
    builder.setOverrideDeadline((60 * 60000).toLong()) // maximum delay 1 hour?
    builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
    //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
    //builder.setRequiresDeviceIdle(true); // device should be idle
    //builder.setRequiresCharging(false); // we don't care if the device is charging or not

    val jobScheduler = context.getSystemService(JobScheduler::class.java)

    jobScheduler.schedule(builder.build())

}

class InitJobService : JobService() {

    override fun onStartJob(params: JobParameters): Boolean {

        Log.d(TAG, "job scheduler started - auto")

        val service = Intent(applicationContext, RequestService::class.java)

        applicationContext.startService(service)

        scheduleJob(applicationContext) // reschedule the job

        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        return true
    }

    companion object {
        private val TAG = "SyncService"
    }

}