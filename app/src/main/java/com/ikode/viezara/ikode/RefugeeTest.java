package com.ikode.viezara.ikode;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devspark.robototextview.widget.RobotoTextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RefugeeTest extends Fragment {


    public RefugeeTest() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RobotoTextView migrantCheck = (RobotoTextView) getView().findViewById(R.id.migrantCheck);
        migrantCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent("android.intent.action.CAPTURE"));
            }
        });
        return inflater.inflate(R.layout.fragment_refugee_test, container, false);


    }

}
