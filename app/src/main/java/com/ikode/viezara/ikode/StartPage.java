package com.ikode.viezara.ikode;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by viezara on 7/12/2015.
 */
public class StartPage extends Activity{

    private Button SCAN;
    private String PHONE_NUMBER;

    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.start_page);

        SCAN = (Button) findViewById(R.id.scan);

        SCAN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(!getPhoneNumber().equals("")) {
                    PHONE_NUMBER = getPhoneNumber();
                } else {
                    AccountManager manager = AccountManager.get(StartPage.this);
                    Account[] accounts = manager.getAccounts();
                    boolean checker = false;

                    for(Account ctr : accounts){
                        String accountNames = ctr.name;
                        boolean num = RequestData.getAccountsPhoneNumber(accountNames);
                        if(num) {
                            //Toast.makeText(getApplicationContext(), accountNames, Toast.LENGTH_LONG).show();
                            PHONE_NUMBER = accountNames;
                            checker = true;
                        }
                    }

                    if (!checker) {
                        PHONE_NUMBER = getDeviceID();
                    }
                }

                Toast.makeText(getApplicationContext(), "Phone Number: " + PHONE_NUMBER, Toast.LENGTH_LONG).show();
                //send token
                Intent intent = new Intent("android.intent.action.VERIFICATION");
                startActivity(intent);
            }
        });
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
}
