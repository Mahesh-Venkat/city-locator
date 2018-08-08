package com.backbase.citylocator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.backbase.citylocator.R;
import com.backbase.citylocator.transferobjects.City;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements HelperFragment, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public static final String SELECTED_CITY = "Selected City";

    private static final String TAG_MAP_FRAGMENT = MapFragment.class.getSimpleName();

    private static final int CAMERA_ZOOM = 5;
    private static final int CAMERA_BEARING = 0;
    private static final int CAMERA_TILT = 30;

    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mGoogleMap;
    private MapView mMapView;
    private City selectedCity;

    public MapFragment() {

    }

    public static MapFragment newInstance(City city) {
        MapFragment fragment = new MapFragment();

        Bundle args = new Bundle();
        args.putSerializable(SELECTED_CITY, city);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            selectedCity = (City) getArguments().get(SELECTED_CITY);
        }

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView_map_city_map_view);
        if (mMapView != null) {
            mMapView.onCreate(savedInstanceState);
            mMapView.onResume();
        }

        TextView textViewMapInfo = rootView.findViewById(R.id.textView_map_city_country_name);
        textViewMapInfo.setText(getResources().getString(R.string.label_city_name, selectedCity.getName(), selectedCity.getCountry()));

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
        mMapView.onResume();
        setUpMap();

        Log.d(TAG_MAP_FRAGMENT, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected())
            mGoogleApiClient.disconnect();
        mMapView.onPause();

        Log.d(TAG_MAP_FRAGMENT, "onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();

        Log.d(TAG_MAP_FRAGMENT, "onDestroy");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();

        Log.d(TAG_MAP_FRAGMENT, "onLowMemory");
    }

    @Override
    public void onConnected(Bundle bundle) {

        Log.d(TAG_MAP_FRAGMENT, "onConnected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG_MAP_FRAGMENT, "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG_MAP_FRAGMENT, "onConnectionFailed");
    }

    private void setUpMap() {
        if (mGoogleMap == null)
            mMapView.getMapAsync(new OnMapReadyCallback() {

                @Override
                public void onMapReady(GoogleMap googleMap) {


                    City.GPSCoordinates gpsCoordinates = selectedCity.getCoord();

                    mGoogleMap = googleMap;

                    mGoogleMap.setMapStyle(
                            MapStyleOptions.loadRawResourceStyle(getActivity(), com.backbase.citylocator.R.raw.map_style));

                    mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(gpsCoordinates.getLat(), gpsCoordinates.getLon()));
                    markerOptions.title(selectedCity.getName());
                    mGoogleMap.addMarker(markerOptions);

                    CameraPosition cityCameraPosition = CameraPosition.builder().target(new LatLng(gpsCoordinates.getLat(), gpsCoordinates.getLon())).zoom(CAMERA_ZOOM).bearing(CAMERA_BEARING).tilt(CAMERA_TILT).build();

                    mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cityCameraPosition));
                }
            });
    }

    @Override
    public String getFragmentTag() {
        return TAG_MAP_FRAGMENT;
    }
}