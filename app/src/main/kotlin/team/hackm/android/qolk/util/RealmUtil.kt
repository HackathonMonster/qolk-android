package com.hosshan.android.salad.util

import io.realm.Realm
import io.realm.RealmObject

/**
 * Created by shunhosaka on 2015/12/20.
 */
public object RealmUtil {
    public fun  <T : RealmObject> autoIncrementId(realm: Realm, clazz: Class<T>): Long =
            autoIncrementIdWithName(realm, clazz, "id")

    public fun <T : RealmObject> autoIncrementIdWithName(realm: Realm, clazz: Class<T>, idName: String): Long =
            realm.where(clazz).max(idName).toLong() + 1

}
