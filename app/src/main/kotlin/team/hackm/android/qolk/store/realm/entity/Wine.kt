package team.hackm.android.qolk.store.realm.entity

import io.realm.RealmObject
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass
public open class Wine(
        public open var name: String? = null,
        public open var deviceId: String? = null,
        public open var date: Date = Date(),
        public open var image: String? = null
) : RealmObject()