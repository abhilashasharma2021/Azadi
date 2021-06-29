package com.azadi.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.azadi.R;

public class TrackStepsAndCalculateRunningDistanceActivity extends AppCompatActivity implements SensorEventListener {
    //Sensor related variables
    private SensorManager sensorManager;
    private Sensor stepDetectorSensor;
    private Sensor accelerometer;
    private Sensor magnetometer;
    private float[] accelValues;
    private float[] magnetValues;

    //Variables used in calculations
    private int stepCount = 0;
    private long stepTimestamp = 0;
    private long startTime = 0;
    long timeInMilliseconds = 0;
    long elapsedTime = 0;
    long updatedTime = 0;
    private double distance = 0;

    //Activity Views
    private TextView dayRecordText;
    private TextView stepText;
    private TextView timeText;
    private TextView orientationText;
    private TextView distanceText;
    private TextView achievedText;
    private TextView speedText;
    ImageView iv_back;

    private boolean active = false; //Used to checked if the counter is running
    private Handler handler = new Handler(); //Used to update the time in the UI

    //Preferences are used to remember the step record of the day
    private SharedPreferences sharedPreferences;
    private int dayStepRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_steps_and_calculate_running_distance);
        dayRecordText = (TextView) findViewById(R.id.dayRecordText);
        stepText = (TextView) findViewById(R.id.stepText);
        timeText = (TextView) findViewById(R.id.timeText);
        speedText = (TextView) findViewById(R.id.speedText);
        distanceText = (TextView)findViewById(R.id.distanceText);
        orientationText = (TextView) findViewById(R.id.orientationText);
        achievedText = (TextView) findViewById(R.id.achievedText);
        iv_back =findViewById(R.id.iv_back);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if (stepDetectorSensor == null)
            showErrorDialog();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(TrackStepsAndCalculateRunningDistanceActivity.this);


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  startActivity(new Intent(this, HomeActivity.class));
                ((getActivity())).finish();*/
            }
        });
        setViewDefaultValues();

        //Step counting and other calculations start when user presses "start" button
        final Button startButton = (Button) findViewById(R.id.startButton);
        if (startButton != null) {
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!active) {
                        startButton.setText(R.string.pause);
                        startButton.setBackgroundColor(ContextCompat.getColor(TrackStepsAndCalculateRunningDistanceActivity.this, R.color.grey));
                        sensorManager.registerListener(TrackStepsAndCalculateRunningDistanceActivity.this, stepDetectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
                        sensorManager.registerListener(TrackStepsAndCalculateRunningDistanceActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                        sensorManager.registerListener(TrackStepsAndCalculateRunningDistanceActivity.this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
                        startTime = SystemClock.uptimeMillis();
                        handler.postDelayed(timerRunnable, 0);
                        active = true;

                    } else {
                        startButton.setText(R.string.start);
                        startButton.setBackgroundColor(ContextCompat.getColor(TrackStepsAndCalculateRunningDistanceActivity.this, R.color.grey));
                        sensorManager.unregisterListener(TrackStepsAndCalculateRunningDistanceActivity.this, stepDetectorSensor);
                        sensorManager.unregisterListener(TrackStepsAndCalculateRunningDistanceActivity.this, accelerometer);
                        sensorManager.unregisterListener(TrackStepsAndCalculateRunningDistanceActivity.this, magnetometer);
                        elapsedTime += timeInMilliseconds;
                        handler.removeCallbacks(timerRunnable);
                        active = false;
                    }
                }
            });
        }

        //Reset all calculations to 0
        Button resetButton = (Button)findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepCount = 0;
                distance = 0;
                elapsedTime = 0;
                setViewDefaultValues();
            }
        });

        //Opens SettingsActivity where user can set the step record of the day
        Button settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrackStepsAndCalculateRunningDistanceActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });



    }
    @SuppressLint("StringFormatMatches")
    private void setViewDefaultValues() {
        stepText.setText(String.format(getResources().getString(R.string.steps), 0));
        timeText.setText(String.format(getResources().getString(R.string.time), "0:00:00"));
        speedText.setText(String.format(getResources().getString(R.string.speed), 0));
        distanceText.setText(String.format(getResources().getString(R.string.distance), 0));
        orientationText.setText(String.format(getResources().getString(R.string.orientation), ""));
    }


    @Override
    public void onResume() {
        super.onResume();
      /*  dayStepRecord = sharedPreferences.getInt(ProfileActivity.DAY_STEP_RECORD, 3) * 1000;*/
        dayRecordText.setText(String.format(getResources().getString(R.string.record), dayStepRecord));
    }


    @Override
    public void onPause() {
        super.onPause();
        //Unregister for all sensors
        sensorManager.unregisterListener(this, accelerometer);
        sensorManager.unregisterListener(this, magnetometer);
        sensorManager.unregisterListener(this, stepDetectorSensor);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        //Get sensor values
        switch (event.sensor.getType()) {
            case (Sensor.TYPE_ACCELEROMETER):
                accelValues = event.values;
                break;
            case (Sensor.TYPE_MAGNETIC_FIELD):
                magnetValues = event.values;
                break;
            case (Sensor.TYPE_STEP_DETECTOR):
                countSteps(event.values[0]);
                calculateSpeed(event.timestamp);
                break;
        }

        if (accelValues != null && magnetValues != null) {
            float rotation[] = new float[9];
            float orientation[] = new float[3];
            if (SensorManager.getRotationMatrix(rotation, null, accelValues, magnetValues)) {
                SensorManager.getOrientation(rotation, orientation);
                float azimuthDegree = (float) (Math.toDegrees(orientation[0]) + 360) % 360;
                float orientationDegree = Math.round(azimuthDegree);
                getOrientation(orientationDegree);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



    //Calculates the number of steps and the other calculations related to them
    private void countSteps(float step) {

        //Step count
        stepCount += (int) step;
     stepText.setText(String.format(getResources().getString(R.string.steps), stepCount));

        //Distance calculation
        distance = stepCount * 0.8; //Average step length in an average adult
        String distanceString = String.format("%.2f", distance);
        distanceText.setText(String.format(getResources().getString(R.string.distance), distanceString));

        //Record achievement
        if (stepCount >= dayStepRecord)
            achievedText.setVisibility(View.VISIBLE);
    }


    //Calculated the amount of steps taken per minute at the current rate
    private void calculateSpeed(long eventTimeStamp) {
        long timestampDifference = eventTimeStamp - stepTimestamp;
        stepTimestamp = eventTimeStamp;
        double stepTime = timestampDifference / 1000000000.0;
        int speed = (int) (60 / stepTime);
        speedText.setText(String.format(getResources().getString(R.string.speed), speed));
    }


    //Show cardinal point (compass orientation) according to degree
    private void getOrientation(float orientationDegree) {
        String compassOrientation;
        if (orientationDegree >= 0 && orientationDegree < 90) {
            compassOrientation = "North";
        } else if (orientationDegree >= 90 && orientationDegree < 180) {
            compassOrientation = "East";
        } else if (orientationDegree >= 180 && orientationDegree < 270) {
            compassOrientation = "South";
        } else {
            compassOrientation = "West";
        }
        orientationText.setText(String.format(getResources().getString(R.string.orientation), compassOrientation));
    }


    //Runnable that calculates the elapsed time since the user presses the "start" button
    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = elapsedTime + timeInMilliseconds;

            int seconds = (int) (updatedTime / 1000);
            int minutes = seconds / 60;
            int hours = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;

            String timeString = String.format("%d:%s:%s", hours, String.format("%02d", minutes), String.format("%02d", seconds));
            timeText.setText(String.format(getResources().getString(R.string.time), timeString));
          /*  if (isAdded()) {

            }*/
            handler.postDelayed(this, 0);
        }
    };


    //Shown when necessary censors are not available
    private void showErrorDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TrackStepsAndCalculateRunningDistanceActivity.this);
        alertDialogBuilder.setMessage("Necessary step sensors not available!");

        alertDialogBuilder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TrackStepsAndCalculateRunningDistanceActivity.this.finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}