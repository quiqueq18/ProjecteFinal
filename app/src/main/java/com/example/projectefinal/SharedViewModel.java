package com.example.projectefinal;

import android.annotation.SuppressLint;
import android.app.Application;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedViewModel extends AndroidViewModel {

    private final Application app;
    private static final MutableLiveData<String> currentAddress = new MutableLiveData<>();
    private final MutableLiveData<String> checkPermission = new MutableLiveData<>();
    private final MutableLiveData<String> buttonText = new MutableLiveData<>();
    private final MutableLiveData<Boolean> progressBar = new MutableLiveData<>();
    private final MutableLiveData<LatLng> currentLatLng = new MutableLiveData<>();

    private boolean mTrackingLocation;
    FusedLocationProviderClient mFusedLocationClient;

    public SharedViewModel(@NonNull Application application) {
        super(application);

        this.app = application;
    }

    void setFusedLocationClient(FusedLocationProviderClient mFusedLocationClient) {
        this.mFusedLocationClient = mFusedLocationClient;
    }

    public static LiveData<String> getCurrentAddress() {
        return currentAddress;
    }

    public MutableLiveData<String> getButtonText() {
        return buttonText;
    }

    public MutableLiveData<Boolean> getProgressBar() {
        return progressBar;
    }

    LiveData<String> getCheckPermission() {
        return checkPermission;
    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            if (locationResult != null) {
                fetchAddress(locationResult.getLastLocation());
            }
        }
    };

    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    public void switchTrackingLocation() {
        if (!mTrackingLocation) {
            startTrackingLocation(true);
        } else {
            stopTrackingLocation();
        }

    }

    @SuppressLint("MissingPermission")
    void startTrackingLocation(boolean needsChecking) {
        if (needsChecking) {
            checkPermission.postValue("check");
        } else {
            mFusedLocationClient.requestLocationUpdates(
                    getLocationRequest(),
                    mLocationCallback, null
            );

            currentAddress.postValue("Carregant...");

            progressBar.postValue(true);
            mTrackingLocation = true;
            buttonText.setValue("Aturar el seguiment de la ubicació");
        }
    }


    private void stopTrackingLocation() {
        if (mTrackingLocation) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
            mTrackingLocation = false;
            progressBar.postValue(false);
            buttonText.setValue("Comença a seguir la ubicació");
        }
    }
    private MutableLiveData<FirebaseUser> user = new MutableLiveData<>();;

    public MutableLiveData<LatLng> getCurrentLatLng() {
        return currentLatLng;
    }

    public LiveData<FirebaseUser> getUser() {
        return user;
    }

    public void setUser(FirebaseUser passedUser) {
        user.postValue(passedUser);
    }

    private void fetchAddress(Location location) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        Geocoder geocoder = new Geocoder(app.getApplicationContext(), Locale.getDefault());

        executor.execute(() -> {
            // Aquest codi s'executa en segon pla
            List<Address> addresses = null;
            String resultMessage = "";

            try {
                LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
                currentLatLng.postValue(latlng);

            } catch (Exception ioException) {
                resultMessage = "Servei no disponible";
                Log.e("INCIVISME", resultMessage, ioException);
            }
        });
    }

}

