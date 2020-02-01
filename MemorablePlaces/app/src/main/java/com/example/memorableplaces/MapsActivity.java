package com.example.memorableplaces;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    Location location1;


    public void centermaponlocaion(Location location,String locname)
    {
        LatLng userlocaion=new LatLng(location.getLatitude(),location.getLongitude());

        if(locname!="Your Location")
        {
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(userlocaion).title(locname));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userlocaion,10));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION)==
                PackageManager.PERMISSION_GRANTED)
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);
        Intent intent=getIntent();
        int i;
        //Toast.makeText(this,intent.getIntExtra("PlaceNumber",0),Toast.LENGTH_SHORT).show();


        if(intent.getIntExtra("PlaceNumber",0)==0) {

            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    centermaponlocaion(location, "Your Location");
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            }
        }
        else
        {
            mMap.clear();
            int pos=intent.getIntExtra("PlaceNumber",0);
            LatLng loc=MainActivity.locations.get(pos);
            mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .position(loc).title(MainActivity.arrayList.get(pos)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc,10));
        }


    }

    @Override
    public void onMapLongClick(LatLng latLng)
    {
        mMap.clear();
        String adr="";
        Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> addresses=geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
            if(addresses!=null &&addresses.size()>0)
            {
                if(addresses.get(0).getThoroughfare()!=null)
                {
                    if(addresses.get(0).getSubThoroughfare()!=null)
                    {
                        adr+=addresses.get(0).getSubThoroughfare();
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if(adr =="")
        {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm yyyy-MM-dd ");
            adr=simpleDateFormat.format(new Date());

        }

        mMap.addMarker(new MarkerOptions()
               .position(latLng)
               .title(adr)
               .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        Log.i("location tapped",latLng.toString());

        MainActivity.locations.add(latLng);
        Toast.makeText(this,"Location Saved",Toast.LENGTH_SHORT).show();
        MainActivity.listView.setAdapter(MainActivity.arrayAdapter);
        MainActivity.arrayList.add(adr);
        SharedPreferences sharedPreferences;
        sharedPreferences=this.getSharedPreferences("com.example.memorableplaces", Context.MODE_PRIVATE);
        try {
            ArrayList<String> latitiude=new ArrayList<String>();
            ArrayList<String> longitude=new ArrayList<String>();

            for(LatLng coordinates:MainActivity.locations)
            {
                latitiude.add(Double.toString(coordinates.latitude));
                longitude.add(Double.toString(coordinates.longitude));
            }

            sharedPreferences.edit().putString("location",ObjectSerializer.serialize(MainActivity.arrayList)).apply();
            sharedPreferences.edit().putString("latitude",ObjectSerializer.serialize(latitiude)).apply();
            sharedPreferences.edit().putString("longitude",ObjectSerializer.serialize(longitude)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }



}
