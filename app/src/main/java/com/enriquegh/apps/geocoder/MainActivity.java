package com.enriquegh.apps.geocoder;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void handleFindClick(View clickView) {
        EditText location = (EditText) findViewById(R.id.locationName);
        TextView resultBox = (TextView) findViewById(R.id.resultView);

        String locationName = location.getText().toString();

//        Geocoder geo = new Geocoder(this);
        Geocoder geo = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            Log.d("Sauce","Sending geocoder call");
            List<Address> locationResults = geo.getFromLocationName(locationName, 2);
            Log.d("Sauce","Got results");
            Log.d("Sauce",locationResults.toString());

            resultBox.setText("");
            for (Address oneLocation : locationResults) {
                resultBox.append(oneLocation.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
            resultBox.append("Could not get information. Try again");
        }

//        resultBox.append("Location is: " + locationName);
    }
}
