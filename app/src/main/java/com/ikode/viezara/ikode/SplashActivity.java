package com.ikode.viezara.ikode;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends AppCompatActivity implements OnProgressBarListener {

    private static final int PROGRESS = 0X1;
    private int iProgressStatus = 0;
    private Handler iHandler = new Handler();
    Boolean isInternetPresent = false;
    ConnectionDetector cd;
    public static final int seconds = 10;
    public static final int miliseconds = seconds * 1000;
    private ProgressBar iProgress;
    TextView text;
    public boolean no_Connection = true;
    LoginDataBaseAdapter loginDataBaseAdapter;

    private Timer timer;
    private NumberProgressBar iProgress1;

    private int jumpTime = 0;
    private static final String TAG = "myApp";

    private TextView iklsplash;

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor sharedPreferencesEditor;

    private static boolean chkNewPair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //for ikona License spannable

        iklsplash = (TextView) findViewById(R.id.iklsplashTxt);
        SpannableString iklsplashtxt = new SpannableString("licensed by Ikona®");

        iklsplashtxt.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,18, 0);
        iklsplashtxt.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 10, 0);
        iklsplashtxt.setSpan(new ForegroundColorSpan(Color.rgb(212, 175, 55)), 12, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        iklsplash.setMovementMethod(LinkMovementMethod.getInstance());
        iklsplash.setText(iklsplashtxt);
        iklsplash.setHighlightColor(Color.TRANSPARENT);


        // create a instance of SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        iProgress1 = (NumberProgressBar) findViewById(R.id.numberbar1);
        iProgress1.setOnProgressBarListener(this);

        RequestData.storedEmail = loginDataBaseAdapter.getUserEmail();
        cd = new ConnectionDetector(getApplicationContext());
        Connection();
        //display();
    }

    public void Connection() {
        isInternetPresent = cd.isConnnectedToNet();
        if (isInternetPresent) {
            no_Connection = false;
            timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            iProgress1.incrementProgressBy(2);
                            jumpTime += 2;
                            //Log.v(TAG, "jumpTime "+ jumpTime);
                            if (jumpTime == 50) {
                                display();

                                /*
                                Intent i = new Intent(SplashActivity.this, homescreen.class);
                                SplashActivity.this.startActivityForResult(i, 1);
                                onBackPressed();*/

                                //Send Data to server
                            /*
                            try{
                                UserAesCryptography userAES = new UserAesCryptography();
                                UserRsaCryptography userRSA= new UserRsaCryptography();
                                DataService data_serv= new DataService();

                                SecretKey secret = userAES.generateKey();
                                String strKey = userAES.convertSecretKeyToString(secret);
                                //String encryptAES = userAES.encryptMessage(secret, "xJ9OsDKQDFbqAV1NezGybA== oqNTxzMJnih5FkrffV9nzg==");
                                String encryptAES = userAES.encryptMessage(secret, "6/f7/o6G6rQVuXOUnjqkPA== 3W7K+AlS5fiP/fYNoxrqvg==");
                                //Log.v("encryptAES", encryptAES);
                                String encrypt_secret_key = userRSA.encryptWithServerPublicKey(strKey, RequestData.SERVER_PUBLIC_KEY);
                                //Log.v("encrypt_secret_key", encrypt_secret_key);

                                data_serv.sendData("6/f7/o6G6rQVuXOUnjqkPA== 3W7K+AlS5fiP/fYNoxrqvg==");
                            }catch (Exception e){}
                            */


                            } else if (jumpTime == 500) {
                            /*
                            //AES
                            try {
                                UserAesCryptography userAES = new UserAesCryptography();
                                SecretKey secret = userAES.generateKey();
                                String strKey = userAES.convertSecretKeyToString(secret);
                                Log.v("SPLASH SECRETKEY", strKey);
                                String encryptAES = userAES.encryptMessage(secret, "sOKdmpz0Y2Bw3Cc9qiHG1g== A9v82WQh0oUJHBGZZ06vhQ==");
                                Log.v("SPLASH ENCRYPT", encryptAES);
                                String decryptAES = userAES.decryptMessages(strKey, encryptAES);
                                Log.v("SPLASH DECRYPT", decryptAES);

                            }catch (Exception e){}
                            */

                            /*
                            //RSA
                            try {
                                UserRsaCryptography userRSA= new UserRsaCryptography();
                                //userRSA.clearPreferences(getApplicationContext());
                                //userRSA.generateKeyPair();
                                String pub_key = userRSA.getPublicKey(getApplicationContext());
                                String pri_key = userRSA.getPrivateKey(getApplicationContext());
                                //ex. server msg decrypt using cp_pri_key
                                String decrypt_msg = userRSA.decryptWithPrivateKey(getApplicationContext(),"Wl3BL2CVcKeUzQnGDx9AvrHwfVEFgg8uXQ3LIGX+GoWWduvRJ8/IpI4jK9fgbvB5LsutzowEfZdTmxQRBT4fdwxu8WcUEv4RdXF1TIdAcJ5Q8SXS0P2r/bSRQSk8KdLSmCaFouHkT8RRfP2bHcsZwlH87VK692LHg2E9Zj+zhyE=");
                                Log.v("SPLASH RSA SERVER MSG", decrypt_msg);
                                //ex. phone msg encrypt using ser_pub_key
                                String str = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfG/Jn5Vwio/7tT/jer7q6pezq<break/>BEnhgPTgTgL/ggGDf96LviQxQ2zJhYVGfsc4tO2oL+o5qHeGfM/VjXR0lBnI9s4v<break/>1tGj6DCYgCHxdWQ6L49iBr0P2IVmXYibrnu50oEyeJfgw1HOt5IVKNsYI2U/UBmH<break/>CrIyufgQQeai3Qi98QIDAQAB<break/>";
                                str= str.replace("<break/>"," ");
                                String encrypt_msg = userRSA.encryptWithServerPublicKey("angeli.borga@yahoo.com",str);
                                Log.v("SPLASH RSA CP ENCRYPT", encrypt_msg);
                            }catch (Exception e){}
                            */

                                //Final code Exchange Public Key Pairs
                            /* */
                                try {
                                    UserRsaCryptography userRSA = new UserRsaCryptography();
                                    userRSA.clearPreferences(getApplicationContext());
                                    userRSA.generateKeyPair();
                                    String pub_key = userRSA.getPublicKey(getApplicationContext());

                                    DataService data_serv = new DataService();
                                    data_serv.getPublicKey(pub_key);
                                } catch (Exception e) {
                                }

                            }
                    }
                }
                );
            }
                public void onFinish()
                {
                    timer.cancel();
                    //connectUser();

                }

        }, 100, 100);

        } else {
            new AlertDialog.Builder(SplashActivity.this).setTitle("MESSAGE").setMessage("No Internet Connection, Please check your connection settings!").setView(text).setNegativeButton("CLOSE",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            no_Connection = true;
                            onBackPressed();
                        }
                    }).show();

        }
        //display();

    }


    @Override
    public void onBackPressed() {

        finish();
    }

    @Override
    public void onProgressChange(int current, int max) {

        if(current == max) {

        }

    }



    public void display()
    {
        if (RequestData.storedEmail.isEmpty() == false) {
            connectUser();
        } else {
            RequestData.user_Registered = "false";
            Intent i = new Intent(SplashActivity.this, homescreen.class);
            SplashActivity.this.startActivityForResult(i, 1);
            onBackPressed();
        }
    }
    // code inserted start
    private void connectUser(){
        class conUser extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(SplashActivity.this, "Connecting...", "Wait...", true, false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //loading.dismiss();
                showUser(s);
            }
            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(RequestData.KEY_Email,RequestData.storedEmail);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(RequestData.URL_CONNECTION, params);
                return s;
            }
        }
        conUser ae = new conUser();
        ae.execute();
    }
    private void showUser(String json){
        //Toast.makeText(SplashActivity.this, "insert : " + json.toString(), Toast.LENGTH_LONG).show();
        try {
            JSONObject jsonObject = new JSONObject(json);
            String error = jsonObject.getString(RequestData.TAG_ERROR);
            String msg = jsonObject.getString(RequestData.TAG_Message);
            if(error.equals("false"))
            {
                RequestData.user_Registered = "true";
                RequestData.getToken = jsonObject.getString(RequestData.TAG_Token);
                Log.v(TAG, "jsonObject: " + msg + " " + RequestData.getToken);
                Intent i = new Intent(SplashActivity.this, homescreen.class);

                SplashActivity.this.startActivityForResult(i, 1);
                onBackPressed();
            }
            else
            {
                RequestData.user_Registered = "false";
                Intent i = new Intent(SplashActivity.this, homescreen.class);

                SplashActivity.this.startActivityForResult(i, 1);
                onBackPressed();
            }


        } catch (JSONException e) {
            e.printStackTrace();
            RequestData.user_Registered = "false";
            //Toast.makeText(SplashActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }


}


