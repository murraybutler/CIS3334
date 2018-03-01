package edu.css.floorcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import edu.css.floorcalc.flrDimension;

public class Main2Activity extends AppCompatActivity {

    TextView outPut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        outPut = (TextView) findViewById(R.id.outPut);

        Bundle flrIn = getIntent().getExtras();
        flrDimension iFlr = (flrDimension) flrIn.getParcelable("flrDim");

        double totFlr = iFlr.getLength() * iFlr.getWidth();

        outPut.setText("Width is " + iFlr.getWidth() + " and Length is " + iFlr.getLength() +
        " and required flooring is: " + totFlr);


    }
}
