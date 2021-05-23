package com.example.android.quakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        //Create a fake list of earthquakes.
        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();
        // Create an {@link EarthquakeAdapter}, whose data source is a list of {@link Earthquake}s. The
        // adapter knows how to create list items for each item in the list.
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // earthquake_activity.xml layout file.
        ListView earthQuakeListView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ErathquakeAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Earthquake} in the list.
        earthQuakeListView.setAdapter(adapter);

        earthQuakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Earthquake currentEarthquake = adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }
}