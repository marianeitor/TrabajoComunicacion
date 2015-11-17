package connectionpoint.trabajocomunicacion;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private boolean isLocated = false;
    String categoria;
    Locales listaLocales = new Locales();
    Marker selectedMarker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            categoria = extras.getString("categoria");
        }

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
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                if (!isLocated)
                {
                    try {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude()), 15.0f));
                        for (Local _loc : listaLocales.getLocales(categoria)) {
                            if (_loc.isOpened()) {
                                Marker _mark = mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(_loc.getLatitud(), _loc.getLongitud()))
                                        .title(_loc.getNombre())
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                                        .snippet(_loc.getDireccion() + " - ABIERTO"));
                                _mark.showInfoWindow();
                            }
                            else {
                                Marker _mark = mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(_loc.getLatitud(), _loc.getLongitud()))
                                        .title(_loc.getNombre())
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                                        .snippet(_loc.getDireccion()));
                            }
                        }
                        isLocated = true;
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });


        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                try {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude()), 15.0f));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                LocationManager locationManager = (LocationManager)
                        getSystemService(Context.LOCATION_SERVICE);
                LocationListener locationListener = new MyLocationListener(getBaseContext());
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 5000, 10, locationListener);

               return true;
            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                try {
                    if (selectedMarker.getTitle().equals(marker.getTitle()) && selectedMarker.getSnippet().equals(marker.getSnippet())) {
                        selectedMarker = marker;
                        for (Local _loc : listaLocales.getLocales(categoria)) {
                            if (_loc.getNombre().equals(marker.getTitle())) {
                                openDialog(_loc.getDescuento());
                                return false;
                            }
                        }
                    }
                    else
                    {
                        selectedMarker = marker;
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    selectedMarker = marker;
                }
                return false;
            }
        });



    }

    class MyLocationListener implements LocationListener {
        private Context context;

        public MyLocationListener(Context ctx){
            context = ctx;

        }
        @Override
        public void onLocationChanged(Location loc) {
           /* double latitud = loc.getLatitude();
            double longitud = loc.getLongitude();


            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitud, longitud), 16.0f));
            //pb.setVisibility(View.INVISIBLE);
            String longitude = "Longitude: " + loc.getLongitude();
            longitud = loc.getLongitude();
            latitud = loc.getLatitude();
            String latitude = "Latitude: " + loc.getLatitude();*/

            /*LatLng cup = new LatLng(latitud,longitud);
            mMap.addMarker(new MarkerOptions().position(cup).title("Estas acá"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(cup));*/


        /*------- To get city name from coordinates --------
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
            }*/

        }

        @Override
        public void onProviderDisabled(String provider) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
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

    private void openDialog(final String _codigo)
    {
        final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(MapsActivity.this);
        dlgAlert.setMessage("Deseas obtener un descuento para este local?");
        dlgAlert.setTitle("Atencion");
        dlgAlert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        openCodeDialog(_codigo);
                        dialog.dismiss();
                    }
                });
        dlgAlert.setCancelable(false);
        dlgAlert.setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dlgAlert.create().show();
    }

    private void openCodeDialog(String _codigo)
    {
        final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(MapsActivity.this);
        dlgAlert.setMessage(_codigo);
        dlgAlert.setTitle("Tu codigo es");
        dlgAlert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dlgAlert.setCancelable(false);
        dlgAlert.create().show();
    }

}

