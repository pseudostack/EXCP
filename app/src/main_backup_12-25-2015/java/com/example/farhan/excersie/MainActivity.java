package com.example.farhan.excersie;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteConstraintException;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.database.Cursor;
import android.database.DatabaseUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


import com.beardedhen.androidbootstrap.*;


public class MainActivity extends Activity {


//array list to store exercises for each muscle group//////
static  ArrayList<String> back_list = new ArrayList<String>();
    static ArrayList<String> abs_list = new ArrayList<String>();
    static ArrayList<String> chest_list = new ArrayList<String>();
    //static ArrayList<String> biceps_list = new ArrayList<String>();
  //  static ArrayList<String> triceps_list = new ArrayList<String>();
    static ArrayList<String> arms_list = new ArrayList<String>();
    static ArrayList<String> shoulders_list = new ArrayList<String>();
    static ArrayList<String> legs_list = new ArrayList<String>();


//*********************************************************************************************//
//*********************************************************************************************//
    //to keep track of which muscle groups are chosen for scheduling
    private static boolean mgroup_abs ;
    private  static boolean mgroup_back ;
    private static  boolean mgroup_shoulders ;
    private  static boolean mgroup_legs ;
 //   private static boolean mgroup_triceps ;
   // private static boolean mgroup_biceps ;
    private static boolean mgroup_chest ;
    private static boolean mgroup_arms ;

    private static int num_mgroups;

    private static int abs_complete;
    private  static int back_complete ;
    private static  int shoulders_complete ;
    private  static int legs_complete ;
//    private static int triceps_complete ;
  //  private static int biceps_complete ;
    private static int chest_complete ;
    private static int arms_complete ;

    public int getabs_complete()
    {
        return abs_complete;
    }

    public void setabs_complete(int numabs_complete)
    {
        abs_complete = numabs_complete;
    }

    public int getback_complete()
    {
        return back_complete;
    }

    public void setback_complete(int numback_complete)
    {
        back_complete = numback_complete;
    }


    public int getshoulders_complete()
    {
        return shoulders_complete;
    }

    public void setshoulders_complete(int numshoulders_complete)
    {
        shoulders_complete = numshoulders_complete;
    }


    public int getlegs_complete()
    {
        return legs_complete;
    }

    public void setlegs_complete(int numlegs_complete)
    {
        legs_complete = numlegs_complete;
    }


 /**   public int gettriceps_complete()
    {
        return triceps_complete;
    }

    public void settriceps_complete(int numtriceps_complete)
    {
  triceps_complete = numtriceps_complete;
    }


    public int getbiceps_complete()
    {
        return biceps_complete;
    }

    public void setbiceps_complete(int numbiceps_complete)
    {
        biceps_complete = numbiceps_complete;
    }
  **/

  public int getarms_complete()
  {
  return arms_complete;
  }

  public void setarms_complete(int numarms_complete)
  {
  arms_complete = numarms_complete;
  }


    public int getchest_complete()
    {
        return chest_complete;
    }

    public void setchest_complete(int numchest_complete)
    {
        chest_complete = numchest_complete;
    }




    private static int num_mgroups_configured =1 ; //keep track of how many muscle groups schedule configured

    public int getnummgroupsconfigured()
    {
        return num_mgroups_configured;
    }

    public void setnummgroupsconfigured(int nummgroupconfigured)
    {
     num_mgroups_configured = nummgroupconfigured;
    }
    //getters for these muscle groups for scheduling







    public  int getnummgroups()
    {
        return num_mgroups;
    }

    public  boolean getmgroups_chest()
    {
        return mgroup_chest;
    }

    public  boolean getmgroups_back()
    {
        return mgroup_back;
    }

    public  boolean getmgroups_abs()
    {
        return mgroup_abs;
    }

    /**
    public  boolean getmgroups_triceps()
    {
        return mgroup_triceps;
    }

    public  boolean getmgroups_biceps()
    {
        return mgroup_biceps;
    }

     **/

    public  boolean getmgroups_arms()
    {
        return mgroup_arms;
    }


    public  boolean getmgroups_shoulders()
    {
        return mgroup_shoulders;
    }

    public  boolean getmgroups_legs()
    {
        return mgroup_legs;
    }

    //setters for these muscle groups for scheduling
    public  void  setmgroups_back(boolean mg_back)
    {
        mgroup_back = mg_back;
    }

