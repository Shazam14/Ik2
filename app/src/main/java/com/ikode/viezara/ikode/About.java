package com.ikode.viezara.ikode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //anbout us intent buttons
        /*RobotoButton aboutappbutton = (RobotoButton)findViewById(R.id.AboutApp);
        aboutappbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iaboutapp = new Intent(About.this, AboutUs.class);
                startActivity(iaboutapp);
            }
        });*/

    }


}
