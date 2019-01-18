package me.dlovan.findhotelsonmap;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainPage extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private FloatingActionButton btnAddHote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnAddHote = findViewById(R.id.AddButtonID);

        btnAddHote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this, AddingHotels.class));

//                Snackbar.make(v, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.option_menu, menu);
        return true;
    }
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "Add1 Clicked", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mMap = googleMap;

        //TODO:  Solving marker to add database
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(point));
            }
        });


        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_jason));
        mMap.setOnMarkerClickListener(this);
        //Jyan Hotel
        mMap.addMarker(new MarkerOptions()
                .title("Jyan Hotel")
                .position(new LatLng(36.8692589, 42.9990615))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
        );
        //Jotyar Hotel
        mMap.addMarker(new MarkerOptions()
                .title("Jyan Hotel")
                .position(new LatLng(36.855332, 43.000852))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
        );

        //initializing center of map
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.868358, 42.955613), 10));

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("My Locations: ", location.toString());
                mMap.clear(); // clears our map
                LatLng newLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(newLocation).title("New Location"));

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 10));
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try {
                    List<Address> addressList = geocoder.getFromLocation(location.getLatitude(),
                            location.getLongitude(), 1
                    );
                    String fullAddress = "";
                    if (addressList != null && addressList.size() > 0) {
                        Log.d("Address: ", addressList.get(0).toString());
                        Toast.makeText(MainPage.this, addressList.get(0).getAddressLine(0), Toast.LENGTH_LONG).show();
                        if (addressList.get(0).getAddressLine(0) != null) {
                            fullAddress += addressList.get(0).getAddressLine(0) + " ";
                        }
                        if (addressList.get(0).getSubAdminArea() != null) {
                            fullAddress += addressList.get(0).getSubAdminArea() + " ";
                        }
                        Toast.makeText(MainPage.this, "Address+" + fullAddress, Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("Address:", "Couldn't find Address");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

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

        if (Build.VERSION.SDK_INT < 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {
                //Ask for permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {
                // we have permission!
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0]
                == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION
            )
                    == PackageManager.PERMISSION_GRANTED)

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2, 2, locationListener);

        }

    }



    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        Toast.makeText(this, "Marker Clicked Tag: " + marker.getTag(), Toast.LENGTH_SHORT).show();
        return false;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