    public  void  setmgroups_chest(boolean mg_chest)
    {
        mgroup_chest = mg_chest;
    }
    public  void  setmgroups_abs(boolean mg_abs)
    {
        mgroup_abs = mg_abs;
    }

    /**
    public  void  setmgroups_triceps(boolean mg_triceps)
    {
        mgroup_triceps = mg_triceps;
    }

    public  void  setmgroups_biceps(boolean mg_biceps)
    {
        mgroup_biceps = mg_biceps;
    }

     **/

    public  void  setmgroups_arms(boolean mg_arms)
    {
        mgroup_arms = mg_arms;
    }


    public  void  setmgroups_shoulders(boolean mg_shoulders)
    {
        mgroup_shoulders = mg_shoulders;
    }

      public  void  setmgroups_legs(boolean mg_legs)
    {
        mgroup_legs = mg_legs;
    }

    public  void setnummgroups (int nummgroups)
    {
        num_mgroups = nummgroups;
    }

//**********************************************************************************************//
//**********************************************************************************************//

    private static boolean firstrun = true;

    public  boolean isitFirstRun()
    {
        return firstrun;
    }

    public  void setisitFirstRun(boolean frun)
    {
        firstrun = frun;
    }


//*********************************************************************************************
// //////*************************************************************************************/

    public  int getNback(){
        return num_back;
    }

    public  int getNchest(){
        return num_chest;
    }

    public  int getNabs(){
        return num_abs;
    }

    /**
    public  int getNbiceps(){
        return num_biceps;
    }

    public  int getNtriceps(){
        return num_triceps;
    }


     **/


    public  int getNarms(){
        return num_arms;
    }

    public  int getNshoulders(){
        return num_shoulders;
    }

    public  int getNlegs(){
        return num_legs;
    }

//**********************************************************************************************/

    //keep track of day of week selected
    private  static String dow = "" ;
    //keep track of exercise selected

    private static int ex_index = 0;
    private static int mg_index = 0;


    //keep track of which set (ie. set 1 set 2 set 3 set 4 set 5)
    private static int set = 1;

    public  int getSet()
    {
        return set;
    }

    public  void setSet(int s)
    {
        set = s;
    }
//////////////////////////////////////////////////////////////////////////////


//array list to keep track of exercises for the day
    public static ArrayList<String> ex = new ArrayList<String>();

    public static ArrayList<String> mg = new ArrayList<String>();


    //getter for day of week
    public   String getDow()
    {
        return dow;
    }


    public   int getEx_index()
    {
        return ex_index;
    }

    public  void setEx_index(int exi)
    {
        ex_index = exi;
    }

    //getter for exercise
    public static  ArrayList<String> getEx()
    {
        return ex;
    }


    public   int getmg_index()
    {
        return mg_index;
    }

    public  void setmg_index(int mgi)
    {
        mg_index = mgi;
    }

    //getter for exercise
    public static  ArrayList<String> getmg()
    {
        return mg;
    }

    //number of exercises being performed today
    private  static int ex_num_today = 0;

    public   int get_Ex_num()
    {
        return ex_num_today;
    }

    public  void set_ex_num(int ex_num)
    {
        ex_num_today = ex_num;
    }


    //setter for exercise arraylist
    public static void setEx(String exx, int i)
    {
        ex.add(i,exx);
    }


    public static void clearEx()
    {
        ex.clear();
    }
    //setter for exercise
    public static void setMg(String mgg, int i)
    {
        mg.add(i,mgg);
    }


    //setter for day of week
    public void setDow(String dowx)
    {
        dow = (dowx);
    }


    SharedPreferences prefs = null;


    //initilize strings for doing stuff to read exercises from folders :)
    String[] back ;
    String[] abs ;
    String[] chest;

    /**
    String[] biceps ;
    String[] triceps ;
    **/


    String[] arms ;
     String[] shoulders ;
    String[] legs ;



    // integers to store the number of exercises
    private int num_back ;
    private int num_chest;
    private int num_abs ;

    /**
    private int num_biceps ;
    private int num_triceps ;
    **/

    private int num_arms;

     private int num_shoulders ;
    private int num_legs ;


