package com.example.farhan.excersie;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class history extends Activity {


    public static String MG;
    public static String EX;
    public static String YR;
    public static String MN;

    int isyear = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        getActionBar().setDisplayUseLogoEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle("BACK");

        int jan = 0;
        int feb = 0;
        int mar = 0;
        int apr = 0;
        int may = 0;
        int jun = 0;
        int jul = 0;
        int aug = 0;
        int sep = 0;
        int oct = 0;
        int nov = 0;
        int dec = 0;

        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);

        Cursor db_read_num_mgroups = explusdb.rawQuery("Select count(*) from MGroup", null);
        db_read_num_mgroups.moveToFirst();
        int num_muscle_groups = db_read_num_mgroups.getInt(0);


        Cursor exx = explusdb.rawQuery("Select Muscle_Group from MGroup", null);
        String exxx;

        ArrayList<String> exss = new ArrayList<String>();


        for (int p = 0; p < num_muscle_groups; p++) {

            // if (MainActivity.abs_list.size() == 0) {

            if (p == 0) {
                exx.moveToFirst();
            } else if (p > 0) {
                exx.moveToNext();
            }

            exxx = exx.getString(0);
            exss.add(p, exxx);

        }


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, exss);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spin = (Spinner) findViewById(R.id.mgroup);

        spin.setAdapter(spinnerArrayAdapter);


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String Muscle_group = spin.getSelectedItem().toString();

                history.MG = Muscle_group;

                populate_exs(MG);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }

    public void yearly_graph() {

        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);


        ArrayList<Cursor> jan_rep = new ArrayList<Cursor>();
        ArrayList<Cursor> feb_rep = new ArrayList<Cursor>();
        ArrayList<Cursor> mar_rep = new ArrayList<Cursor>();
        ArrayList<Cursor> apr_rep = new ArrayList<Cursor>();
        ArrayList<Cursor> may_rep = new ArrayList<Cursor>();
        ArrayList<Cursor> jun_rep = new ArrayList<Cursor>();
        ArrayList<Cursor> jul_rep = new ArrayList<Cursor>();
        ArrayList<Cursor> aug_rep = new ArrayList<Cursor>();
        ArrayList<Cursor> sep_rep = new ArrayList<Cursor>();
        ArrayList<Cursor> oct_rep = new ArrayList<Cursor>();
        ArrayList<Cursor> nov_rep = new ArrayList<Cursor>();
        ArrayList<Cursor> dec_rep = new ArrayList<Cursor>();


        for (int a = 1; a < 6; a++) {
            jan_rep.add(explusdb.rawQuery("Select MAX(R" + a + ") FROM sets where date like'%jan%'  AND Exercise ='" + history.EX + "'", null));
        }

        for (int b = 1; b < 6; b++) {
            feb_rep.add(explusdb.rawQuery("Select MAX(R" + b + ") FROM sets where date like'%feb%'  AND Exercise ='" + history.EX + "'", null));
        }
        for (int c = 1; c < 6; c++) {
            mar_rep.add(explusdb.rawQuery("Select MAX(R" + c + ") FROM sets where date like'%mar%'   AND Exercise ='" + history.EX + "'", null));
        }
        for (int d = 1; d < 6; d++) {
            apr_rep.add(explusdb.rawQuery("Select MAX(R" + d + ") FROM sets where date like'%apr%'   AND Exercise ='"+ history.EX + "'", null));
        }
        for (int e = 1; e < 6; e++) {
            may_rep.add(explusdb.rawQuery("Select MAX(R" + e + ") FROM sets where date like'%may%'   AND Exercise ='" + history.EX + "'", null));
        }
        for (int f = 1; f < 6; f++) {
            jun_rep.add(explusdb.rawQuery("Select MAX(R" + f + ") FROM sets where date like'%jun%'   AND Exercise ='" + history.EX + "'", null));
        }
        for (int g = 1; g < 6; g++) {
            jul_rep.add(explusdb.rawQuery("Select MAX(R" + g + ") FROM sets where date like'%jul%'   AND Exercise ='" + history.EX + "'", null));
        }
        for (int h = 1; h < 6; h++) {
            aug_rep.add(explusdb.rawQuery("Select MAX(R" + h + ") FROM sets where date like'%aug%'  AND Exercise ='"+ history.EX + "'", null));
        }
        for (int i = 1; i < 6; i++) {
            sep_rep.add(explusdb.rawQuery("Select MAX(R" + i + ") FROM sets where date like'%sep%'   AND Exercise ='" + history.EX + "'", null));
        }
        for (int j = 1; j < 6; j++) {
            oct_rep.add(explusdb.rawQuery("Select MAX(R" + j + ") FROM sets where date like'%oct%'   AND Exercise ='" + history.EX + "'", null));
        }
        for (int k = 1; k < 6; k++) {
            nov_rep.add(explusdb.rawQuery("Select MAX(R" + k + ") FROM sets where date like'%nov%'  AND Exercise ='"+ history.EX + "'", null));
        }
        for (int l = 1; l < 6; l++) {
            dec_rep.add(explusdb.rawQuery("Select MAX(R" + l + ") FROM sets where date like'%dec%'   AND Exercise ='"+ history.EX + "'", null));
        }


        String jan_reps[] = new String[5];
        String feb_reps[] = new String[5];
        String mar_reps[] = new String[5];
        String apr_reps[] = new String[5];
        String may_reps[] = new String[5];
        String jun_reps[] = new String[5];
        String jul_reps[] = new String[5];
        String aug_reps[] = new String[5];
        String sep_reps[] = new String[5];
        String oct_reps[] = new String[5];
        String nov_reps[] = new String[5];
        String dec_reps[] = new String[5];

