package team.hackm.android.qolk

import timber.log.Timber


public class QolkApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
