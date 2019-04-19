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
import de.chrisward.theyworkforyou.model.Lord
import de.chrisward.theyworkforyou.view.adapter.LordAdapter
import de.chrisward.theyworkforyou.viewmodel.LordViewModel

class LordListFragment : Fragment(), LordAdapter.OnLordItemListener {
    private var lordRecyclerView: RecyclerView? = null
    private var lords: List<Lord>? = null

    private val adapter: LordAdapter?
        get() = lordRecyclerView!!.adapter as LordAdapter?

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

        if (activity == null) {
            return
        }

        val personListTitle = activity!!.findViewById<TextView>(R.id.personListTitle)
        personListTitle.setText(R.string.all_lords)

        lordRecyclerView = activity!!.findViewById(R.id.personRecyclerView)
        lordRecyclerView!!.setHasFixedSize(true)
        lordRecyclerView!!.addItemDecoration(DividerItemDecoration(activity!!, LinearLayoutManager.VERTICAL))
        lordRecyclerView!!.layoutManager = LinearLayoutManager(activity)

        val lordViewModel = ViewModelProviders.of(this).get<LordViewModel>(LordViewModel::class.java)

        lordViewModel.lordList.observe(this, Observer<List<Lord>?> { lords  ->
            this.lords = lords
            if (adapter != null) {
                adapter!!.replaceAll(lords)
            } else {
                lordRecyclerView!!.adapter = LordAdapter(lords, layoutInflater, this)
            }
        })

        if (savedInstanceState == null) {
            lordViewModel.refreshLords(this)
        }
    }

    override fun onLordItemClick(position: Int) {
        //TODO Handle click.
    }
}
