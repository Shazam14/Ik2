package com.ikode.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ikode.viezara.ikode.R;

/**
 * Created by viezara on 11/04/2016.
 */
public class FragmentHelp extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_help_page,container,false);
        return v;
    }
}
