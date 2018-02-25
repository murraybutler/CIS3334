package edu.css.contact;

import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    ImageView face;
    Button call, text;
    Spinner callSpinner, textSpinner;
    EditText info;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        face = (ImageView) findViewById(R.id.picImg);
        call = (Button) findViewById(R.id.callBtn);
        text = (Button) findViewById(R.id.txtBtn);
        callSpinner = (Spinner) findViewById(R.id.castSpinner);
        textSpinner = (Spinner) findViewById(R.id.msgSpinner);
        info = (EditText) findViewById(R.id.message);

        final TypedArray faces = getResources().obtainTypedArray(R.array.icons);
        final String[] names = getResources().getStringArray(R.array.names);
        final String[] numbers = getResources().getStringArray(R.array.numbers);
        final String[] messages = getResources().getStringArray(R.array.messages);

        boolean pselect = false;

        face.setImageResource(R.drawable.alien);

        ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, names);
        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> msgAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, messages);
        msgAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        callSpinner.setAdapter(nameAdapter);
        textSpinner.setAdapter(msgAdapter);

        callSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                face.setImageResource(faces.getResourceId(i,-1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                info.setText("No contact selected");
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText("Calling: " + names[index]);
                dialPhoneNumber(numbers[callSpinner.getSelectedItemPosition()]);

            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setText("Texting: " + names[index]);
                composeMmsMessage(messages[textSpinner.getSelectedItemPosition()], numbers[callSpinner.getSelectedItemPosition()]);
            }
        });
    }

    // pilfered from the android Intents Guide
    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    // pilfered from the android Intents Guide
    public void composeMmsMessage(String message, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("sms:" + phoneNumber));
        intent.putExtra("sms_body", message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
