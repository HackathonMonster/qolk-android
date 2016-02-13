package team.hackm.android.qolk.component.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import team.hackm.android.qolk.R

public class HomeFragment : Fragment() {

    companion object {
        fun createInstance(): Fragment = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }
}