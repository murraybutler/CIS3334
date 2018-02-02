package edu.css.converter;

import android.os.AsyncTask;
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
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText inBills;
    private TextView outBills;
    pricate EditText

    //double rateUS = getXRate("USD", "EUR");
    //double rateEU = getXRate("EUR", "USD");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inBills = findViewById(R.id.inBills);
        outBills = findViewById(R.id.outBills);

        inBills.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    inBills.setText("");
                }
            }
        });


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
        //double rate = getXRate(base, comp);
        double rate = .085;
        double outMoney = amt / rate;
        return outMoney;
    }

    protected class exchRate extends AsyncTask<Void, Void, JSONObject>
    {
        @Override
        protected JSONObject doInBackground(String base, String comp)
        {

            String str="https://api.fixer.io/latest?symbols=" + comp + ",base=" + base;
            URLConnection urlConn = null;
            BufferedReader bufferedReader = null;
            try
            {
                URL url = new URL(str);
                urlConn = url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

                StringBuffer stringBuffer = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null)
                {
                    stringBuffer.append(line);
                }

                return new JSONObject(stringBuffer.toString());
            }
            catch(Exception ex)
            {
                Log.e("rates", comp, ex);
                return null;
            }
            finally
            {
                if(bufferedReader != null)
                {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(JSONObject response)
        {
            if(response != null)
            {
                try {
                    ("rates", "Success: " + response.getString("yourJsonElement") );
                } catch (JSONException ex) {
                    Log.e("App", "Failure", ex);
                }
            }
        }
    }

}
