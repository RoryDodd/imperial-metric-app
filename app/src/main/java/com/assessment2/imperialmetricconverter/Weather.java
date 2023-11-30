package com.assessment2.imperialmetricconverter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Weather extends Activity implements View.OnClickListener {

    Button exit, show;
    TextView title, tempC, tempF, location;
    EditText input;
    View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_page);

        exit = findViewById(R.id.exit);
        exit.setOnClickListener(this);

        show = findViewById(R.id.show);
        show.setOnClickListener(this);

        title = findViewById(R.id.title);
        title.setGravity(Gravity.CENTER_VERTICAL);

        location = findViewById(R.id.location);
        location.setGravity(Gravity.CENTER_VERTICAL);

        tempC = findViewById(R.id.tempC);
        tempC.setGravity(Gravity.CENTER_VERTICAL);

        tempF = findViewById(R.id.tempF);
        tempF.setGravity(Gravity.CENTER_VERTICAL);

        input = findViewById(R.id.input);
        input.setGravity(Gravity.CENTER_VERTICAL);

        root = findViewById(R.id.root);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (id == R.id.exit) {
            finish();
        }

        if (id == R.id.show) {
            if (input.getText().toString().isEmpty()){
                Toast.makeText(this,"Enter A Location!", Toast.LENGTH_SHORT).show();
            } else {
                @SuppressLint("StaticFieldLeak") WeatherApiCall api = new WeatherApiCall() {
                    @Override
                    protected void onPostExecute(WeatherData weatherData) {
                        if (weatherData != null) {
                            String loc = weatherData.getLocation();
                            double temp_c = weatherData.getTempC();
                            double temp_f = weatherData.getTempF();
                            String type = weatherData.getType();

                            location.setText(loc);
                            tempC.setText(temp_c + " C");
                            tempF.setText(temp_f + " F");

                            if (type.equals("Cloudy")) {
                                root.setBackgroundResource(R.drawable.cloudy);
                            } else if (type.equals("Sunny")) {
                                root.setBackgroundResource(R.drawable.sunny);
                            } else if (type.equals("Clear")){
                                root.setBackgroundResource(R.drawable.clear);
                            } else {
                                root.setBackgroundResource(R.drawable.background);
                            }
                        }
                    }
                };
                api.execute(input.getText().toString());
            }
        }
        imm.hideSoftInputFromWindow(location.getWindowToken(), 0);
    }
}
