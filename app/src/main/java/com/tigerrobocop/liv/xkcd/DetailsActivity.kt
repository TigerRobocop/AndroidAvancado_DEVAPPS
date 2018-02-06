package com.tigerrobocop.liv.xkcd

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tigerrobocop.liv.xkcd.model.XKCD
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    val EXTRA_ITEM_DETAILS = "itemDetails"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent = intent
        val mXKCD = intent.getSerializableExtra(EXTRA_ITEM_DETAILS) as XKCD

        supportActionBar!!.setDisplayShowTitleEnabled(true)
        supportActionBar!!.title = mXKCD.title


        val detailsFragment = DetailsFragment.newInstance(mXKCD)

        supportFragmentManager.beginTransaction()
                .replace(dedetails.id, detailsFragment)
                .commit()



    }
}


