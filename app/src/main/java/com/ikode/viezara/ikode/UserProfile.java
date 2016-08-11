package com.ikode.viezara.ikode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ikode.fragments.FragmentProfSettings;
import com.ikode.fragments.FragmentProfile;
import com.ikode.fragments.SecureDataStore;


//import android.app.FragmentManager;

public class UserProfile extends AppCompatActivity {


    //private TextView setText, canText;
    private Toolbar toolbar;
    SharedPreferences SP;
    private ActionBarDrawerToggle iDrawerToggle;
    private NavigationView iDrawer;
    private DrawerLayout iDrawerLayout;
    private TextView userID;
    private ImageView qr;
    private Bitmap bitmap = null;
    //for real
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    public final static int WIDTH = 500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // iAdapter = new MyPagerAdapter(getSupportFragmentManager());
        toolbar = (Toolbar) findViewById(R.id.ikode_bar);
        setSupportActionBar(toolbar);

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();



       /* //new viewpager
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assigning the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        tabs.setViewPager(pager);*/

        SP = getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE);


        //Initializing NavigationView
        iDrawer = (NavigationView) findViewById(R.id.main_drawer_profile);

        //for Navigation Drawer code
        iDrawer = (NavigationView) findViewById(R.id.main_drawer_profile);
        View header=iDrawer.getHeaderView(0);
        userID = (TextView) header.findViewById(R.id.user_);
        String[] parts = RequestData.storedEmail.split("@");
        userID.setText(parts[0].toString());
        qr = (ImageView) header.findViewById(R.id.img_QRCode);
        getQRCode();
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
                       /* FragmentCam fragmentCam = new FragmentCam();
                        android.support.v4.app.FragmentTransaction fragmentTransaction5 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction5.replace(R.id.containerView, fragmentCam);
                        fragmentTransaction5.commit();*/
                        return true;

                    case R.id.navigation_item_1:
                        Toast.makeText(getApplicationContext(),"Profile Selected",Toast.LENGTH_SHORT).show();

                        FragmentProfile fragmentProfile = new FragmentProfile();
                        android.support.v4.app.FragmentTransaction fragmentTransaction6 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction6.replace(R.id.containerView, fragmentProfile);
                        fragmentTransaction6.commit();
                       /* Intent intentProfile = new Intent("android.intent.action.UserProfile");
                        startActivity(intentProfile);*/
                        return true;

                    case R.id.navigation_item_2:
                        Toast.makeText(getApplicationContext(),"Secured Data Store",Toast.LENGTH_SHORT).show();
                        SecureDataStore fragmentSecure = new SecureDataStore();
                        android.support.v4.app.FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction4.replace(R.id.containerView, fragmentSecure);
                        fragmentTransaction4.commit();
//                        Intent intent4 = new Intent("android.intent.action.SecureStoreActivity");
//                        startActivity(intent4);
                        return true;

                    case R.id.navigation_item_3:
                        Toast.makeText(getApplicationContext(),"Reports",Toast.LENGTH_SHORT).show();
                        Reports fragment = new Reports();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.containerView,fragment);
                        fragmentTransaction.commit();
                        return true;


                    case R.id.navigation_item_4:
                        Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
                        FragmentProfSettings fragmentSettings = new FragmentProfSettings();
                        android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.containerView,fragmentSettings);
                        fragmentTransaction1.commit();

                        /*Intent intent6 = new Intent("android.intent.action.Settings1");
                        startActivity(intent6);*/
                        return true;

                    case R.id.navigation_item_5:
                        Toast.makeText(getApplicationContext(),"Help",Toast.LENGTH_SHORT).show();
                        /*FragmentHelp fragmentHelp = new FragmentHelp();
                        android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.containerView,fragmentHelp);
                        fragmentTransaction2.commit();*/

                       Intent intent1 = new Intent("android.intent.action.HelpPage");
                        startActivity(intent1);
                        return true;


                    case R.id.navigation_item_6:
                        Toast.makeText(getApplicationContext(),"About Us",Toast.LENGTH_SHORT).show();
                        /*FragmentAbout fragmentAbout = new FragmentAbout();
                        android.support.v4.app.FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.containerView, fragmentAbout);
                        fragmentTransaction3.commit();*/
                        Intent intent2 = new Intent("android.intent.action.AboutUs");
                        startActivity(intent2);
                        return true;


                    case R.id.navigation_item_7:
                        //Toast.makeText(getApplicationContext(),"LogOff",Toast.LENGTH_SHORT).show();
                        logout();

                        break;
                }

                return false;
            }
            //fragment code very important *gmbg*
                    /*case R.id.navigation_item_0:
                        Toast.makeText(getApplicationContext(),"Camera Test",Toast.LENGTH_SHORT).show();
                        FragmentProfile fragment = new FragmentProfile();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.containerView,fragment);
                        fragmentTransaction.commit();
                        return true;*/

            private void logout(){
                //TODO delete cookie from db

                SharedPreferences.Editor editor = SP.edit();
                editor.clear();
                editor.commit();
                finish();
                Intent TO_LOGOFF = new Intent(UserProfile.this, homescreen.class);
                startActivity(TO_LOGOFF);


                /*Intent i = new Intent(UserProfile.this, homescreen.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("EXIT", true);
                startActivity(i);*/
            }

        });

        iDrawerLayout = (DrawerLayout) findViewById(R.id.ikode_drawer_profile);
        //for hamburger icon
        iDrawerToggle = new ActionBarDrawerToggle(this, iDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        if (RequestData.checkSession(getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE))) {

        }

    }

    public void exit(View view)
    {
        System.exit(0);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        iDrawerToggle.syncState();
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

    @Override
    public void onBackPressed() {
        if (iDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            iDrawerLayout.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();

        }
    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    private void getQRCode() {
        try {
            bitmap = encodeAsBitmap(RequestData.storedEmail);
            qr.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    // this is method call from on create and return bitmap image of QRCode.
    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, WIDTH, WIDTH, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? getResources().getColor(R.color.black) : getResources().getColor(R.color.white);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, 500, 0, 0, w, h);
        return bitmap;
    } /// end of this method

}

// iTabLayout = (TabLayout) findViewById(R.id.ikode_tab_layout);
// iPager = (ViewPager) findViewById(R.id.ikode_pager);
/*
        iPager.setAdapter(iAdapter);
        iTabLayout.setTabsFromPagerAdapter(iAdapter);
        iTabLayout.setupWithViewPager(iPager);
*/

//        iPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(iTabLayout));


//setText = (TextView) findViewById(R./**/id.tvSettings);
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



