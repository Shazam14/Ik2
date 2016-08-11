package com.ikode.viezara.ikode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.style.TextAlignment;

public class License extends Activity {

    private Button licenseOkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);

        DocumentView documentView = new DocumentView(this, DocumentView.PLAIN_TEXT);
        documentView.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        documentView.setText("Copyright 2016, Ikona AG\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "    http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and limitations under the License.");

        licenseOkButton  = (Button) findViewById(R.id.licenseOkBtn);

        licenseOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestData.accepted_Privacy = true;
                onBackPressed();
            }
        });
    }


}
