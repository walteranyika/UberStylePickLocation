package com.walter.dragmap;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnCameraIdleListener, GoogleMap.OnCameraMoveListener {

    private GoogleMap mMap;

    ImageView imgPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        imgPin=findViewById(R.id.imgLocationPinUp);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-1.2647, 36.8006);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Westlands Nairobi"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,18f));
        mMap.setOnCameraMoveListener(this);
        mMap.setOnCameraIdleListener(this);

    }

    @Override
    public void onCameraIdle() {
        imgPin.setVisibility(View.GONE);
        MarkerOptions markerOptions=new MarkerOptions().position(mMap.getCameraPosition().target)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_icon));
        mMap.addMarker(markerOptions);
    }

    @Override
    public void onCameraMove() {

     mMap.clear();
     imgPin.setVisibility(View.VISIBLE);

    }
}
