package com.example.farhan.excersie;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;


import com.beardedhen.androidbootstrap.BootstrapButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Train extends Activity {


            MainActivity ma = new MainActivity();
            ArrayList<String> exercise_array = ma.getEx();

    ArrayList<ArrayList<String>> listItems = new ArrayList<ArrayList<String>>();


            @Override
            protected void onCreate (Bundle savedInstanceState){
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_train);

                getActionBar().setDisplayUseLogoEnabled(false);
                getActionBar().setDisplayHomeAsUpEnabled(true);
                getActionBar().setDisplayShowHomeEnabled(false);
                getActionBar().setDisplayShowTitleEnabled(true);
                getActionBar().setTitle("TRAINING IN SESSION");

                String dow = "";

                    Calendar cal0 = Calendar.getInstance();

                    Date now0 = new Date();
                    SimpleDateFormat df0 = new SimpleDateFormat("EEEE");
                    String weekday = df0.format(cal0.getTime());
                    SimpleDateFormat dd = new SimpleDateFormat("EEEE, MMM, dd, yyyy");
                    String display_date = dd.format(cal0.getTime());


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
//                    txt_weekday.setText("Today is " + display_date);

                    //open conenction to the database
                    final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);

                    //read the chosen muscle groups for this weekday.......----------------------------------->
                    Cursor mgs = explusdb.rawQuery("Select Muscle_Group from MGroup where " + dow+"=1",null);

              //      BootstrapButton button_begin_training = (BootstrapButton) findViewById(R.id.button_begin_training);

                    int dbg = mgs.getCount();

                    if (dbg == 0)
                    {

                        new AlertDialog.Builder(this)
                                .setTitle("Error")
                                .setMessage("There is no workout setup for today!  Please setup your workout schedule!")


                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                        startActivity(new Intent(Train.this, MainActivity.class));
                                    }
                                })

                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();


                    }

                    else {


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
                            while (exs.isAfterLast() == false && ma.isitFirstRun() == true) {

                                MainActivity.setEx(exs.getString(0),numex);
                                numex = numex + 1;

                                listItems.get(i).add(j,exs.getString(0));
                                j++;
                                exs.moveToNext();
                            }
                            i++;
                            mgs.moveToNext();

                        }

                        ma.set_ex_num(numex);


                        /* removed for the removed stat_workout_class
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

                        int num_of_exs = exercise_array.size();
                int newIndex = 0;

                int set = ma.getSet();

                int max_rep;

                //day of the week
                dow = ma.getDow();

                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_train);

                int min_value = 5;
                int max_value = 200;
                int step = 5;

                final String[] values = new String[40];


                String[] Srep = new String[5];  //array for reading each rep from sets table
                String[] lw_Srep = new String[5];  //array for reading each rep from sets table

                int c = 0;
                for (int ii = min_value; ii <= max_value; ii += step)

                {
                    values[c] = Integer.toString(ii);
                    c = c + 1;
                }

                // TextView test = (TextView) findViewById(R.id.test);
                Calendar cal = Calendar.getInstance();

                Date now = new Date();
                SimpleDateFormat df = new SimpleDateFormat("EEEE, MMM, dd, yyyy");  //date format (full)

                SimpleDateFormat ef = new SimpleDateFormat("yyy-MM-dd");  //easy date format 2015-05-25


                final NumberPicker np1 = (NumberPicker) findViewById(R.id.numberPicker);
                final NumberPicker np2 = (NumberPicker) findViewById(R.id.numberPicker2);
                final NumberPicker np3 = (NumberPicker) findViewById(R.id.numberPicker3);
                final NumberPicker np4 = (NumberPicker) findViewById(R.id.numberPicker4);
                final NumberPicker np5 = (NumberPicker) findViewById(R.id.numberPicker5);


                //   np1.setMinValue(5);

                np1.setWrapSelectorWheel(true);
                np1.setDisplayedValues(values);
                np1.setMaxValue(39);
                np1.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

                np2.setWrapSelectorWheel(true);
                np2.setDisplayedValues(values);
                np2.setMaxValue(39);
                np2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

                np3.setWrapSelectorWheel(true);
                np3.setDisplayedValues(values);
                np3.setMaxValue(39);
                np3.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

                np4.setWrapSelectorWheel(true);
                np4.setDisplayedValues(values);
                np4.setMaxValue(39);
                np4.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

                np5.setWrapSelectorWheel(true);
                np5.setDisplayedValues(values);
                np5.setMaxValue(39);
                np5.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);


                //final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);
    /*
        ArrayAdapter<String>  adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,weights);
        r1.setAdapter(adapter);
        r2.setAdapter(adapter);
        r3.setAdapter(adapter);
        r4.setAdapter(adapter);
        r5.setAdapter(adapter);


*/      //let's set our date variable
                final String date_s = df.format(cal.getTime());  //full format

                final String date_e = ef.format(cal.getTime());  //easy format

                //and the date for last week!

                Calendar lw_cal = Calendar.getInstance();  //read date from sys
                lw_cal.add(Calendar.DAY_OF_MONTH, -7);     //take away 7 days
                final String lw_date_s = df.format(lw_cal.getTime());  //and this  is full and final value


                TextView ex_title = (TextView) findViewById(R.id.ex_title);

