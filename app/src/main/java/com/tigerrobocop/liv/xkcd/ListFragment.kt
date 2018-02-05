package com.tigerrobocop.liv.xkcd

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.tigerrobocop.liv.xkcd.R.id.list_item
import com.tigerrobocop.liv.xkcd.adapters.XKCDAdapter
import com.tigerrobocop.liv.xkcd.model.XKCD


class ListFragment : android.support.v4.app.ListFragment() {

    private val TAG = ListFragment::class.java.simpleName

    val mDAO = XKCDApp.DB_INSTANCE.xkcdDao()

    lateinit var mAdapter: XKCDAdapter
    lateinit var mList : ArrayList<XKCD>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        mList = ArrayList<XKCD>()
        setAdapter()
    }

    private fun setAdapter() {
        mAdapter = XKCDAdapter(activity, list_item, mList)
        listAdapter = mAdapter
    }

    fun loadList(){
        mList.clear()

        // run the sentence in a new thread
        Thread(Runnable {
            mList.addAll(mDAO.getAllXKCD())
            Log.d(TAG, "total items :: " + mList.count())

            mAdapter.notifyDataSetChanged()

        }).start()
    }

    override fun onResume() {
        super.onResume()
        loadList()
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)

        if(l != null){
            val xkcd = l.getItemAtPosition(position) as XKCD
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(xkcd.img)))
            // todo :: implement list clict in new activity
        }

    }



}
