package com.ikode.viezara.ikode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class Settings1 extends AppCompatActivity {

    //private TextView doneText;
    private ImageButton bProf, bReport, bDataStore;
    private TextView HELP, ABOUT;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.ikode_bar);
        setSupportActionBar(toolbar);

        HELP = (TextView) findViewById(R.id.textView44_help);
        ABOUT = (TextView) findViewById(R.id.textView45_about);

        HELP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TO_HELP = new Intent("android.intent.action.HelpPage");
                startActivity(TO_HELP);
            }
        });

        ABOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TO_ABOUT = new Intent("android.intent.action.AboutUs");
                startActivity(TO_ABOUT);

            }
        });



/*
        if(RequestData.checkSession(getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE))){
            //doneText = (TextView) findViewById(R.id.tvDone);
            bProf = (ImageButton) findViewById(R.id.setBSettings);
            bReport = (ImageButton) findViewById(R.id.imgRep);
            bDataStore = (ImageButton) findViewById(R.id.imgSecDs);


          *//*  doneText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent TO_USERCHOICE = new Intent("android.intent.action.UserChoice");
                    startActivity(TO_USERCHOICE);

                }
            });*//*
        } else {
            Intent TO_LOGIN = new Intent(this, UserConnect.class);
            startActivity(TO_LOGIN);
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    } // END onCreateOptionsMenu


    //for toolbar
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
}

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

