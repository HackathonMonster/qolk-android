package team.hackm.android.qolk.util

import android.widget.ImageView
import com.bumptech.glide.Glide

inline fun ImageView.loadImageUrl(url: String) {
    Glide.with(context).load(url).into(this)
}
