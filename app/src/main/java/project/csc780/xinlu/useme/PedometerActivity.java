package project.csc780.xinlu.useme;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Xinlu Chen on 5/13/17.
 */

public class PedometerActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sManager;
    private Sensor mSensorAccelerometer;
    private TextView mStepTextView;
    private Button mStartButton;

    private int step = 0;
    private double originValue = 0;
    private double lastValue = 0;
    private double currentValue = 0;
    private boolean motiveState = true;    // if it is in the moving state
    private boolean processState = false;  // if it is in the counting state


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorAccelerometer = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sManager.registerListener(this, mSensorAccelerometer, SensorManager.SENSOR_DELAY_UI);
        bindViews();
    }

    private void bindViews() {
        mStepTextView = (TextView) findViewById(R.id.stepTextView);
        mStartButton = (Button) findViewById(R.id.startButton);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step = 0;
                mStepTextView.setText("0");
                if (processState) {
                    mStartButton.setText(R.string.start_button);
                    processState = false;
                } else {
                    mStartButton.setText(R.string.stop_button);
                    processState = true;
                }
            }
        });
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        double range = 2;
        float[] value = event.values;
        currentValue = magnitude(value[0], value[1], value[2]);

        // accelerating upwards
        if (motiveState) {
            if (currentValue >= lastValue) lastValue = currentValue;
            else {
                if (Math.abs(currentValue - lastValue) > range) {
                    originValue = currentValue;
                    motiveState = false;
                }
            }
        }

        // accelerating downwards
        if (!motiveState) {
            if (currentValue <= lastValue) lastValue = currentValue;
            else {
                if (Math.abs(currentValue - lastValue) > range) {
                    originValue = currentValue;
                    if (processState) {
                        step++;
                        if (processState) {
                            mStepTextView.setText(step + "");
                        }
                    }
                    motiveState = true;
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    public double magnitude(float x, float y, float z) {
        double magnitude = 0;
        magnitude = Math.sqrt(x * x + y * y + z * z);
        return magnitude;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sManager.unregisterListener(this);
    }
}
