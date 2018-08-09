package com.backbase.citylocator.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.backbase.citylocator.R;
import com.backbase.citylocator.asynctasks.ParsingAsyncTask;
import com.backbase.citylocator.interfaces.TaskListener;
import com.backbase.citylocator.transferobjects.Cities;
import com.backbase.citylocator.transferobjects.City;

import java.util.List;

public class WelcomeFragment extends Fragment implements HelperFragment, TaskListener, View.OnClickListener {

    public static final String TAG_WELCOME_FRAGMENT = WelcomeFragment.class.getSimpleName();

    private ProgressDialog progressDialog;
    private boolean isTaskRunning = false;
    private ParsingAsyncTask asyncTask;

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
        button.setOnClickListener(this);

        initiateProgressDialogObject();

        return rootView;
    }

    private void initiateProgressDialogObject() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.custom_progress_drawable));
        progressDialog.setTitle(getResources().getString(R.string.loading_bar_welcome_page_title));
        progressDialog.setMessage(getResources().getString(R.string.loading_bar_welcome_page_message));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_welcome_continue) {
            if (!isTaskRunning) {
                asyncTask = new ParsingAsyncTask(getActivity(), this);
                asyncTask.execute();
            }
        }
    }

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

    @Override
    public void onTaskStarted() {
        isTaskRunning = true;
        progressDialog.show();
    }

    @Override
    public void onTaskFinished(List<City> cities) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        isTaskRunning = false;

        Cities.getInstance().setCities(cities);

        addCitiesFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (isTaskRunning) {
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        }
    }

    @Override
    public void onDetach() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        super.onDetach();
    }
}