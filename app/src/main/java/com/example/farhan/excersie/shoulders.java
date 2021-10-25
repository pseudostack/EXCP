package com.example.farhan.excersie;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Collections;

public class shoulders extends Activity {
    MainActivity ma = new MainActivity();
    String dow = ma.getDow();



    TableLayout linearMain;
    CheckBox checkBox;
    TextView textView;
    TextView textView2;
    TextView textView3;
    TableRow tablerow;
    WebView webView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercises);


        getActionBar().setDisplayUseLogoEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle("BACK");


        ArrayList<String> exs = new ArrayList<String>();
        linearMain= (TableLayout)findViewById(R.id.linear_main);
        linearMain.setBackgroundColor(Color.RED);

        int selected;

        //connection to db
        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);

        Cursor db_read_num_shoulders = explusdb.rawQuery("Select count(*) from Exercises where Muscle_Group = 'shoulders'", null);
        db_read_num_shoulders.moveToFirst();
        int dbnumshoulders = db_read_num_shoulders.getInt(0);

        ma.setshoulders_complete(dbnumshoulders);

        Cursor exx = explusdb.rawQuery("Select Exercise from Exercises where Muscle_Group = 'shoulders'", null);

        String exxx;   //current exercise read from database
        String exxd;  //description of exercise
        ArrayList<String> exss = new ArrayList<String>();


        for (int p = 0; p < dbnumshoulders;p++) {

            // if (MainActivity.shoulders_list.size() == 0) {

            if (p == 0) {
                exx.moveToFirst();
            }
            else if ( p > 0)
            {
                exx.moveToNext();
            }

            exxx = exx.getString(0);
            exss.add(p, exxx);

            TableRow row1 = new TableRow(this);
            row1.setPadding(0, 5, 5, 0);
            row1.setBackgroundColor(Color.WHITE);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);

            LinearLayout cell = new LinearLayout(this);
            cell.setBackgroundColor(Color.WHITE);

            textView = new TextView(this);
            textView.setId(p);
            textView.setText(exxx);
            cell.addView(textView);
            textView.setWidth(700);
            row1.addView(cell);

            textView2 = new TextView(this);
            textView2.setText("");
            LinearLayout cell3 = new LinearLayout(this);

            cell3.setBackgroundColor(Color.WHITE);
            cell3.addView(textView2);
            row1.addView(cell3);

            linearMain.addView(row1);

            TableRow row2 = new TableRow(this);
            row2.setPadding(0, 5, 5, 0);
            row2.setBackgroundColor(Color.WHITE);
            LinearLayout celld = new LinearLayout(this);
            celld.setBackgroundColor(Color.WHITE);

            textView3 = new TextView(this);
            textView3.setId(p);

            Cursor exd = explusdb.rawQuery("Select description FROM Exercises where Exercise ='" + exxx + "'", null);

            exd.moveToFirst();
            exxd = exd.getString(0);


            textView3.setText(exxd);
            textView3.setWidth(700);
            textView3.setTextSize(10);
            celld.addView(textView3);
            row2.addView(celld);


            TableRow row3 = new TableRow(this);
            row3.setPadding(0, 5, 5, 0);
            row3.setBackgroundColor(Color.WHITE);;
            ImageView imageview= new ImageView(this);
            Ion.with(imageview).load("file:///android_asset/shoulders/" + exxx + ".gif");

            LinearLayout cell2 = new LinearLayout(this);
            cell2.setBackgroundColor(Color.WHITE);
            cell2.addView(imageview);
            row3.addView(cell2);

            checkBox = new CheckBox(this);
            checkBox.setText("Enable");
            checkBox.setId(p);
            checkBox.setOnClickListener(getOnClickDoSomething(checkBox));
            LinearLayout cell4 = new LinearLayout(this);
            cell4.setBackgroundColor(Color.WHITE);
            cell4.addView(checkBox);
            row2.addView(cell4);
            linearMain.addView(row2);
            linearMain.addView(row3);

            View v = new View(this);
            v.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    5
            ));
            v.setBackgroundColor(Color.parseColor("#B3B3B3"));

            linearMain.addView(v);


            Cursor db_read = explusdb.rawQuery("Select "+dow + " from Exercises where Exercise ='" + exxx+"'",null);
            db_read.moveToFirst();
            selected = db_read.getInt(0);


            //set zoom level
//            lView.get(p).setInitialScale(140);



            if (selected == 1)
            {
                checkBox.setChecked(true);
            }
            else {
                checkBox.setChecked(false);
            }


        }

        MainActivity.shoulders_list = exss;;

    }

    View.OnClickListener getOnClickDoSomething(final CheckBox checkBox){
        return new View.OnClickListener()

        {

            @Override
            public void onClick (View v){

                //connection to db
                final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);

                int id = 0;

                id = checkBox.getId();

                String ex_name = "";

                TextView current_ex = (TextView)findViewById(id);

                ex_name = (String)current_ex.getText();

                if (checkBox.isChecked())
                {


                    explusdb.execSQL("Update Exercises set " + dow + " =1 WHERE Exercise ='" + ex_name+ "'");
                }
                else {
                    explusdb.execSQL("Update Exercises set " + dow + " =0 WHERE Exercise ='" + ex_name + "'");
                }
            }
        };


    }
    public void OI()
    {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("There is no workout setup for today!  Please setup your workout schedule!")


                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onBackPressed()
    {

        int ok = 0 ;

        for (int k = 0; k <ma.getshoulders_complete();k++)
        {
            if (checkBox.isChecked())
            {
                ok = 1;
            }

        }

        if (ok ==1) {
            startActivity(new Intent(shoulders.this, configure_exercises.class));
            finish();
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shoulders, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                // ProjectsActivity is my 'home' activity
                startActivity(new Intent(shoulders.this, configure_exercises.class));
                finish();

        }

        return super.onOptionsItemSelected(menuItem);
    }


}
