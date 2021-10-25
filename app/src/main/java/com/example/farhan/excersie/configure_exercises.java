package com.example.farhan.excersie;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;

import java.io.IOException;
import java.io.InputStream;


public class configure_exercises
        extends Activity {
    final boolean[] enabled = new boolean[10];

    CheckBox CheckBox;
    TextView textView;
    TextView textView2;
    TableRow tablerow;
    WebView webView;

    MainActivity ma = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_exercises);


        //reset all the trackers of selected workouts for scheduling purposes

        getActionBar().setDisplayUseLogoEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle("BACK");


        //used for sql queries and to display chosen day of week
        final String dow = ma.getDow();
        String dow_name = "";


//text view to instruct next steps and show day of week
        //TextView day_week_txt = (TextView) findViewById(R.id.day_week_text);

        if (dow.equals("sun"))
        {
            dow_name = "Sunday";
        }
        else if (dow.equals("mon"))
        {
            dow_name = "Monday";
        }
        else if (dow.equals("tues"))
        {
            dow_name = "Tuesday";
        }
        else if (dow.equals("wed"))
        {
            dow_name = "Wednesday";
        }
        else if (dow.equals("thurs"))
        {
            dow_name = "Thursday";
        }
        else if (dow.equals("fri"))
        {
            dow_name = "Friday";
        }
        else if (dow.equals("sat"))
        {
            dow_name = "Saturday";
        }

        //day_week_txt.setText(dow_name);

        String exxx = "";

        for (int p = 0; p < 6;p++) {
            if (p == 0) {
                exxx = "Abdominals";
            }
            else if (p==1)
            {
                exxx = "Arms";
            }

            else if (p==2)
            {
                exxx = "Back";
            }

            else if (p==3)
            {
                exxx = "Chest";
            }

            else if (p==4)
            {
                exxx = "Legs";
            }

            else if (p==5)
            {
                exxx = "Shoulders";
            }

        }




        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus",MODE_PRIVATE,null);
        final ToggleButton edit_abs = (ToggleButton) findViewById(R.id.edit_abs);
        final ToggleButton edit_arms = (ToggleButton) findViewById(R.id.edit_arms);
        final   ToggleButton edit_back = (ToggleButton) findViewById(R.id.edit_back);
        final  ToggleButton edit_chest = (ToggleButton) findViewById(R.id.edit_chest);
        final ToggleButton edit_legs = (ToggleButton) findViewById(R.id.edit_legs);
        final ToggleButton edit_shoulders = (ToggleButton) findViewById(R.id.edit_shoulders);


        //////////////////READING AND WRITING CheckBox values from database////////////////////////////////////////////////

        //===============================ABS (Index 0) ===============================================================

        //---------------------READ----------------------------------------------------------------------------------------
        Cursor abs = explusdb.rawQuery("Select " + dow +  " from MGroup where Muscle_Group = 'abs'",null);
        abs.moveToFirst();
        int int_abs = abs.getInt(0);

        //initirialize CheckBox
        CheckBox cb_abs = (CheckBox) findViewById(R.id.s1);

        //  com.beardedhen.androidbootstrap.BootstrapButton button_abs = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.button_abs);

        //if enabled in database
        if (int_abs == 1)
        {
            //show checkmark
            enabled[0] = true;
            cb_abs.setChecked(true);
            edit_abs.setEnabled(true);
            edit_abs.setChecked(true);
            edit_abs.setVisibility(View.VISIBLE);

            //   button_abs.setEnabled(true);


        }
        //if not enabled in database
        else {
            //remote checkmark
            enabled[0] = false;
            cb_abs.setChecked(false);
            edit_abs.setEnabled(false);
            edit_abs.setChecked(false);
           // edit_abs.setVisibility(View.INVISIBLE);
            //    button_abs.setEnabled(false);
        }

        //--------------------WRITE--------------------------------------------------------------------------------------
        cb_abs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            //    com.beardedhen.androidbootstrap.BootstrapButton button_abs = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.button_abs);
            CheckBox cb_abs = (CheckBox) findViewById(R.id.s1);
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)

            {
                if ( isChecked )
                {
                    enabled[0] = true;

                    if (over_limit() ==true )
                    {
                        display_over_limit_error();
                        enabled[0] = false;
                        cb_abs.setChecked(false);
                        edit_abs.setEnabled(false);
                       // edit_abs.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        edit_abs.setEnabled(true);;             edit_abs.setChecked(true);
                        edit_abs.setVisibility(View.VISIBLE);
                        explusdb.execSQL("UPDATE MGroup set " + dow + "= 1 WHERE Muscle_Group = 'abs'");
                        //    button_abs.setEnabled(true);
                        //  finish();
                        // startActivity(new Intent(configure_exercises.this, abs.class));
                        ma.setmgroups_abs(true);
                    }
                }
                else {
                    enabled[0] = false;
                    edit_abs.setEnabled(false);
                    edit_abs.setChecked(false);
                   // edit_abs.setVisibility(View.INVISIBLE);
                    explusdb.execSQL("UPDATE MGroup set " + dow + "= 0 WHERE Muscle_Group = 'abs'");

                    //    button_abs.setEnabled(false);
                }
            }
        });

