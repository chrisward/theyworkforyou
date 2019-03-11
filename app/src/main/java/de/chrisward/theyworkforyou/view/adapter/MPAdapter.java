package de.chrisward.theyworkforyou.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.chrisward.theyworkforyou.R;
import de.chrisward.theyworkforyou.customview.CircleView;
import de.chrisward.theyworkforyou.helper.ResourceHelper;
import de.chrisward.theyworkforyou.model.MP;

public class MPAdapter extends RecyclerView.Adapter<MPAdapter.PersonRowHolder> {
    private List<MP> mps;
    private final LayoutInflater layoutInflater;
    private final OnMPItemListener personItemListener;

    public MPAdapter(List<MP> mps, LayoutInflater layoutInflater, OnMPItemListener personItemListener) {
        this.mps = mps;
        this.layoutInflater = layoutInflater;
        this.personItemListener = personItemListener;

        setHasStableIds(true);
    }

    @NonNull
    @Override
    public PersonRowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PersonRowHolder(
                layoutInflater.inflate(
                        R.layout.person_list_item,
                        viewGroup,
                        false
                ),
                personItemListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PersonRowHolder holder, int position) {
        holder.bind(mps.get(position));
    }

    @Override
    public long getItemId(int position) {
        return mps.get(position).member_id;
    }

    @Override
    public int getItemCount() {
        return mps == null ? 0 : mps.size();
    }

    public void replaceAll(final List<MP> newProducts) {
        this.mps = newProducts;
        notifyDataSetChanged();
    }

    class PersonRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView party;
        TextView constituency;
        CircleView partyColor;

        OnMPItemListener onMPItemListener;

        PersonRowHolder(View itemView, OnMPItemListener listener) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            party = itemView.findViewById(R.id.party);
            constituency = itemView.findViewById(R.id.constituency);
            partyColor = itemView.findViewById(R.id.partyColorCircle);
            this.onMPItemListener = listener;
        }

        void bind(MP mp) {
            name.setText(mp.name);
            party.setText(mp.party);
            constituency.setText(mp.constituency);
            partyColor.setCircleColor(partyColor.getResources().getColor(ResourceHelper.getPartyColor(mp.party)));
        }

        @Override
        public void onClick(View v) {
            onMPItemListener.onMPItemClick(getAdapterPosition());
        }
    }

    public interface OnMPItemListener {
        void onMPItemClick(int position);
    }
}
