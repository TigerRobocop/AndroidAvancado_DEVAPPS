package com.tigerrobocop.liv.xkcd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {


    // TODO sugerido :: ALARM > BROADCAST > SERVICE > ROOM

    // todo que vou tentar fazer :: Broadcast receiver > job scheduler > service > room
    // possivel melhoria: implemetar um forceupdate com swipe refresh



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
