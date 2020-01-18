package com.appsnipp.milo.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.appsnipp.milo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Button Button1, Button2;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View i = inflater.inflate(R.layout.fragment_home,container,false);

        Button1 = i.findViewById(R.id.button1);//get id of button 1
        Button2 = i.findViewById(R.id.button2);//get id of button 2

        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpcomingFragment upcomingFragment= new UpcomingFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_layout, upcomingFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NowPlayingFragment nowPlayingFragment= new NowPlayingFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_layout, nowPlayingFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return i;

    }

}
