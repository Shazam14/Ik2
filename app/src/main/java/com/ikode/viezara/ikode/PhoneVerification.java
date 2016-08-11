package com.ikode.viezara.ikode;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by viezara on 16/11/2015.
 */
public class PhoneVerification extends Activity{

    private Button VERIFY_BTN;
    private EditText txtSecurityCode;
    private TextView tvSecurityCode;
    private ProgressDialog DIALOG;
    private String DEVICE_ID;
    private String PhoneNumber,SecurityCode;
    private String VerifiedCode;
    private String Phrase;
    private String PhoneSerial;
    private TextView codeHandler;
    private String Security_Code="";
    private String convertPhrase="";
    private StringBuilder sb;


    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.phone_verification_layout);

        DIALOG = new ProgressDialog(this);
        VERIFY_BTN = (Button) findViewById(R.id.verifyBtn);
        txtSecurityCode = (EditText) findViewById(R.id.tokenCode);

        Intent intent = getIntent();
        PhoneNumber = intent.getStringExtra("serial");
        Phrase = intent.getStringExtra("phrase");


        Security_Code = intent.getStringExtra(RequestData.display_code);
        //codeHandler = (TextView) findViewById(R.id.verificationCode);
        //codeHandler.setText(Security_Code);
        codeHandler = (TextView) findViewById(R.id.passPhrase);
        //codeHandler.setText(Phrase);

        convertPhrase=Phrase.toString();
        int str_len = (Phrase.toString().length()/4);
        sb = new StringBuilder(Phrase.toString());
        for (int index = 4; index < sb.length(); index++) {
            if (sb.charAt(index) == ' ') {
            } else {
                sb.setCharAt(index, '*');
            }
        }
        codeHandler.setText(sb.toString());

        VERIFY_BTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SecurityCode =txtSecurityCode.getText().toString();
                if(isEmpty(SecurityCode)==false) {
                    //getData();
                    verify();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter security code or code sent to your email", Toast.LENGTH_LONG).show();
                }
            }

        });

    }
    @Override
    public void onBackPressed() {
        finish();
    }
    //check security code
    private boolean isEmpty(String scode) {
        if (scode.toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }
    private void verify(){
        if ( (SecurityCode.equals(convertPhrase))) {Toast.makeText(getApplicationContext(), "User Credential is Verified!", Toast.LENGTH_LONG).show();
            //Intent intent = new Intent("android.intent.action.UserConnect");
            Intent intent = new Intent("android.intent.action.homescreen");
            startActivity(intent);
            RequestData.user_Registered= "true";
            onBackPressed();
        } else {
            Toast.makeText(getApplicationContext(), "Verification failed: Code is Incorrect or Expired ", Toast.LENGTH_LONG).show();
        }
    }
    //Inserted Code
    private void getData(){
        class GetUser extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(PhoneVerification.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showPhone(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(RequestData.URL_GET_SECURITY_NUMBER,SecurityCode);
                return s;
            }
        }
        GetUser ge = new GetUser();
        ge.execute();
    }

    private void showPhone(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(RequestData.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            VerifiedCode = c.getString(RequestData.TAG_SECURITY_NUMBER);
            PhoneSerial = c.getString(RequestData.TAG_SERIAL_NUMBER);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        verify();
    }
    //End Inserted COde

    protected void onResume() {
        super.onResume();
    }
}
