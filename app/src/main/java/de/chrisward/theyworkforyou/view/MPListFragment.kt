package de.chrisward.theyworkforyou.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import de.chrisward.theyworkforyou.R
import de.chrisward.theyworkforyou.model.MP
import de.chrisward.theyworkforyou.view.adapter.MPAdapter
import de.chrisward.theyworkforyou.viewmodel.MPViewModel

class MPListFragment : Fragment(), MPAdapter.OnMPItemListener {
    private var mpRecyclerView: RecyclerView? = null
    private var mps: List<MP> = ArrayList()

    private val adapter: MPAdapter?
        get() = mpRecyclerView!!.adapter as MPAdapter?

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

        val personListTitle = activity!!.findViewById<TextView>(R.id.personListTitle)
        personListTitle.setText(R.string.all_mps)

        mpRecyclerView = activity!!.findViewById(R.id.personRecyclerView)
        mpRecyclerView!!.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        }

        val mpViewModel = ViewModelProviders.of(this).get<MPViewModel>(MPViewModel::class.java)

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
