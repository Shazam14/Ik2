package com.ikode.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.ikode.viezara.ikode.LoginDataBaseAdapter;
import com.ikode.viezara.ikode.R;

/**
 * Created by jem on 30/01/16.
 */
public class DocsFragment extends Fragment {
    LoginDataBaseAdapter loginDataBaseAdapter;
    private SimpleCursorAdapter dataAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //displayListView();
        View v =inflater.inflate(R.layout.docu, container,false);

        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(getActivity());
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        Cursor cursor = loginDataBaseAdapter.fetchAllDocs();

        // The desired columns to be bound


        String[] columns = new String[]{
                "_id",
                "type",
                "desc",
                "scan_at"
        };
        // the XML defined views which the data will be bound tso
        int[] to = new int[] {
                R.id.list_ID,
                R.id.list_type,
                R.id.list_desc,
                R.id.list_scan_at,
        };

        dataAdapter = new SimpleCursorAdapter(getActivity(), R.layout.list_row, cursor, columns, to, 0);
        final ListView myList = (ListView) v.findViewById(R.id.listView1);
        myList.setAdapter(dataAdapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                String dataID = ((TextView) v.findViewById(R.id.list_ID)).getText().toString();
                String datadesc = ((TextView) v.findViewById(R.id.list_desc)).getText().toString();
                Intent TO_REGISTRATION = new Intent("android.intent.action.ContentView");
                TO_REGISTRATION.putExtra("content_view", dataID);
                TO_REGISTRATION.putExtra("content_view_desc", datadesc);
                startActivity(TO_REGISTRATION);
                Log.v("log_tag", "List Item Click " + dataID);
            }
        });
        return v;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



}
