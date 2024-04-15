package com.example.sensors_sf;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textViewX, textViewY, textViewZ;
    private SensorManager sensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.err.println("test onCreate");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textViewX = findViewById(R.id.textViewX);
        textViewY = findViewById(R.id.textViewY);
        textViewZ = findViewById(R.id.textViewZ);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null) {
            System.err.println("Датчик не доступен на данном устройстве");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.err.println("test onResume");

        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.err.println("test onPause");
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float changedValueX = event.values[0];
        float changedValueY = event.values[1];
        float changedValueZ = event.values[2];
//        System.err.println("получены новые данные: " + changedValue);
        textViewX.setText("X coord:  " +changedValueX);
        textViewY.setText("Y coord:  " +changedValueY);
        textViewZ.setText("Z coord:  " +changedValueZ);

        System.err.println("X coord:  " +changedValueX);
        System.err.println("Y coord:  " +changedValueY);
        System.err.println("Z coord:  " +changedValueZ);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}