//=============================================arms===========================================-

        //---------------------READ----------------------------------------------------------------------------------------
        Cursor arms = explusdb.rawQuery("Select " + dow +  " from MGroup where Muscle_Group = 'arms'",null);
        arms.moveToFirst();
        int int_arms = arms.getInt(0);

        //initirialize CheckBox
        CheckBox cb_arms = (CheckBox) findViewById(R.id.s2);
        //   com.beardedhen.androidbootstrap.BootstrapButton button_legs = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.button_legs);

        //if enabled in database
        if (int_arms == 1)
        {
            //show checkmark
            enabled[1] = true;
            //    button_arms.setEnabled(true);
            cb_arms.setChecked(true);
            edit_arms.setEnabled(true);;             edit_arms.setChecked(true);
            edit_arms.setVisibility(View.VISIBLE);
        }
        //if not enabled in database
        else {
            //remote checkmark
            enabled[1] = false;
            cb_arms.setChecked(false);
            edit_arms.setEnabled(false);
           // edit_arms.setVisibility(View.INVISIBLE);
            //    button_legs.setEnabled(false);
        }

        //--------------------WRITE--------------------------------------------------------------------------------------
        cb_arms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            //      com.beardedhen.androidbootstrap.BootstrapButton  button_legs = (com.beardedhen.androidbootstrap.BootstrapButton ) findViewById(R.id.button_legs);
            CheckBox cb_arms = (CheckBox) findViewById(R.id.s2);
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked ) {
                    enabled[1] = true;

                    if (over_limit() == true) {
                        display_over_limit_error();
                        enabled[1] = false;
                        cb_arms.setChecked(false);
                        edit_arms.setEnabled(false);
                        edit_arms.setChecked(false);
                       // edit_arms.setVisibility(View.INVISIBLE);
                    } else {
                        edit_arms.setEnabled(true);;             edit_arms.setChecked(true);
                        edit_arms.setVisibility(View.VISIBLE);
                        explusdb.execSQL("UPDATE MGroup set " + dow + "= 1 WHERE Muscle_Group = 'arms'");
                        //            button_legs.setEnabled(true);
                        //  finish();
                        // startActivity(new Intent(configure_exercises.this, arms.class));
                        ma.setmgroups_arms(true);
                    }

                }
                else {
                    enabled[1] = false;
                    edit_arms.setEnabled(false);
                    edit_arms.setChecked(false);
                   // edit_arms.setVisibility(View.INVISIBLE);
                    //        button_legs.setEnabled(false);
                    explusdb.execSQL("UPDATE MGroup set " + dow + "= 0 WHERE Muscle_Group = 'arms'");
                }
            }
        });




        //=============================back (index 1)============================================================

        //---------------------READ----------------------------------------------------------------------------------------
        Cursor back = explusdb.rawQuery("Select " + dow +  " from MGroup where Muscle_Group = 'back'",null);
        back.moveToFirst();
        int int_back = back.getInt(0);

        //initirialize CheckBox
        CheckBox cb_back = (CheckBox) findViewById(R.id.s3);
        //   com.beardedhen.androidbootstrap.BootstrapButton button_back = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.button_back);

        //if enabled in database
        if (int_back == 1)
        {
            //show checkmark
            enabled[2] = true;
            //  button_back.setEnabled(true);
            cb_back.setChecked(true);
                   edit_back.setEnabled(true);             edit_back.setChecked(true);
            
            edit_back.setVisibility(View.VISIBLE);
        }
        //if not enabled in database
        else {
            //remote checkmark
            enabled[2] = false;
            cb_back.setChecked(false);
      edit_back.setEnabled(false);             edit_back.setChecked(false);
           // edit_back.setVisibility(View.INVISIBLE);
           // edit_back.setVisibility(View.INVISIBLE);
            //button_back.setEnabled(false);
        }

        //--------------------WRITE--------------------------------------------------------------------------------------
        cb_back.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            //    com.beardedhen.androidbootstrap.BootstrapButton button_back = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.button_back);
            CheckBox cb_back = (CheckBox) findViewById(R.id.s3);
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked ) {
                    enabled[2] = true;

                    if (over_limit() == true) {
                        display_over_limit_error();
                        enabled[2] = false;
                        cb_back.setChecked(false);
                  edit_back.setEnabled(false);             edit_back.setChecked(false);
                       // edit_back.setVisibility(View.INVISIBLE);
                       // edit_back.setVisibility(View.INVISIBLE);
                    } else {
                               edit_back.setEnabled(true);             edit_back.setChecked(true);
                        edit_back.setVisibility(View.VISIBLE);
                        explusdb.execSQL("UPDATE MGroup set " + dow + "= 1 WHERE Muscle_Group = 'back'");
                        //       button_back.setEnabled(true);
                        //finish();
                        //startActivity(new Intent(configure_exercises.this, back.class));
                        ma.setmgroups_back(true);
                    }
                }
                else {
                    enabled[2] = false;
              edit_back.setEnabled(false);             edit_back.setChecked(false);
                   // edit_back.setVisibility(View.INVISIBLE);
                   // edit_back.setVisibility(View.INVISIBLE);
                    //     button_back.setEnabled(false);
                    explusdb.execSQL("UPDATE MGroup set " + dow + "= 0 WHERE Muscle_Group = 'back'");
                }
            }
        });


        /**
         //====================================biceps-==================================================

         //---------------------READ----------------------------------------------------------------------------------------
         Cursor biceps = explusdb.rawQuery("Select " + dow +  " from MGroup where Muscle_Group = 'biceps'",null);
         biceps.moveToFirst();
         int int_biceps = biceps.getInt(0);

         //initirialize CheckBox
         CheckBox cb_biceps = (CheckBox) findViewById(R.id.cb_biceps);
         //  com.beardedhen.androidbootstrap.BootstrapButton button_biceps = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.button_biceps);

         //if enabled in database
         if (int_biceps == 1)
         {
         //show checkmark
         enabled[2] = true;
         cb_biceps.setChecked(true);
         //     button_biceps.setEnabled(true);
         }
         //if not enabled in database
         else {
         //remote checkmark
         enabled[2] = false;
         cb_biceps.setChecked(false);
         //     button_biceps.setEnabled(false);
         }

         //--------------------WRITE--------------------------------------------------------------------------------------
         cb_biceps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
         {
         //     com.beardedhen.androidbootstrap.BootstrapButton button_biceps = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.button_biceps);
         CheckBox cb_biceps = (CheckBox) findViewById(R.id.cb_biceps);
         public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
         {
         if ( isChecked ) {
         enabled[2] = true;

         if (over_limit() == true) {
         display_over_limit_error();
         enabled[2] = false;
         cb_biceps.setChecked(false);
         } else {
         explusdb.execSQL("UPDATE MGroup set " + dow + "= 1 WHERE Muscle_Group = 'biceps'");
         //            button_biceps.setEnabled(true);
         finish();
         startActivity(new Intent(configure_exercises.this, biceps.class));
         ma.setmgroups_biceps(true);
         }
         }
         else {
         enabled[2] = false;
         //        button_biceps.setEnabled(false);
         explusdb.execSQL("UPDATE MGroup set " + dow + "= 0 WHERE Muscle_Group = 'biceps'");
         }
         }
         });

         **/

        //==============================================chest==========================================-

        //---------------------READ----------------------------------------------------------------------------------------
        Cursor chest = explusdb.rawQuery("Select " + dow +  " from MGroup where Muscle_Group = 'chest'",null);
        chest.moveToFirst();
        int int_chest = chest.getInt(0);

        //initirialize CheckBox
        CheckBox cb_chest= (CheckBox) findViewById(R.id.s4);
        //   com.beardedhen.androidbootstrap.BootstrapButton button_chest = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.button_chest);

        //if enabled in database
        if (int_chest == 1)
        {
            //show checkmark
            enabled[3] = true;
            //    button_chest.setEnabled(true);
            cb_chest.setChecked(true);
            edit_chest.setEnabled(true);
            edit_chest.setVisibility(View.VISIBLE);
        }
        //if not enabled in database
        else {
            //remote checkmark
            enabled[3] = false;
            //   button_chest.setEnabled(false);
            cb_chest.setChecked(false);
      edit_chest.setEnabled(false);             edit_chest.setChecked(false);
           // edit_chest.setVisibility(View.INVISIBLE);
        }

        //--------------------WRITE--------------------------------------------------------------------------------------
        cb_chest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            //     com.beardedhen.androidbootstrap.BootstrapButton button_chest = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.button_chest);
            CheckBox cb_chest= (CheckBox) findViewById(R.id.s4);
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked ) {
                    enabled[3] = true;

                    if (over_limit() == true) {
                        display_over_limit_error();
                        enabled[3] = false;
                        cb_chest.setChecked(false);
                  edit_chest.setEnabled(false);             edit_chest.setChecked(false);
                       // edit_chest.setVisibility(View.INVISIBLE);
                    } else {
                        cb_chest.setChecked(true);
                        edit_chest.setEnabled(true);
                        edit_chest.setChecked(true);
                        edit_chest.setVisibility(View.VISIBLE);
                        explusdb.execSQL("UPDATE MGroup set " + dow + "= 1 WHERE Muscle_Group = 'chest'");
                        //button_chest.setEnabled(true);
                        // finish();
                        // startActivity(new Intent(configure_exercises.this, chest.class));
                        ma.setmgroups_chest(true);
                    }
                }
                else {
                    enabled[3] = false;
              edit_chest.setEnabled(false);
                    edit_chest.setChecked(false);
                   // edit_chest.setVisibility(View.INVISIBLE);
                    //         button_chest.setEnabled(false);
                    explusdb.execSQL("UPDATE MGroup set " + dow + "= 0 WHERE Muscle_Group = 'chest'");
                }
            }
        });



        //=============================================legs===========================================-

        //---------------------READ----------------------------------------------------------------------------------------
        Cursor legs = explusdb.rawQuery("Select " + dow +  " from MGroup where Muscle_Group = 'legs'",null);
        legs.moveToFirst();
        int int_legs = legs.getInt(0);

        //initirialize CheckBox
        CheckBox cb_legs = (CheckBox) findViewById(R.id.s5);
        //   com.beardedhen.androidbootstrap.BootstrapButton button_legs = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.button_legs);

        //if enabled in database
        if (int_legs == 1)
        {
            //show checkmark
            enabled[4] = true;
            //    button_legs.setEnabled(true);
            cb_legs.setChecked(true);
            edit_legs.setEnabled(true);
            edit_legs.setChecked(true);
            edit_legs.setVisibility(View.VISIBLE);
        }
        //if not enabled in database
        else {
            //remote checkmark
            enabled[4] = false;
            cb_legs.setChecked(false);
      edit_legs.setEnabled(false);             edit_legs.setChecked(false);
           // edit_legs.setVisibility(View.INVISIBLE);
            //   button_legs.setEnabled(false);
        }

        //--------------------WRITE--------------------------------------------------------------------------------------
        cb_legs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            //      com.beardedhen.androidbootstrap.BootstrapButton  button_legs = (com.beardedhen.androidbootstrap.BootstrapButton ) findViewById(R.id.button_legs);
            CheckBox cb_legs = (CheckBox) findViewById(R.id.s5);
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked ) {
                    enabled[4] = true;

                    if (over_limit() == true) {
                        display_over_limit_error();
                        enabled[4] = false;
                        cb_legs.setChecked(false);
                        edit_legs.setEnabled(false);             edit_legs.setChecked(false);
                       // edit_legs.setVisibility(View.INVISIBLE);
                    } else {
                        cb_legs.setChecked(true);
                        edit_legs.setEnabled(true);
                        edit_legs.setChecked(true);
                        edit_legs.setVisibility(View.VISIBLE);
                        explusdb.execSQL("UPDATE MGroup set " + dow + "= 1 WHERE Muscle_Group = 'legs'");
                        //            button_legs.setEnabled(true);
                        //  finish();
                        // startActivity(new Intent(configure_exercises.this, legs.class));
                        ma.setmgroups_legs(true);
                    }

                }
                else {
                    enabled[4] = false;
              edit_legs.setEnabled(false);             edit_legs.setChecked(false);
                   // edit_legs.setVisibility(View.INVISIBLE);
                    //           button_legs.setEnabled(false);
                    explusdb.execSQL("UPDATE MGroup set " + dow + "= 0 WHERE Muscle_Group = 'legs'");
                }
            }
        });







        //==============================================shoulders==========================================-

        //---------------------READ----------------------------------------------------------------------------------------
        Cursor shoulders = explusdb.rawQuery("Select " + dow +  " from MGroup where Muscle_Group = 'shoulders'",null);
        shoulders.moveToFirst();
        int int_shoulders = shoulders.getInt(0);

        //initirialize CheckBox
        CheckBox cb_shoulders = (CheckBox) findViewById(R.id.s6);
        //    com.beardedhen.androidbootstrap.BootstrapButton  button_shoulder = (com.beardedhen.androidbootstrap.BootstrapButton ) findViewById(R.id.button_shoulder);

        //if enabled in database
        if (int_shoulders == 1)
        {
            //show checkmark
            enabled[5] = true;
            //    button_shoulder.setEnabled(true);
            cb_shoulders.setChecked(true);
            edit_shoulders.setEnabled(true); edit_shoulders.setChecked(true);
            edit_shoulders.setVisibility(View.VISIBLE);
        }
        //if not enabled in database
        else {
            //remote checkmark
            enabled[5] = false;
         //      button_shoulder.setEnabled(false);
            cb_shoulders.setChecked(false);
      edit_shoulders.setEnabled(false);
            edit_shoulders.setChecked(false);
           // edit_shoulders.setVisibility(View.INVISIBLE);
        }

        //--------------------WRITE--------------------------------------------------------------------------------------
        cb_shoulders.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            //    com.beardedhen.androidbootstrap.BootstrapButton  button_shoulder = (com.beardedhen.androidbootstrap.BootstrapButton ) findViewById(R.id.button_shoulder);
            CheckBox cb_shoulders = (CheckBox) findViewById(R.id.s6);
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked ) {
                    enabled[5] = true;

                    if (over_limit() == true) {
                        display_over_limit_error();
                        enabled[5] = false;
                        cb_shoulders.setChecked(false);
                        edit_shoulders.setEnabled(false);
                        edit_shoulders.setChecked(false);
                        edit_shoulders.setChecked(false);
                       // edit_shoulders.setVisibility(View.INVISIBLE);
                    } else {
                        edit_shoulders.setEnabled(true); edit_shoulders.setChecked(true);
                        edit_shoulders.setVisibility(View.VISIBLE);
                        explusdb.execSQL("UPDATE MGroup set " + dow + "= 1 WHERE Muscle_Group = 'shoulders'");
                        //      button_shoulder.setEnabled(true);
                        //  finish();
                        // startActivity(new Intent(configure_exercises.this, shoulders.class));
                        ma.setmgroups_shoulders(true);
                    }
                }
                else {
                    enabled[5] = false;
              edit_shoulders.setEnabled(false);
                    edit_shoulders.setChecked(false);
                   // edit_shoulders.setVisibility(View.INVISIBLE);
                    //     button_shoulder.setEnabled(false);
                    explusdb.execSQL("UPDATE MGroup set " + dow + "= 0 WHERE Muscle_Group = 'shoulders'");
                }
            }
        });






        edit_abs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edit_abs.setChecked(true);

                finish();
                startActivity(new Intent(configure_exercises.this, abs.class));

            }
        });

        edit_arms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edit_arms.setChecked(true);

                finish();
                startActivity(new Intent(configure_exercises.this, arms.class));

            }
        });

        edit_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edit_back.setChecked(true);

                finish();
                startActivity(new Intent(configure_exercises.this, back.class));

            }
        });

        edit_chest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edit_chest.setChecked(true);

                finish();
                startActivity(new Intent(configure_exercises.this, chest.class));

            }
        });

        edit_legs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edit_legs.setChecked(true);

                finish();
                startActivity(new Intent(configure_exercises.this, legs.class));

            }
        });



        edit_shoulders.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edit_shoulders.setChecked(true);

                finish();
                startActivity(new Intent(configure_exercises.this, shoulders.class));

            }
        });


    }




    private boolean over_limit () {
        int total = 0;

        for (int i = 0; i < 10; i++) {
            if (enabled[i] == true) {
                total = total + 1;
            }
        }

        if (total > 2) {

            return true;

        } else {
            return false;
        }
    }


    public void display_over_limit_error() {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Please choose maximum of 2 muscle groups.")


                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_configure_exercises, menu);

        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(configure_exercises.this, Configure_workout_schedule.class));

        // Intent myIntent = new Intent(configure_exercises.this,Configure_workout_schedule.class);
        //configure_exercises.this.startActivity(myIntent);
        // super.onBackPressed();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                // ProjectsActivity is my 'home' activity
                finish();
                startActivity(new Intent(configure_exercises.this, Configure_workout_schedule.class));

        }

        return super.onOptionsItemSelected(menuItem);
    }






}



