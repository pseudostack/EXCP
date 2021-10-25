package com.example.farhan.excersie;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class Configure_workout_schedule extends Activity {

    MainActivity ma  = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_workout_schedule);

        getActionBar().setDisplayUseLogoEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle("BACK");


        String[] wdays = new String[7];
        wdays[0] = "Sunday";
        wdays[1] = "Monday";
        wdays[2] = "Tuesday";
        wdays[3] = "Wednesday";
        wdays[4] = "Thursday";
        wdays[5] = "Friday";
        wdays[6] = "Saturday";


        final com.beardedhen.androidbootstrap.BootstrapButton button_sunday = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.sunday);

        button_sunday.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button_sunday.setFontAwesomeIcon("fa-chevron-right");
                ma.setDow("sun");
                finish();
                startActivity(new Intent(Configure_workout_schedule.this, configure_exercises.class));

            }
        });

        com.beardedhen.androidbootstrap.BootstrapButton button_monday = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.monday);

        button_monday.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ma.setDow("mon");
                finish();
                startActivity(new Intent(Configure_workout_schedule.this, configure_exercises.class));

            }
        });

        com.beardedhen.androidbootstrap.BootstrapButton button_tuesday = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.tuesday);

        button_tuesday.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ma.setDow("tues");
                finish();
                startActivity(new Intent(Configure_workout_schedule.this, configure_exercises.class));

            }
        });

        com.beardedhen.androidbootstrap.BootstrapButton button_Wednesday = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.wednesday);

        button_Wednesday.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ma.setDow("wed");
                finish();
                startActivity(new Intent(Configure_workout_schedule.this, configure_exercises.class));

            }
        });

        com.beardedhen.androidbootstrap.BootstrapButton button_thursday = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.thursday);

        button_thursday.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ma.setDow("thurs");
                finish();
                startActivity(new Intent(Configure_workout_schedule.this, configure_exercises.class));

            }
        });

        com.beardedhen.androidbootstrap.BootstrapButton button_Friday = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.friday);

        button_Friday.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ma.setDow("fri");
                finish();
                startActivity(new Intent(Configure_workout_schedule.this, configure_exercises.class));

            }
        });

        com.beardedhen.androidbootstrap.BootstrapButton button_Saturday = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.saturday);

        button_Saturday.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ma.setDow("sat");
                finish();
                startActivity(new Intent(Configure_workout_schedule.this, configure_exercises.class));

            }
        });





    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_configure_workout_schedule, menu);

        return true;
    }


    @Override
    public void onBackPressed() {

      //  Intent myIntent = new Intent(Configure_workout_schedule.this,MainActivity.class);
      //  Configure_workout_schedule.this.startActivity(myIntent);
        finish();
        startActivity(new Intent(this, MainActivity.class));
       // super.onBackPressed();
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                // ProjectsActivity is my 'home' activity
                finish();
                startActivity(new Intent(this, MainActivity.class));

        }

        return super.onOptionsItemSelected(menuItem);
    }
}
