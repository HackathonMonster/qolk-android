package team.hackm.android.qolk.component.details

import android.app.Activity
import dagger.Component
import team.hackm.android.qolk.AppComponent
import team.hackm.android.qolk.DaggerApplication

@Component(dependencies = arrayOf(AppComponent::class))
interface DetailsComponent {

    fun inject(activity: DetailsActivity)

    fun inject(fragment: DetailsFragment)

    object Initializer {
        fun init(activity: Activity): DetailsComponent =
                DaggerDetailsComponent.builder()
                        .appComponent((activity.application as DaggerApplication).appComponent)
                        .build()
    }

}