package team.hackm.android.qolk.component.home

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import butterknife.bindView
import team.hackm.android.qolk.R
import team.hackm.android.qolk.component.add.AddActivity
import team.hackm.android.qolk.util.setContentFragment

public class HomeActivity : AppCompatActivity() {

    val toolbar: Toolbar by bindView(R.id.toolbar)
    val floatingActionButton: FloatingActionButton by bindView(R.id.home_fab)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        actionBar.hide()
        toolbar.setTitle(R.string.app_name)

        floatingActionButton.setOnClickListener { view ->
            startActivity(Intent(AppCompatActivity@this, AddActivity::class.java))
        }

        setContentFragment(R.id.home_layout_container, HomeFragment.createInstance())
    }

}
