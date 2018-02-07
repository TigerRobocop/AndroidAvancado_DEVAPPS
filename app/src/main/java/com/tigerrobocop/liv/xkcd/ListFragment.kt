package com.tigerrobocop.liv.xkcd

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.content.BroadcastReceiver
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.tigerrobocop.liv.xkcd.adapters.XKCDAdapter
import com.tigerrobocop.liv.xkcd.model.XKCD
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v4.view.ViewCompat





class ListFragment : android.support.v4.app.ListFragment() {

    private val TAG = ListFragment::class.java.simpleName

    private val mDAO = XKCDApp.DB_INSTANCE.xkcdDao()

    private lateinit var mAdapter: XKCDAdapter
    private lateinit var mList : ArrayList<XKCD>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        mList = ArrayList()
        setAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var listFragmentView = super.onCreateView(inflater, container, savedInstanceState)

        /* mSwipeRefreshLayout = ListFragmentSwipeRefreshLayout(container!!.context)

        if(mSwipeRefreshLayout != null){

            // Add the list fragment's content view to the SwipeRefreshLayout, making sure that it fills
            // the SwipeRefreshLayout
            mSwipeRefreshLayout?.addView(listFragmentView,
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

            // Make sure that the SwipeRefreshLayout will fill the fragment
            mSwipeRefreshLayout?.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)
        } */

        return listFragmentView
    }

    fun setAdapter() {
        mAdapter = XKCDAdapter(activity, mList)
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
            //startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(xkcd.img)))
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra("itemDetails", xkcd)
            startActivity(intent)
            // todo :: implement list click in new activity
        }
    }

    inner class ServiceReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {

            if(mList != null && mAdapter != null) loadList()
        }
    }




    // TODO :: IMPLEMENT SWIPE TO REFRESH

    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null

    fun setOnRefreshListener(listener: SwipeRefreshLayout.OnRefreshListener) {
        mSwipeRefreshLayout?.setOnRefreshListener(listener)
    }
    private inner class ListFragmentSwipeRefreshLayout(context: Context) : SwipeRefreshLayout(context) {

        /**
         * As mentioned above, we need to override this method to properly signal when a
         * 'swipe-to-refresh' is possible.
         *
         * @return true if the [android.widget.ListView] is visible and can scroll up.
         */
        override fun canChildScrollUp(): Boolean {
            val listView = listView
            return if (listView.visibility == View.VISIBLE) {
                canListViewScrollUp(listView)
            } else {
                false
            }
        }

    }

    // BEGIN_INCLUDE (check_list_can_scroll)
    /**
     * Utility method to check whether a [ListView] can scroll up from it's current position.
     * Handles platform version differences, providing backwards compatible functionality where
     * needed.
     */
    private fun canListViewScrollUp(listView: ListView): Boolean {
        return if (android.os.Build.VERSION.SDK_INT >= 14) {
            // For ICS and above we can call canScrollVertically() to determine this
           true // ViewCompat.canScrollVertically(listView, -1)
        } else {
            // Pre-ICS we need to manually check the first visible item and the child view's top
            // value
            listView.childCount > 0 && (listView.firstVisiblePosition > 0 || listView.getChildAt(0).top < listView.paddingTop)
        }
    }
    // END_INCLUDE (check_list_can_scroll)


}
