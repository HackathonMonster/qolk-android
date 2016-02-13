package team.hackm.android.qolk

import dagger.Component

@Component(modules = arrayOf(AppModule::class, DataModule::class))
interface AppComponent : BaseAppComponent {

    fun inject(app: DaggerApplication)

    object Initializer {
        fun init(app: DaggerApplication): AppComponent =
                DaggerAppComponent.builder()
                        .appModule(AppModule(app))
                        .dataModule(DataModule())
                        .build()
    }

}
