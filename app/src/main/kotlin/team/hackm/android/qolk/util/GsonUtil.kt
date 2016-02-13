package com.hosshan.android.salad.util

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.realm.RealmObject

/**
 * Created by a40875 on 2015/09/10.
 */
public object GsonUtil {
    public fun getInstance(): Gson {
        val builder: GsonBuilder = GsonBuilder()
        builder.setDateFormat("EEE, d MMM yyyy HH:mm:ss Z")
        builder.setExclusionStrategies(object : ExclusionStrategy {
            override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                return clazz?.declaringClass?.equals(RealmObject::class) ?: false
            }
            override fun shouldSkipField(f: FieldAttributes?): Boolean {
                return false
            }
        })
        return builder.create()
    }
}
