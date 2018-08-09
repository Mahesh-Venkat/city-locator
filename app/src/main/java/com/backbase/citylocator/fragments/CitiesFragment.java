package com.backbase.citylocator.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.backbase.citylocator.R;
import com.backbase.citylocator.adapters.CitiesAdapter;
import com.backbase.citylocator.transferobjects.Cities;
import com.backbase.citylocator.transferobjects.City;

import java.util.List;

public class CitiesFragment extends Fragment implements HelperFragment, SearchView.OnQueryTextListener {
    public static final String TAG_CITIES_FRAGMENT = "CitiesFragment";

    private RecyclerView recyclerViewCities;
    private CitiesAdapter citiesAdapter;

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
        setHasOptionsMenu(true);
        setRetainInstance(true);
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
        List<City> cityList = Cities.getInstance().getCities();

        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_animation_fall_down);

        citiesAdapter = new CitiesAdapter(getActivity(), cityList);
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
            addCorrespondingCityMapFragment(city);
        }
    };

    private void addCorrespondingCityMapFragment(City city) {
        MapFragment mapFragment = new MapFragment();

        Bundle mapDetailsBundle = new Bundle();
        mapDetailsBundle.putSerializable(MapFragment.SELECTED_CITY, city);
        mapFragment.setArguments(mapDetailsBundle);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.framelayout_city_locator_container, mapFragment, mapFragment.getFragmentTag());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public String getFragmentTag() {
        return TAG_CITIES_FRAGMENT;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint(getResources().getString(R.string.search_hint_text));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        citiesAdapter.getFilter().filter(query);

        return false;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        citiesAdapter.getFilter().filter(newText);

        return false;
    }
}
