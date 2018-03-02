package edu.css.floorcalc;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.css.floorcalc.flrDimension;


public class MainActivity extends AppCompatActivity {

    EditText rmlength;
    EditText rmwidth;
    Button flrBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rmlength = findViewById(R.id.rmLength);
        rmwidth = findViewById(R.id.rmWidth);
        flrBtn = (Button) findViewById(R.id.buyIt);

        /*
        Listens for the click event and parcels object to second activity.
        @param OnClickListener
        sub method handles the click event
         */
        flrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double len = Double.parseDouble(rmlength.getText().toString());
                double wid = Double.parseDouble(rmwidth.getText().toString());

                flrDimension aflr = new flrDimension(wid, len);

                Intent flrIntent = new Intent(getApplicationContext(), Main2Activity.class);
                flrIntent.putExtra("flrDim", aflr);
                startActivity(flrIntent);
            }
        });

    }



}
