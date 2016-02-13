package team.hackm.android.qolk.component.add

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import butterknife.bindView
import team.hackm.android.qolk.R
import team.hackm.android.qolk.util.setContentFragment

/**
 * Created by shunhosaka on 2016/02/13.
 */
public class AddActivity : AppCompatActivity() {

    val toolbar: Toolbar by bindView(R.id.toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        actionBar.hide()
        toolbar.setTitle(R.string.title_add)
        setContentFragment(R.id.add_layout_container, AddFragment.createInstance())
    }
}