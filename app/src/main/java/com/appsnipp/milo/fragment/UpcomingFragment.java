package com.appsnipp.milo.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.appsnipp.milo.R;
import com.appsnipp.milo.adapter.UpcomingAdapter;
import com.appsnipp.milo.model.Upcoming;
import com.appsnipp.milo.viewmodel.UpcomingViewModel;

import java.util.ArrayList;


public class UpcomingFragment extends Fragment {
    private UpcomingAdapter adapter;
    private ProgressBar progressBar;
    private UpcomingViewModel UpcomingViewModel;

    public UpcomingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new UpcomingAdapter();
        View view = inflater.inflate(R.layout.fragment_upcoming,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_Upcoming);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progress_list);

        UpcomingViewModel = ViewModelProviders.of(this).get(UpcomingViewModel.class);
        UpcomingViewModel.getUpcoming().observe(this, getUpcoming);
        UpcomingViewModel.setUpcoming("EXTRA_MOVIE");

        showLoading(true);

        return view;
    }
    private Observer<ArrayList<Upcoming>> getUpcoming = new Observer<ArrayList<Upcoming>>() {
        @Override
        public void onChanged(ArrayList<Upcoming> upcoming) {
            if (upcoming != null) {
                adapter.setData(upcoming);
            }

            showLoading(false);

        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
