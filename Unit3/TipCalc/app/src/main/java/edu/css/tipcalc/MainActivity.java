package edu.css.tipcalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int num = 0;
    private EditText billAmt;
    private EditText tipAmt;
    private EditText totAmt;
    private boolean bigTip = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmt = findViewById(R.id.billAmt);
        tipAmt = findViewById(R.id.tipAmt);
        totAmt = findViewById(R.id.totAmt);
        Button calc = findViewById(R.id.calcTip);

        //calc.setOnClickListener(new View.OnClickListener());

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio1:
                if (checked)
                    num = 1;
                break;
            case R.id.radio2:
                if (checked)
                    num = 2;
                break;
            case R.id.radio3:
                if (checked)
                    num = 3;
                break;
            case R.id.radio4:
                if (checked)
                    num = 4;
                break;
        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox)view).isChecked();

        switch(view.getId()) {
            case R.id.serviceUpg:
                if (checked)
                    bigTip = true;
                break;
        }
    }

    private void tipper(int party, boolean big) {
        double bill = Double.parseDouble(billAmt.getText().toString());
        double tip;
        double tot;

        if (big) {
            tip = (bill * .2) / party;
        } else {
            tip = (bill * .1) / party;
        }

        tot = (bill + tip)/party;

        NumberFormat USFormat = NumberFormat.getCurrencyInstance(Locale.US);

        tipAmt.setText(USFormat.format(tip));
        totAmt.setText(USFormat.format(tot));
    }

    public void onClick(View view) {
        if (num > 0) {
            tipper(num, bigTip);
        }
    }
}
