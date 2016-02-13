package team.hackm.android.qolk.component.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import team.hackm.android.qolk.R
import team.hackm.android.qolk.store.realm.entity.Wine
import timber.log.Timber
import java.text.SimpleDateFormat

class WineAdapter(context: Context) : ArrayAdapter<Wine>(context, R.layout.item_wine) {

    companion object {
        val DATE_FORMAT = SimpleDateFormat("yyyy/MM/DD")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val viewHolder: ViewHolder
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_wine, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val wine = getItem(position)
        viewHolder.apply {
            Glide.with(context)
                    .load(wine.image)
                    .error(R.mipmap.img_add_noimg)
                    .placeholder(R.mipmap.img_add_noimg)
                    .into(imageView)
            Timber.d(wine.image)
            nameTextView.text = wine.name
            dateTextView.text = DATE_FORMAT.format(wine.date)
        }
        return view
    }

    class ViewHolder(view: View) {
        val imageView: ImageView
        val nameTextView: TextView
        val dateTextView: TextView

        init {
            imageView = view.findViewById(R.id.item_wine_image) as ImageView
            nameTextView = view.findViewById(R.id.item_wine_text_name) as TextView
            dateTextView = view.findViewById(R.id.item_wine_text_date) as TextView
        }

    }

}