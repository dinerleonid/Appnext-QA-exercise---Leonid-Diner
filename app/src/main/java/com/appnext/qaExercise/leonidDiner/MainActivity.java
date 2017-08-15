package com.appnext.qaExercise.leonidDiner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.appnext.ads.interstitial.Interstitial;
import com.appnext.ads.interstitial.InterstitialConfig;
import com.appnext.base.Appnext;
import com.appnext.core.Ad;
import com.appnext.core.callbacks.OnAdClicked;
import com.appnext.core.callbacks.OnAdClosed;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnAdLoaded;
import com.appnext.core.callbacks.OnAdOpened;

public class MainActivity extends AppCompatActivity {

    private Button btnAppnextSdk;
    private Interstitial interstitial_Ad;
    private static final String PLACEMENT_ID = "24233eba-345a-4128-a6af-48e14d9dd858";
    public InterstitialConfig config;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        config = new InterstitialConfig();
        config.setButtonColor("#ff0000");
        config.setOrientation(Ad.ORIENTATION_LANDSCAPE);
        Appnext.init(this);
        interstitial_Ad = new Interstitial(this, PLACEMENT_ID,  config);
        btnAppnextSdk = (Button) findViewById(R.id.btnAppnextSdk);
        btnAppnextSdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interstitial_Ad.loadAd();
                interstitial_Ad.showAd();
            }
        });
        // Get callback for ad loaded
        interstitial_Ad.setOnAdLoadedCallback(new OnAdLoaded() {
            @Override
            public void adLoaded(String bannerID) {
                Toast.makeText(MainActivity.this, "Ad Loaded - " + bannerID, Toast.LENGTH_SHORT).show();
            }
        });
        // Get callback for ad opened
        interstitial_Ad.setOnAdOpenedCallback(new OnAdOpened() {
            @Override
            public void adOpened() {
                Toast.makeText(MainActivity.this, "Ad Opened", Toast.LENGTH_SHORT).show();
            }
        });
        // Get callback for ad clicked
        interstitial_Ad.setOnAdClickedCallback(new OnAdClicked() {
            @Override
            public void adClicked() {
                Toast.makeText(MainActivity.this, "Ad Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        // Get callback for ad closed
        interstitial_Ad.setOnAdClosedCallback(new OnAdClosed() {
            @Override
            public void onAdClosed() {
                Toast.makeText(MainActivity.this, "Ad Closed", Toast.LENGTH_SHORT).show();
            }
        });
        // Get callback for ad error
        interstitial_Ad.setOnAdErrorCallback(new OnAdError() {
            @Override
            public void adError(String error) {
                Toast.makeText(MainActivity.this, "Error Occurred - " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(this, "App Terminated", Toast.LENGTH_SHORT).show();
        finish();
    }
}
