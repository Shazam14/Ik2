package com.ikode.viezara.ikode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Terms extends Activity {

    private Button termsOkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        termsOkButton  = (Button) findViewById(R.id.termsOkBtn);


        termsOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestData.accepted_Privacy = true;
                onBackPressed();
            }
        });

    }
}
