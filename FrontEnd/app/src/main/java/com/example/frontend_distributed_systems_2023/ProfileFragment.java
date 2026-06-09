package com.example.frontend_distributed_systems_2023;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflates the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        // Retrieves the values using the keys
        String firstName = sharedPreferences.getString("firstName", "");
        String lastName = sharedPreferences.getString("lastName", "");
        String birthDate = sharedPreferences.getString("birthDate", "");
        String gender = sharedPreferences.getString("gender", "");
        TextView firstNameTextView = view.findViewById(R.id.first_name_text_view);
        firstNameTextView.setText(firstName);

        TextView lastNameTextView = view.findViewById(R.id.last_name_text_view);
        lastNameTextView.setText(lastName);

        TextView birthDateTextView = view.findViewById(R.id.birth_date_text_view);
        birthDateTextView.setText(birthDate);

        TextView genderTextView = view.findViewById(R.id.gender_text_view);
        genderTextView.setText(gender);
        return view;

    }
}