    //set the number of each exercise
    public void setNumExercises()
    {
        num_back = back_list.size()-1;
         num_chest = chest_list.size()-1;
        num_abs = abs_list.size()-1;

        /**
         num_biceps = biceps_list.size()-1;
         num_triceps = triceps_list.size()-1;
         **/


        num_arms = arms_list.size()-1;

         num_shoulders = shoulders_list.size()-1;
         num_legs = legs_list.size()-1;
    }

    public void addExerciseToList()
    {
        //lets add them to the global arraylist.....
        Collections.addAll(back_list,back);
        Collections.addAll(abs_list,abs);
        Collections.addAll(chest_list,chest);
        /**
        Collections.addAll(biceps_list,biceps);
        Collections.addAll(triceps_list,triceps);
        **/
        Collections.addAll(arms_list,arms);
         Collections.addAll(shoulders_list,shoulders);
        Collections.addAll(legs_list,legs);
    }


    public void readExercises()
    {
        //lets read all the exercises from the assets folder.....
        try {
            back = getAssets().list("back");
            abs = getAssets().list("abs");
            chest = getAssets().list("chest");
            /**
            biceps = getAssets().list("biceps");
            triceps = getAssets().list("triceps");
            **/

        arms = getAssets().list("arms");

             shoulders = getAssets().list("shoulders");
            legs = getAssets().list("legs");

        }catch
                (IOException ex)
        {

        }
    }


    public void removeGIF()
    {

        for (int k = 0; k < num_abs+1;k++)
        {

            abs_list.set(k,abs_list.get(k).replace(".gif",""));
        }


        for (int k = 0; k < num_back+1;k++)
        {

            back_list.set(k,back_list.get(k).replace(".gif",""));
        }


        for (int k = 0; k < num_chest+1;k++)
        {
            chest_list.set(k,chest_list.get(k).replace(".gif",""));
        }

/**
        for (int k = 0; k < num_biceps+1;k++)
        {

            biceps_list.set(k,biceps_list.get(k).replace(".gif",""));
        }


        for (int k = 0; k < num_triceps+1;k++)
        {

            triceps_list.set(k,triceps_list.get(k).replace(".gif",""));
        }
**/

        for (int k = 0; k < num_arms+1;k++)
        {

            arms_list.set(k, arms_list.get(k).replace(".gif", ""));
        }


        for (int k = 0; k < num_shoulders+1;k++)
        {

            shoulders_list.set(k, shoulders_list.get(k).replace(".gif", ""));
        }


        for (int k = 0; k < num_legs+1;k++)
        {
            legs_list.set(k,legs_list.get(k).replace(".gif",""));
        }
    }

       @Override
    protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);


           TypefaceProvider.registerDefaultIconSets();

           setContentView(R.layout.activity_main);

           getActionBar().setDisplayUseLogoEnabled(false);
           getActionBar().setDisplayHomeAsUpEnabled(false);
           getActionBar().setDisplayShowHomeEnabled(false);
           getActionBar().setDisplayShowTitleEnabled(true);
           getActionBar().setTitle("Workout+");


           // TextView test = (TextView) findViewById(R.id.test);
            Calendar c = Calendar.getInstance();

           Date now = new Date();
           SimpleDateFormat df = new SimpleDateFormat("EEEE, MMM, dd, yyyy");



          //    TextView day_of_Week = (TextView) findViewById(R.id.day_of_Week);
       //    day_of_Week.setText(df.format(c.getTime()));

    //button for setting up a schedule for workout, ie muscle groups for sunday, mon....
           com.beardedhen.androidbootstrap.BootstrapButton button_setup_schedule = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.button_setup_schedule);

           button_setup_schedule.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v){
                   finish();
                   startActivity(new Intent(MainActivity.this, Configure_workout_schedule.class));

               }
           });


           com.beardedhen.androidbootstrap.BootstrapButton button_view_history = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.view_history);
           button_view_history.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v){
                   finish();
                   startActivity(new Intent(MainActivity.this, history.class));

               }
           });


           com.beardedhen.androidbootstrap.BootstrapButton button_exit = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.exit);
           button_exit.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v){
                   finish();


               }
           });
           //button to show exercise configuration
    // setting up Exercise for each muscle group, ie, chest,, bench press ...
