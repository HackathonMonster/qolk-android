package team.hackm.android.qolk.component.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import team.hackm.android.qolk.R
import team.hackm.android.qolk.store.realm.entity.Wine
import team.hackm.android.qolk.util.setContentFragment

public class DetailsActivity : AppCompatActivity() {

    companion object {
        val KEY_NAME: String = "key_name"
        val KEY_DATE: String = "key_date"

        public fun createIntent(context: Context, wine: Wine) =
                Intent(context, DetailsActivity::class.java).apply {
                    putExtra(KEY_NAME, wine.name)
                    putExtra(KEY_DATE, wine.date?.time ?: 0)
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setContentFragment(R.id.details_layout_container, DetailsFragment.createInstance(
                intent.getStringExtra(KEY_NAME),
                intent.getLongExtra(KEY_DATE, 0)
        ))
    }
}