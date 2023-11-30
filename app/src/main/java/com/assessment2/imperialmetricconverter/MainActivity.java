package com.assessment2.imperialmetricconverter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    //Global variables
    Button exit,measurements,currency,weather;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exit = findViewById(R.id.exit);
        exit.setOnClickListener(this);

        measurements = findViewById(R.id.measurementsPage);
        measurements.setOnClickListener(this);

        currency = findViewById(R.id.currencyPage);
        currency.setOnClickListener(this);

        weather = findViewById(R.id.weatherPage);
        weather.setOnClickListener(this);

        title = findViewById(R.id.title);
        title.setGravity(Gravity.CENTER_VERTICAL);

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.exit) {
            finishAffinity();
        }
        if (id == R.id.currencyPage) {
            Intent currency = new Intent(MainActivity.this,CurrencyPage.class);
            startActivity(currency);
        }
        if (id == R.id.measurementsPage){
            Intent measure = new Intent(MainActivity.this, MeasurementsPage.class);
            startActivity(measure);
        }
        if(id == R.id.weatherPage){
            Intent weather = new Intent(MainActivity.this, Weather.class);
            startActivity(weather);
        }

    }
}