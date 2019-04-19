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
import de.chrisward.theyworkforyou.model.Lord;

public class LordAdapter extends RecyclerView.Adapter<LordAdapter.LordViewHolder> {
    private List<Lord> lords;
    private final LayoutInflater layoutInflater;
    private final OnLordItemListener lordItemListener;

    public LordAdapter(List<Lord> lords, LayoutInflater layoutInflater, OnLordItemListener lordItemListener) {
        this.lords = lords;
        this.layoutInflater = layoutInflater;
        this.lordItemListener = lordItemListener;

        setHasStableIds(true);
    }

    @NonNull
    @Override
    public LordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LordViewHolder(
                layoutInflater.inflate(
                        R.layout.person_list_item,
                        viewGroup,
                        false
                ),
                lordItemListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull LordViewHolder holder, int position) {
        holder.bind(lords.get(position));
    }

    @Override
    public int getItemCount() {
        return lords.size();
    }

    public void replaceAll(List<Lord> newList) {
        //TODO: Use diffutil.
        lords = newList;
        notifyDataSetChanged();
    }

    class LordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView party;
        CircleView partyColor;

        OnLordItemListener onLordItemListener;

        LordViewHolder(@NonNull View itemView, OnLordItemListener listener) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            party = itemView.findViewById(R.id.party);
            partyColor = itemView.findViewById(R.id.partyColorCircle);
            this.onLordItemListener = listener;
        }

        void bind(Lord lord) {
            name.setText(lord.name);
            party.setText(lord.party);
            partyColor.setCircleColor(partyColor.getResources().getColor(ResourceHelper.getPartyColor(lord.party)));
        }

        @Override
        public void onClick(View v) {
            lordItemListener.onLordItemClick(getAdapterPosition());
        }
    }

    public interface OnLordItemListener {
        void onLordItemClick(int position);
    }
}
