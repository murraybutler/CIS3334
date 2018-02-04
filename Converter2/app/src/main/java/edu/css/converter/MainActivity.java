package edu.css.converter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText inBills;
    private TextView outBills;

    double rateUS = .80509;
    double rateEU = 1.2492;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inBills = findViewById(R.id.inBills);
        outBills = findViewById(R.id.outBills);
        final Button EUconv = findViewById(R.id.buttonEuro);
        final Button USconv = findViewById(R.id.buttonDolla);

        EUconv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                buttonEuroClick(view);
            }
        });

        USconv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                buttonDollaClick(view);
            }
        });

        inBills.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    inBills.setText("");
                }
            }
        });


    }

    public void buttonDollaClick(View view){
        double startMoney = Double.parseDouble(inBills.getText().toString());
        double outMoney = calcRate("USD", "EUR", startMoney);
        NumberFormat USFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String oMon = USFormat.format(outMoney);
        outBills.setText(oMon);
    }

    public void buttonEuroClick(View view) {
        double startMoney = Double.parseDouble(inBills.getText().toString());
        double outMoney = calcRate("EUR", "USD", startMoney);
        NumberFormat EUFormat = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
        String oMon = EUFormat.format(outMoney);
        outBills.setText(oMon);
    }

    private double calcRate(String base, String comp, double amt) {
        double oMoney;
        if (base == "USD") {
            oMoney = amt * rateEU;
        } else {
            oMoney = amt * rateUS;
        }
        return oMoney;
    }
}
