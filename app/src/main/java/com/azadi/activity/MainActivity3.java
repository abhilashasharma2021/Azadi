package com.azadi.activity;


import android.Manifest;

import android.content.Intent;

import android.content.pm.PackageManager;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.azadi.R;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


import me.dm7.barcodescanner.zxing.ZXingScannerView;



public class MainActivity3 extends AppCompatActivity  {
TextView txScan;
    ZXingScannerView mScannerView;
    private static int SPLASH_TIME_OUT = 1000;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private static final int PERMISSIONS_CALL_PHONE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main3);

        mScannerView=findViewById(R.id.mScannerView);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);


        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void run() {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        checkSelfPermission(Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]
                                    {Manifest.permission.CAMERA},
                            PERMISSIONS_REQUEST_READ_CONTACTS);

                } else {

                    IntentIntegrator integrator = new IntentIntegrator(MainActivity3.this);// Use a specific camera of the device
                    integrator.setOrientationLocked(true);
                    integrator.setBeepEnabled(true);
                    integrator.setBarcodeImageEnabled(true);
                    integrator.setCaptureActivity(CaptureActivityPortrait.class);
                    integrator.initiateScan();

                }
            }
        }, SPLASH_TIME_OUT);


        IntentIntegrator integrator = new IntentIntegrator(MainActivity3.this);// Use a specific camera of the device
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.setCaptureActivity(CaptureActivityPortrait.class);
        integrator.initiateScan();


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {

                String strScannedData = result.getContents();

                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {

                try {

                    String strScannedData = result.getContents();


                } catch (Exception e) {
                    Log.e("sh", e.getMessage());
                }



              /*  try {
                    Log.e("djdfdfdsd", result.getContents());
                    show_data(result.getContents());

                }
                catch (Exception ex) {
                    Log.e("yrioewohjgksxjl", ex.getMessage());
                    // Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }*/
            }
        } else {


            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}