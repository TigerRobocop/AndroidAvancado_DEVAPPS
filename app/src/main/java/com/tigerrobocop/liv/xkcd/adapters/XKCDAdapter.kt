package com.tigerrobocop.liv.xkcd.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tigerrobocop.liv.xkcd.R
import com.tigerrobocop.liv.xkcd.model.XKCD
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by Livia on 04/02/2018.
 */
open class XKCDAdapter(context: Context, objects: ArrayList<XKCD>)
    : ArrayAdapter<XKCD>(context, 0, objects) {

    private var vi: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var viewHolder: ViewHolder
        var retView: View

        if (convertView == null) {

            retView = vi.inflate(R.layout.list_item, null)
            viewHolder = ViewHolder()

            viewHolder.mImg = retView?.img_image
            viewHolder.mTxtMediaType = retView?.lbl_img_info
            viewHolder.mTxtTitle = retView?.lbl_title

            retView.tag = viewHolder

        } else {

            viewHolder = convertView.tag as ViewHolder
            retView = convertView
        }

        val item = getItem(position)

        if (item != null) {

            viewHolder.mTxtMediaType?.text = item.day
            viewHolder.mTxtTitle?.text = item.title

            //  viewHolder.mTxtMediaType.setText(item.media_type + " - " + Util.FormatDate(item.date))

            Picasso.with(context).load(item.img).into(viewHolder.mImg)


        }

        return retView
        // return super.getView(position, convertView, parent)


    }


}

internal class ViewHolder {
    var mImg: ImageView? = null
    var mTxtTitle: TextView? = null
    var mTxtMediaType: TextView? = null
}