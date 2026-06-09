package com.example.frontend_distributed_systems_2023;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidplot.pie.PieChart;
import com.androidplot.pie.Segment;
import com.androidplot.pie.SegmentFormatter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class StatisticsFragment extends Fragment {

    private PieChart pieGraph;
    public static Map<String,Double> map;
    private double total = 0;
    private double max = Double.MIN_VALUE;
    private Segment fastestSegment = null;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_statistics, container, false);



        if(map!=null){
            // Find the pie graph view
            pieGraph = view.findViewById(R.id.pie_chart);
            // Create the segments
            List<Segment> segments = new ArrayList<>();
            segments.add(new Segment("User1",12.0));
            segments.add(new Segment("User2",14.1));
            segments.add(new Segment("You", map.get("Total time")));
            // Log.e(TAG, "onCreateView: " + map.get("Total time"));

            // Create the formatters
            SegmentFormatter formatter_user_1 = new SegmentFormatter(Color.rgb(18, 20, 58));
            SegmentFormatter formatter_user_2 = new SegmentFormatter(Color.rgb(74, 85, 151));
            SegmentFormatter formatter_user_3 = new SegmentFormatter(Color.rgb(104, 121, 201));

            // Set the formatters for each segment
            formatter_user_1.getLabelPaint().setTypeface(Typeface.DEFAULT_BOLD);
            formatter_user_1.getLabelPaint().setColor(Color.WHITE);
            formatter_user_1.getLabelPaint().setTextSize(getResources().getDimension(R.dimen.pie_value_text_size));
            formatter_user_1.getLabelPaint().setShadowLayer(3, 1, 1, Color.BLACK);
            formatter_user_1.getLabelPaint().setTextAlign(Paint.Align.CENTER);
            formatter_user_1.getFillPaint().setColor(Color.rgb(0, 255, 0));
            formatter_user_1.getFillPaint().setStyle(Paint.Style.FILL);

            formatter_user_2.getLabelPaint().setTypeface(Typeface.DEFAULT_BOLD);
            formatter_user_2.getLabelPaint().setColor(Color.WHITE);
            formatter_user_2.getLabelPaint().setTextSize(getResources().getDimension(R.dimen.pie_value_text_size));
            formatter_user_2.getLabelPaint().setShadowLayer(3, 1, 1, Color.BLACK);
            formatter_user_2.getLabelPaint().setTextAlign(Paint.Align.CENTER);
            formatter_user_2.getFillPaint().setColor(Color.rgb(0, 0, 255));
            formatter_user_2.getFillPaint().setStyle(Paint.Style.FILL);

            formatter_user_3.getLabelPaint().setTypeface(Typeface.DEFAULT_BOLD);
            formatter_user_3.getLabelPaint().setColor(Color.WHITE);
            formatter_user_3.getLabelPaint().setTextSize(getResources().getDimension(R.dimen.pie_value_text_size));
            formatter_user_3.getLabelPaint().setShadowLayer(3, 1, 1, Color.BLACK);
            formatter_user_3.getLabelPaint().setTextAlign(Paint.Align.CENTER);
            formatter_user_3.getFillPaint().setColor(Color.rgb(255, 0, 0));
            formatter_user_3.getFillPaint().setStyle(Paint.Style.FILL);

            // Add the segments to the pie graph


            for(Segment segment:segments) {
                total += (double)segment.getValue();
            }


            for (int i = 0; i < segments.size(); i++) {
                Segment segment = segments.get(i);
                SegmentFormatter formatter=null;

                // Assign the appropriate formatter based on the segment
                if (segment.getTitle().equals("User1")) {
                    formatter = formatter_user_1;
                    if((double)segment.getValue()>max){
                        max = (double) segment.getValue();
                        fastestSegment = segment;
                    }
                } else if (segment.getTitle().equals("User2")) {
                    formatter = formatter_user_2;
                    if((double)segment.getValue()>max){
                        max = (double) segment.getValue();
                        fastestSegment = segment;
                    }
                } else if (segment.getTitle().equals("You")) {
                    formatter = formatter_user_3;
                    if((double)segment.getValue()>max){
                        max = (double) segment.getValue();
                        fastestSegment = segment;
                    }
                }

                if(formatter!=null){
                    double segmentValue =(double) segment.getValue();
                    double percent = (segmentValue/total)*100;
                    //set the segment label as the title and percentage
                    String label = segment.getTitle()+" ("+String.format("%.1f",percent)+"%"+")";
                    segment.setTitle(label);
                    pieGraph.addSegment(segment, formatter);
                }


            }
            if(fastestSegment!=null){
                TextView fastest = view.findViewById(R.id.text_view_faster);
                fastest.setVisibility(View.VISIBLE);
                fastest.setText(fastestSegment.getTitle()+" is faster than the others!");
            }
        }

        return view;
    }
    public void setMapInStatistics(Map<String,Double>map){
        this.map = map;
    }

}