<<<<<<< Updated upstream
package com.ikode.viezara.ikode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

public class homescreen extends AppCompatActivity {

    private Button REGISTER_BTN;
    private Button SCAN_BTN;
    private ImageButton LOGIN_BTN, popup_btn;
    private TextView HELP;
    private TextView ABOUT;
    private CheckBox REGISTER_BOX;
    private View mViewOption;
    private TextView ikonalicense;
    private SharedPreferences SP;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        popup_btn = (ImageButton) findViewById(R.id.popInfo);

        popup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(homescreen.this, RegInfo.class));
                displayPopupWindow(v);
            }

        });

        ikonalicense = (TextView) findViewById(R.id.ikonalicensetext);
        SpannableString ikltext = new SpannableString("licensed by Ikona®");

        ikltext.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,18, 0);
        ikltext.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 10, 0);
        ikltext.setSpan(new ForegroundColorSpan(Color.rgb(212, 175, 55)), 12, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ikonalicense.setMovementMethod(LinkMovementMethod.getInstance());
        ikonalicense.setText(ikltext);
        ikonalicense.setHighlightColor(Color.TRANSPARENT);


        REGISTER_BOX = (CheckBox) findViewById(R.id.checkBox2);

//        REGISTER_BTN = (Button) findViewById(R.id.registerBtn);
        SCAN_BTN  = (Button) findViewById(R.id.scanButton);
        LOGIN_BTN =(ImageButton) findViewById(R.id.loginButton1);
        View mViewOption = (View)findViewById(R.id.relativeLayout);
        HELP = (TextView) findViewById(R.id.textView26);
        ABOUT = (TextView) findViewById(R.id.textView27);

        //LOGIN_BTN.setImageResource(R.drawable.ic_login);

        SP = getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE);

        if(!RequestData.checkSession(getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE))){

            //login picture

            //LOGIN_BTN.setImageResource(R.drawable.ic_login);
            LOGIN_BTN.setBackgroundResource(R.drawable.ic_login_user);
            LOGIN_BTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (RequestData.checkSession(getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE))) {
                        Intent userChoice = new Intent("android.intent.action.UserProfile");
                        startActivity(userChoice);
                    } else {
                        Intent toLogin = new Intent("android.intent.action.UserConnect");
                        startActivity(toLogin);
                    }

                }
            });


        }else{

            //exit picture

            LOGIN_BTN.setBackgroundResource(R.drawable.ic_exit);
            LOGIN_BTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences.Editor editor = SP.edit();
                    editor.clear();
                    editor.commit();
                    //onRestart();
                    int id = android.os.Process.myPid();
                    android.os.Process.killProcess(id);
                    finish();

                }
            });

        }

        //End application
        if (getIntent().getBooleanExtra("EXIT", false))
        {
            finish();
        }

        if (RequestData.user_Registered.equals("false")) {
            LOGIN_BTN.setVisibility(View.GONE);
            REGISTER_BOX.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (REGISTER_BOX.isChecked()) {
                        Intent TO_REGISTRATION = new Intent("android.intent.action.REGISTRATION");
                        startActivity(TO_REGISTRATION);
                    }

                }
            });

        }
        else
        {
            REGISTER_BOX.setVisibility(View.GONE);
            popup_btn.setVisibility(View.GONE);



        }
        SCAN_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TO_REGISTRATION = new Intent("android.intent.action.CAPTURE");
                startActivity(TO_REGISTRATION);
            }
        });



        HELP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent toHelp = new Intent("android.intent.action.HelpPage");
