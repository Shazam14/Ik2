package com.ikode.viezara.ikode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Darkuz on 22/06/2016.
 */
public class Refugee extends AppCompatActivity {
    private String id;
    private ImageView mImageView1;
    public TextView txtname,txtid,txtcc,txtdob,txtplace,txtdate,txtstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refugee_profile_screen_xml_ui_design);

        txtname= (TextView) findViewById(R.id.user_profile_name);
        txtid= (TextView) findViewById(R.id.user_profile_short_bio);
        txtcc= (TextView) findViewById(R.id.user_cc);
        txtdob= (TextView) findViewById(R.id.user_dob);
        txtplace = (TextView) findViewById(R.id.user_place);
        txtdate = (TextView) findViewById(R.id.user_date);
        txtstatus = (TextView) findViewById(R.id.user_status);
        mImageView1 = (ImageView) findViewById(R.id.user_profile_photo);
        mImageView1.bringToFront();



        //Intent intent = getIntent();
        //id = intent.getStringExtra(RequestData.scan_qr);

        Display();

    }

    public void Display(){

            txtname.setText(RequestData.ref_name);
            txtid.setText(RequestData.ref_id);
            txtcc.setText(RequestData.ref_cc);
            txtdob.setText(RequestData.ref_dob);
            txtplace.setText(RequestData.ref_place);
            txtdate.setText(RequestData.ref_date);
            txtstatus.setText(RequestData.ref_stat);

            startLoad();
    }

    private void startLoad() {
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(
                "http://219.89.205.123/ikode/v1/" + RequestData.ref_img,
                new BinaryHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {
                        Log.d("Refugee", "onSuccess");
                        Bitmap bitmap = BitmapFactory.decodeByteArray(binaryData, 0, binaryData.length);
                        mImageView1.setImageBitmap(bitmap);
                        mImageView1.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
                        error.printStackTrace();
                        Log.d("Refugee", "onFailure");
                    }
                });

        //RequestParams param = new RequestParams();
        //param.put("hpge", "fuga");

    }
    @Override
    public void onBackPressed() {
        RequestData.clearRefugee();
        finish();
    }
}
