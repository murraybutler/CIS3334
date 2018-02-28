package css.cis3334.heartratetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {

    String range;
    int pulse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView pulseTxt = (TextView) findViewById(R.id.pulse);
        TextView rangeTxt = (TextView) findViewById(R.id.range);

        Bundle passthru = getIntent().getExtras();
        range = passthru.getString("MainRange");
        pulse = passthru.getInt("MainPulse");

        rangeTxt.setText("Range : " + range);
        pulseTxt.setText("Pulse : " + pulse);

    }

}
