package com.example.farhan.excersie;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.Collections;


public class chest extends Activity {
    MainActivity ma = new MainActivity();
    String dow = ma.getDow();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_selection);





        getActionBar().setDisplayUseLogoEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle("BACK");

        ArrayList<CheckBox> cbox = new ArrayList<>();
        CheckBox c1 = (CheckBox) findViewById(R.id.cb1);
        CheckBox c2 = (CheckBox) findViewById(R.id.cb2);
        CheckBox c3 = (CheckBox) findViewById(R.id.cb3);
        CheckBox c4 = (CheckBox) findViewById(R.id.cb4);
        CheckBox c5 = (CheckBox) findViewById(R.id.cb5);
        CheckBox c6 = (CheckBox) findViewById(R.id.cb6);
        CheckBox c7 = (CheckBox) findViewById(R.id.cb7);
        CheckBox c8 = (CheckBox) findViewById(R.id.cb8);
        CheckBox c9 = (CheckBox) findViewById(R.id.cb9);
        CheckBox c10 = (CheckBox) findViewById(R.id.cb10);
        CheckBox c11 = (CheckBox) findViewById(R.id.cb11);

        cbox.add(0,c1);
        cbox.add(1, c2);
        cbox.add(2,c3);
        cbox.add(3,c4);
        cbox.add(4,c5);
        cbox.add(5,c6);
        cbox.add(6,c7);
        cbox.add(7,c8);
        cbox.add(8, c9);
        cbox.add(9,c10);
        cbox.add(10,c11);



        ArrayList<WebView> lView = new ArrayList<WebView>();
        WebView w1 = (WebView) findViewById(R.id.webView);
        WebView w2 = (WebView) findViewById(R.id.webView2);
        WebView w3 = (WebView) findViewById(R.id.webView3);
        WebView w4 = (WebView) findViewById(R.id.webView4);
        WebView w5 = (WebView) findViewById(R.id.webView5);
        WebView w6 = (WebView) findViewById(R.id.webView6);
        WebView w7 = (WebView) findViewById(R.id.webView7);
        WebView w8 = (WebView) findViewById(R.id.webView8);
        WebView w9 = (WebView) findViewById(R.id.webView9);
        WebView w10 = (WebView) findViewById(R.id.webView10);
        WebView w11 = (WebView) findViewById(R.id.webView11);

        lView.add(0,w1);
        lView.add(1,w2);
        lView.add(2, w3);
        lView.add(3,w4);
        lView.add(4,w5);
        lView.add(5,w6);
        lView.add(6,w7);
        lView.add(7,w8);
        lView.add(8,w9);
        lView.add(9,w10);
        lView.add(10,w11);
        ArrayList<TableRow> rc = new ArrayList<>();
        TableRow rc1 = (TableRow) findViewById(R.id.rc1);
        TableRow rc2 = (TableRow) findViewById(R.id.rc2);
        TableRow rc3 = (TableRow) findViewById(R.id.rc3);
        TableRow rc4 = (TableRow) findViewById(R.id.rc4);
        TableRow rc5 = (TableRow) findViewById(R.id.rc5);
        TableRow rc6 = (TableRow) findViewById(R.id.rc6);
        TableRow rc7 = (TableRow) findViewById(R.id.rc7);
        TableRow rc8 = (TableRow) findViewById(R.id.rc8);
        TableRow rc9 = (TableRow) findViewById(R.id.rc9);
        TableRow rc10 = (TableRow) findViewById(R.id.rc10);
        TableRow rc11 = (TableRow) findViewById(R.id.rc11);

        rc.add(0,rc1);
        rc.add(1,rc2);
        rc.add(2,rc3);
        rc.add(3,rc4);
        rc.add(4,rc5);
        rc.add(5,rc6);
        rc.add(6,rc7);
        rc.add(7,rc8);
        rc.add(8,rc9);
        rc.add(9,rc10);
        rc.add(10,rc11);


        ArrayList<TableRow> rw = new ArrayList<>();
        TableRow rw1 = (TableRow) findViewById(R.id.rw1);
        TableRow rw2 = (TableRow) findViewById(R.id.rw2);
        TableRow rw3 = (TableRow) findViewById(R.id.rw3);
        TableRow rw4 = (TableRow) findViewById(R.id.rw4);
        TableRow rw5 = (TableRow) findViewById(R.id.rw5);
        TableRow rw6 = (TableRow) findViewById(R.id.rw6);
        TableRow rw7 = (TableRow) findViewById(R.id.rw7);
        TableRow rw8 = (TableRow) findViewById(R.id.rw8);
        TableRow rw9 = (TableRow) findViewById(R.id.rw9);
        TableRow rw10 = (TableRow) findViewById(R.id.rw10);
        TableRow rw11 = (TableRow) findViewById(R.id.rw11);

        rw.add(0,rw1);
        rw.add(1,rw2);
        rw.add(2,rw3);
        rw.add(3,rw4);
        rw.add(4,rw5);
        rw.add(5,rw6);
        rw.add(6,rw7);
        rw.add(7,rw8);
        rw.add(8,rw9);
        rw.add(9,rw10);
        rw.add(10,rw11);

        int selected;

        //connection to db
        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus",MODE_PRIVATE,null);

        Cursor db_read_num_chest = explusdb.rawQuery("Select count(*) from Exercises where Muscle_Group = 'chest'",null);
        db_read_num_chest.moveToFirst();
        int dbnumchest = db_read_num_chest.getInt(0);

        ma.setchest_complete(dbnumchest);

        for (int q = dbnumchest; q < 11;q++)
        {
            cbox.get(q).setVisibility(View.GONE);
            lView.get(q).setVisibility(View.GONE);

            rc.get(q).setVisibility(View.GONE);
            rw.get(q).setVisibility(View.GONE);
        }


        Cursor exx = explusdb.rawQuery("Select Exercise from Exercises where Muscle_Group = 'chest'", null);
        String exxx;

        ArrayList<String> exss = new ArrayList<String>();


        for (int p = 0; p < dbnumchest;p++) {

            // if (MainActivity.abs_list.size() == 0) {

            if (p == 0) {
                exx.moveToFirst();
            }
            else if ( p > 0)
            {
                exx.moveToNext();
            }

            exxx = exx.getString(0);
            exss.add(p,exxx);
            lView.get(p).loadUrl("file:///android_asset/chest/" + exxx + ".gif");
            //set all checkbox names
            cbox.get(p).setText(exxx);

            Cursor db_read = explusdb.rawQuery("Select "+dow + " from Exercises where Exercise ='" + exxx+"'",null);
            db_read.moveToFirst();
            selected = db_read.getInt(0);


            //set zoom level
            lView.get(p).setInitialScale(140);



            if (selected == 1)
            {
                cbox.get(p).setChecked(true);
            }
            else {
                cbox.get(p).setChecked(false);
            }
        }

        MainActivity.chest_list = exss;

        for (int p = 0; p < dbnumchest;p++) {
            final int i = p;
            cbox.get(i).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        explusdb.execSQL("Update Exercises set " + dow + " =1 WHERE Exercise ='" + MainActivity.chest_list.get(i) + "'");
                    } else {
                        explusdb.execSQL("Update Exercises set " + dow + " =0 WHERE Exercise ='" + MainActivity.chest_list.get(i) + "'");
                    }
                }
            });


        }
    }

    @Override
    public void onBackPressed() {

        int ok = 0 ;

        ArrayList<CheckBox> cbox = new ArrayList<>();
        CheckBox c1 = (CheckBox) findViewById(R.id.cb1);
        CheckBox c2 = (CheckBox) findViewById(R.id.cb2);
        CheckBox c3 = (CheckBox) findViewById(R.id.cb3);
        CheckBox c4 = (CheckBox) findViewById(R.id.cb4);
        CheckBox c5 = (CheckBox) findViewById(R.id.cb5);
        CheckBox c6 = (CheckBox) findViewById(R.id.cb6);
        CheckBox c7 = (CheckBox) findViewById(R.id.cb7);
        CheckBox c8 = (CheckBox) findViewById(R.id.cb8);
        CheckBox c9 = (CheckBox) findViewById(R.id.cb9);
        CheckBox c10 = (CheckBox) findViewById(R.id.cb10);
        CheckBox c11 = (CheckBox) findViewById(R.id.cb11);

        cbox.add(0,c1);
        cbox.add(1, c2);
        cbox.add(2,c3);
        cbox.add(3,c4);
        cbox.add(4,c5);
        cbox.add(5,c6);
        cbox.add(6,c7);
        cbox.add(7,c8);
        cbox.add(8, c9);
        cbox.add(9,c10);
        cbox.add(10, c11);

        for (int k = 0; k <ma.getchest_complete();k++)
        {
            if (cbox.get(k).isChecked()== true)
            {
                ok = 1;
            }

        }

        if (ok ==1) {
            startActivity(new Intent(chest.this, configure_exercises.class));
            finish();
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chest, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                // ProjectsActivity is my 'home' activity
                startActivity(new Intent(chest.this, configure_exercises.class));
                finish();

        }

        return super.onOptionsItemSelected(menuItem);
    }
}
