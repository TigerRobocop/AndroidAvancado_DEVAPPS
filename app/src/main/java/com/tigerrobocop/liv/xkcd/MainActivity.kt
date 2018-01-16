package com.tigerrobocop.liv.xkcd

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tigerrobocop.liv.xkcd.jobscheduler.InitJobService
import com.tigerrobocop.liv.xkcd.jobscheduler.scheduleJob
import com.tigerrobocop.liv.xkcd.service.RequestService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    // TODO sugerido :: ALARM > BROADCAST > SERVICE > ROOM

    // todo que vou tentar fazer :: Broadcast receiver > job scheduler > service > room
    // possivel melhoria: implemetar um forceupdate com swipe refresh



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTrigger.setOnClickListener {

            Log.d(MainActivity::class.java.simpleName, "service triggered manually")
            val service = Intent(applicationContext, RequestService::class.java)

            applicationContext.startService(service)

            scheduleJob(applicationContext) // reschedule the job

        }
    }
}
