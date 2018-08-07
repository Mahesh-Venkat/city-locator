package com.backbase.citylocator.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.backbase.citylocator.R;
import com.backbase.citylocator.adapters.CitiesAdapter;
import com.backbase.citylocator.parser.CitiesParser;
import com.backbase.citylocator.transferobjects.City;

import java.util.List;

public class MapFragment extends Fragment implements HelperFragment {
    private static final String TAG_Map_FRAGMENT = "MapFragment";

    public MapFragment() {
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedCitiesView = inflater.inflate(R.layout.fragment_cities, container, false);


        return inflatedCitiesView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public String getFragmentTag() {
        return TAG_Map_FRAGMENT;
    }
}
