package com.teamtreehouse.oslist;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by myatminmaung on 10/28/15.
 */
public class Home extends Fragment{
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        rootView=inflater.inflate(R.layout.home_layout,container,false);
        return rootView;
    }
}
