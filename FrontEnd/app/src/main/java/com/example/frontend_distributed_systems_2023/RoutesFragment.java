package com.example.frontend_distributed_systems_2023;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.io.*;


public class RoutesFragment extends Fragment {

    public RoutesFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_routes, container, false);
        Button btn_rt1 = view.findViewById(R.id.route_1);
        Button btn_rt2 = view.findViewById(R.id.route_2);


        btn_rt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File gpxFile = new File(Environment.getExternalStorageDirectory(), "gpxs/route1.gpx");
                MyThread th1 = null;
                try {
                    th1 = new MyThread(gpxFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                th1.start();
            }
        });
        btn_rt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File gpxFile = new File(Environment.getExternalStorageDirectory(), "gpxs/route4.gpx");
                MyThread th2 = null;
                try {
                    th2 = new MyThread(gpxFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                th2.start();


            }


        });
        return view;
    }


}