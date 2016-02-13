package team.hackm.android.qolk.store.realm

import io.realm.Realm
import team.hackm.android.qolk.store.realm.entity.Wine
import java.util.*

public class LocalStore(val realm: Realm) {

    public fun add(name: String, deviceId: String, image: String?) {
        realm.use {
            it.executeTransaction {
                val wine: Wine = realm.createObject(Wine::class.java)
                wine.name = name
                wine.deviceId = deviceId
                wine.date = Date()
                wine.image = image
            }
        }
    }

    public fun getList() =
            realm.where(Wine::class.java).findAllAsync().asObservable()

    public fun getItem(name: String, date: Date) =
        realm.where(Wine::class.java).equalTo("name", name).equalTo("date", date).findAllAsync().asObservable()

}
