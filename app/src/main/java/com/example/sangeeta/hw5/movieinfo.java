package com.example.sangeeta.hw5;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class movieinfo extends Fragment {

    ShareActionProvider myshareact;
    HashMap<String,?> mov;

    public movieinfo() {
        // Required empty public constructor
    }

    public static movieinfo newInstance(HashMap<String ,?> movie){

        movieinfo myfragment = new movieinfo();
        Bundle args = new Bundle();
        args.putSerializable("Movie", movie);
        myfragment.setArguments(args);
        return myfragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = null;
        mov = (HashMap<String,?>)getArguments().getSerializable("Movie");

        rootView = inflater.inflate(R.layout.fragment_movieinfo, container, false);

        ImageView mimg = (ImageView) rootView.findViewById(R.id.image2);
        TextView mdes = (TextView) rootView.findViewById(R.id.des2);
        TextView mtit = (TextView) rootView.findViewById(R.id.title2);
        TextView my = (TextView) rootView.findViewById(R.id.year2);
        RatingBar mr = (RatingBar) rootView.findViewById(R.id.rate2);
        TextView mc = (TextView) rootView.findViewById(R.id.cast);
        TextView mlen = (TextView) rootView.findViewById(R.id.mlen);
        TextView mdir = (TextView) rootView.findViewById(R.id.dir);

        //setting resources

        mimg.setImageResource((Integer) mov.get("image"));
        mtit.setText((String) mov.get("name"));
        mdes.setText((String) mov.get("description"));
        my.setText((String) mov.get(("year")));
        mc.setText((String) mov.get("stars"));
        mlen.setText((String) mov.get("length"));
        mdir.setText((String) mov.get("director"));

        double e= (Double) mov.get("rating");
        mr.setRating(((Double) (e / 2D)).floatValue());

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        if(menu.findItem(R.id.share)==null){
            inflater.inflate(R.menu.menufrag3, menu);

            MenuItem shareitem =menu.findItem(R.id.share);
            myshareact=(ShareActionProvider) MenuItemCompat.getActionProvider(shareitem);

            Intent intentshare= new Intent(Intent.ACTION_SEND);
            intentshare.setType("text/plain");

            intentshare.putExtra(Intent.EXTRA_TEXT, (String) mov.get("name"));
            myshareact.setShareIntent(intentshare);

            super.onCreateOptionsMenu(menu, inflater);


        }

    }


}
