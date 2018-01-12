package com.tigerrobocop.liv.xkcd.jobscheduler

import android.content.Context
import android.app.job.JobScheduler
import android.app.job.JobInfo
import android.content.ComponentName
import android.app.job.JobParameters
import android.content.Intent
import android.app.job.JobService
import com.tigerrobocop.liv.xkcd.service.RequestService


fun scheduleJob (context : Context){

    val serviceComponent = ComponentName(context, InitJobService::class.java)

    val builder = JobInfo.Builder(0, serviceComponent)
    builder.setMinimumLatency((1 * 1000).toLong()) // wait at least
    builder.setOverrideDeadline((3 * 1000).toLong()) // maximum delay

    //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
    //builder.setRequiresDeviceIdle(true); // device should be idle
    //builder.setRequiresCharging(false); // we don't care if the device is charging or not

    val jobScheduler = context.getSystemService(JobScheduler::class.java)

    jobScheduler.schedule(builder.build())

}

class InitJobService : JobService() {

    override fun onStartJob(params: JobParameters): Boolean {

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