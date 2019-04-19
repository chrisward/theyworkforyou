package de.chrisward.theyworkforyou.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.chrisward.theyworkforyou.R;
import de.chrisward.theyworkforyou.model.Lord;
import de.chrisward.theyworkforyou.view.adapter.LordAdapter;
import de.chrisward.theyworkforyou.viewmodel.LordViewModel;

public class LordListFragment extends Fragment implements LordAdapter.OnLordItemListener {
    private RecyclerView lordRecyclerView;
    private List<Lord> lords;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.person_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() == null) {
            return;
        }

        TextView personListTitle = getActivity().findViewById(R.id.personListTitle);
        personListTitle.setText(R.string.all_lords);

        lordRecyclerView = getActivity().findViewById(R.id.personRecyclerView);
        lordRecyclerView.setHasFixedSize(true);
        lordRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        lordRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        LordViewModel lordViewModel = ViewModelProviders.of(this).get(LordViewModel.class);

        lordViewModel.lordList.observe(this, lords -> {
            this.lords = lords;
            if (getAdapter() != null) {
                getAdapter().replaceAll(lords);
                return;
            }

            lordRecyclerView.setAdapter(
                    new LordAdapter(lords, getLayoutInflater(), this)
            );
        });

        if (savedInstanceState == null) {
            lordViewModel.refreshLords(this);
        }
    }

    private LordAdapter getAdapter() {
        return ((LordAdapter) lordRecyclerView.getAdapter());
    }

    @Override
    public void onLordItemClick(int position) {
        //TODO Handle click.
    }
}
