package edu.css.pushme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    EditText name;
    TextView outer;
    Button  pusher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.prog);
        outer = (TextView) findViewById(R.id.progOut);
        pusher = (Button) findViewById(R.id.pushBtn);
        final String progSuf = " is a great programmer!";

        pusher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("")) {
                    outer.setText("Please enter a name...");
                } else {
                    outer.setText(name.getText().toString() + progSuf);
                }
            }
        });

    }
}
