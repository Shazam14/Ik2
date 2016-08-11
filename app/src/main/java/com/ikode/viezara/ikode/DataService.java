package com.ikode.viezara.ikode;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import javax.crypto.SecretKey;


/**
 * Created by Darkuz on 18/04/2016.
 */
public class DataService extends Activity{
    private static final String TAG = "DataService";
    private boolean currentlyProcessingLocation = false;
    private LocationRequest locationRequest;
    private GoogleApiClient googleApiClient;
    final String defaultUploadWebsite = "http://219.89.205.123/ikode/v1/getPkey";
    public String strData="";
    public String RSAPublicKey="";
    public String decryptedData;
    public JSONObject jsonResponse;

    public void getPublicKey(String cp_pub_key) {
        final RequestParams requestParams = new RequestParams();

        requestParams.put("cp_pub_key", cp_pub_key);
        LoopjHttpClient.post(defaultUploadWebsite, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                //LoopjHttpClient.debugLoopJ(TAG, "sendLocationDataToWebsite - success", defaultUploadWebsite, null, responseBody, headers, statusCode, null);
                //Log.e(TAG, new String(responseBody));
                try {
                    //Log.e(TAG, new String(responseBody));
                    JSONObject jsonObject = new JSONObject(new String(responseBody));

                    RequestData.API_SECRET_KEY= jsonObject.getString("API_SECRET_KEY");
                    RequestData.ENCRYPTED_API= jsonObject.getString("ENCRYPTED_API_KEY");
                    RequestData.SERVER_PUBLIC_SECRET_KEY= jsonObject.getString("PUBLIC_SECRET_KEY");
                    RequestData.ENCRYPTED_SERVER_PUBLIC_KEY= jsonObject.getString("ENCRYPTED_PUB_KEY");


                        try {
                            UserRsaCryptography userRSA= new UserRsaCryptography();
                            //RSA DECRYPTION
                            //Log.v("SERVICE API SECRET KEY", RequestData.API_SECRET_KEY);
                            String api_secretkey = userRSA.decryptWithPrivateKey(RequestData.API_SECRET_KEY);
                            String pub_key_secretkey = userRSA.decryptWithPrivateKey(RequestData.SERVER_PUBLIC_SECRET_KEY);
                            //Log.v("API SECRETKEY",  api_secretkey);
                            //Log.v("PUBLIC_KEY SECRETKEY",  pub_key_secretkey);

                            UserAesCryptography userAES = new UserAesCryptography();
                            RequestData.API_KEY = userAES.decryptMessages(api_secretkey, RequestData.ENCRYPTED_API);
                            RequestData.SERVER_PUBLIC_KEY = userAES.decryptMessages(pub_key_secretkey, RequestData.ENCRYPTED_SERVER_PUBLIC_KEY);
                            RequestData.SERVER_PUBLIC_KEY= RequestData.SERVER_PUBLIC_KEY.replace("<break/>", " ");
                            //Log.v("API KEY", RequestData.API_KEY);
                            //Log.v("SERVER PUBLIC_KEY", RequestData.SERVER_PUBLIC_KEY);

                        }catch (Exception e){}


                    } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] errorResponse, Throwable e) {
                LoopjHttpClient.debugLoopJ(TAG, "sendLocationDataToWebsite - failure", defaultUploadWebsite, null, errorResponse, headers, statusCode, e);

            }
        });
    }
    //Start Inserted Codes
    public void sendData(String scan_data)
    {

        final String sendKey = "http://219.89.205.123/ikode/v1/encrypt";
        final RequestParams requestParams = new RequestParams();
        String encryptAES = "";
        String encrypt_secret_key= "";
        String data_ = "";

        try{
            UserAesCryptography userAES = new UserAesCryptography();
            UserRsaCryptography userRSA= new UserRsaCryptography();
            SecretKey secret = userAES.generateKey();
            String strKey = userAES.convertSecretKeyToString(secret);
            encryptAES = userAES.encryptMessage(secret, scan_data);
            encrypt_secret_key = userRSA.encryptWithServerPublicKey(strKey, RequestData.SERVER_PUBLIC_KEY);
            String data_space = encryptAES.replace(" ", "<space>");
            data_ = data_space.replace("+", "<plus>");

        }catch (Exception e){}

        requestParams.put("api_key", RequestData.API_KEY);
        requestParams.put("secret_key", encrypt_secret_key);
        requestParams.put("data", data_);
        LoopjHttpClient.post(sendKey, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {


                try {

                    JSONObject jsonObject = new JSONObject(new String(responseBody));
                    RequestData.ref_id = jsonObject.getString("id");
                    RequestData.ref_name = jsonObject.getString("name");
                    RequestData.ref_dob = RequestData.ref_dob + jsonObject.getString("dob");
                    RequestData.ref_cc = RequestData.ref_cc + jsonObject.getString("cc");
                    RequestData.ref_date = RequestData.ref_date + jsonObject.getString("date");
                    RequestData.ref_stat = RequestData.ref_stat + jsonObject.getString("status");
                    RequestData.ref_place = RequestData.ref_place + jsonObject.getString("place");
                    RequestData.ref_img = jsonObject.getString("img");
                    decryptedData = jsonObject.toString();

                    Log.v("SERVER Response", jsonObject.toString());



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] errorResponse, Throwable e) {
                LoopjHttpClient.debugLoopJ(TAG, "sendLocationDataToWebsite - failure", sendKey, null, errorResponse, headers, statusCode, e);
                decryptedData ="";
            }
        });
        //return decryptedData;
    }
    //End Inserted Codes

}
