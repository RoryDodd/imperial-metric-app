package com.assessment2.imperialmetricconverter;

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

public class MeasurementsPage extends Activity implements View.OnClickListener{

    Button exit,miles,km,gallons,liters,pounds,kg,fahrenheit,celsius;
    EditText input;
    TextView title,valueDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.measurements_page);

        exit = findViewById(R.id.exit);
        exit.setOnClickListener(this);

        miles = findViewById(R.id.miles);
        miles.setOnClickListener(this);

        km = findViewById(R.id.km);
        km.setOnClickListener(this);

        gallons = findViewById(R.id.gallons);
        gallons.setOnClickListener(this);

        liters = findViewById(R.id.liters);
        liters.setOnClickListener(this);

        pounds = findViewById(R.id.pounds);
        pounds.setOnClickListener(this);

        kg = findViewById(R.id.kg);
        kg.setOnClickListener(this);

        fahrenheit = findViewById(R.id.fahrenheit);
        fahrenheit.setOnClickListener(this);

        celsius = findViewById(R.id.celsius);
        celsius.setOnClickListener(this);

        input = findViewById(R.id.input);

        valueDisplay = findViewById(R.id.valueDisplay);
        valueDisplay.setGravity(Gravity.CENTER_VERTICAL);

        title = findViewById(R.id.title);
        title.setGravity(Gravity.CENTER_VERTICAL);

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        double output;
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        String formattedValue;

        if (id == R.id.exit) {
            finish();
        } else {

                if (input.getText().toString().isEmpty()) {
                    Toast.makeText(this, "You Have To Enter Something!", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    try {
                            double intOut = Double.parseDouble(input.getText().toString());

                            if (id == R.id.miles) {
                                output = intOut * 1.61;
                                formattedValue = String.format("%.2f", output);
                                valueDisplay.setText(formattedValue + " KM");
                            } else if (id == R.id.km) {
                                output = intOut * 0.62;
                                formattedValue = String.format("%.2f", output);
                                valueDisplay.setText(formattedValue + " MILES");
                            } else if (id == R.id.gallons) {
                                output = intOut * 3.785;
                                formattedValue = String.format("%.2f", output);
                                valueDisplay.setText(formattedValue + " LITERS");
                            } else if (id == R.id.liters) {
                                output = intOut * 0.264;
                                formattedValue = String.format("%.2f", output);
                                valueDisplay.setText(formattedValue + " GALLONS");
                            } else if (id == R.id.pounds) {
                                output = intOut * 2.204;
                                formattedValue = String.format("%.2f", output);
                                valueDisplay.setText(formattedValue + " KG");
                            } else if (id == R.id.kg) {
                                output = intOut * 0.453;
                                formattedValue = String.format("%.2f", output);
                                valueDisplay.setText(formattedValue + " POUNDS");
                            } else if (id == R.id.fahrenheit) {
                                output = (intOut - 32) * 5 / 9;
                                formattedValue = String.format("%.2f", output);
                                valueDisplay.setText(formattedValue + " C");
                            } else if (id == R.id.celsius) {
                                output = intOut * 9 / 5 + 32;
                                formattedValue = String.format("%.2f", output);
                                valueDisplay.setText(formattedValue + " F");
                            }
                        } catch (NumberFormatException e) {
                            Toast.makeText(this, "That's Not A Number Is It?", Toast.LENGTH_SHORT).show();
                        }
                    }

                imm.hideSoftInputFromWindow(valueDisplay.getWindowToken(), 0);
                }
    }
}
