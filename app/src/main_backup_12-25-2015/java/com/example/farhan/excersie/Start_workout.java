package com.example.farhan.excersie;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Start_workout extends Activity {

    String dow = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_workout);


        Calendar c = Calendar.getInstance();

        Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat("EEEE");
        String weekday = df.format(c.getTime());
        SimpleDateFormat dd = new SimpleDateFormat("EEEE, MMM, dd, yyyy");
        String display_date = dd.format(c.getTime());


        if (weekday.equals("Sunday"))
        {
            dow = "sun";
        }
else if (weekday.equals("Monday"))
        {
            dow = "mon";
        }
        else if (weekday.equals("Tuesday"))
        {
            dow = "tues";
        }
        else if (weekday.equals("Wednesday"))
        {
            dow = "wed";
        }
        else if (weekday.equals("Thursday"))
        {
            dow = "thurs";
        }
        else if (weekday.equals("Friday"))
        {
            dow = "fri";
        }
        else if (weekday.equals("Saturday"))
        {
            dow = "sat";
        }


        //get today week day and display it ------------------------------>
        TextView txt_weekday = (TextView) findViewById(R.id.txt_weekday);
        txt_weekday.setText("Today is " + display_date);

        //open conenction to the database
        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus",MODE_PRIVATE,null);

        //read the chosen muscle groups for this weekday.......----------------------------------->
        Cursor mgs = explusdb.rawQuery("Select Muscle_Group from MGroup where " + dow+"=1",null);


        ArrayList<ArrayList<String>> listItems = new ArrayList<ArrayList<String>>();



        BootstrapButton button_begin_training = (BootstrapButton) findViewById(R.id.button_begin_training);

        int dbg = mgs.getCount();

        if (dbg == 0)
        {

            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("There is no workout setup for today!  Please setup your workout schedule!")


                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            startActivity(new Intent(Start_workout.this, MainActivity.class));
                        }
                    })

                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();


        }

        else
        {


/*
        //create string array to read and display the muscle groups
        String mg[] = new String[mgs.getCount()];
        int i = 0;

        int numex = 0;

        mgs.moveToFirst();
        while (mgs.isAfterLast() == false) {
            mg[i] = mgs.getString(0);

            listItems.add(new ArrayList<String>());

            //read the chosen exercises
            Cursor exs = explusdb.rawQuery("Select Exercise from Exercises where " + dow+"=1 and Muscle_Group='"+mg[i]+"'",null);

            MainActivity.setMg(mg[i],i);


            int j = 0;


                exs.moveToFirst();


            //read exercises and add to 2-dimensional array list
            while (exs.isAfterLast() == false) {

                MainActivity.setEx(exs.getString(0),numex);
                numex = numex + 1;

                listItems.get(i).add(j,exs.getString(0));
                j++;
                exs.moveToNext();
            }
            i++;
            mgs.moveToNext();

        }

        MainActivity.set_ex_num(numex);

        TextView txt_mg1 = (TextView) findViewById(R.id.txt_mg1);
        TextView txt_mg2 = (TextView) findViewById(R.id.txt_mg2);


        ListView lv_ex1 = (ListView) findViewById(R.id.exs1);
        ListView lv_ex2 = (ListView) findViewById(R.id.exs2);




        if (i==1)
        {
            txt_mg1.setText(mg[0]);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems.get(0));

            lv_ex1.setAdapter(adapter);

        }
        else if (i==2)
        {
            txt_mg1.setText(mg[0]);
            txt_mg2.setText(mg[1]);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems.get(0));
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems.get(1));

            lv_ex1.setAdapter(adapter);
            lv_ex2.setAdapter(adapter2);
        }

*/
        }

        button_begin_training.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Start_workout.this, Train.class));

            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Start_workout.this, MainActivity.class));
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_workout, menu);

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

        else if (id==R.id.home)
        {
            startActivity(new Intent(Start_workout.this, MainActivity.class));
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
