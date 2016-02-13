package team.hackm.android.qolk.component.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import butterknife.bindView
import team.hackm.android.qolk.R
import team.hackm.android.qolk.component.add.AddActivity
import team.hackm.android.qolk.util.setContentFragment

public class HomeActivity : AppCompatActivity() {

    companion object {
        val CODE_ADD: Int = 0
        public fun createIntent(context: Context) =
                Intent(context, HomeActivity::class.java)
    }

    val toolbar: Toolbar by bindView(R.id.toolbar)
    val floatingActionButton: FloatingActionButton by bindView(R.id.home_fab)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        floatingActionButton.setOnClickListener { view ->
            startActivityForResult(Intent(AppCompatActivity@this, AddActivity::class.java), CODE_ADD)
        }

        setContentFragment(R.id.home_layout_container, HomeFragment.createInstance())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        supportFragmentManager?.findFragmentById(R.id.home_layout_container)?.onActivityResult(requestCode, resultCode, data)
    }
}