try {
    ex_title.setText(exercise_array.get(ma.getEx_index()));


                //lets read and store all the reps from the sets table

                Cursor rep1 = explusdb.rawQuery("Select MAX(R1) FROM sets where Exercise ='" + exercise_array.get(ma.getEx_index()) + "'", null);
                Cursor rep2 = explusdb.rawQuery("Select MAX(R2) FROM sets where Exercise ='" + exercise_array.get(ma.getEx_index()) + "'", null);
                Cursor rep3 = explusdb.rawQuery("Select MAX(R3) FROM sets where Exercise ='" + exercise_array.get(ma.getEx_index()) + "'", null);
                Cursor rep4 = explusdb.rawQuery("Select MAX(R4) FROM sets where Exercise ='" + exercise_array.get(ma.getEx_index()) + "'", null);
                Cursor rep5 = explusdb.rawQuery("Select MAX(R5) FROM sets where Exercise ='" + exercise_array.get(ma.getEx_index()) + "'", null);

                rep1.moveToFirst();
                Srep[0] = rep1.getString(0);

                rep2.moveToFirst();
                Srep[1] = rep2.getString(0);

                rep3.moveToFirst();
                Srep[2] = rep3.getString(0);

                rep4.moveToFirst();
                Srep[3] = rep4.getString(0);

                rep5.moveToFirst();
                Srep[4] = rep5.getString(0);

                //now lets see which rep was the highest

                if (Srep[1] != null) {


                    int max = Integer.parseInt(Srep[0]);

                    for (int ik = 1; ik < Srep.length; ik++) {
                        if (Integer.parseInt(Srep[ik]) > max) {
                            max = Integer.parseInt(Srep[ik]);
                        }
                    }

                    //no display in personal best field
                    TextView p_best = (TextView) findViewById(R.id.txt_best);
                    p_best.setText(max + " lbs");

                }

                //let's find values for last week!

                Cursor lw_rep1 = explusdb.rawQuery("Select MAX(R1) FROM sets where date ='" + lw_date_s + "'  AND Exercise ='" + exercise_array.get(ma.getEx_index()) + "'", null);
                Cursor lw_rep2 = explusdb.rawQuery("Select MAX(R2) FROM sets where date ='" + lw_date_s + "' AND  Exercise ='" + exercise_array.get(ma.getEx_index()) + "'", null);
                Cursor lw_rep3 = explusdb.rawQuery("Select MAX(R3) FROM sets where date ='" + lw_date_s + "' AND  Exercise ='" + exercise_array.get(ma.getEx_index()) + "'", null);
                Cursor lw_rep4 = explusdb.rawQuery("Select MAX(R4) FROM sets where date ='" + lw_date_s + "' AND  Exercise ='" + exercise_array.get(ma.getEx_index()) + "'", null);
                Cursor lw_rep5 = explusdb.rawQuery("Select MAX(R5) FROM sets where date ='" + lw_date_s + "' AND  Exercise ='" + exercise_array.get(ma.getEx_index()) + "'", null);

                lw_rep1.moveToFirst();
                lw_Srep[0] = lw_rep1.getString(0);

                lw_rep2.moveToFirst();
                lw_Srep[1] = lw_rep2.getString(0);

                lw_rep3.moveToFirst();
                lw_Srep[2] = lw_rep3.getString(0);

                lw_rep4.moveToFirst();
                lw_Srep[3] = lw_rep4.getString(0);

                lw_rep5.moveToFirst();
                lw_Srep[4] = lw_rep5.getString(0);

                //now lets see which rep was the highest (for last week)
                if (lw_Srep[1] != null) {


                    int lw_max = Integer.parseInt(lw_Srep[0]);

                    for (int j = 1; j < lw_Srep.length; j++) {
                        if (Integer.parseInt(lw_Srep[j]) > lw_max) {
                            lw_max = Integer.parseInt(lw_Srep[j]);
                        }
                    }
                    //let's see the values for last week best!!

                    TextView lw_best = (TextView) findViewById(R.id.txt_lastweek);
                    lw_best.setText(lw_max + " lbs");
                }


                Cursor mgg = explusdb.rawQuery("Select Muscle_Group from Exercises where Exercise ='" + exercise_array.get(ma.getEx_index()) + "'", null);
                mgg.moveToFirst();
                final String MG = mgg.getString(0);

                WebView ex_demons = (WebView) findViewById(R.id.ex_demons);
                ex_demons.loadUrl("file:///android_asset/" + mgg.getString(0) + "/" + exercise_array.get(ma.getEx_index()) + ".gif");
                ex_demons.setInitialScale(140);



    Button steps = (Button) findViewById(R.id.btn_Steps);

    Button tips = (Button) findViewById(R.id.btn_trips);


                //buytton to move on to the next training
                BootstrapButton next_ex = (BootstrapButton) findViewById(R.id.button_next_exercise);

                //button on last training to end training and save and exit
                BootstrapButton finish_train = (BootstrapButton) findViewById(R.id.finish_train);
                finish_train.setVisibility(View.GONE);  //hide the finish button


                //if the current index of exercise + next one is the last exercise
                //then disable th enext button and reset the index tracker


                if (ma.getEx_index() + 1 == num_of_exs) {
                    next_ex.setEnabled(false); //disable the next button
                    next_ex.setVisibility(View.GONE); //hide the next button
                    ma.setisitFirstRun(false);
                    finish_train.setVisibility(View.VISIBLE);  //show the finish button
                    //  clearout Start_workout listitems??
                }
                //otherwise, if next exercise is selected, store info regarding last exercise and....
                else {
                    next_ex.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            create(explusdb, exercise_array, ma.getEx_index(), MG, values, np1, np2, np3, np4, np5, date_s, date_e);
                            ma.setEx_index(ma.getEx_index() + 1); //increment the index tracker
                            ma.setSet(1);
                            ma.setisitFirstRun(false);
                            finish();

                            startActivity(getIntent());

                        }
                    });




                }


    steps.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            new AlertDialog.Builder(Train.this)
                    .setTitle("Error")
                    .setMessage("You haven't chosen any exercises!  Please choose at least 1 exercise for today.")


                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })

                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();


        }
    });


    tips.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {

            new AlertDialog.Builder(Train.this)
                    .setTitle("Error")
                    .setMessage("You haven't chosen any exercises!  Please choose at least 1 exercise for today.")


                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })

                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();


        }
    });





    finish_train.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        create(explusdb, exercise_array, ma.getEx_index(), MG, values, np1, np2, np3, np4, np5, date_s, date_e);

                        //clear out settings as if on last exercse if pressed exit
                        ma.setEx_index(0);
                        ma.setSet(0);


                        //clear out the  arraylist keeping track of exercises selected for workout
                        MainActivity.clearEx();

                        //make it as if it was the first time the app is opened
                        //just to set the first run tracker for
                        //the purpose of reading the exercises only once
                        ma.setisitFirstRun(true);

                        finish();

                        Intent MA = new Intent(Train.this, MainActivity.class);

                        MA.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(MA);


                    }
                });

}
catch (IndexOutOfBoundsException e)
{
    new AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("You haven't chosen any exercises!  Please choose at least 1 exercise for today.")


            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    startActivity(new Intent(Train.this, MainActivity.class));
                }
            })

            .setIcon(android.R.drawable.ic_dialog_alert)
            .show();

}
            }


        }


    public static void create(SQLiteDatabase explusdb, ArrayList<String> exercise_array, int which_ex, String MG, String[] values, NumberPicker np1, NumberPicker np2, NumberPicker np3, NumberPicker np4, NumberPicker np5, String date, String date2)

    {

        int r_max = 0;  //to store the max_rep for today for this exercise and muscle  group!!

        int[] r = new int[5];

        r[0] = Integer.parseInt(values[np1.getValue()]);
        r[1] = Integer.parseInt(values[np2.getValue()]);
        r[2] = Integer.parseInt(values[np3.getValue()]);
        r[3] = Integer.parseInt(values[np4.getValue()]);
        r[4] = Integer.parseInt(values[np5.getValue()]);


        //let's figure out the max rep!
        r_max = r[0];

        for (int j = 0; j < 5; j++) {
            if (r[j] > r_max) {
                r_max = r[j];
            }
        }


        //let's insert our data into the database!
        explusdb.execSQL("INSERT INTO sets (Exercise,Muscle_Group,r1,r2,r3,r4,r5,r_max,date,date2) VALUES ('" + exercise_array.get(which_ex) + "','" + MG + "'," + r[0] + "," + r[1] + "," + r[2] + "," + r[3] + "," + r[4] + "," + r_max + ",'" + date + "','" + date2 + "');");


    }



    @Override
    public void onBackPressed() {
     // there is no way back. :)  complete your exercises!
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_train, menu);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(false);
        return true;
    }


}
