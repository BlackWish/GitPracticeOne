package com.teamtreehouse.oslist;

import android.app.Fragment;
import android.content.Intent;
import android.gesture.Gesture;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by myatminmaung on 10/28/15.
 */
public class Recipes extends Fragment{

    View rootView;
    Button timerClick;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        rootView=inflater.inflate(R.layout.recipes_layout,container,false);

        //Connecting ID
        timerClick=(Button)rootView.findViewById(R.id.goTimer);

        timerClick.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent switchTimer= new Intent(v.getContext(), Clock.class);
                        startActivityForResult(switchTimer,3);
                    }
                }

        );

        return rootView;
    }
}
