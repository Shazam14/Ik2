package com.ikode.viezara.ikode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class UserChoice extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    /*private static final String SELECTED_ITEM_ID = "selected_item_id";
    private static final String FIRST_TIME = "first_time";
    private ImageButton SCAN_PROD;
    private ImageButton UPDATE_PROF;
    private ImageButton CHNG_SETTINGS;
    private ImageButton LOGOFF_B;*/
    private SharedPreferences SP;
    private NavigationView iDrawer;
    private DrawerLayout iDrawerLayout;
    private ActionBarDrawerToggle iDrawerToggle;
    /* private int iSelectedId;
    private boolean iUserSeeDrawer = false;*/

    ArrayList<String> mPeople = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.ikode_bar);
        setSupportActionBar(toolbar);
        //
        iDrawer = (NavigationView) findViewById(R.id.main_drawer_choice);
        iDrawerLayout = (DrawerLayout) findViewById(R.id.ikode_drawer_choice);
        iDrawerToggle = new ActionBarDrawerToggle(this, iDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        iDrawerLayout.setDrawerListener(iDrawerToggle);
        iDrawerToggle.syncState();
        //end of code for Navigation Drawer 



        /*        if (!whenUserSeeDrawer()) {
            showDrawer();
            iDrawerShown();
        } else {
            hideDrawer();
        }*/
        //iSelectedId = savedInstanceState == null ? R.id.navigation_item_1 : savedInstanceState.getInt(SELECTED_ITEM_ID);
        //navigate(isSelectedId);



    }

    //save instance for user when user closes the main program or app
/*    private boolean whenUserSeeDrawer(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.getBoolean(FIRST_TIME, false);
        return iUserSeeDrawer;

    }
    //if user already seen drawer
    private void iDrawerShown(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        iUserSeeDrawer = true;
        sharedPreferences.edit().putBoolean(FIRST_TIME, iUserSeeDrawer).apply();
    }

    private void showDrawer(){
        iDrawerLayout.openDrawer(GravityCompat.START);
    }

    private void hideDrawer(){
        iDrawerLayout.closeDrawer(GravityCompat.START);
    }*/

        //previous code for USER CHOICE Version 1
       /*if(RequestData.checkSession(getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE))){

            SCAN_PROD = (ImageButton) findViewById(R.id.scanProduct);
            UPDATE_PROF = (ImageButton) findViewById(R.id.updateProf);
            CHNG_SETTINGS = (ImageButton) findViewById(R.id.chngSettings);
            LOGOFF_B = (ImageButton) findViewById(R.id.logOff);
            SP = getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE);



            SCAN_PROD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent TO_SCAN = new Intent("android.intent.action.CAPTURE");
                    startActivity(TO_SCAN);
                }
            });


            UPDATE_PROF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent TO_UPDATEPROF = new Intent("android.intent.action.UserProfile");
                    startActivity(TO_UPDATEPROF);
                }
            });

            CHNG_SETTINGS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent TO_CHNGSET = new Intent("android.intent.action.Settings1");
                    startActivity(TO_CHNGSET);
                }


            });

            LOGOFF_B.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {



                    SharedPreferences.Editor editor = SP.edit();
                    editor.clear();
                    editor.commit();


                    Intent TO_LOGOFF = new Intent("android.intent.action.REGISTRATION");
                    startActivity(TO_LOGOFF);
                }
            });

        } else {
            Intent TO_LOGIN = new Intent(this, UserConnect.class);
            startActivity(TO_LOGIN);
        }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //to go back to same task *gmbg*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Intent intent = null;
        menuItem.setChecked(true);
        //iSelectedId = menuItem.getItemId();
        //navigate(iSelectedId);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putInt(SELECTED_ITEM_ID, iSelectedId);
    }
/*
    @Override
    public void onBackPressed() {
        if (iDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            iDrawerLayout.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }*/
}

//comment out because this should be in user profile//
    /*@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        iDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume (){
        super.onResume();

        if(!RequestData.checkSession(getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE))){
            Intent TO_LOGIN = new Intent(this, UserConnect.class);
            startActivity(TO_LOGIN);
        }
    }*/
