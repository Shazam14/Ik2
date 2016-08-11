package com.ikode.viezara.ikode;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Darkuz on 2/03/2016.
 */
public class ContentView extends Activity {
    private TextView infoView;
    private ImageView imageView;
    private String id;
    private String dataDesc;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_doc_view);
        FacebookSdk.sdkInitialize(getApplicationContext());
        infoView = (TextView) findViewById(R.id.txtContentInfo);
        imageView  = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();
        id = getIntent().getExtras().getString("content_view");
        dataDesc = getIntent().getExtras().getString("content_view_desc");
        infoView.setText(id + " " + dataDesc);
        getData();
    }

    //aries code for getting data images and texts
    private void getData(){
        class ContentV extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ContentView.this,"Fetching...","Wait...",false,false);
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
        ContentV cv = new ContentV();
        cv.execute();
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

                Toast.makeText(ContentView.this, msg, Toast.LENGTH_LONG).show();

                String dataId = jsonObject.getString(RequestData.TAG_docId);
                String type = jsonObject.getString(RequestData.TAG_TYPE);
                String desc = jsonObject.getString(RequestData.TAG_DESC);
                String ver = jsonObject.getString(RequestData.TAG_VER);
                String img = jsonObject.getString(RequestData.TAG_Img);


                byte[] decodedString = Base64.decode(img, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageView.setImageBitmap(decodedByte);



            }
            else {
                Toast.makeText(ContentView.this, msg, Toast.LENGTH_LONG).show();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}