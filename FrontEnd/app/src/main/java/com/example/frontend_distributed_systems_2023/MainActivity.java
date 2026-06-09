package com.example.frontend_distributed_systems_2023;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private Button toSbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText name = findViewById(R.id.firstName);
        EditText lastname = findViewById(R.id.lastName);
        EditText birth = findViewById(R.id.birthDate);
        EditText gender = findViewById(R.id.gender);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toSbutton = findViewById(R.id.startButton);
        toSbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //gets the values from EditText fields
            String getName = name.getText().toString();
            String getLastName = lastname.getText().toString();
            String getBirthday = birth.getText().toString();
            String getGender = gender.getText().toString();
            //stores the values in shared preferences
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("firstName", getName);
            editor.putString("lastName", getLastName);
            editor.putString("birthDate", getBirthday);
            editor.putString("gender", getGender);
            editor.apply();
            //starts the start activity
            Intent intent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(intent);
            }
        });
    }
}


