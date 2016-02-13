package team.hackm.android.qolk.component.home

import android.app.Activity
import dagger.Component
import team.hackm.android.qolk.AppComponent
import team.hackm.android.qolk.DaggerApplication

@Component(dependencies = arrayOf(AppComponent::class))
interface HomeComponent {

    fun inject(activity: HomeActivity)

    fun inject(fragment: HomeFragment)

    object Initializer {
        fun init(activity: Activity): HomeComponent =
                DaggerHomeComponent.builder()
                        .appComponent((activity.application as DaggerApplication).appComponent)
                        .build()
    }

}