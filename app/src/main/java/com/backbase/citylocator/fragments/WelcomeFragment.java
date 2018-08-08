package com.backbase.citylocator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.backbase.citylocator.R;

public class WelcomeFragment extends Fragment implements HelperFragment{

    public static final String TAG_WELCOME_FRAGMENT = WelcomeFragment.class.getSimpleName();

    public WelcomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);

        Button button = rootView.findViewById(R.id.button_welcome_continue);
        button.setOnClickListener(continueButtonClickListener);

        return rootView;
    }

    private View.OnClickListener continueButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCitiesFragment();
        }
    };

    private void addCitiesFragment() {
        CitiesFragment citiesFragment = new CitiesFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.framelayout_city_locator_container, citiesFragment, CitiesFragment.TAG_CITIES_FRAGMENT);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public String getFragmentTag() {
        return TAG_WELCOME_FRAGMENT;
    }
}