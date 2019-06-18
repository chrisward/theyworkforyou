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
import de.chrisward.theyworkforyou.model.Lord
import de.chrisward.theyworkforyou.view.adapter.LordAdapter
import de.chrisward.theyworkforyou.viewmodel.LordViewModel
import javax.inject.Inject

class LordListFragment : Fragment(), LordAdapter.OnLordItemListener {
    private var lordRecyclerView: RecyclerView? = null
    private var lords: List<Lord> = ArrayList()

    private val adapter: LordAdapter?
        get() = lordRecyclerView!!.adapter as LordAdapter?

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
        personListTitle.setText(R.string.all_lords)

        lordRecyclerView = activity!!.findViewById(R.id.personRecyclerView)
        lordRecyclerView!!.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        }

        val lordViewModel = ViewModelProviders.of(this, viewModelFactory).get(LordViewModel::class.java)

        lordViewModel.lordList.observe(this, Observer<List<Lord>> { lords  ->
            this.lords = lords!!
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
