package team.hackm.android.qolk.component.add

import android.app.Activity
import dagger.Component
import team.hackm.android.qolk.AppComponent
import team.hackm.android.qolk.DaggerApplication

@Component(dependencies = arrayOf(AppComponent::class))
interface AddComponent {

    fun inject(activity: AddActivity)

    fun inject(fragment: AddFragment)

    object Initializer {
        fun init(activity: Activity): AddComponent =
                DaggerAddComponent.builder()
                        .appComponent((activity.application as DaggerApplication).appComponent)
                        .build()
    }
}