package de.chrisward.theyworkforyou.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection

import de.chrisward.theyworkforyou.R
import de.chrisward.theyworkforyou.model.MP
import de.chrisward.theyworkforyou.view.adapter.MPAdapter
import de.chrisward.theyworkforyou.viewmodel.MPViewModel
import javax.inject.Inject

class MPListFragment : Fragment(), MPAdapter.OnMPItemListener {
    private var mpRecyclerView: RecyclerView? = null
    private var mps: List<MP> = ArrayList()

    private val adapter: MPAdapter?
        get() = mpRecyclerView!!.adapter as MPAdapter?

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.person_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)

        val personListTitle = activity!!.findViewById<TextView>(R.id.personListTitle)
        personListTitle.setText(R.string.all_mps)

        mpRecyclerView = activity!!.findViewById(R.id.personRecyclerView)
        mpRecyclerView!!.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        }

        val mpViewModel = ViewModelProviders.of(this, viewModelFactory).get(MPViewModel::class.java)

        mpViewModel.mpList.observe(this, Observer<List<MP>> { mps ->
            this.mps = mps!!
            if (adapter != null) {
                adapter!!.replaceAll(mps)
            } else {
                mpRecyclerView!!.adapter = MPAdapter(mps, layoutInflater, this)
            }
        })

        if (savedInstanceState == null) {
            mpViewModel.refreshMPs(this)
        }
    }

    override fun onMPItemClick(position: Int) {
        // TODO: deal with the click.
    }
}
