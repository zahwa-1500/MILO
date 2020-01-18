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
import com.appsnipp.milo.adapter.PopularAdapter;
import com.appsnipp.milo.model.Popular;
import com.appsnipp.milo.viewmodel.PopularViewModel;

import java.util.ArrayList;


public class PopularFragment extends Fragment {
    private PopularAdapter adapter;
    private ProgressBar progressBar;
    private PopularViewModel PopularViewModel;

    public PopularFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new PopularAdapter();
        View view = inflater.inflate(R.layout.fragment_popular,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_Popular);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progress_list);

        PopularViewModel = ViewModelProviders.of(this).get(PopularViewModel.class);
        PopularViewModel.getPopular().observe(this, getPopular);
        PopularViewModel.setPopular("EXTRA_MOVIE");

        showLoading(true);

        return view;
    }
    private Observer<ArrayList<Popular>> getPopular = new Observer<ArrayList<Popular>>() {
        @Override
        public void onChanged(ArrayList<Popular> popular) {
            if (popular != null) {
                adapter.setData(popular);
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
