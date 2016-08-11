package com.ikode.viezara.ikode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ikode.adapters.SecureDSFragmentPageAdapters;

/**
 * Created by jem on 30/01/16.
 */
public class SecureStoreActivity extends AppCompatActivity {

    private TextView setText, canText;
    private Toolbar toolbar;
    private TabLayout iTabLayout;
    SharedPreferences SP;
    CharSequence Titles[]={"Inbox","Archive"};
    private ActionBarDrawerToggle iDrawerToggle;
    private NavigationView iDrawer;
    private DrawerLayout iDrawerLayout;
    SecureDSFragmentPageAdapters adapter;


    /*SlidingTabLayout tabs;
    ViewPager pager;
    int Numboftabs =2;*/

    FragmentManager mFragmentManager2;
    FragmentTransaction mFragmentTransaction2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secure_data_store );

        // iAdapter = new MyPagerAdapter(getSupportFragmentManager());

        toolbar = (Toolbar) findViewById(R.id.ikode_bar);
        setSupportActionBar(toolbar);

        mFragmentManager2 = getSupportFragmentManager();
        mFragmentTransaction2 = mFragmentManager2.beginTransaction();
        mFragmentTransaction2.replace(R.id.store_containerView,new SDSFragment()).commit();

        SP = getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE);


/*

        //Initializing NavigationView
        iDrawer = (NavigationView) findViewById(R.id.ikode_drawer_store);
*/

        //for Navigation Drawer code
        iDrawer = (NavigationView) findViewById(R.id.main_drawer_store);

        iDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                iDrawerLayout.closeDrawers();
                switch (menuItem.getItemId()){

                    case R.id.navigation_item_0:
                        Intent intent = new Intent("android.intent.action.CAPTURE");
                        startActivity(intent);
                        return true;


                    case R.id.navigation_item_1:
                        Toast.makeText(getApplicationContext(),"Profile Selected",Toast.LENGTH_SHORT).show();
                        Intent intentProfile = new Intent("android.intent.action.UserProfile");
                        startActivity(intentProfile);
                        return true;

                    case R.id.navigation_item_2:
                        Toast.makeText(getApplicationContext(),"Secured Data Store",Toast.LENGTH_SHORT).show();
                        Intent intent4 = new Intent("android.intent.action.SecureStoreActivity");
                        startActivity(intent4);
                        return true;

                    case R.id.navigation_item_3:
                        Toast.makeText(getApplicationContext(),"Reports",Toast.LENGTH_SHORT).show();
                        Intent intentReport = new Intent("android.intent.action.Reports");
                        startActivity(intentReport);
                        return true;

                    case R.id.navigation_item_4:
                        Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
                        Intent intent5 = new Intent("android.intent.action.Settings1");
                        startActivity(intent5);
                        return true;

                    case R.id.navigation_item_5:
                        Toast.makeText(getApplicationContext(),"Help",Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent("android.intent.action.HelpPage");
                        startActivity(intent1);
                        return true;


                    case R.id.navigation_item_6:
                        Toast.makeText(getApplicationContext(),"About Us",Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent("android.intent.action.AboutUs");
                        startActivity(intent2);
                        return true;


                    case R.id.navigation_item_7:
                        Toast.makeText(getApplicationContext(),"LogOff",Toast.LENGTH_SHORT).show();
                        logout();
                        break;
                        /*UserProfile fragment3 = new Reports();
                        android.support.v4.app.FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.main_profile,fragment3);
                        fragmentTransaction3.commit();*/
                }

                return false;
            }

            private void logout(){
                //TODO delete cookie from db

                SharedPreferences.Editor editor = SP.edit();
                editor.clear();
                editor.commit();

                Intent TO_LOGOFF = new Intent(SecureStoreActivity.this, homescreen.class);
                startActivity(TO_LOGOFF);
            }

        });


        iDrawerLayout = (DrawerLayout) findViewById(R.id.ikode_drawer_store);
        //for hamburger icon
        iDrawerToggle = new ActionBarDrawerToggle(this,
                iDrawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        iDrawerLayout.setDrawerListener(iDrawerToggle);
        iDrawerToggle.syncState();
        //end of code for Navigation Drawer



//        iPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(iTabLayout));
        //test for returning to previous activity *gmbg*
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //this is for the SAVE AND SETTINGS BUTTON CODE BY DEMY

        if (RequestData.checkSession(getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE))) {

//            setText = (TextView) findViewById(R./**/id.tvSettings);
//            canText = (TextView) findViewById(R.id.tvCancel);

/*
            setText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent TO_SETTINGS = new Intent("android.intent.action.Settings1");
                    startActivity(TO_SETTINGS);

                }
            });
*/


            /*canText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent User_CHOICE = new Intent("android.intent.action.UserChoice");
                    startActivity(User_CHOICE);

                }
            });*/

        }

        /*//this is for the tab change code
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Documents"));
        tabLayout.addTab(tabLayout.newTab().setText("Decodes"));
        tabLayout.addTab(tabLayout.newTab().setText("Verification"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final SecureDSFragmentPageAdapters adapter = new SecureDSFragmentPageAdapters
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
          tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });*/
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
    } //END onOptionItemSelected
    //code for Navigation menu //
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        iDrawerToggle.onConfigurationChanged(newConfig);
    }

/*    public static class MyFragment extends Fragment {
        public MyFragment() {

        }
        //@Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }*/


/*   class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            //docu.MyFragment myFragment = new Fragment1();
            MyFragment myFragment = new MyFragment();
            return myFragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }
    }*/ // END MYPAGERADAPTER

    @Override
    public void onBackPressed() {
        if (iDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            iDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}