//                startActivity(toHelp);

                startActivity(new Intent(homescreen.this, RegInfo.class));

                //helpPopUpWindow(v);




            }
        });

        ABOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHelp = new Intent("android.intent.action.AboutUs");
                startActivity(toHelp);

            }
        });

    }

    /*private void helpPopUpWindow(View anchorView1) {

        PopupWindow popUpHelp = new PopupWindow(homescreen.this);
        View layoutHelp = getLayoutInflater().inflate(R.layout.help_pop, null);
        popUpHelp.setContentView(layoutHelp);

        popUpHelp.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popUpHelp.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);

        popUpHelp.setOutsideTouchable(true);
        popUpHelp.setFocusable(true);
        popUpHelp.setBackgroundDrawable(new BitmapDrawable());
        popUpHelp.showAsDropDown(anchorView1);


    }
*/
    private void displayPopupWindow(View anchorView) {



        PopupWindow popup = new PopupWindow(homescreen.this);
        View layout = getLayoutInflater().inflate(R.layout.pop_up1, null);
        popup.setContentView(layout);

        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);

        popup.setOutsideTouchable(true);
        popup.setFocusable(true);

        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(anchorView);

    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();

        startActivity(getIntent());
    }


}
=======
package com.ikode.viezara.ikode;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class homescreen extends AppCompatActivity {

    private Button REGISTER_BTN;
    private Button SCAN_BTN;
    private Button LOGIN_BTN, popup_btn;
    private TextView HELP;
    private TextView ABOUT;
    private CheckBox REGISTER_BOX;
    private View mViewOption;
    private TextView ikonalicense;
    private SharedPreferences SP;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

         /*popup_btn = (ImageButton) findViewById(R.id.popInfo);

        popup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homescreen.this, RegInfo.class));

            }
        });*/

        ikonalicense = (TextView) findViewById(R.id.ikonalicensetext);
        SpannableString ikltext = new SpannableString("licensed by Ikona®");

        ikltext.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,18, 0);
        ikltext.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 10, 0);
        ikltext.setSpan(new ForegroundColorSpan(Color.rgb(212, 175, 55)), 12, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ikonalicense.setMovementMethod(LinkMovementMethod.getInstance());
        ikonalicense.setText(ikltext);
        ikonalicense.setHighlightColor(Color.TRANSPARENT);


        REGISTER_BOX = (CheckBox) findViewById(R.id.notyetRegister);

//        REGISTER_BTN = (Button) findViewById(R.id.registerBtn);
        SCAN_BTN  = (Button) findViewById(R.id.scanButton);
        LOGIN_BTN = (Button) findViewById(R.id.loginButton1);
        //View mViewOption = (View)findViewById(R.id.relativeLayout);
        HELP = (TextView) findViewById(R.id.helpButton);
        ABOUT = (TextView) findViewById(R.id.aboutButton);

        //LOGIN_BTN.setImageResource(R.drawable.ic_login);

        SP = getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE);

        if(!RequestData.checkSession(getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE))){

            //login picture

            //LOGIN_BTN.setImageResource(R.drawable.ic_login);
            //LOGIN_BTN.setBackgroundResource(R.drawable.ic_login_user);
            LOGIN_BTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (RequestData.checkSession(getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE))) {
                        Intent userChoice = new Intent("android.intent.action.UserProfile");
                        startActivity(userChoice);
                    } else {
                        Intent toLogin = new Intent("android.intent.action.UserConnect");
                        startActivity(toLogin);
                    }

                }
            });


        }else{

            //exit picture

            LOGIN_BTN.setBackgroundResource(R.drawable.ic_exit);
            LOGIN_BTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences.Editor editor = SP.edit();
                    editor.clear();
                    editor.commit();
                    //onRestart();
                    int id = android.os.Process.myPid();
                    android.os.Process.killProcess(id);
                    finish();

                }
            });

        }

        //End application
        if (getIntent().getBooleanExtra("EXIT", false))
        {
            finish();
        }

        if (RequestData.user_Registered.equals("false")) {
            //LOGIN_BTN.setVisibility(View.GONE); (ORIGINAL)
            LOGIN_BTN.setVisibility(View.VISIBLE);
            REGISTER_BOX.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (REGISTER_BOX.isChecked()) {
                        Intent TO_REGISTRATION = new Intent("android.intent.action.REGISTRATION");
                        startActivity(TO_REGISTRATION);
                    }

                }
            });

        }
        else
        {
            REGISTER_BOX.setVisibility(View.GONE);
            //popup_btn.setVisibility(View.GONE);


        }
        SCAN_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inserted Code
                /* This is for geo location*/
                AlertDialog.Builder builder = new AlertDialog.Builder(homescreen.this);
                builder.setCancelable(false);
                builder.setTitle("IkodeSC Reader");
                builder.setMessage("“IkodeSC Reader is requesting to access your Location Service to enable it to log your " +
                        "location. The log will be deleted if your scan verified that product is an Original. However, if " +
                        "verification result is negative, your location data will be highly helpful for the brand owner’s " +
                        "counter-fraud actions. Your Location Service will be closed upon completing of the scan.”");
                builder.setInverseBackgroundForced(true);
                builder.setPositiveButton("[YES, I agree]", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent serviceIntent = new Intent(homescreen.this, LocationService.class);
                        startService(serviceIntent);

                        Intent TO_REGISTRATION = new Intent("android.intent.action.CAPTURE");
                        startActivity(TO_REGISTRATION);
                    }
                });
                builder.setNegativeButton("[NO, not at this time]", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                //End Code

                /* This is for direct CaptureActivity
                Intent TO_SCAN = new Intent("android.intent.action.CAPTURE");
                startActivity(TO_SCAN);*/

            }
        });



        HELP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(homescreen.this);
                builder.setCancelable(false);
                builder.setTitle("HELP");
                builder.setMessage("“If you see this icon next to a particular feature or function, by clicking the icon you can get a more detailed explanation of this feature/explanation.”");
                builder.setInverseBackgroundForced(true);
                builder.setPositiveButton("[OK, I UNDERSTAND]", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("[CANCEL]", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                //End Code

                /* This is for direct CaptureActivity
                Intent TO_SCAN = new Intent("android.intent.action.CAPTURE");
                startActivity(TO_SCAN);*/

            }

        });

        ABOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHelp = new Intent("android.intent.action.About");
                startActivity(toHelp);
            }
        });

    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();

        startActivity(getIntent());
    }


}
>>>>>>> Stashed changes
