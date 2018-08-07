package com.backbase.citylocator.fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class CitiesFragment extends Fragment implements HelperFragment {
    private static final String TAG_CITIES_FRAGMENT = "CitiesFragment";

    private RecyclerView recyclerViewCities;

    public CitiesFragment() {
    }

    public static CitiesFragment newInstance() {
        CitiesFragment fragment = new CitiesFragment();
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
        recyclerViewCities = inflatedCitiesView.findViewById(R.id.recyclerView_cities);

        setRecyclerViewCities();

        return inflatedCitiesView;
    }

    private void setRecyclerViewCities() {
        List<City> cities = new CitiesParser(getActivity()).getCityList();
        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_animation_fall_down);

        CitiesAdapter citiesAdapter = new CitiesAdapter(getActivity(), cities);
        citiesAdapter.setOnMainMenuItemClickListener(mainMenuItemClickListener);
        recyclerViewCities.setLayoutAnimation(animationController);
        recyclerViewCities.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewCities.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        recyclerViewCities.setAdapter(citiesAdapter);
    }

    private CitiesAdapter.OnItemClickListener mainMenuItemClickListener = new CitiesAdapter.OnItemClickListener() {
        @Override
        public void onItemClicked(City city) {

        }
    };

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
        return TAG_CITIES_FRAGMENT;
    }
}
