package com.backbase.citylocator.interfaces;

import com.backbase.citylocator.transferobjects.City;

import java.util.List;

public interface TaskListener {
    void onTaskStarted();

    void onTaskFinished(List<City> result);
}