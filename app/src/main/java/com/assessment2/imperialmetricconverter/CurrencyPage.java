package com.assessment2.imperialmetricconverter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CurrencyPage extends Activity implements View.OnClickListener {

    Button usDollar,ausDollar,exit;
    EditText amount;
    TextView title,display,exchange,exchangeRate,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_page);

        usDollar = findViewById(R.id.usDollar);
        usDollar.setOnClickListener(this);

        ausDollar = findViewById(R.id.ausDollar);
        ausDollar.setOnClickListener(this);

        exit = findViewById(R.id.exit);
        exit.setOnClickListener(this);

        exchangeRate = findViewById(R.id.rate);

        amount = findViewById(R.id.amount);

        title = findViewById(R.id.title);
        title.setGravity(Gravity.CENTER_VERTICAL);

        display = findViewById(R.id.display);
        display.setGravity(Gravity.CENTER_VERTICAL);

        exchange = findViewById(R.id.exchange);
        exchange.setGravity(Gravity.CENTER_VERTICAL);

        date = findViewById(R.id.date);
        date.setGravity(Gravity.CENTER_VERTICAL);

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (id == R.id.exit) {
            finish();
        }

        if (id == R.id.usDollar) {
            if (amount.getText().toString().isEmpty()){
                Toast.makeText(this,"Enter an Amount!", Toast.LENGTH_SHORT).show();
            } else {
                @SuppressLint("StaticFieldLeak") CurrencyApiCall api = new CurrencyApiCall() {
                    @Override
                    public void onPostExecute(CurrencyData currencyData) {

                            if (currencyData != null) {
                                double usRate = currencyData.getUsExchangeRate();
                                String dateOut = currencyData.getLastUpdate();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        exchange.setText("USD");
                                        exchangeRate.setText(String.valueOf(usRate));
                                        if (!amount.getText().toString().isEmpty()) {
                                            double result = Double.parseDouble(amount.getText().toString());
                                            display.setText(result * usRate + " AUD");
                                            date.setText(dateOut);
                                        }
                                    }
                                });

                            } else {
                                // Handle the case where the API call failed or data is not available
                                Toast.makeText(getApplicationContext(), "Failed to fetch exchange rates", Toast.LENGTH_SHORT).show();
                            }
                    }
                };
                api.execute("AUD");
            }
        }

        if (id == R.id.ausDollar) {
            if (amount.getText().toString().isEmpty()){
                Toast.makeText(this,"Enter an Amount!", Toast.LENGTH_SHORT).show();
            } else {
                @SuppressLint("StaticFieldLeak") CurrencyApiCall api = new CurrencyApiCall() {
                    @Override
                    public void onPostExecute(CurrencyData currencyData) {

                            if (currencyData != null) {
                                double auRate = currencyData.getAuExchangeRate();
                                String dateOut = currencyData.getLastUpdate();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        exchange.setText("AUD");
                                        exchangeRate.setText(String.valueOf(auRate));
                                        if (!amount.getText().toString().isEmpty()) {
                                            double result = Double.parseDouble(amount.getText().toString());
                                            display.setText(result * auRate + " USD");
                                            date.setText(dateOut);
                                        }
                                    }
                                });
                            } else {
                                // Handle the case where the API call failed or data is not available
                                Toast.makeText(getApplicationContext(), "Failed to fetch exchange rates", Toast.LENGTH_SHORT).show();
                            }
                    }
                };
                api.execute("USD");
            }
        }
        imm.hideSoftInputFromWindow(display.getWindowToken(), 0);
    }
}