for (int w = 0; w<5; w++) {
    jan_rep.get(w).moveToFirst();
    jan_reps[w] = jan_rep.get(w).getString(0);
}

        int jan_max =0;
        int feb_max=0;
        int mar_max=0;
        int apr_max=0;
        int may_max=0;
        int jun_max=0;
        int jul_max=0;
        int aug_max=0;
        int sep_max=0;
        int oct_max=0;
        int nov_max=0;
        int dec_max=0;


        //now lets see which rep was the highest for january
        if (jan_reps[1] != null) {


             jan_max = Integer.parseInt(jan_reps[0]);

            for (int j = 1; j < jan_reps.length; j++) {
                if (Integer.parseInt(jan_reps[j]) > jan_max) {
                    jan_max = Integer.parseInt(jan_reps[j]);
                }
            }

        }



        for (int w = 0; w<5; w++) {
            aug_rep.get(w).moveToFirst();
            aug_reps[w] = aug_rep.get(w).getString(0);
        }

        //now lets see which rep was the highest for january
        if (aug_reps[1] != null) {


            aug_max = Integer.parseInt(aug_reps[0]);

            for (int j = 1; j < aug_reps.length; j++) {
                if (Integer.parseInt(aug_reps[j]) > aug_max) {
                    aug_max = Integer.parseInt(aug_reps[j]);
                }
            }

        }


        for (int w = 0; w<5; w++) {
            oct_rep.get(w).moveToFirst();
            oct_reps[w] = oct_rep.get(w).getString(0);
        }

        //now lets see which rep was the highest for january
        if (oct_reps[1] != null) {


            oct_max = Integer.parseInt(oct_reps[0]);

            for (int j = 1; j < oct_reps.length; j++) {
                if (Integer.parseInt(oct_reps[j]) > oct_max) {
                    oct_max = Integer.parseInt(oct_reps[j]);
                }
            }

        }

        for (int w = 0; w<5; w++) {
            nov_rep.get(w).moveToFirst();
            nov_reps[w] = nov_rep.get(w).getString(0);
        }

        //now lets see which rep was the highest for january
        if (nov_reps[1] != null) {


             nov_max = Integer.parseInt(nov_reps[0]);

            for (int j = 1; j < nov_reps.length; j++) {
                if (Integer.parseInt(nov_reps[j]) > nov_max) {
                    nov_max = Integer.parseInt(nov_reps[j]);
                }
            }

        }

        for (int w = 0; w<5; w++) {
            dec_rep.get(w).moveToFirst();
            dec_reps[w] = dec_rep.get(w).getString(0);
        }

        //now lets see which rep was the highest for january
        if (dec_reps[1] != null) {


             dec_max = Integer.parseInt(dec_reps[0]);

            for (int j = 1; j < dec_reps.length; j++) {
                if (Integer.parseInt(dec_reps[j]) > dec_max) {
                    dec_max = Integer.parseInt(dec_reps[j]);
                }
            }

        }




        GraphView graph = (GraphView) findViewById(R.id.graph);

        graph.removeAllSeries();

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun"});

        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Month");


        LineGraphSeries series = new LineGraphSeries(new DataPoint[]{

               new DataPoint(1, jan_max),
                new DataPoint(2, feb_max),
                new DataPoint(3, mar_max),
                new DataPoint(4, apr_max),
                new DataPoint(5, may_max),
                new DataPoint(6, jun_max),
                new DataPoint(7, jul_max),
                new DataPoint(8, aug_max),
                new DataPoint(9, sep_max),
                new DataPoint(10, oct_max),
                new DataPoint(11, nov_max),
                new DataPoint(12, dec_max)

        });

        graph.addSeries(series);



        //"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});

    }



    ////daily graph

    public void jan_graph() {

        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);

        Cursor db_read_num_jan = explusdb.rawQuery("Select count(*) FROM sets where date2 BETWEEN '" + history.YR + "-01-01' AND '" + history.YR + "-01-31' AND Exercise ='" + history.EX + "'", null);



        db_read_num_jan.moveToFirst();
        int num_jan = db_read_num_jan.getInt(0);


        if (num_jan == 0)
        {
            noHistoryFoundMonth();
        }

        else {
            HistoryFoundMonth();


            Cursor jan_exs = explusdb.rawQuery("Select r_max FROM sets where date2 BETWEEN '" + history.YR + "-01-01' AND '" + history.YR + "-01-31' AND Exercise ='" + history.EX + "'", null);


            Integer[] janmax = new Integer[31];

            for (int z = 0; z < 31; z++)

            {
                janmax[z] = 0;
            }


            for (int q = 0; q < num_jan; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    jan_exs.moveToFirst();
                } else if (q > 0) {
                    jan_exs.moveToNext();
                }

                janmax[q] = Integer.parseInt(jan_exs.getString(0));

            }

            Cursor jan_exsd = explusdb.rawQuery("Select date2 FROM sets where date2 BETWEEN '" + history.YR + "-01-01' AND '" + history.YR + "-01-31' AND Exercise ='" + history.EX + "'", null);

            String[] jandate = new String[31];


            for (int q = 0; q < num_jan; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    jan_exsd.moveToFirst();
                } else if (q > 0) {
                    jan_exsd.moveToNext();
                }

                jandate[q] = jan_exsd.getString(0);

            }


            GraphView graph = (GraphView) findViewById(R.id.graph);
            graph.removeAllSeries();
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Day");
            graph.getGridLabelRenderer().setHighlightZeroLines(true);
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(1);
            graph.getViewport().setMaxY(200);
            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(1);
            graph.getViewport().setMaxX(31);


            LineGraphSeries series = new LineGraphSeries(new DataPoint[]{

                    new DataPoint(1, janmax[0]),
                    new DataPoint(2, janmax[1]),
                    new DataPoint(3, janmax[2]),
                    new DataPoint(4, janmax[3]),
                    new DataPoint(5, janmax[4]),
                    new DataPoint(6, janmax[5]),
                    new DataPoint(7, janmax[6]),
                    new DataPoint(8, janmax[7]),
                    new DataPoint(9, janmax[8]),
                    new DataPoint(10, janmax[9]),
                    new DataPoint(11, janmax[10]),
                    new DataPoint(12, janmax[11]),
                    new DataPoint(13, janmax[12]),
                    new DataPoint(14, janmax[13]),
                    new DataPoint(15, janmax[14]),
                    new DataPoint(16, janmax[15]),
                    new DataPoint(17, janmax[16]),
                    new DataPoint(18, janmax[17]),
                    new DataPoint(19, janmax[18]),
                    new DataPoint(20, janmax[19]),
                    new DataPoint(21, janmax[20]),
                    new DataPoint(22, janmax[21]),
                    new DataPoint(23, janmax[22]),
                    new DataPoint(24, janmax[23]),
                    new DataPoint(25, janmax[24]),
                    new DataPoint(26, janmax[25]),
                    new DataPoint(27, janmax[26]),
                    new DataPoint(28, janmax[27]),
                    new DataPoint(29, janmax[28]),
                    new DataPoint(30, janmax[29]),
                    new DataPoint(31, janmax[30])

            });

            graph.addSeries(series);

        }
    }

    public void feb_graph() {

        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);

        Cursor db_read_num_feb;
        if (history.YR.equals(2016) || history.YR.equals(2020) || history.YR.equals(2024) || history.YR.equals(2028) || history.YR.equals(2032)) {

            db_read_num_feb = explusdb.rawQuery("Select count(*) FROM sets where date2 BETWEEN '" + history.YR + "-02-01' AND '" + history.YR + "-02-29' AND Exercise ='" + history.EX + "'", null);

        } else {
            db_read_num_feb = explusdb.rawQuery("Select count(*) FROM sets where date2 BETWEEN '" + history.YR + "-02-01' AND '" + history.YR + "-02-28' AND Exercise ='" + history.EX + "'", null);
        }


        db_read_num_feb.moveToFirst();
        int num_feb = db_read_num_feb.getInt(0);

        if (num_feb == 0) {
            noHistoryFoundMonth();
        } else {
            HistoryFoundMonth();


            Integer[] febmax28 = new Integer[28];
            Integer[] febmax29 = new Integer[29];

            String[] febdate28 = new String[28];
            String[] febdate29 = new String[29];


            for (int z = 0; z < 28; z++)

            {
                febmax28[z] = 0;
            }


            for (int z = 0; z < 29; z++)

            {
                febmax29[z] = 0;
            }


            Cursor feb_exs;
            if (history.YR.equals(2016) || history.YR.equals(2020) || history.YR.equals(2024) || history.YR.equals(2028) || history.YR.equals(2032)) {
                feb_exs = explusdb.rawQuery("Select r_max FROM sets where date2 BETWEEN '" + history.YR + "-02-01' AND '" + history.YR + "-02-29' AND Exercise ='" + history.EX + "'", null);


                for (int q = 0; q < num_feb; q++) {

                    // if (MainActivity.abs_list.size() == 0) {

                    if (q == 0) {
                        feb_exs.moveToFirst();
                    } else if (q > 0) {
                        feb_exs.moveToNext();
                    }


                    febmax29[q] = Integer.parseInt(feb_exs.getString(0));

                }

            } else {
                feb_exs = explusdb.rawQuery("Select r_max FROM sets where date2 BETWEEN '" + history.YR + "-02-01' AND '" + history.YR + "-02-28' AND Exercise ='" + history.EX + "'", null);


                for (int q = 0; q < num_feb; q++) {

                    // if (MainActivity.abs_list.size() == 0) {

                    if (q == 0) {
                        feb_exs.moveToFirst();
                    } else if (q > 0) {
                        feb_exs.moveToNext();
                    }


                    febmax28[q] = Integer.parseInt(feb_exs.getString(0));

                }

            }


            Cursor feb_exsd;
            if (history.YR.equals(2016) || history.YR.equals(2020) || history.YR.equals(2024) || history.YR.equals(2028) || history.YR.equals(2032)) {
                feb_exsd = explusdb.rawQuery("Select date2 FROM sets where date2 BETWEEN '" + history.YR + "-02-01' AND '" + history.YR + "-02-29' AND Exercise ='" + history.EX + "'", null);


                for (int q = 0; q < num_feb; q++) {

                    // if (MainActivity.abs_list.size() == 0) {

                    if (q == 0) {
                        feb_exsd.moveToFirst();
                    } else if (q > 0) {
                        feb_exsd.moveToNext();
                    }


                    febdate28[q] = feb_exsd.getString(0);

                }
            } else {
                feb_exsd = explusdb.rawQuery("Select date2 FROM sets where date2 BETWEEN '" + history.YR + "-02-01' AND '" + history.YR + "-02-28' AND Exercise ='" + history.EX + "'", null);


                for (int q = 0; q < num_feb; q++) {

                    // if (MainActivity.abs_list.size() == 0) {

                    if (q == 0) {
                        feb_exsd.moveToFirst();
                    } else if (q > 0) {
                        feb_exsd.moveToNext();
                    }


                    febdate29[q] = feb_exsd.getString(0);

                }
            }


            if (history.YR.equals(2016) || history.YR.equals(2020) || history.YR.equals(2024) || history.YR.equals(2028) || history.YR.equals(2032)) {


                GraphView graph = (GraphView) findViewById(R.id.graph);
                graph.removeAllSeries();
                StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
                graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
                graph.getGridLabelRenderer().setHorizontalAxisTitle("Day");
                graph.getGridLabelRenderer().setHighlightZeroLines(true);
                graph.getViewport().setYAxisBoundsManual(true);
                graph.getViewport().setMinY(1);
                graph.getViewport().setMaxY(200);
                graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setMinX(1);
                graph.getViewport().setMaxX(28);


                LineGraphSeries series = new LineGraphSeries(new DataPoint[]{

                        new DataPoint(1, febmax28[0]),
                        new DataPoint(2, febmax28[1]),
                        new DataPoint(3, febmax28[2]),
                        new DataPoint(4, febmax28[3]),
                        new DataPoint(5, febmax28[4]),
                        new DataPoint(6, febmax28[5]),
                        new DataPoint(7, febmax28[6]),
                        new DataPoint(8, febmax28[7]),
                        new DataPoint(9, febmax28[8]),
                        new DataPoint(10, febmax28[9]),
                        new DataPoint(11, febmax28[10]),
                        new DataPoint(12, febmax28[11]),
                        new DataPoint(13, febmax28[12]),
                        new DataPoint(14, febmax28[13]),
                        new DataPoint(15, febmax28[14]),
                        new DataPoint(16, febmax28[15]),
                        new DataPoint(17, febmax28[16]),
                        new DataPoint(18, febmax28[17]),
                        new DataPoint(19, febmax28[18]),
                        new DataPoint(20, febmax28[19]),
                        new DataPoint(21, febmax28[20]),
                        new DataPoint(22, febmax28[21]),
                        new DataPoint(23, febmax28[22]),
                        new DataPoint(24, febmax28[23]),
                        new DataPoint(25, febmax28[24]),
                        new DataPoint(26, febmax28[25]),
                        new DataPoint(27, febmax28[26]),
                        new DataPoint(28, febmax28[27])

                });

                graph.addSeries(series);
            } else {
                GraphView graph = (GraphView) findViewById(R.id.graph);
                graph.removeAllSeries();
                StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
                graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
                graph.getGridLabelRenderer().setHorizontalAxisTitle("Day");
                graph.getGridLabelRenderer().setHighlightZeroLines(true);
                graph.getViewport().setYAxisBoundsManual(true);
                graph.getViewport().setMinY(1);
                graph.getViewport().setMaxY(200);
                graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setMinX(1);
                graph.getViewport().setMaxX(29);


                LineGraphSeries series = new LineGraphSeries(new DataPoint[]{

                        new DataPoint(1, febmax29[0]),
                        new DataPoint(2, febmax29[1]),
                        new DataPoint(3, febmax29[2]),
                        new DataPoint(4, febmax29[3]),
                        new DataPoint(5, febmax29[4]),
                        new DataPoint(6, febmax29[5]),
                        new DataPoint(7, febmax29[6]),
                        new DataPoint(8, febmax29[7]),
                        new DataPoint(9, febmax29[8]),
                        new DataPoint(10, febmax29[9]),
                        new DataPoint(11, febmax29[10]),
                        new DataPoint(12, febmax29[11]),
                        new DataPoint(13, febmax29[12]),
                        new DataPoint(14, febmax29[13]),
                        new DataPoint(15, febmax29[14]),
                        new DataPoint(16, febmax29[15]),
                        new DataPoint(17, febmax29[16]),
                        new DataPoint(18, febmax29[17]),
                        new DataPoint(19, febmax29[18]),
                        new DataPoint(20, febmax29[19]),
                        new DataPoint(21, febmax29[20]),
                        new DataPoint(22, febmax29[21]),
                        new DataPoint(23, febmax29[22]),
                        new DataPoint(24, febmax29[23]),
                        new DataPoint(25, febmax29[24]),
                        new DataPoint(26, febmax29[25]),
                        new DataPoint(27, febmax29[26]),
                        new DataPoint(28, febmax29[27]),
                        new DataPoint(29, febmax29[28])

                });

                graph.addSeries(series);
            }


            //"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});

        }
    }
    public void mar_graph() {

        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);


        Cursor db_read_num_mar = explusdb.rawQuery("Select count(*) FROM sets where date2 BETWEEN '" + history.YR + "-03-01' AND '" + history.YR + "-03-31' AND Exercise ='" + history.EX + "'", null);
        db_read_num_mar.moveToFirst();
        int num_mar = db_read_num_mar.getInt(0);
        if (num_mar == 0) {
            noHistoryFoundMonth();
        } else {
            HistoryFoundMonth();


            Cursor mar_exs = explusdb.rawQuery("Select r_max FROM sets where date2 BETWEEN '" + history.YR + "-03-01' AND '" + history.YR + "-03-31' AND Exercise ='" + history.EX + "'", null);


            Integer[] marmax = new Integer[31];

            for (int z = 0; z < 31; z++)

            {
                marmax[z] = 0;
            }


            for (int q = 0; q < num_mar; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    mar_exs.moveToFirst();
                } else if (q > 0) {
                    mar_exs.moveToNext();
                }


                marmax[q] = Integer.parseInt(mar_exs.getString(0));

            }


            Cursor mar_exsd = explusdb.rawQuery("Select date2 FROM sets where date2 BETWEEN '" + history.YR + "-03-01' AND '" + history.YR + "-03-31' AND Exercise ='" + history.EX + "'", null);

            String[] mardate = new String[31];


            for (int q = 0; q < num_mar; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    mar_exsd.moveToFirst();
                } else if (q > 0) {
                    mar_exsd.moveToNext();
                }

                mardate[q] = mar_exsd.getString(0);

            }


            GraphView graph = (GraphView) findViewById(R.id.graph);

            graph.removeAllSeries();
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);

            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Day");

            graph.getGridLabelRenderer().setHighlightZeroLines(true);

            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(1);
            graph.getViewport().setMaxY(200);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(1);
            graph.getViewport().setMaxX(31);


            LineGraphSeries series = new LineGraphSeries(new DataPoint[]{


                    new DataPoint(1, marmax[0]),
                    new DataPoint(2, marmax[1]),
                    new DataPoint(3, marmax[2]),
                    new DataPoint(4, marmax[3]),
                    new DataPoint(5, marmax[4]),
                    new DataPoint(6, marmax[5]),
                    new DataPoint(7, marmax[6]),
                    new DataPoint(8, marmax[7]),
                    new DataPoint(9, marmax[8]),
                    new DataPoint(10, marmax[9]),
                    new DataPoint(11, marmax[10]),
                    new DataPoint(12, marmax[11]),
                    new DataPoint(13, marmax[12]),
                    new DataPoint(14, marmax[13]),
                    new DataPoint(15, marmax[14]),
                    new DataPoint(16, marmax[15]),
                    new DataPoint(17, marmax[16]),
                    new DataPoint(18, marmax[17]),
                    new DataPoint(19, marmax[18]),
                    new DataPoint(20, marmax[19]),
                    new DataPoint(21, marmax[20]),
                    new DataPoint(22, marmax[21]),
                    new DataPoint(23, marmax[22]),
                    new DataPoint(24, marmax[23]),
                    new DataPoint(25, marmax[24]),
                    new DataPoint(26, marmax[25]),
                    new DataPoint(27, marmax[26]),
                    new DataPoint(28, marmax[27]),
                    new DataPoint(29, marmax[28]),
                    new DataPoint(30, marmax[29]),
                    new DataPoint(31, marmax[30])

            });

            graph.addSeries(series);


            //"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});

        }
    }
    public void apr_graph() {

        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);

        Cursor db_read_num_apr = explusdb.rawQuery("Select count(*) FROM sets where date2 BETWEEN '" + history.YR + "-04-01' AND '" + history.YR + "-04-30' AND Exercise ='" + history.EX + "'", null);
        db_read_num_apr.moveToFirst();
        int num_apr = db_read_num_apr.getInt(0);
        if (num_apr == 0) {
            noHistoryFoundMonth();
        } else {
            HistoryFoundMonth();


            Cursor apr_exs = explusdb.rawQuery("Select r_max FROM sets where date2 BETWEEN '" + history.YR + "-04-01' AND '" + history.YR + "-04-30' AND Exercise ='" + history.EX + "'", null);

            Integer[] aprmax = new Integer[30];

            for (int z = 0; z < 30; z++)

            {
                aprmax[z] = 0;
            }


            for (int q = 0; q < num_apr; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    apr_exs.moveToFirst();
                } else if (q > 0) {
                    apr_exs.moveToNext();
                }

                aprmax[q] = Integer.parseInt(apr_exs.getString(0));

            }


            Cursor apr_exsd = explusdb.rawQuery("Select date2 FROM sets where date2 BETWEEN '" + history.YR + "-04-01' AND '" + history.YR + "-04-31' AND Exercise ='" + history.EX + "'", null);

            String[] aprdate = new String[31];


            for (int q = 0; q < num_apr; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    apr_exsd.moveToFirst();
                } else if (q > 0) {
                    apr_exsd.moveToNext();
                }

                aprdate[q] = apr_exsd.getString(0);

            }

            GraphView graph = (GraphView) findViewById(R.id.graph);

            graph.removeAllSeries();
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);

            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Day");

            graph.getGridLabelRenderer().setHighlightZeroLines(true);

            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(1);
            graph.getViewport().setMaxY(200);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(1);
            graph.getViewport().setMaxX(30);


            LineGraphSeries series = new LineGraphSeries(new DataPoint[]{

                    new DataPoint(1, aprmax[0]),
                    new DataPoint(2, aprmax[1]),
                    new DataPoint(3, aprmax[2]),
                    new DataPoint(4, aprmax[3]),
                    new DataPoint(5, aprmax[4]),
                    new DataPoint(6, aprmax[5]),
                    new DataPoint(7, aprmax[6]),
                    new DataPoint(8, aprmax[7]),
                    new DataPoint(9, aprmax[8]),
                    new DataPoint(10, aprmax[9]),
                    new DataPoint(11, aprmax[10]),
                    new DataPoint(12, aprmax[11]),
                    new DataPoint(13, aprmax[12]),
                    new DataPoint(14, aprmax[13]),
                    new DataPoint(15, aprmax[14]),
                    new DataPoint(16, aprmax[15]),
                    new DataPoint(17, aprmax[16]),
                    new DataPoint(18, aprmax[17]),
                    new DataPoint(19, aprmax[18]),
                    new DataPoint(20, aprmax[19]),
                    new DataPoint(21, aprmax[20]),
                    new DataPoint(22, aprmax[21]),
                    new DataPoint(23, aprmax[22]),
                    new DataPoint(24, aprmax[23]),
                    new DataPoint(25, aprmax[24]),
                    new DataPoint(26, aprmax[25]),
                    new DataPoint(27, aprmax[26]),
                    new DataPoint(28, aprmax[27]),
                    new DataPoint(29, aprmax[28]),
                    new DataPoint(30, aprmax[29])

            });

            graph.addSeries(series);


            //"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});

        }
    }

    public void may_graph() {

        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);
        Cursor db_read_num_may = explusdb.rawQuery("Select count(*) FROM sets where date2 BETWEEN '" + history.YR + "-05-01' AND '" + history.YR + "-05-31' AND Exercise ='" + history.EX + "'", null);
        db_read_num_may.moveToFirst();
        int num_may = db_read_num_may.getInt(0);
        if (num_may == 0) {
            noHistoryFoundMonth();
        } else {
            HistoryFoundMonth();


            Cursor may_exs = explusdb.rawQuery("Select r_max FROM sets where date2 BETWEEN '" + history.YR + "-05-01' AND '" + history.YR + "-05-31' AND Exercise ='" + history.EX + "'", null);


            Integer[] maymax = new Integer[31];

            for (int z = 0; z < 31; z++)

            {
                maymax[z] = 0;
            }


            for (int q = 0; q < num_may; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    may_exs.moveToFirst();
                } else if (q > 0) {
                    may_exs.moveToNext();
                }

                maymax[q] = Integer.parseInt(may_exs.getString(0));

            }

            Cursor may_exsd = explusdb.rawQuery("Select date2 FROM sets where date2 BETWEEN '" + history.YR + "-05-01' AND '" + history.YR + "-05-31' AND Exercise ='" + history.EX + "'", null);

            String[] maydate = new String[31];


            for (int q = 0; q < num_may; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    may_exsd.moveToFirst();
                } else if (q > 0) {
                    may_exsd.moveToNext();
                }

                maydate[q] = may_exsd.getString(0);

            }

            GraphView graph = (GraphView) findViewById(R.id.graph);

            graph.removeAllSeries();
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);

            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Day");

            graph.getGridLabelRenderer().setHighlightZeroLines(true);

            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(1);
            graph.getViewport().setMaxY(200);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(1);
            graph.getViewport().setMaxX(31);


            LineGraphSeries series = new LineGraphSeries(new DataPoint[]{

                    new DataPoint(1, maymax[0]),
                    new DataPoint(2, maymax[1]),
                    new DataPoint(3, maymax[2]),
                    new DataPoint(4, maymax[3]),
                    new DataPoint(5, maymax[4]),
                    new DataPoint(6, maymax[5]),
                    new DataPoint(7, maymax[6]),
                    new DataPoint(8, maymax[7]),
                    new DataPoint(9, maymax[8]),
                    new DataPoint(10, maymax[9]),
                    new DataPoint(11, maymax[10]),
                    new DataPoint(12, maymax[11]),
                    new DataPoint(13, maymax[12]),
                    new DataPoint(14, maymax[13]),
                    new DataPoint(15, maymax[14]),
                    new DataPoint(16, maymax[15]),
                    new DataPoint(17, maymax[16]),
                    new DataPoint(18, maymax[17]),
                    new DataPoint(19, maymax[18]),
                    new DataPoint(20, maymax[19]),
                    new DataPoint(21, maymax[20]),
                    new DataPoint(22, maymax[21]),
                    new DataPoint(23, maymax[22]),
                    new DataPoint(24, maymax[23]),
                    new DataPoint(25, maymax[24]),
                    new DataPoint(26, maymax[25]),
                    new DataPoint(27, maymax[26]),
                    new DataPoint(28, maymax[27]),
                    new DataPoint(29, maymax[28]),
                    new DataPoint(30, maymax[29]),
                    new DataPoint(31, maymax[30])

            });

            graph.addSeries(series);


            //"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});

        }
    }

    public void jun_graph() {

        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);

        Cursor db_read_num_jun = explusdb.rawQuery("Select count(*) FROM sets where date2 BETWEEN '" + history.YR + "-06-01' AND '" + history.YR + "-06-30' AND Exercise ='" + history.EX + "'", null);
        db_read_num_jun.moveToFirst();
        int num_jun = db_read_num_jun.getInt(0);

        if (num_jun == 0) {
            noHistoryFoundMonth();
        } else {
            HistoryFoundMonth();

            Cursor jun_exs = explusdb.rawQuery("Select r_max FROM sets where date2 BETWEEN '" + history.YR + "-06-01' AND '" + history.YR + "-06-31' AND Exercise ='" + history.EX + "'", null);


            Integer[] junmax = new Integer[31];

            for (int z = 0; z < 31; z++)

            {
                junmax[z] = 0;
            }


            for (int q = 0; q < num_jun; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    jun_exs.moveToFirst();
                } else if (q > 0) {
                    jun_exs.moveToNext();
                }

                junmax[q] = Integer.parseInt(jun_exs.getString(0));

            }


            Cursor jun_exsd = explusdb.rawQuery("Select date2 FROM sets where date2 BETWEEN '" + history.YR + "-06-01' AND '" + history.YR + "-06-31' AND Exercise ='" + history.EX + "'", null);

            String[] jundate = new String[31];


            for (int q = 0; q < num_jun; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    jun_exsd.moveToFirst();
                } else if (q > 0) {
                    jun_exsd.moveToNext();
                }

                jundate[q] = jun_exsd.getString(0);

            }

            GraphView graph = (GraphView) findViewById(R.id.graph);

            graph.removeAllSeries();
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);

            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Day");

            graph.getGridLabelRenderer().setHighlightZeroLines(true);

            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(1);
            graph.getViewport().setMaxY(200);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(1);
            graph.getViewport().setMaxX(30);


            LineGraphSeries series = new LineGraphSeries(new DataPoint[]{

                    new DataPoint(1, junmax[0]),
                    new DataPoint(2, junmax[1]),
                    new DataPoint(3, junmax[2]),
                    new DataPoint(4, junmax[3]),
                    new DataPoint(5, junmax[4]),
                    new DataPoint(6, junmax[5]),
                    new DataPoint(7, junmax[6]),
                    new DataPoint(8, junmax[7]),
                    new DataPoint(9, junmax[8]),
                    new DataPoint(10, junmax[9]),
                    new DataPoint(11, junmax[10]),
                    new DataPoint(12, junmax[11]),
                    new DataPoint(13, junmax[12]),
                    new DataPoint(14, junmax[13]),
                    new DataPoint(15, junmax[14]),
                    new DataPoint(16, junmax[15]),
                    new DataPoint(17, junmax[16]),
                    new DataPoint(18, junmax[17]),
                    new DataPoint(19, junmax[18]),
                    new DataPoint(20, junmax[19]),
                    new DataPoint(21, junmax[20]),
                    new DataPoint(22, junmax[21]),
                    new DataPoint(23, junmax[22]),
                    new DataPoint(24, junmax[23]),
                    new DataPoint(25, junmax[24]),
                    new DataPoint(26, junmax[25]),
                    new DataPoint(27, junmax[26]),
                    new DataPoint(28, junmax[27]),
                    new DataPoint(29, junmax[28]),
                    new DataPoint(30, junmax[29])

            });

            graph.addSeries(series);


            //"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});

        }
    }
    public void jul_graph() {

        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);
        Cursor db_read_num_jul = explusdb.rawQuery("Select count(*) FROM sets where date2 BETWEEN '" + history.YR + "-07-01' AND '" + history.YR + "-07-31' AND Exercise ='" + history.EX + "'", null);
        db_read_num_jul.moveToFirst();
        int num_jul = db_read_num_jul.getInt(0);
        if (num_jul == 0) {
            noHistoryFoundMonth();
        } else {
            HistoryFoundMonth();


            Cursor jul_exs = explusdb.rawQuery("Select r_max FROM sets where date2 BETWEEN '" + history.YR + "-07-01' AND '" + history.YR + "-07-31' AND Exercise ='" + history.EX + "'", null);

            Integer[] julmax = new Integer[31];

            for (int z = 0; z < 31; z++)

            {
                julmax[z] = 0;
            }


            for (int q = 0; q < num_jul; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    jul_exs.moveToFirst();
                } else if (q > 0) {
                    jul_exs.moveToNext();
                }

                julmax[q] = Integer.parseInt(jul_exs.getString(0));

            }


            Cursor jul_exsd = explusdb.rawQuery("Select date2 FROM sets where date2 BETWEEN '" + history.YR + "-07-01' AND '" + history.YR + "-07-31' AND Exercise ='" + history.EX + "'", null);

            String[] juldate = new String[31];


            for (int q = 0; q < num_jul; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    jul_exsd.moveToFirst();
                } else if (q > 0) {
                    jul_exsd.moveToNext();
                }

                juldate[q] = jul_exsd.getString(0);

            }

            GraphView graph = (GraphView) findViewById(R.id.graph);
            graph.removeAllSeries();
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Day");
            graph.getGridLabelRenderer().setHighlightZeroLines(true);
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(1);
            graph.getViewport().setMaxY(200);
            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(1);
            graph.getViewport().setMaxX(31);


            LineGraphSeries series = new LineGraphSeries(new DataPoint[]{


                    new DataPoint(1, julmax[0]),
                    new DataPoint(2, julmax[1]),
                    new DataPoint(3, julmax[2]),
                    new DataPoint(4, julmax[3]),
                    new DataPoint(5, julmax[4]),
                    new DataPoint(6, julmax[5]),
                    new DataPoint(7, julmax[6]),
                    new DataPoint(8, julmax[7]),
                    new DataPoint(9, julmax[8]),
                    new DataPoint(10, julmax[9]),
                    new DataPoint(11, julmax[10]),
                    new DataPoint(12, julmax[11]),
                    new DataPoint(13, julmax[12]),
                    new DataPoint(14, julmax[13]),
                    new DataPoint(15, julmax[14]),
                    new DataPoint(16, julmax[15]),
                    new DataPoint(17, julmax[16]),
                    new DataPoint(18, julmax[17]),
                    new DataPoint(19, julmax[18]),
                    new DataPoint(20, julmax[19]),
                    new DataPoint(21, julmax[20]),
                    new DataPoint(22, julmax[21]),
                    new DataPoint(23, julmax[22]),
                    new DataPoint(24, julmax[23]),
                    new DataPoint(25, julmax[24]),
                    new DataPoint(26, julmax[25]),
                    new DataPoint(27, julmax[26]),
                    new DataPoint(28, julmax[27]),
                    new DataPoint(29, julmax[28]),
                    new DataPoint(30, julmax[29]),
                    new DataPoint(31, julmax[30])

            });


            graph.addSeries(series);


            //"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});

        }
    }

    public void aug_graph() {

        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);

        Cursor db_read_num_aug = explusdb.rawQuery("Select count(*) FROM sets where date2 BETWEEN '" + history.YR + "-08-01' AND '" + history.YR + "-08-31' AND Exercise ='" + history.EX + "'", null);
        db_read_num_aug.moveToFirst();
        int num_aug = db_read_num_aug.getInt(0);

        if (num_aug == 0) {
            noHistoryFoundMonth();
        } else {
            HistoryFoundMonth();

            Cursor aug_exs = explusdb.rawQuery("Select r_max FROM sets where date2 BETWEEN '" + history.YR + "-08-01' AND '" + history.YR + "-08-31' AND Exercise ='" + history.EX + "'", null);

            Integer[] augmax = new Integer[31];
            ;

            for (int z = 0; z < 31; z++)

            {
                augmax[z] = 0;
            }


            for (int q = 0; q < num_aug; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    aug_exs.moveToFirst();
                } else if (q > 0) {
                    aug_exs.moveToNext();
                }

                augmax[q] = Integer.parseInt(aug_exs.getString(0));

            }

            Cursor aug_exsd = explusdb.rawQuery("Select date2 FROM sets where date2 BETWEEN '" + history.YR + "-08-01' AND '" + history.YR + "-08-31' AND Exercise ='" + history.EX + "'", null);

            String[] augdate = new String[31];


            for (int q = 0; q < num_aug; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    aug_exsd.moveToFirst();
                } else if (q > 0) {
                    aug_exsd.moveToNext();
                }

                augdate[q] = aug_exsd.getString(0);

            }

            GraphView graph = (GraphView) findViewById(R.id.graph);

            graph.removeAllSeries();
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);

            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Day");

            graph.getGridLabelRenderer().setHighlightZeroLines(true);

            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(1);
            graph.getViewport().setMaxY(200);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(1);
            graph.getViewport().setMaxX(31);

            LineGraphSeries series = new LineGraphSeries(new DataPoint[]{

                    new DataPoint(1, augmax[0]),
                    new DataPoint(2, augmax[1]),
                    new DataPoint(3, augmax[2]),
                    new DataPoint(4, augmax[3]),
                    new DataPoint(5, augmax[4]),
                    new DataPoint(6, augmax[5]),
                    new DataPoint(7, augmax[6]),
                    new DataPoint(8, augmax[7]),
                    new DataPoint(9, augmax[8]),
                    new DataPoint(10, augmax[9]),
                    new DataPoint(11, augmax[10]),
                    new DataPoint(12, augmax[11]),
                    new DataPoint(13, augmax[12]),
                    new DataPoint(14, augmax[13]),
                    new DataPoint(15, augmax[14]),
                    new DataPoint(16, augmax[15]),
                    new DataPoint(17, augmax[16]),
                    new DataPoint(18, augmax[17]),
                    new DataPoint(19, augmax[18]),
                    new DataPoint(20, augmax[19]),
                    new DataPoint(21, augmax[20]),
                    new DataPoint(22, augmax[21]),
                    new DataPoint(23, augmax[22]),
                    new DataPoint(24, augmax[23]),
                    new DataPoint(25, augmax[24]),
                    new DataPoint(26, augmax[25]),
                    new DataPoint(27, augmax[26]),
                    new DataPoint(28, augmax[27]),
                    new DataPoint(29, augmax[28]),
                    new DataPoint(30, augmax[29]),
                    new DataPoint(31, augmax[30])

            });

            graph.addSeries(series);


            //"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});

        }
    }

    public void sep_graph() {

        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);
        Cursor db_read_num_sep = explusdb.rawQuery("Select count(*) FROM sets where date2 BETWEEN '" + history.YR + "-09-01' AND '" + history.YR + "-09-30' AND Exercise ='" + history.EX + "'", null);
        db_read_num_sep.moveToFirst();
        int num_sep = db_read_num_sep.getInt(0);
        if (num_sep == 0) {
            noHistoryFoundMonth();
        } else {
            HistoryFoundMonth();


            Cursor sep_exs = explusdb.rawQuery("Select r_max FROM sets where date2 BETWEEN '" + history.YR + "-09-01' AND '" + history.YR + "-09-30' AND Exercise ='" + history.EX + "'", null);


            Integer[] sepmax = new Integer[30];


            for (int z = 0; z < 30; z++)

            {
                sepmax[z] = 0;
            }


            for (int q = 0; q < num_sep; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    sep_exs.moveToFirst();
                } else if (q > 0) {
                    sep_exs.moveToNext();
                }

                sepmax[q] = Integer.parseInt(sep_exs.getString(0));

            }

            Cursor sep_exsd = explusdb.rawQuery("Select date2 FROM sets where date2 BETWEEN '" + history.YR + "-09-01' AND '" + history.YR + "-09-30' AND Exercise ='" + history.EX + "'", null);

            String[] sepdate = new String[30];


            for (int q = 0; q < num_sep; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    sep_exsd.moveToFirst();
                } else if (q > 0) {
                    sep_exsd.moveToNext();
                }

                sepdate[q] = sep_exsd.getString(0);

            }
            GraphView graph = (GraphView) findViewById(R.id.graph);

            graph.removeAllSeries();
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);

            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Day");

            graph.getGridLabelRenderer().setHighlightZeroLines(true);

            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(1);
            graph.getViewport().setMaxY(200);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(1);
            graph.getViewport().setMaxX(30);


            LineGraphSeries series = new LineGraphSeries(new DataPoint[]{

                    new DataPoint(1, sepmax[0]),
                    new DataPoint(2, sepmax[1]),
                    new DataPoint(3, sepmax[2]),
                    new DataPoint(4, sepmax[3]),
                    new DataPoint(5, sepmax[4]),
                    new DataPoint(6, sepmax[5]),
                    new DataPoint(7, sepmax[6]),
                    new DataPoint(8, sepmax[7]),
                    new DataPoint(9, sepmax[8]),
                    new DataPoint(10, sepmax[9]),
                    new DataPoint(11, sepmax[10]),
                    new DataPoint(12, sepmax[11]),
                    new DataPoint(13, sepmax[12]),
                    new DataPoint(14, sepmax[13]),
                    new DataPoint(15, sepmax[14]),
                    new DataPoint(16, sepmax[15]),
                    new DataPoint(17, sepmax[16]),
                    new DataPoint(18, sepmax[17]),
                    new DataPoint(19, sepmax[18]),
                    new DataPoint(20, sepmax[19]),
                    new DataPoint(21, sepmax[20]),
                    new DataPoint(22, sepmax[21]),
                    new DataPoint(23, sepmax[22]),
                    new DataPoint(24, sepmax[23]),
                    new DataPoint(25, sepmax[24]),
                    new DataPoint(26, sepmax[25]),
                    new DataPoint(27, sepmax[26]),
                    new DataPoint(28, sepmax[27]),
                    new DataPoint(29, sepmax[28]),
                    new DataPoint(30, sepmax[29])

            });

            graph.addSeries(series);


            //"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});

        }
    }
    public void oct_graph() {

        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);

        Cursor db_read_num_oct = explusdb.rawQuery("Select count(*) FROM sets where date2 BETWEEN '" + history.YR + "-10-01' AND '" + history.YR + "-10-31' AND Exercise ='" + history.EX + "'", null);
        db_read_num_oct.moveToFirst();
        int num_oct = db_read_num_oct.getInt(0);
        if (num_oct == 0) {
            noHistoryFoundMonth();
        } else {
            HistoryFoundMonth();


            Cursor oct_exs = explusdb.rawQuery("Select r_max FROM sets where date2 BETWEEN '" + history.YR + "-10-01' AND '" + history.YR + "-10-31' AND Exercise ='" + history.EX + "'", null);

            Integer[] octmax = new Integer[31];


            for (int z = 0; z < 31; z++)

            {
                octmax[z] = 0;
            }


            for (int q = 0; q < num_oct; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    oct_exs.moveToFirst();
                } else if (q > 0) {
                    oct_exs.moveToNext();
                }

                octmax[q] = Integer.parseInt(oct_exs.getString(0));

            }


            Cursor oct_exsd = explusdb.rawQuery("Select date2 FROM sets where date2 BETWEEN '" + history.YR + "-10-01' AND '" + history.YR + "-10-31' AND Exercise ='" + history.EX + "'", null);

            String[] octdate = new String[31];


            for (int q = 0; q < num_oct; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    oct_exsd.moveToFirst();
                } else if (q > 0) {
                    oct_exsd.moveToNext();
                }

                octdate[q] = oct_exsd.getString(0);

            }

            GraphView graph = (GraphView) findViewById(R.id.graph);

            graph.removeAllSeries();
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);

            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Day");

            graph.getGridLabelRenderer().setHighlightZeroLines(true);

            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(1);
            graph.getViewport().setMaxY(200);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(1);
            graph.getViewport().setMaxX(31);


            LineGraphSeries series = new LineGraphSeries(new DataPoint[]{

                    new DataPoint(1, octmax[0]),
                    new DataPoint(2, octmax[1]),
                    new DataPoint(3, octmax[2]),
                    new DataPoint(4, octmax[3]),
                    new DataPoint(5, octmax[4]),
                    new DataPoint(6, octmax[5]),
                    new DataPoint(7, octmax[6]),
                    new DataPoint(8, octmax[7]),
                    new DataPoint(9, octmax[8]),
                    new DataPoint(10, octmax[9]),
                    new DataPoint(11, octmax[10]),
                    new DataPoint(12, octmax[11]),
                    new DataPoint(13, octmax[12]),
                    new DataPoint(14, octmax[13]),
                    new DataPoint(15, octmax[14]),
                    new DataPoint(16, octmax[15]),
                    new DataPoint(17, octmax[16]),
                    new DataPoint(18, octmax[17]),
                    new DataPoint(19, octmax[18]),
                    new DataPoint(20, octmax[19]),
                    new DataPoint(21, octmax[20]),
                    new DataPoint(22, octmax[21]),
                    new DataPoint(23, octmax[22]),
                    new DataPoint(24, octmax[23]),
                    new DataPoint(25, octmax[24]),
                    new DataPoint(26, octmax[25]),
                    new DataPoint(27, octmax[26]),
                    new DataPoint(28, octmax[27]),
                    new DataPoint(29, octmax[28]),
                    new DataPoint(30, octmax[29]),
                    new DataPoint(31, octmax[30])

            });

            graph.addSeries(series);


            //"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});

        }
    }

    public void nov_graph() {

        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);

        Cursor db_read_num_nov = explusdb.rawQuery("Select count(*) FROM sets where date2 BETWEEN '" + history.YR + "-11-01' AND '" + history.YR + "-11-30' AND Exercise ='" + history.EX + "'", null);
        db_read_num_nov.moveToFirst();
        int num_nov = db_read_num_nov.getInt(0);

        if (num_nov == 0) {
            noHistoryFoundMonth();
        } else {
            HistoryFoundMonth();


            Cursor nov_exs = explusdb.rawQuery("Select r_max FROM sets where date2 BETWEEN '" + history.YR + "-11-01' AND '" + history.YR + "-11-30' AND Exercise ='" + history.EX + "'", null);

            Integer[] novmax = new Integer[30];
            ;

            for (int z = 0; z < 30; z++)

            {
                novmax[z] = 0;
            }


            for (int q = 0; q < num_nov; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    nov_exs.moveToFirst();
                } else if (q > 0) {
                    nov_exs.moveToNext();
                }

                novmax[q] = Integer.parseInt(nov_exs.getString(0));

            }


            Cursor nov_exsd = explusdb.rawQuery("Select date2 FROM sets where date2 BETWEEN '" + history.YR + "-11-01' AND '" + history.YR + "-11-30' AND Exercise ='" + history.EX + "'", null);

            String[] novdate = new String[30];


            for (int q = 0; q < num_nov; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    nov_exsd.moveToFirst();
                } else if (q > 0) {
                    nov_exsd.moveToNext();
                }

                novdate[q] = nov_exsd.getString(0);

            }

            GraphView graph = (GraphView) findViewById(R.id.graph);

            graph.removeAllSeries();
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);

            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Day");

            graph.getGridLabelRenderer().setHighlightZeroLines(true);

            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(1);
            graph.getViewport().setMaxY(200);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(1);
            graph.getViewport().setMaxX(30);


            LineGraphSeries series = new LineGraphSeries(new DataPoint[]{

                    new DataPoint(1, novmax[0]),
                    new DataPoint(2, novmax[1]),
                    new DataPoint(3, novmax[2]),
                    new DataPoint(4, novmax[3]),
                    new DataPoint(5, novmax[4]),
                    new DataPoint(6, novmax[5]),
                    new DataPoint(7, novmax[6]),
                    new DataPoint(8, novmax[7]),
                    new DataPoint(9, novmax[8]),
                    new DataPoint(10, novmax[9]),
                    new DataPoint(11, novmax[10]),
                    new DataPoint(12, novmax[11]),
                    new DataPoint(13, novmax[12]),
                    new DataPoint(14, novmax[13]),
                    new DataPoint(15, novmax[14]),
                    new DataPoint(16, novmax[15]),
                    new DataPoint(17, novmax[16]),
                    new DataPoint(18, novmax[17]),
                    new DataPoint(19, novmax[18]),
                    new DataPoint(20, novmax[19]),
                    new DataPoint(21, novmax[20]),
                    new DataPoint(22, novmax[21]),
                    new DataPoint(23, novmax[22]),
                    new DataPoint(24, novmax[23]),
                    new DataPoint(25, novmax[24]),
                    new DataPoint(26, novmax[25]),
                    new DataPoint(27, novmax[26]),
                    new DataPoint(28, novmax[27]),
                    new DataPoint(29, novmax[28]),
                    new DataPoint(30, novmax[29])

            });

            graph.addSeries(series);


            //"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});

        }
    }

    public void dec_graph() {

        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);
        Cursor db_read_num_dec = explusdb.rawQuery("Select count(*) FROM sets where date2 BETWEEN '" + history.YR + "-12-01' AND '" + history.YR + "-12-31' AND Exercise ='" + history.EX + "'", null);
        db_read_num_dec.moveToFirst();
        int num_dec = db_read_num_dec.getInt(0);
        if (num_dec == 0) {
            noHistoryFoundMonth();
        } else {
            HistoryFoundMonth();


            Cursor dec_exs = explusdb.rawQuery("Select r_max FROM sets where date2 BETWEEN '" + history.YR + "-12-01' AND '" + history.YR + "-12-31' AND Exercise ='" + history.EX + "'", null);

            Integer[] decmax = new Integer[31];

            for (int z = 0; z < 31; z++)

            {
                decmax[z] = 0;
            }


            for (int q = 0; q < num_dec; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    dec_exs.moveToFirst();
                } else if (q > 0) {
                    dec_exs.moveToNext();
                }

                decmax[q] = Integer.parseInt(dec_exs.getString(0));

            }


            Cursor dec_exsd = explusdb.rawQuery("Select date2 FROM sets where date2 BETWEEN '" + history.YR + "-12-01' AND '" + history.YR + "-12-31' AND Exercise ='" + history.EX + "'", null);

            String[] decdate = new String[31];


            for (int q = 0; q < num_dec; q++) {

                // if (MainActivity.abs_list.size() == 0) {

                if (q == 0) {
                    dec_exsd.moveToFirst();
                } else if (q > 0) {
                    dec_exsd.moveToNext();
                }

                decdate[q] = dec_exsd.getString(0);

            }
            GraphView graph = (GraphView) findViewById(R.id.graph);

            graph.removeAllSeries();
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);

            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Day");

            graph.getGridLabelRenderer().setHighlightZeroLines(true);

            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(1);
            graph.getViewport().setMaxY(200);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(1);
            graph.getViewport().setMaxX(31);


            LineGraphSeries series = new LineGraphSeries(new DataPoint[]{

                    new DataPoint(1, decmax[0]),
                    new DataPoint(2, decmax[1]),
                    new DataPoint(3, decmax[2]),
                    new DataPoint(4, decmax[3]),
                    new DataPoint(5, decmax[4]),
                    new DataPoint(6, decmax[5]),
                    new DataPoint(7, decmax[6]),
                    new DataPoint(8, decmax[7]),
                    new DataPoint(9, decmax[8]),
                    new DataPoint(10, decmax[9]),
                    new DataPoint(11, decmax[10]),
                    new DataPoint(12, decmax[11]),
                    new DataPoint(13, decmax[12]),
                    new DataPoint(14, decmax[13]),
                    new DataPoint(15, decmax[14]),
                    new DataPoint(16, decmax[15]),
                    new DataPoint(17, decmax[16]),
                    new DataPoint(18, decmax[17]),
                    new DataPoint(19, decmax[18]),
                    new DataPoint(20, decmax[19]),
                    new DataPoint(21, decmax[20]),
                    new DataPoint(22, decmax[21]),
                    new DataPoint(23, decmax[22]),
                    new DataPoint(24, decmax[23]),
                    new DataPoint(25, decmax[24]),
                    new DataPoint(26, decmax[25]),
                    new DataPoint(27, decmax[26]),
                    new DataPoint(28, decmax[27]),
                    new DataPoint(29, decmax[28]),
                    new DataPoint(30, decmax[29]),
                    new DataPoint(31, decmax[30])

            });

            graph.addSeries(series);


            //"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});

        }
    }

    public void populate_exs(String MG) {
        MG = history.MG;
        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);
        Cursor db_read_num_exs = explusdb.rawQuery("Select count(*) from Exercises where Muscle_Group='" + history.MG + "';", null);
        db_read_num_exs.moveToFirst();
        int num_exs = db_read_num_exs.getInt(0);


        Cursor exercises_to_list = explusdb.rawQuery("Select Exercise from Exercises where Muscle_Group='" + history.MG + "';", null);
        String exercise_name;

        ArrayList<String> exercisearray = new ArrayList<String>();


        for (int q = 0; q < num_exs; q++) {

            // if (MainActivity.abs_list.size() == 0) {

            if (q == 0) {
                exercises_to_list.moveToFirst();
            } else if (q > 0) {
                exercises_to_list.moveToNext();
            }

            exercise_name = exercises_to_list.getString(0);
            exercisearray.add(q, exercise_name);


        }

        ArrayAdapter<String> spinner2ArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, exercisearray);

        spinner2ArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spin2 = (Spinner) findViewById(R.id.workout);

        spin2.setAdapter(spinner2ArrayAdapter);


        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String Exercise = spin2.getSelectedItem().toString();

                history.EX = Exercise;


                    getYears();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

    }



    public void getYears()
    {



        final SQLiteDatabase explusdb = openOrCreateDatabase("EPlus", MODE_PRIVATE, null);

        Cursor db_read_num_mgroups = explusdb.rawQuery("Select count(*) from sets where Muscle_Group = '" + MG + "' AND Exercise ='" + EX + "' AND date not NULL", null);
        db_read_num_mgroups.moveToFirst();
        int num_muscle_groups = db_read_num_mgroups.getInt(0);


        Cursor exx = explusdb.rawQuery("Select date from sets where Muscle_Group = '" + MG + "' AND Exercise ='" + EX + "' AND date not NULL", null);
        String exxx;

        ArrayList<String> exss = new ArrayList<String>();

        String[] year_draft = new String[num_muscle_groups];


          for (int p = 0; p < num_muscle_groups; p++) {

            // if (MainActivity.abs_list.size() == 0) {

            if (p == 0) {
                exx.moveToFirst();
            } else if (p > 0) {
                exx.moveToNext();
            }

            exxx = exx.getString(0);




              if (exxx != null)
              {
                  year_draft[p] = exxx.substring(exxx.length() - 4);
                  exss.add(p, exxx);

              }





        }
        Set<String> year = new HashSet<String>(Arrays.asList(year_draft));

        ArrayList<String> yr_list = new ArrayList<String>();

        yr_list.addAll(year);


        if (yr_list.isEmpty())
        {
                noHistoryFound();
        }
        else
        {
            HistoryFound();
        }


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yr_list);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spin3 = (Spinner) findViewById(R.id.spinner_year);

        spin3.setAdapter(spinnerArrayAdapter);


        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                //   String Muscle_group = spin.getSelectedItem().toString();

                //   history.MG = Muscle_group;

                //   populate_exs(MG);


                YR = spin3.getSelectedItem().toString();


                if (YR != null) {


                    getMonth();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });



    }

  //no history found for the selected month
    public void noHistoryFoundMonth()
    {
        GraphView graph = (GraphView) findViewById(R.id.graph);
        TextView text_no_record_ex = (TextView) findViewById(R.id.textView14);  //no ex found error
        TextView text_no_record_month = (TextView) findViewById(R.id.textView15); //no ex in month

        graph.setVisibility(View.GONE);
        text_no_record_month.setVisibility(View.VISIBLE); //show no ex in month error
        text_no_record_ex.setVisibility(View.GONE); //hide no ex found error
    }

    //histroy found in the selected month
    public void HistoryFoundMonth()
    {
        GraphView graph = (GraphView) findViewById(R.id.graph);
        TextView text_no_record_month = (TextView) findViewById(R.id.textView15);

        graph.setVisibility(View.VISIBLE);
        text_no_record_month.setVisibility(View.GONE);
    }

    //no history found of the exercise in any years (ie. 2012 2013 ..etc)
    public void noHistoryFound()
    {


        TextView text_view_year = (TextView) findViewById(R.id.textView12);
        TextView text_view_month = (TextView) findViewById(R.id.textView13);
        TextView text_no_record = (TextView) findViewById(R.id.textView14);  //no ex found error
        TextView text_no_record_month = (TextView) findViewById(R.id.textView15); //no ex in month
        Spinner var_spinner_year = (Spinner) findViewById(R.id.spinner_year);
        Spinner var_spinner_month = (Spinner) findViewById(R.id.spinner_month);

        GraphView graph = (GraphView) findViewById(R.id.graph);

        text_view_year.setVisibility(View.GONE);
        text_view_month.setVisibility(View.GONE);
        text_no_record.setVisibility(View.VISIBLE);   //show no ex found error
        text_no_record_month.setVisibility(View.GONE); //hide no ex in month error
        var_spinner_year .setVisibility(View.GONE);
        var_spinner_month .setVisibility(View.GONE);
        graph.setVisibility(View.GONE);


    }

    //histroy found of the exercise in a year (ie. 2012 2013 .. etc)
    public void HistoryFound()
    {

        TextView text_view_year = (TextView) findViewById(R.id.textView12);
        TextView text_view_month = (TextView) findViewById(R.id.textView13);
        TextView text_no_record = (TextView) findViewById(R.id.textView14);
        Spinner var_spinner_year = (Spinner) findViewById(R.id.spinner_year);
        Spinner var_spinner_month = (Spinner) findViewById(R.id.spinner_month);

        GraphView graph = (GraphView) findViewById(R.id.graph);

        text_view_year.setVisibility(View.VISIBLE);
        text_view_month.setVisibility(View.VISIBLE);
        text_no_record.setVisibility(View.GONE);
        var_spinner_year .setVisibility(View.VISIBLE);
        var_spinner_month .setVisibility(View.VISIBLE);
        graph.setVisibility(View.VISIBLE);
    }




    public void getMonth()
    {
        ArrayList<String> month = new ArrayList<String>();

        month.add(0,"January");
        month.add(1,"February");
        month.add(2,"March");
        month.add(3,"April");
        month.add(4,"May");
        month.add(5,"June");
        month.add(6,"July");
        month.add(7,"August");
        month.add(8,"September");
        month.add(9, "October");
        month.add(10, "November");
        month.add(11, "December");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, month);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spin4 = (Spinner) findViewById(R.id.spinner_month);

        spin4.setAdapter(spinnerArrayAdapter);


        spin4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                //   String Muscle_group = spin.getSelectedItem().toString();

                //   history.MG = Muscle_group;

                //   populate_exs(MG);

                MN = spin4.getSelectedItem().toString();


                if (history.MN.equals("January")) {
                    jan_graph();
                } else if (history.MN.equals("February")) {
                    feb_graph();
                } else if (history.MN.equals("March")) {
                    mar_graph();
                } else if (history.MN.equals("April")) {
                    apr_graph();
                } else if (history.MN.equals("May")) {
                    mar_graph();
                } else if (history.MN.equals("June")) {
                    jun_graph();

                } else if (history.MN.equals("July")) {
                    jul_graph();
                } else if (history.MN.equals("August")) {
                    aug_graph();
                } else if (history.MN.equals("September")) {
                    sep_graph();
                } else if (history.MN.equals("October")) {
                    oct_graph();
                } else if (history.MN.equals("November")) {
                    nov_graph();
                } else if (history.MN.equals("December")) {
                    dec_graph();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });




    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(history.this, MainActivity.class));
        finish();
        super.onBackPressed();
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                // ProjectsActivity is my 'home' activity
                startActivity(new Intent(history.this, MainActivity.class));
                finish();

        }

        return super.onOptionsItemSelected(menuItem);
    }
}