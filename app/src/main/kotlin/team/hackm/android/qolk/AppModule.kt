package team.hackm.android.qolk

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: DaggerApplication) {

    @Provides
    fun provideApplication(): Application = app
}
