package com.assessment2.imperialmetricconverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CurrencyData {

    String code;
    double usExchangeRate;
    double auExchangeRate;
    String lastUpdate;

    public CurrencyData(String code, double usExchangeRate,double auExchangeRate, String lastUpdate){
        this.code = code;
        this.usExchangeRate = usExchangeRate;
        this.auExchangeRate = auExchangeRate;

        try {
            SimpleDateFormat apiDateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z", Locale.US);
            Date date = apiDateFormat.parse(lastUpdate);

            SimpleDateFormat desiredDateFormat = new SimpleDateFormat("E, dd MMM HH:mm:ss", Locale.US);
            this.lastUpdate = desiredDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            this.lastUpdate = lastUpdate; // Use the original format in case of an error
        }
    }

    public double getUsExchangeRate() {return usExchangeRate;}
    public double getAuExchangeRate() {return auExchangeRate;}
    public String getLastUpdate(){return lastUpdate;}
}
