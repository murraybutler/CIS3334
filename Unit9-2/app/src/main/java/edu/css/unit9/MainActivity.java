package edu.css.unit9;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, R.string.EMail, Snackbar.LENGTH_LONG)
                //        .setAction(R.string.EMail, null).show();
                /* Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/email");
                intent.setData(Uri.parse("mailto:"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }*/
                // Log.i("FAB button", "MAde it to here");
                maily();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        String act;
        String msg;

        //noinspection SimplifiableIfStatement
        if (id == R.id.setter) {
            startActivity(new Intent(Settings.ACTION_SETTINGS));
        } else if (id == R.id.add) {
            act = getResources().getString(R.string.addAct);
            msg = getResources().getString(R.string.Add);
            snacky(act,msg);
        } else if (id == R.id.delete) {
            act = getResources().getString(R.string.deleteAct);
            msg = getResources().getString(R.string.Delete);
            snacky(act,msg);
        } else if (id == R.id.mail) {
            maily();
        } else if (id == R.id.sms) {
            smsy();
        }

         return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        String act;
        String msg;

        if (id == R.id.setter) {
            startActivity(new Intent(Settings.ACTION_SETTINGS));
        } else if (id == R.id.add) {
            act = getResources().getString(R.string.addAct);
            msg = getResources().getString(R.string.Add);
            snacky(act,msg);
        } else if (id == R.id.delete) {
            act = getResources().getString(R.string.deleteAct);
            msg = getResources().getString(R.string.Delete);
            snacky(act,msg);
        } else if (id == R.id.mail) {
            maily();
        } else if (id == R.id.sms) {
            smsy();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * A method to return snackbar messages to the user
     * @param act String action msg
     * @param msg String text of msg
     */
    private void snacky(String act, String msg) {
        Snackbar.make(getWindow().getDecorView(), msg, Snackbar.LENGTH_LONG)
                .setAction(act, null)
                .show();
    }

    /**
     * A Method to call the mailer intent
     */
    private void maily() {
        Log.v("Send Email","");
        Intent intent = new Intent(android.content.Intent.ACTION_SENDTO);
        intent.putExtra(Intent.EXTRA_EMAIL, "someone@someplace.org");
        intent.putExtra(Intent.EXTRA_SUBJECT, "test message");
        try {
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
                finish();
                Log.v("Email Sent", "");
            }
        }catch (android.content.ActivityNotFoundException ex) {
            snacky("Email App","Email app failed to find");
        }
    }

    /**
     * A method to call the SMS intent
     */
    private void smsy() {
        Log.v("Send SMS","");
        Intent intent = new Intent(android.content.Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:"));
        intent.putExtra("sms_body", "Test test test");
        try {
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
                finish();
                Log.v("SMS Sent", "");
            }
        }catch (android.content.ActivityNotFoundException ex) {
            snacky("SMS App","SMS app failed to find");
        }
    }
}
