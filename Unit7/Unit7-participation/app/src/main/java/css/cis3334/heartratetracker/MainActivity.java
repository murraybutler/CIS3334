package css.cis3334.heartratetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable{

    HeartRateList heartRateList;        // The list of heart rate objects
    ArrayAdapter<HeartRate> hrAdapter;  // The custom array adapter for displaying the heart rates in the list view
    ListView lvHeartRates;              // The list view for the heart rates from the activity_mail.xml file
    TextView tvSelect;                  // The text view for the selected rate the user clicks on from the activity_mail.xml file
    HeartRate hr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvHeartRates = (ListView) findViewById(R.id.listViewHeartRates);
        tvSelect = (TextView) findViewById(R.id.textViewSelect);

        heartRateList = new HeartRateList();
        heartRateList.InitRandomElderly();

        hrAdapter = new HeartRateAdapter(this, R.layout.heart_rate_row, R.id.textViewPulse, heartRateList);
        hrAdapter.setDropDownViewResource(R.layout.heart_rate_row);
        lvHeartRates.setAdapter(hrAdapter);

        lvHeartRates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
              hr = (HeartRate) parent.getItemAtPosition(position);
              tvSelect.setText("You selected: " + hr.toString());
              // Sending output to a new Activity....
              String range = hr.getRangeName();
              int pulse = hr.getPulse();
              Intent secAct = new Intent(view.getContext(), Main2Activity.class);
              secAct.putExtra("MainRange", range);
              secAct.putExtra("MainPulse", pulse);
              startActivity(secAct);
          }
        });

    }
}