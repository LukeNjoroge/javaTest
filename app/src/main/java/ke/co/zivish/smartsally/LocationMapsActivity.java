package ke.co.zivish.smartsally;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.Connection;

public class LocationMapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_maps);
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

    private Marker myMarker;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Nairobi and move the camera
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }else {
            mMap.setMyLocationEnabled(true);
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        /*mMap.addMarker(new MarkerOptions().position(latLng).title("Current Position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));*/

        LatLng latLng = new LatLng(-1.266624, 36.804344);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));

        googleMap.setOnMarkerClickListener(this);

        myMarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-1.232809, 36.930691))
                .title("Victoria Salon"));

        //SABT Salon Marker
        myMarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(-1.217792, 36.902720))
                .title("SABT Salon"));
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        String name= marker.getTitle();

        if (name.equalsIgnoreCase("Victoria Salon"))
        {
            Intent marker_intent = new Intent(LocationMapsActivity.this, EventActivity.class);
            startActivity(marker_intent);
        }

        if (name.equalsIgnoreCase("SABT Salon"))
        {
            Intent marker_intent = new Intent(LocationMapsActivity.this, EventActivity.class);
            startActivity(marker_intent);
        }
        return false;
    }
}
