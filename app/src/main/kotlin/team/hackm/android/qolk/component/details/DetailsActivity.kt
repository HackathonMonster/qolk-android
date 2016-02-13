package team.hackm.android.qolk.component.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import team.hackm.android.qolk.R
import team.hackm.android.qolk.util.setContentFragment

public class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        actionBar.hide()
        setContentFragment(R.id.details_layout_container, DetailsFragment.createInstance())
    }
}