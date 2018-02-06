package com.tigerrobocop.liv.xkcd

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tigerrobocop.liv.xkcd.model.XKCD
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : android.support.v4.app.Fragment() {


    val EXTRA_ITEM_DETAILS = "itemDetails"


    internal var mImg: ImageView? = null


    var mXKCD : XKCD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mXKCD = arguments.getSerializable(EXTRA_ITEM_DETAILS) as XKCD
          //  Picasso.with(context).load(mXKCD?.img).into(img_details)
            setHasOptionsMenu(true)
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_details, container, false)

        var teste = ""
       // Picasso.with(context).load(mXKCD?.img).into(img_details)

        return view
    }







    companion object {
        val EXTRA_ITEM_DETAILS = "itemDetails"
        val TAG_DETAILS = "itemDetails"

        fun newInstance(xkcd: XKCD): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()


            args.putSerializable(EXTRA_ITEM_DETAILS, xkcd)
            fragment.arguments = args

            return fragment
        }
    }
}
