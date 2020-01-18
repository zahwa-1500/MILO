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
import com.appsnipp.milo.adapter.NowPlayingAdapter;
import com.appsnipp.milo.model.NowPlaying;
import com.appsnipp.milo.viewmodel.NowPlayingViewModel;


import java.util.ArrayList;


public class NowPlayingFragment extends Fragment {
    private NowPlayingAdapter adapter;
    private ProgressBar progressBar;
    private NowPlayingViewModel NowPlayingViewModel;

    public NowPlayingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new NowPlayingAdapter();
        View view = inflater.inflate(R.layout.fragment_now_playing,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_NowPlaying);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progress_list);

        NowPlayingViewModel = ViewModelProviders.of(this).get(NowPlayingViewModel.class);
        NowPlayingViewModel.getNowPlaying().observe(this, getNowPlaying);
        NowPlayingViewModel.setNowPlaying("EXTRA_MOVIE");

        showLoading(true);

        return view;
    }
    private Observer<ArrayList<NowPlaying>> getNowPlaying = new Observer<ArrayList<NowPlaying>>() {
        @Override
        public void onChanged(ArrayList<NowPlaying> nowplaying) {
            if (nowplaying != null) {
                adapter.setData(nowplaying);
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
