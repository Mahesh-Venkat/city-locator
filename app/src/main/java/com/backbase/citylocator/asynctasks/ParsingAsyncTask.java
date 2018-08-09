package com.backbase.citylocator.asynctasks;

import android.app.Activity;
import android.os.AsyncTask;

import com.backbase.citylocator.interfaces.TaskListener;
import com.backbase.citylocator.parser.CitiesParser;
import com.backbase.citylocator.transferobjects.City;

import java.util.List;

public class ParsingAsyncTask extends AsyncTask<String, Integer, List<City>> {

    private final TaskListener listener;
    private Activity activity;

    public ParsingAsyncTask(Activity mActivity, TaskListener listener) {
        this.listener = listener;
        this.activity = mActivity;
    }

    @Override
    protected void onPreExecute() {
        listener.onTaskStarted();
    }

    @Override
    protected List<City> doInBackground(String... params) {

        return new CitiesParser(activity).getCityList();
    }

    @Override
    protected void onPostExecute(List<City> result) {
        listener.onTaskFinished(result);
    }
}
