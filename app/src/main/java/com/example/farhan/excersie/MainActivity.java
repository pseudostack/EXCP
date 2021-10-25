package com.example.farhan.excersie;

import com.example.farhan.excersie.NavDrawerListAdapter;
import com.example.farhan.excersie.NavDrawerItem;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteException;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.beardedhen.androidbootstrap.*;


public class MainActivity extends FragmentActivity {




    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;


    private ArrayAdapter<String> mAdapter;
    private String mActivityTitle;


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
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
        // Pages
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // What's hot, We  will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));



        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,

                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }




        //Icon stuff button ActionBar

    //    getActionBar().setDisplayUseLogoEnabled(false);
    //    getActionBar().setDisplayHomeAsUpEnabled(false);
    //    getActionBar().setDisplayShowHomeEnabled(false);
    //    getActionBar().setDisplayShowTitleEnabled(true);
   //     getActionBar().setTitle("Workout+");


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

    /**
     * Slide menu item click listener
     * */

    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }




    //--------------------------first time, create DB----------------------------------------
    @Override
    protected void onResume()
    {

        super.onResume();

        try {
            SQLiteDatabase.openDatabase("/data/data/com.example.farhan.excersie/databases/EPlus",null,0);


        } catch (SQLiteException e) {
            // database doesn't exist yet.


        // array for images
        String images[][] = new String[279][3];

        // array for musle group
        String mg[] = new String[279];

        // array for ex. name
        String ex[] = new String[279];

        // array for description
        String desc[] = new String[279];

        // array for steps
        String steps[][] = new String[279][6];

        // array for tips
        String tips[] = new String[279];







        int rows = 0;
        int col = 0;
        InputStream fis = null;


try {

     fis = getAssets().open("exercises-mg.xlsx");
}
catch (java.io.IOException ee)
{

        }


        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = null;
        try {
            myWorkBook = new XSSFWorkbook(fis);
        } catch (IOException eee) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = mySheet.iterator();
        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // For each row, iterate through each columns

            Iterator<Cell> cellIterator = row.cellIterator();
            col = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();



                //	switch (cell.getCellType()) {
                //case Cell.CELL_TYPE_STRING:

                // System.out.print(cell.getStringCellValue() + "\t");
                //break;

                //case Cell.CELL_TYPE_NUMERIC:
                //	//System.out.print(cell.getNumericCellValue() + "\t");
                //	break;
                //case Cell.CELL_TYPE_BOOLEAN:
                //	//System.out.print(cell.getBooleanCellValue() + "\t");
                //	break;
                //default:
                //}


                if (col == 0)
                {
                    mg[rows] = cell.getStringCellValue();
                }
                else if (col ==1)
                {
                    ex[rows] = cell.getStringCellValue();
                }

                else if (col ==2)
                {
                    desc[rows] = cell.getStringCellValue();
                }

                else if (col == 3 && !cell.getStringCellValue().equals("null"))
                {
                    steps[rows][0] = cell.getStringCellValue();
                }

                else if (col == 4 && !cell.getStringCellValue().equals("null"))
                {
                    steps[rows][1] = cell.getStringCellValue();
                }

                else if (col == 5 && !cell.getStringCellValue().equals("null"))
                {
                    steps[rows][2] = cell.getStringCellValue();
                }

                else if (col == 6 && !cell.getStringCellValue().equals("null"))
                {
                    steps[rows][3] = cell.getStringCellValue();
                }

                else if (col == 7 && !cell.getStringCellValue().equals("null"))
                {
                    steps[rows][4] = cell.getStringCellValue();
                }

                else if (col == 8 && !cell.getStringCellValue().equals("null"))
                {
                    steps[rows][5] = cell.getStringCellValue();
                }

                else if (col == 9 )
                {
                    tips[rows] = cell.getStringCellValue();
                }


                col = col + 1;
            }

            rows = rows + 1;
        }

//        if (prefs.getBoolean("firstrun", true)) {


        readExercises();
        addExerciseToList();
        setNumExercises();
        removeGIF();



        SQLiteDatabase explusdb = openOrCreateDatabase("EPlus",MODE_PRIVATE,null);

        //+++++++++++++++++++++++++++++++++create main table+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        explusdb.execSQL("CREATE TABLE IF NOT EXISTS Exercises(Exercise String PRIMARY KEY, Muscle_Group String, description String, step1 String, step2 String, step3 String, step4 String, step5 String, step6 String, tips String, sun int, mon int, tues int, wed int, thurs int, fri int, sat int);");



        for (int aval = 0; aval < 277;aval++)

        {

            try {
                explusdb.execSQL("INSERT INTO Exercises VALUES('" + ex[aval]+ "','" +mg[aval]+"','"+desc[aval] + "', '" + steps[aval][0]+"', '" + steps[aval][1]+"','" + steps[aval][2]+"', '" + steps[aval][3]+"','" + steps[aval][4]+"', '" + steps[aval][5]+"','" + tips[aval]+"',0,0,0,0,0,0,0);");
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

        //insert exercises for legs
        for (int l = 0;l<num_legs+1;l++) {
            try {

                explusdb.execSQL("INSERT INTO Exercises VALUES('"+legs_list.get(l)+"','legs',0,0,0,0,0,0,0);");
            }
            catch (SQLiteConstraintException exception)
            {

            }
        }
         **/
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
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */



    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new FindPeopleFragment();
                break;
            case 2:
                fragment = new PhotosFragment();
                break;
            case 3:
                fragment = new CommunityFragment();
                break;
            case 4:
                fragment = new PagesFragment();
                break;
            case 5:
                fragment = new WhatsHotFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container,fragment).commit();

                            // update selected item and title, then close the drawer
                            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

}
