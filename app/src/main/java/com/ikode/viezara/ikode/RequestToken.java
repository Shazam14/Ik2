package com.ikode.viezara.ikode;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Darkuz on 3/03/2016.
 */
public class RequestToken extends Activity{
    private TextView txtEmail;
    private Button btnRequestToken;
    private String input_email;
    private String id;
    private String dataDesc;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_forgot_token);
        FacebookSdk.sdkInitialize(getApplicationContext());
        txtEmail = (TextView) findViewById(R.id.registration_email);
        btnRequestToken  = (Button) findViewById(R.id.req1Token_btn);

        btnRequestToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input_email = txtEmail.getText().toString().trim();
                if (isEmpty(input_email) == false) {
                    if (isValid(input_email) == true && RequestData.storedEmail.equals(input_email)) {
                            sendData();
                    } else {
                        Toast.makeText(getApplicationContext(), "User email is invalid / not registered", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "User email is empty", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    //check valid email
    public boolean isValid(String email)
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
    @Override
    public void onBackPressed() {
        finish();
    }
    //check password
    private boolean isEmpty(String email) {
        if (email.toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }
    //aries code for getting data images and texts
    private void sendData(){
        class ContentV extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RequestToken.this,"Sending requested token...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                showData(s);
                loading.dismiss();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> v = new HashMap<>();
                v.put(RequestData.KEY_Email, input_email);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(RequestData.URL_REQUEST_TOKEN, v);
                return s;
            }
        }
        ContentV cv = new ContentV();
        cv.execute();
    }

    private void showData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            String error = jsonObject.getString(RequestData.TAG_ERROR);
            String msg = jsonObject.getString(RequestData.TAG_Message);

            if(error.equals("false")) {
                Toast.makeText(RequestToken.this, msg, Toast.LENGTH_LONG).show();
                //Intent i = new Intent(RequestToken.this, homescreen.class);
                //startActivity(i);
                onBackPressed();
            }
            else {
                Toast.makeText(RequestToken.this, msg, Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
