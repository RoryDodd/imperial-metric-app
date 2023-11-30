package com.assessment2.imperialmetricconverter;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherApiCall extends AsyncTask<String, Void, WeatherData> {

    @Override
    protected WeatherData doInBackground(String... location) {
        if (location.length != 1) {
            // Ensure that there is exactly one location provided
            return null;
        }

        String apiUrl = "http://api.weatherapi.com/v1/current.json?key=INSERT-KEY=" + location[0] + "&aqi=no";

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
                JSONObject dataLocation = dataObject.getJSONObject("location");

                if (dataLocation.has("name")) {
                    String loc = dataLocation.getString("name");
                    double tempc = dataObject.getJSONObject("current").getDouble("temp_c");
                    double tempf = dataObject.getJSONObject("current").getDouble("temp_f");
                    String type = dataObject.getJSONObject("current").getJSONObject("condition").getString("text");

                    return new WeatherData(loc, tempc, tempf,type);
                } else {
                    return new WeatherData("not available",0.0,0.0,"type");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the exception for debugging
        }

        return null;
    }
}
