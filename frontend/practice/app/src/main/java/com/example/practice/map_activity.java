package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.overlay.PathOverlay;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.widget.CompassView;
import com.naver.maps.map.widget.LocationButtonView;
import com.naver.maps.map.widget.ScaleBarView;
import com.naver.maps.map.widget.ZoomControlView;

import java.util.ArrayList;
import java.util.List;

public class map_activity extends AppCompatActivity implements OnMapReadyCallback, Overlay.OnClickListener{

    private static final String TAG = "MapActivity";
    private NaverMap naverMap;
    private FusedLocationSource locationSource;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private MapView mapView;

    private final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    List<LatLng> lstLatLng = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
//        FragmentManager fm = getSupportFragmentManager();
//        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);
//        if (mapFragment == null) {
//            mapFragment = MapFragment.newInstance();
//            fm.beginTransaction().add(R.id.map, mapFragment).commit();
//        }
        mapView.getMapAsync(this);
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Log.d(TAG, "onMapReady");

        this.naverMap = naverMap;
        naverMap.setLocationSource(locationSource);  // ?????? ?????? ??????

        Marker marker = new Marker();
        CameraPosition cameraPosition = new CameraPosition(
                new LatLng(
                        naverMap.getCameraPosition().target.latitude,
                        naverMap.getCameraPosition().target.longitude
                ),9);
        marker.setPosition(new LatLng(
                naverMap.getCameraPosition().target.latitude,
                naverMap.getCameraPosition().target.longitude
        ));
        marker.setMap(naverMap);

        marker.setOnClickListener(this);


//        // UI ????????? ?????????
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setCompassEnabled(true); // ????????? : true
        uiSettings.setScaleBarEnabled(true); // ????????? : true
        uiSettings.setZoomControlEnabled(true); // ????????? : true
        uiSettings.setLocationButtonEnabled(true); // ????????? : false
//        uiSettings.setLocationButtonEnabled(false); // ????????? : false
//        uiSettings.setLogoGravity(Gravity.RIGHT | Gravity.BOTTOM);
//
//        CompassView compassView = findViewById(R.id.compass);
//        compassView.setMap(naverMap);
//        ScaleBarView scaleBarView = findViewById(R.id.scalebar);
//        scaleBarView.setMap(naverMap);
//        ZoomControlView zoomControlView = findViewById(R.id.zoom);
//        zoomControlView.setMap(naverMap);
//        LocationButtonView locationButtonView = findViewById(R.id.location);
//        locationButtonView.setMap(naverMap);

        ActivityCompat.requestPermissions(this, PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);

        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated()) {   // ?????? ?????????
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
                return;
            } else {
                naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
            }
        }
    }
    @Override
    public boolean onClick(@NonNull Overlay overlay) {
        if (overlay instanceof Marker) {
            Toast.makeText(this.getApplicationContext(), "????????? ?????????????????????", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}