package com.ikode.viezara.ikode;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by viezara on 3/02/2016.
 */
public class ConnectionDetector {

    private Context ctx;

    public ConnectionDetector (Context context){
        this.ctx = context;
    }

    public boolean isConnnectedToNet(){
        ConnectivityManager connectManager = (ConnectivityManager)ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);
        if (connectManager != null)
        {
            NetworkInfo[] info = connectManager.getAllNetworkInfo();
            if(info != null )
                for (int i = 0; i < info.length; i++ )
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)

            {   return true;

            }

        }return  false;


    }




    }