/*
           com.beardedhen.androidbootstrap.BootstrapButton button_exercises = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.button_exercises);

           button_exercises.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v) {
                   startActivity(new Intent(MainActivity.this, configure_exercises.class));
               }

           });
*/
           //button to start exercise
           //UNDER CONSTRUCTION!! LETS DO THIS FARHANDO TORREZ WOOHOOO
           // I AM SEXY AND I KNOW IT!!...
           //SLADANA IS AN UGLY DYKE AND SHE KNOWS IT!

           com.beardedhen.androidbootstrap.BootstrapButton button_start_workout = (com.beardedhen.androidbootstrap.BootstrapButton) findViewById(R.id.button_start_workout);

           button_start_workout.setOnClickListener(new View.OnClickListener() {
                                                       public void onClick(View v) {
                                                           //close the current activity before opening new one
                                                           finish();
                                                           startActivity(new Intent(MainActivity.this, Train.class));
                                                       }
                                                   });
        //for first run check
          prefs = getSharedPreferences("package com.example.farhan.excersie", MODE_PRIVATE);

       }

    //--------------------------first time, create DB----------------------------------------
    @Override
    protected void onResume()
    {

        super.onResume();

//        if (prefs.getBoolean("firstrun", true)) {


            readExercises();
            addExerciseToList();
            setNumExercises();
            removeGIF();



            SQLiteDatabase explusdb = openOrCreateDatabase("EPlus",MODE_PRIVATE,null);

            //+++++++++++++++++++++++++++++++++create main table+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            explusdb.execSQL("CREATE TABLE IF NOT EXISTS Exercises(Exercise String PRIMARY KEY, Muscle_Group String, sun int, mon int, tues int, wed int, thurs int, fri int, sat int);");

            //insert exercises for chest
            for (int l = 0;l<num_chest+1;l++) {
                try {
                    explusdb.execSQL("INSERT INTO Exercises VALUES('" + chest_list.get(l) + "','chest',0,0,0,0,0,0,0);");
                }
                catch (SQLiteConstraintException exception)
                {

                }
            }

            //insert exercises for back
            for (int l = 0;l<num_back+1;l++) {
                try {
                explusdb.execSQL("INSERT INTO Exercises VALUES('"+back_list.get(l)+"','back',0,0,0,0,0,0,0);");
                }
                catch (SQLiteConstraintException exception)
                {

                }
            }

            //insert exercises for shoulders
            for (int l = 0;l<num_shoulders+1;l++) {
                try {

                explusdb.execSQL("INSERT INTO Exercises VALUES('"+shoulders_list.get(l)+"','shoulders',0,0,0,0,0,0,0);");
            }
                catch (SQLiteConstraintException exception)
                {

                }
            }

            //insert exercises for abs
            for (int l = 0;l<num_abs+1;l++) {
                try {

                explusdb.execSQL("INSERT INTO Exercises VALUES('"+abs_list.get(l)+"','abs',0,0,0,0,0,0,0);");
    }
                catch (SQLiteConstraintException exception)
                {

                }
            }


        //insert exercises for arms
        for (int l = 0;l<num_arms+1;l++) {
            try {

                explusdb.execSQL("INSERT INTO Exercises VALUES('"+arms_list.get(l)+"','arms',0,0,0,0,0,0,0);");
            }
            catch (SQLiteConstraintException exception)
            {

            }
        }



        /**  Getting rid of triceps and biceps and adding arms

         //insert exercises for triceps
            for (int l = 0;l<num_triceps+1;l++) {
                try {

                explusdb.execSQL("INSERT INTO Exercises VALUES('"+triceps_list.get(l)+"','triceps',0,0,0,0,0,0,0);");
        }
                catch (SQLiteConstraintException exception)
                {

                }
            }
        //insert exercises for biceps
            for (int l = 0;l<num_biceps+1;l++) {
                try {

                explusdb.execSQL("INSERT INTO Exercises VALUES('"+biceps_list.get(l)+"','biceps',0,0,0,0,0,0,0);");
        }
                catch (SQLiteConstraintException exception)
                {

                }
            }
**/
            //insert exercises for legs
            for (int l = 0;l<num_legs+1;l++) {
                try {

                explusdb.execSQL("INSERT INTO Exercises VALUES('"+legs_list.get(l)+"','legs',0,0,0,0,0,0,0);");
        }
                catch (SQLiteConstraintException exception)
                {

                }
            }
            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


            //++++++++++++++++create muscle group table++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            explusdb.execSQL("CREATE TABLE IF NOT EXISTS MGroup(Muscle_Group String PRIMARY KEY, sun int, mon int, tues int, wed int, thurs int, fri int, sat int);");

        try {

            //insert exercises for chest
            explusdb.execSQL("INSERT INTO MGroup VALUES('chest',0,0,0,0,0,0,0);");
        }
            catch (SQLiteConstraintException exception)
        {

        }
        try {


            //insert exercises for back
            explusdb.execSQL("INSERT INTO MGroup VALUES('back',0,0,0,0,0,0,0);");
        }
        catch (SQLiteConstraintException exception)
        {

        }
        try {


            //insert exercises for shoulders
                    explusdb.execSQL("INSERT INTO MGroup VALUES('shoulders',0,0,0,0,0,0,0);");
        }
        catch (SQLiteConstraintException exception)
        {

        }
        try {


            //insert exercises for abs
                    explusdb.execSQL("INSERT INTO MGroup VALUES('abs',0,0,0,0,0,0,0);");
        }
        catch (SQLiteConstraintException exception)
        {

        }


/**
        try {


            //insert exercises for triceps
                    explusdb.execSQL("INSERT INTO MGroup VALUES('triceps',0,0,0,0,0,0,0);");
        }
        catch (SQLiteConstraintException exception)
        {

        }


        try {


            //insert exercises for biceps
                    explusdb.execSQL("INSERT INTO MGroup VALUES('biceps',0,0,0,0,0,0,0);");
        }
        catch (SQLiteConstraintException exception)
        {

        }
**/


        try {

            //insert exercises for arms
            explusdb.execSQL("INSERT INTO MGroup VALUES('arms',0,0,0,0,0,0,0);");
        }
        catch (SQLiteConstraintException exception)
        {

        }

 try {

                    //insert exercises for legs
                    explusdb.execSQL("INSERT INTO MGroup VALUES('legs',0,0,0,0,0,0,0);");
}
        catch (SQLiteConstraintException exception)
        {

        }

            //+++++++++++++++++++++++++++++++++create reps and sets table+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            explusdb.execSQL("CREATE TABLE IF NOT EXISTS sets(Exercise String, Muscle_Group String, r1 int, r2 int, r3 int, r4 int, r5 int,r_max int, date String, date2 String);");

            //pre-created database metadata if required for history tracking in the future...
/*
            //insert exercises for chest
            for (int l = 0;l<num_chest+1;l++) {

                explusdb.execSQL("INSERT INTO sets VALUES('"+chest_list.get(l)+"','chest',null,null,null,null,null,null);");

            }

            //insert exercises for back
            for (int l = 0;l<num_back+1;l++) {
                explusdb.execSQL("INSERT INTO sets VALUES('"+back_list.get(l)+"','back',null,null,null,null,null,null);");

            }

            //insert exercises for shoulders
            for (int l = 0;l<num_shoulders+1;l++) {
                explusdb.execSQL("INSERT INTO sets VALUES('"+shoulders_list.get(l)+"','shoulders',null,null,null,null,null,null);");

            }

            //insert exercises for abs
            for (int l = 0;l<num_abs+1;l++) {
                explusdb.execSQL("INSERT INTO sets VALUES('"+abs_list.get(l)+"','abs',null,null,null,null,null,null);");

            }

            //insert exercises for triceps
            for (int l = 0;l<num_triceps+1;l++) {
                explusdb.execSQL("INSERT INTO sets VALUES('"+triceps_list.get(l)+"','triceps',null,null,null,null,null,null);");

            }

            //insert exercises for biceps
            for (int l = 0;l<num_biceps+1;l++) {
                explusdb.execSQL("INSERT INTO sets VALUES('"+biceps_list.get(l)+"','biceps',null,null,null,null,null,null);");

            }

            //insert exercises for legs
            for (int l = 0;l<num_legs+1;l++) {
                explusdb.execSQL("INSERT INTO sets VALUES('"+legs_list.get(l)+"','legs',null,null,null,null,null,null);");

            }
            */

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


          //  prefs.edit().putBoolean("firstrun", false).commit();
            explusdb.close();
  //      }
    }

    //----------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onBackPressed() {

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

        //when the user clicks on chest



        return super.onOptionsItemSelected(item);
    }

}
