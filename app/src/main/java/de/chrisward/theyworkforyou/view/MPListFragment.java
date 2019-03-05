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

import java.util.List;

import de.chrisward.theyworkforyou.R;
import de.chrisward.theyworkforyou.model.MP;
import de.chrisward.theyworkforyou.view.adapter.MPAdapter;
import de.chrisward.theyworkforyou.viewmodel.MPViewModel;

public class MPListFragment extends Fragment implements MPAdapter.OnMPItemListener {
    private RecyclerView mpRecyclerView;
    private List<MP> mps;

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

        mpRecyclerView = getActivity().findViewById(R.id.personRecyclerView);
        mpRecyclerView.setHasFixedSize(true);
        mpRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mpRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        MPViewModel mpViewModel = ViewModelProviders.of(this).get(MPViewModel.class);

        mpViewModel.mpList.observe(this, mps -> {
            this.mps = mps;
            if (getAdapter() != null) {
                getAdapter().replaceAll(mps);
                return;
            }

            mpRecyclerView.setAdapter(
                    new MPAdapter(mps, getLayoutInflater(), this)
            );
        });

        if (savedInstanceState == null) {
            mpViewModel.refreshMPs(this);
        }
    }

    private MPAdapter getAdapter() {
        return ((MPAdapter) mpRecyclerView.getAdapter());
    }

    @Override
    public void onMPItemClick(int position) {
        // TODO: deal with the click.
    }
}
