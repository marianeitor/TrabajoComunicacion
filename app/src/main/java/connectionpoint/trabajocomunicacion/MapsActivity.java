package connectionpoint.trabajocomunicacion;

import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                LocationManager locationManager = (LocationManager)
                        getSystemService(Context.LOCATION_SERVICE);
                LocationListener locationListener = new MyLocationListener(getBaseContext());
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 5000, 10, locationListener);

                return true;
            }
        });
        // Add a marker in Sydney and move the camera
        /*LatLng CUP = new LatLng(-31.4249103, -64.1922421);
        mMap.addMarker(new MarkerOptions().position(CUP).title("Estás aquí!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(CUP));*/


    }

    class MyLocationListener implements LocationListener {
        private Context context;

        public MyLocationListener(Context ctx){
            context = ctx;

        }
        @Override
        public void onLocationChanged(Location loc) {
            double latitud = loc.getLatitude();
            double longitud = loc.getLongitude();

            //pb.setVisibility(View.INVISIBLE);
            /*String longitude = "Longitude: " + loc.getLongitude();
            longitud = loc.getLongitude();
            latitud = loc.getLatitude();
            String latitude = "Latitude: " + loc.getLatitude();*/

            /*LatLng cup = new LatLng(latitud,longitud);
            mMap.addMarker(new MarkerOptions().position(cup).title("Estas acá"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(cup));*/


        /*------- To get city name from coordinates -------- */
            String cityName = null;
            Geocoder gcd = new Geocoder(context, Locale.getDefault());
            List<android.location.Address> addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
                if (addresses.size() > 0)
                    System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onProviderDisabled(String provider) {

            Log.println(1,"Check",provider);
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.println(1,"Check",provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.println(1,"Check",provider);
        }
    }


}

