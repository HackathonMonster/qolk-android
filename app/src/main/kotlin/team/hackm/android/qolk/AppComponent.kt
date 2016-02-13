package team.hackm.android.qolk

import android.app.Application
import dagger.Component
import team.hackm.android.qolk.store.qolk.RemoteStore
import team.hackm.android.qolk.store.realm.LocalStore

@Component(modules = arrayOf(AppModule::class, DataModule::class))
interface AppComponent {

    fun inject(app: DaggerApplication)

    // Provide
    fun application(): Application

    fun localStore(): LocalStore

    fun remoteStore(): RemoteStore

    object Initializer {
        fun init(app: DaggerApplication): AppComponent =
                DaggerAppComponent.builder()
                        .appModule(AppModule(app))
                        .dataModule(DataModule())
                        .build()
    }

}
