<<<<<<< Updated upstream
package com.ikode.viezara.ikode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class PrivacyPolicy extends Activity {

    Button cancel;
    Button accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_privacy_policy);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        //text1


       /* String text = "<html><body style=\"text-align:justify\"> %s </body></Html>";
        String data = "Your privacy is important to Ikona and we go to great lengths to protect it. This Privacy Policy applies to the online collection of personal information via the IkodeSC Reader® mobile applications operated by Ikona AG and its worldwide affiliated companies and subsidiaries. This Statement does not apply to information collected in any other way, including offline, through Ikona® and Ikode® Websites, or through other Ikona mobile applications. IkodeSC Reader® is a counter-fraud authenticity versification tool and may contain buttons or links to Ikona's Counter-fraud Exchanges or to sites maintained by third party Ikode® clients that offer such authenticity verification for their products, documents or services. Whereas the Ikona Counter-fraud Exchange servers are operated and it's appropriate security, third party sites are not operated or controlled by Ikona and may have different privacy practices.";
        WebView webView = (WebView) findViewById(R.id.wView1);
        webView.loadData(String.format(text, data), "text/html; charset=utf-8 ", "utf-8");
        webView.setBackgroundColor(Color.rgb(219, 227, 227));

        //text2
        String text1 = "<html><body style=\"text-align:justify\"> %s </body></Html>";
        String data1 = "•\the terms \"Personal Information\" or \"Personally Identifiable Information\" means your phone number, email address, and/or user ID information that identify you personally as well as unique device identifiers such as IMEI, MEID, IMSI, or ESN. Geo-location (GPS, WiFi) is a user choice/opt-in, though recommended;";
        WebView webView1 = (WebView) findViewById(R.id.wView2);
        webView1.loadData(String.format(text, data), "text/html; charset=utf-8 ", "utf-8");
        webView1.setBackgroundColor(Color.rgb(219, 227, 227));

        //text3
        String text2 = "<html><body style=\"text-align:justify\"> %s </body></Html>";
        String data2 = "the terms “Personal Information” and \"Personally Identifiable Information” do not include name, technical information (for example, Media Access Control “MAC” address, and Internet Protocol or “IP” address), or numbers or alpha-numeric identifiers assigned by us, third-parties, or your computer. We do not access your text messages or email, call logs, contacts/address book, financial and payment information, health and medical information, photos or videos, Web browsing history and/or apps downloaded or used on your mobile device.";
        WebView webView2 = (WebView) findViewById(R.id.wView3);
        webView2.loadData(String.format(text, data), "text/html; charset=utf-8", "utf-8");
        webView2.setBackgroundColor(Color.rgb(219, 227, 227));

        //text4 collect
        String text3 = "<html><body style=\"text-align:justify\"> %s </body></Html>";
        String data3 = "We collect information about our users in the following ways: directly from the user (when registering with us), from our Counter-fraud Exchange server & application logs every time you start to use the IkodeSC Reader® app for user authentication, as well as when you make changes to the app's Settings, for instance:";
        WebView webView3 = (WebView) findViewById(R.id.wView4);
        webView3.loadData(String.format(text, data), "text/html; charset=utf-8", "utf-8");
        webView3.setBackgroundColor(Color.rgb(219, 227, 227));*/



        cancel  = (Button) findViewById(R.id.btnCancel);
        accept  = (Button) findViewById(R.id.btnAccept);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestData.accepted_Privacy=false;
                onBackPressed();
            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestData.accepted_Privacy=true;
                onBackPressed();
            }
        });



    }


    

}
=======
package com.ikode.viezara.ikode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class PrivacyPolicy extends Activity {

    Button cancel;
    Button accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_privacy_policy);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        //text1


       /* String text = "<html><body style=\"text-align:justify\"> %s </body></Html>";
        String data = "Your privacy is important to Ikona and we go to great lengths to protect it. This Privacy Policy applies to the online collection of personal information via the IkodeSC Reader® mobile applications operated by Ikona AG and its worldwide affiliated companies and subsidiaries. This Statement does not apply to information collected in any other way, including offline, through Ikona® and Ikode® Websites, or through other Ikona mobile applications. IkodeSC Reader® is a counter-fraud authenticity versification tool and may contain buttons or links to Ikona's Counter-fraud Exchanges or to sites maintained by third party Ikode® clients that offer such authenticity verification for their products, documents or services. Whereas the Ikona Counter-fraud Exchange servers are operated and it's appropriate security, third party sites are not operated or controlled by Ikona and may have different privacy practices.";
        WebView webView = (WebView) findViewById(R.id.wView1);
        webView.loadData(String.format(text, data), "text/html; charset=utf-8 ", "utf-8");
        webView.setBackgroundColor(Color.rgb(219, 227, 227));

        //text2
        String text1 = "<html><body style=\"text-align:justify\"> %s </body></Html>";
        String data1 = "•\the terms \"Personal Information\" or \"Personally Identifiable Information\" means your phone number, email address, and/or user ID information that identify you personally as well as unique device identifiers such as IMEI, MEID, IMSI, or ESN. Geo-location (GPS, WiFi) is a user choice/opt-in, though recommended;";
        WebView webView1 = (WebView) findViewById(R.id.wView2);
        webView1.loadData(String.format(text, data), "text/html; charset=utf-8 ", "utf-8");
        webView1.setBackgroundColor(Color.rgb(219, 227, 227));

        //text3
        String text2 = "<html><body style=\"text-align:justify\"> %s </body></Html>";
        String data2 = "the terms “Personal Information” and \"Personally Identifiable Information” do not include name, technical information (for example, Media Access Control “MAC” address, and Internet Protocol or “IP” address), or numbers or alpha-numeric identifiers assigned by us, third-parties, or your computer. We do not access your text messages or email, call logs, contacts/address book, financial and payment information, health and medical information, photos or videos, Web browsing history and/or apps downloaded or used on your mobile device.";
        WebView webView2 = (WebView) findViewById(R.id.wView3);
        webView2.loadData(String.format(text, data), "text/html; charset=utf-8", "utf-8");
        webView2.setBackgroundColor(Color.rgb(219, 227, 227));

        //text4 collect
        String text3 = "<html><body style=\"text-align:justify\"> %s </body></Html>";
        String data3 = "We collect information about our users in the following ways: directly from the user (when registering with us), from our Counter-fraud Exchange server & application logs every time you start to use the IkodeSC Reader® app for user authentication, as well as when you make changes to the app's Settings, for instance:";
        WebView webView3 = (WebView) findViewById(R.id.wView4);
        webView3.loadData(String.format(text, data), "text/html; charset=utf-8", "utf-8");
        webView3.setBackgroundColor(Color.rgb(219, 227, 227));*/



        cancel  = (Button) findViewById(R.id.btnCancel);
        accept  = (Button) findViewById(R.id.btnAccept);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestData.accepted_Privacy=false;
                onBackPressed();
            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestData.accepted_Privacy=true;
                onBackPressed();
            }
        });



    }


    

}
>>>>>>> Stashed changes
