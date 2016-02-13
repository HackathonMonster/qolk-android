package team.hackm.android.qolk.component.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import team.hackm.android.qolk.R

class DetailsFragment : Fragment() {

    companion object {
        fun createInstance(): DetailsFragment = DetailsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_details, container, false)
    }
}