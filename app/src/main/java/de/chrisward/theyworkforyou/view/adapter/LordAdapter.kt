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
import de.chrisward.theyworkforyou.model.Lord

class LordAdapter(private var lords: List<Lord>, private val layoutInflater: LayoutInflater, private val lordItemListener: OnLordItemListener) : RecyclerView.Adapter<LordAdapter.LordViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): LordViewHolder {
        return LordViewHolder(
                layoutInflater.inflate(
                        R.layout.person_list_item,
                        viewGroup,
                        false
                ),
                lordItemListener
        )
    }

    override fun onBindViewHolder(holder: LordViewHolder, position: Int) {
        holder.bind(lords[position])
    }

    override fun getItemCount(): Int = lords.size

    fun replaceAll(newList: List<Lord>) {
        //TODO: Use diffutil.
        lords = newList
        notifyDataSetChanged()
    }

    class LordViewHolder(itemView: View, private val onLordItemListener: OnLordItemListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val name: TextView = itemView.findViewById(R.id.name)
        val party: TextView = itemView.findViewById(R.id.party)
        private val partyColor: CircleView = itemView.findViewById(R.id.partyColorCircle)

        fun bind(lord: Lord) {
            name.text = lord.name
            party.text = lord.party
            partyColor.circleColor = ContextCompat.getColor(partyColor.context, ResourceHelper.getPartyColor(lord.party))
        }

        override fun onClick(v: View) {
            onLordItemListener.onLordItemClick(adapterPosition)
        }
    }

    interface OnLordItemListener {
        fun onLordItemClick(position: Int)
    }
}
