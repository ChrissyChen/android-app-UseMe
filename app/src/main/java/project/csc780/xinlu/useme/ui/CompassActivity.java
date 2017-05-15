package project.csc780.xinlu.useme.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;

import project.csc780.xinlu.useme.CompassView;
import project.csc780.xinlu.useme.R;

/**
 * Created by Xinlu Chen on 5/14/17.
 */

public class CompassActivity extends AppCompatActivity implements SensorEventListener {

    private CompassView mCompassView;
    private SensorManager mSensorManager;
    private Sensor mSensorOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompassView = new CompassView(CompassActivity.this);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mSensorManager.registerListener(this, mSensorOrientation, SensorManager.SENSOR_DELAY_UI);

//        LinearLayout layout = new LinearLayout(CompassActivity.this);
//        ImageView imageView = new ImageView(this);
//        imageView.setImageURI(Uri.fromFile(new File("/data/data/....")));
//        LinearLayout.LayoutParams params =
//                new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.gravity = Gravity.CENTER;
//        layout.addView(imageView, params);
//        setContentView(layout);
        //setContentView(R.layout.activity_compass);
        setContentView(mCompassView);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        mCompassView.setDegree(event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homeAction:
                startActivity(new Intent(CompassActivity.this, MainActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
