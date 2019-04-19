package de.chrisward.theyworkforyou.view.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import de.chrisward.theyworkforyou.R
import de.chrisward.theyworkforyou.customview.CircleView
import de.chrisward.theyworkforyou.helper.ResourceHelper
import de.chrisward.theyworkforyou.model.MP

class MPAdapter(private var mps: List<MP>?, private val layoutInflater: LayoutInflater, private val personItemListener: OnMPItemListener) : RecyclerView.Adapter<MPAdapter.PersonRowHolder>() {

    init {

        setHasStableIds(true)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PersonRowHolder {
        return PersonRowHolder(
                layoutInflater.inflate(
                        R.layout.person_list_item,
                        viewGroup,
                        false
                ),
                personItemListener
        )
    }

    override fun onBindViewHolder(holder: PersonRowHolder, position: Int) {
        holder.bind(mps!![position])
    }

    override fun getItemId(position: Int): Long {
        return mps!![position].member_id.toLong()
    }

    override fun getItemCount(): Int {
        return if (mps == null) 0 else mps!!.size
    }

    fun replaceAll(newProducts: List<MP>?) {
        this.mps = newProducts
        notifyDataSetChanged()
    }

    class PersonRowHolder(itemView: View, var onMPItemListener: OnMPItemListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var name: TextView
        var party: TextView
        var constituency: TextView
        var partyColor: CircleView

        init {

            name = itemView.findViewById(R.id.name)
            party = itemView.findViewById(R.id.party)
            constituency = itemView.findViewById(R.id.constituency)
            partyColor = itemView.findViewById(R.id.partyColorCircle)
        }

        fun bind(mp: MP) {
            name.text = mp.name
            party.text = mp.party
            constituency.text = mp.constituency
            partyColor.circleColor = ContextCompat.getColor(partyColor.context, ResourceHelper.getPartyColor(mp.party))
        }

        override fun onClick(v: View) {
            onMPItemListener.onMPItemClick(adapterPosition)
        }
    }

    interface OnMPItemListener {
        fun onMPItemClick(position: Int)
    }
}
