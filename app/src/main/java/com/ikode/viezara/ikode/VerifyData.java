package com.ikode.viezara.ikode;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;

public class VerifyData extends AppCompatActivity {

    private EditText editDataId;
    private EditText editReceived;
    private EditText editDataType;
    private EditText editDesc;
    private EditText editVer;
    private ImageView imageView;
    private Button buttonSets, buttonSave;
    private SharedPreferences SP;
    private boolean save_Docs =false;
    private EditText editTxtConfirm;
    //for Toolbar
    private Toolbar toolbar;

    private String id;
    private String docId;
    private String token;

    private String JSON_STRING;

    LoginDataBaseAdapter loginDataBaseAdapter;

    private String sql_id="";
    private String sql_type="";
    private String sql_desc="";
    private String MOBILE_NUMBER="";
    private boolean checker = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_data);

        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        //for toolbar
       Toolbar  actionBarToolBar = (Toolbar) findViewById(R.id.ikode_bar);
        setSupportActionBar(actionBarToolBar);

        Intent intent = getIntent();

        id = intent.getStringExtra(RequestData.barcode_ID);
        /** whats app functionality
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is the message");
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);
        **/

        editDataId = (EditText) findViewById(R.id.editDataId);
        editDataType = (EditText) findViewById(R.id.editDataType);
        editDesc = (EditText) findViewById(R.id.editDesc);
        editVer = (EditText) findViewById(R.id.editVer);
        imageView  = (ImageView) findViewById(R.id.imageView);
        buttonSets = (Button) findViewById(R.id.settButt);
        buttonSave = (Button) findViewById(R.id.saveButt);
        editTxtConfirm = (EditText) findViewById(R.id.txtConfirmation);
        editReceived = (EditText) findViewById(R.id.txtRequestor);
        editDataId.setText(id);

        SP = getSharedPreferences(RequestData.SESSION, Context.MODE_PRIVATE);
        String uname = SP.getString(RequestData.SESSION_CODE, RequestData.DEFAULT_SESSION_VALUE);

        if(uname.equals(RequestData.DEFAULT_SESSION_VALUE)){
            buttonSets.setVisibility(View.GONE);
            buttonSave.setVisibility(View.GONE);
        }
        getVerificationDetails();
        getData();

        buttonSets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent TO_SETTINGS = new Intent("android.intent.action.Settings1");
                startActivity(TO_SETTINGS);
            }


        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (save_Docs == true) {
                    String insert_Response = loginDataBaseAdapter.insertScan(sql_id, sql_type, sql_desc);
                    Toast.makeText(VerifyData.this, insert_Response, Toast.LENGTH_LONG).show();
                    Intent uprof = new Intent("android.intent.action.UserProfile");
                    startActivity(uprof);
                }
            }


        });
    } //END OF ONCREATE

    //this is for the menu bar for toolbar *gmbg*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_profile){
            //Intent intent = new Intent(this, UserConnect.class);
            //startActivity(intent);
            /*
            if(checker) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Data ID: " + editDataId.getText() + "\n"
                        + "Data Type: " + editDataType.getText() + "\n"
                        + "Data Description: " + editDesc.getText() +"\n"
                        + "Data Version: " + editVer.getText());
                sendIntent.setType("text/plain");
                //sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);



            }
            */
        }
        return super.onOptionsItemSelected(item);
    }


    //aries code for getting data images and texts
    private void getData(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(VerifyData.this,"Fetching...","Wait...",false,false);
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
                v.put(RequestData.KEY_docId, id);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(RequestData.URL_GET_DATA, v);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            String error = jsonObject.getString(RequestData.TAG_ERROR);
            String msg = jsonObject.getString(RequestData.TAG_Message);
            //int success = jsonObject.getInt(Config.TAG_SUCCESS);
            if(error.equals("false")) {
                //JSONArray result = jsonObject.getJSONArray(RequestData.TAG_JSON_ARRAY);
                //JSONObject c = result.getJSONObject(0);

                Toast.makeText(VerifyData.this, msg, Toast.LENGTH_LONG).show();

                String dataId = jsonObject.getString(RequestData.TAG_docId);
                String type = jsonObject.getString(RequestData.TAG_TYPE);
                String desc = jsonObject.getString(RequestData.TAG_DESC);
                String ver = jsonObject.getString(RequestData.TAG_VER);
                String img = jsonObject.getString(RequestData.TAG_Img);

                    editDataId.setText(dataId);
                    editDataType.setText(type);
                    editDesc.setText(desc);
                    editVer.setText(ver);
                    editTxtConfirm.setText("Product Verified Authentic");
                    editReceived.setText(MOBILE_NUMBER);

                    //getImage();

                    byte[] decodedString = Base64.decode(img, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    imageView.setImageBitmap(decodedByte);

                    //sqlite data
                    sql_id = dataId;
                    sql_type = type;
                    sql_desc = desc;
                    save_Docs=true;

            }
            else {
                Toast.makeText(VerifyData.this, msg, Toast.LENGTH_LONG).show();
                save_Docs=false;
                checker = false;
                buttonSave.setVisibility(View.GONE);
                editTxtConfirm.setText("We can not confirm its original content");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Start Insert
    private void getImage() {
        docId = id;
        class GetImage extends AsyncTask<String,Void,Bitmap>{


            ImageView bmImage;
            ProgressDialog loading1;

            public GetImage(ImageView bmImage) {
                this.bmImage = bmImage;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                loading1.dismiss();
                bmImage.setImageBitmap(bitmap);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading1 = ProgressDialog.show(VerifyData.this,"Downloading Image","Please wait...",true,true);
            }

            @Override
            protected Bitmap doInBackground(String... strings) {
                String url = RequestData.URL_GET_DATA_IMAGE + strings[0];
                Bitmap mIcon = null;
                try {
                    InputStream in = new java.net.URL(url).openStream();
                    mIcon = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                }
                return mIcon;
            }
        }

        GetImage gi = new GetImage(imageView);

        gi.execute(docId);
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
            AccountManager manager = AccountManager.get(VerifyData.this);
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

}
