package team.hackm.android.qolk.component.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import butterknife.bindView
import team.hackm.android.qolk.R
import team.hackm.android.qolk.component.details.DetailsActivity
import team.hackm.android.qolk.store.realm.LocalStore
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates

class HomeFragment : Fragment() {

    companion object {
        fun createInstance(): Fragment = HomeFragment()
    }

    val listView: ListView by bindView(R.id.home_list)
    var adapter: WineAdapter by Delegates.notNull()

    @Inject
    lateinit var localStore: LocalStore

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        HomeComponent.Initializer.init(activity).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = WineAdapter(activity)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView.adapter = adapter
        listView.setOnItemClickListener { adapterView, view, i, l ->
            activity.startActivity(DetailsActivity.createIntent(activity, adapter.getItem(i)))
        }
        getData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Timber.d("onActivityResult")
        if (requestCode == HomeActivity.CODE_ADD && resultCode == Activity.RESULT_OK) {
            adapter.clear()
            getData()
        }
    }

    private fun getData() {
        localStore.getList()
                .filter { wines ->
                    wines.isLoaded
                }
                .subscribe { wines ->
                    adapter.addAll(wines.toList())
                }
    }


}