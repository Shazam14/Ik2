package com.ikode.viezara.ikode;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegistrationPage extends Activity implements View.OnClickListener {

    private EditText EMAIL, PASSWORD, TXTPHRASE;
    private Button SIGNUP_BTN;
    private String MOBILE_NUMBER = "";
    private String VERIFICATION_TYPE = "";
    private TextView cancel, policy;
    private ImageButton HELPBUTTON;

    private String security_code="";
    private String input_password="";
    private String input_email="";
    private String input_phrase="";
    private String Security_Code="";
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        FacebookSdk.sdkInitialize(getApplicationContext());

        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        policy = (TextView) findViewById(R.id.textView16);

        EMAIL = (EditText) findViewById(R.id.registration_email);
        PASSWORD = (EditText) findViewById(R.id.registration_password);
        SIGNUP_BTN = (Button) findViewById(R.id.signup_btn);
//        cancel = (TextView) findViewById(R.id.textView2);
        TXTPHRASE = (EditText) findViewById(R.id.tokensignup);
        //HELPBUTTON = (ImageButton) findViewById(R.id.imageButton3);

        String sp = "Please read our Privacy Policy at this time before you register!";

        TextView pText = (TextView) findViewById(R.id.textView16);

        SpannableString policyText = new SpannableString(sp);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View policy) {
                startActivity(new Intent(RegistrationPage.this, PrivacyPolicy.class));

            }
        };
        policyText.setSpan(clickableSpan, 16, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        policyText.setSpan(new ForegroundColorSpan(Color.rgb(0, 127, 127)), 16,30, 0);
        policyText.setSpan(new UnderlineSpan(), 16, 30, 0);
        pText.setText(policyText);
        pText.setMovementMethod(LinkMovementMethod.getInstance());
        pText.setHighlightColor(Color.TRANSPARENT);



//        cancel.setOnClickListener(this);

        SIGNUP_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input_password = PASSWORD.getText().toString().trim();
                    input_email = EMAIL.getText().toString().trim();
                    input_phrase = TXTPHRASE.getText().toString().trim();
                    if (isValid(input_email) == true) {
                        if (isEmpty(input_password) == false) {
                            if(RequestData.accepted_Privacy == false)
                            {
                                Toast.makeText(getApplicationContext(), "Unable to register,. Please read our Privacy Policy.", Toast.LENGTH_LONG).show();
                            }
                            else {
                                if(TXTPHRASE.getText().toString().length() >4 && TXTPHRASE.getText().toString().length()<=16) {
                                    getVerificationDetails();
                                    addCode();
                                }
                                else
                                {Toast.makeText(getApplicationContext(), "Passphrase must be atleast 12 to 16 characters.", Toast.LENGTH_LONG).show();}
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Password is empty", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "User email is invalid", Toast.LENGTH_LONG).show();
                        EMAIL.requestFocus();
                    }

            }
        });

        /*HELPBUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hint = "Benefits of Registration:";

                TextView tx = new TextView(RegistrationPage.this);
                tx.setText(hint);
                tx.setGravity(Gravity.TOP);

                new android.support.v7.app.AlertDialog.Builder(RegistrationPage.this).setTitle("MESSAGE").setMessage("CONTENT: ").setView(tx).setNegativeButton("CLOSE",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                            }
                        }).show();

            }
        });*/

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
    }
    @Override
    public void onBackPressed() {
        finish();
    }
    //Onclick TextView
    public void onClick(View v) {
        Intent intent = new Intent(this, homescreen.class);
        startActivity(intent);
    }

    //check password
    private boolean isEmpty(String pword) {
        if (pword.toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }
    //check valid email
    public static boolean isValid(String email)
    {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
    }
    public String getDeviceID() {
        TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        String ID = tm.getDeviceId();
        return ID;
    }

    public String getPhoneNumber() {
        TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        String NUMBER = tm.getLine1Number();
        return NUMBER;
    }

    private void getVerificationDetails () {
        if(!getPhoneNumber().equals("")) {
            MOBILE_NUMBER = getPhoneNumber();
            //VERIFICATION_TYPE = RequestData.VERIFICATION_TYPE_PHONE;
        } else {
            AccountManager manager = AccountManager.get(RegistrationPage.this);
            Account[] accounts = manager.getAccounts();
            boolean checker = false;

            for(Account ctr : accounts){
                String accountNames = ctr.name;
                boolean num = RequestData.getAccountsPhoneNumber(accountNames);
                if(num) {
                    //Toast.makeText(getApplicationContext(), accountNames, Toast.LENGTH_LONG).show();
                    MOBILE_NUMBER = accountNames;
                    //VERIFICATION_TYPE = RequestData.VERIFICATION_TYPE_PHONE;
                    checker = true;
                }
            }

            if (!checker) {
                MOBILE_NUMBER = getDeviceID();
                //VERIFICATION_TYPE = RequestData.VERIFICATION_TYPE_DEVICE;
            }
        }
    }
    //Adding an User
    private void addCode(){

        class AddUser extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegistrationPage.this,"Register...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                showSecurityCode(s);
                //Toast.makeText(RegistrationPage.this, s, Toast.LENGTH_LONG).show();
                loading.dismiss();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(RequestData.KEY_Token,input_phrase);
                params.put(RequestData.KEY_Email,input_email);
                params.put(RequestData.KEY_Pass,input_password);
                params.put(RequestData.KEY_Code,MOBILE_NUMBER);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(RequestData.URL_CREATE_USER, params);
                return s;
            }
        }

        AddUser ae = new AddUser();
        ae.execute();
    }

    private void showSecurityCode(String json){
            try {
                //Toast.makeText(getApplicationContext(),  json, Toast.LENGTH_LONG).show();
                JSONObject jsonObject = new JSONObject(json);
                //String Code = jsonObject.getString(RequestData.TAG_DISPLAY_SECURITY_CODE);
                String error = jsonObject.getString(RequestData.TAG_ERROR);
                String msg = jsonObject.getString(RequestData.TAG_Message);
                if(error.equals("false"))
                {
                    RequestData.getTMobileNumber = MOBILE_NUMBER;
                    RequestData.accepted_Privacy=false;
                    Toast.makeText(RegistrationPage.this, msg, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent("android.intent.action.VERIFICATION");
                    intent.putExtra("serial", MOBILE_NUMBER);
                    intent.putExtra("phrase", TXTPHRASE.getText().toString());
                    //intent.putExtra(RequestData.display_code, Code);
                    startActivity(intent);

                    loginDataBaseAdapter.insertEntry(input_email, " ");

                    RequestData.getToken = TXTPHRASE.getText().toString();

                    onBackPressed();
                }
                else
                {
                    Toast.makeText(RegistrationPage.this, msg, Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
                //Toast.makeText(RegistrationPage.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
    }

}
