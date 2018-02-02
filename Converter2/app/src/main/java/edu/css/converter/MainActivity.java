package edu.css.converter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.ArrayList;
import static java.lang.*;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText inBills;
    private TextView outBills;

    double rateUS = getRate("USD");
    double rateEU = getRate("EUR");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inBills = findViewById(R.id.inBills);
        outBills = findViewById(R.id.outBills);

        ArrayList curs = new ArrayList<>();


    }

    public void buttonDollaClick(View view){
        double startMoney = Double.parseDouble(inBills.getText().toString());
        double outMoney = calcRate("USD", "EUR", startMoney);
        outBills.setText(Double.toString(outMoney));
    }

    public void buttonEuroClick(View view) {
        double startMoney = Double.parseDouble(inBills.getText().toString());
        double outMoney = calcRate("EUR", "USD", startMoney);
        outBills.setText(Double.toString(outMoney));
    }

    private double calcRate(String base, String comp, double amt) {
        double rate = getRate(base, comp);
        double outMoney = amt / rate;
    }

    private double getRate(String base, String comp) {
        //RequestQueue queue = Volley.newRequestQueue(this);

        String strurl = "http://api.fixer.io/latest?base=" + base + "&symbols=" + comp;
        URLConnection urlConn;
        BufferedReader buffRead = null;

        try {
            URL url = new URL(strurl);
            urlConn = url.openConnection();
            buffRead = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            Stringbuffer strBuff = new StringBuffer();
            String lin;
            while ((lin = buffRead.readLine()) != null) {
                strBuff.append(lin);
            }
            return new JSONObject(strBuff.toString());
        } catch(JSONException e) {
            Log.e("Converter", "DataGet", e);
            return null;
        } finally {
            if(buffRead != null) {
                try {
                    buffRead.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
