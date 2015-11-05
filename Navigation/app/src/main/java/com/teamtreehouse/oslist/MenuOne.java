package com.teamtreehouse.oslist;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by myatminmaung on 10/27/15.
 */
public class MenuOne extends Fragment{
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        rootView=inflater.inflate(R.layout.menu_one,container,false);
        return rootView;
    }

}
