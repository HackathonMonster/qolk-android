package team.hackm.android.qolk

import android.app.Application
import team.hackm.android.qolk.store.realm.LocalStore

interface BaseAppComponent {

    public fun application(): Application

    public fun localStore(): LocalStore

}
