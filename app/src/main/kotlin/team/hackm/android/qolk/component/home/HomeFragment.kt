package team.hackm.android.qolk.component.home

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import butterknife.bindView
import team.hackm.android.qolk.R
import team.hackm.android.qolk.component.WineAdapter
import team.hackm.android.qolk.component.details.DetailsActivity
import kotlin.properties.Delegates

class HomeFragment : Fragment() {

    companion object {
        fun createInstance(): Fragment = HomeFragment()
    }

    val listView: ListView by bindView(R.id.home_list)
    var wineAdapter: WineAdapter by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wineAdapter = WineAdapter(activity)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView.adapter = wineAdapter
        listView.setOnItemClickListener { adapterView, view, i, l ->
            activity.startActivity(Intent(activity, DetailsActivity::class.java))
        }
    }
}