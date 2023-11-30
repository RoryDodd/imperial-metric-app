package com.assessment2.imperialmetricconverter;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public abstract class CurrencyApiCall extends AsyncTask<String, Void, CurrencyData> {

    @Override
    protected CurrencyData doInBackground(String... code) {
        if (code.length != 1) {
            // Ensure that there is exactly one location provided
            return null;
        }

        String apiUrl = "https://v6.exchangerate-api.com/v6/INSERT-KEY/latest/" + code[0];

        try {
            URL url = new URL(apiUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                StringBuilder data = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    data.append(scanner.nextLine());
                }

                scanner.close();

                JSONObject dataObject = new JSONObject(data.toString());

                if (dataObject.has("result")) {
                    String outCode = dataObject.getString("base_code");
                    double usRate = dataObject.getJSONObject("conversion_rates").getDouble("USD");
                    double auRate = dataObject.getJSONObject("conversion_rates").getDouble("AUD");
                    String date = dataObject.getString("time_last_update_utc");

                    return new CurrencyData(outCode, usRate, auRate, date);
                } else {
                    return new CurrencyData("not available", 0.0, 0.0, "type");
                }
            }
        } catch (Exception ex) {
            Log.d("NOT WORKING!!!"," WHATS GOING ON",ex);// Log the exception for debugging
        }

        return null;
    }

    @Override
    protected void onPostExecute(CurrencyData currencyData) {}
}
