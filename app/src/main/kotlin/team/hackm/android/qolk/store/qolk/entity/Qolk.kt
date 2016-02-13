package team.hackm.android.qolk.store.qolk.entity

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by shunhosaka on 2016/02/14.
 */
data class Qolk(
        @SerializedName("_id")
        val id: String,
        val alcohol: Int,
        val humidity: Float,
        val temperature: Float,
        val date: Date,
        @SerializedName("__v")
        val v: Int
)