package project.csc780.xinlu.useme.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import project.csc780.xinlu.useme.R;

public class MainActivity extends AppCompatActivity {

    private Button mFlashlightButton;
    private Button mPedometerButton;
    private Button mCompassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFlashlightButton = (Button) findViewById(R.id.flashlightButton);
        mFlashlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FlashlightActivity.class));
            }
        });

        mPedometerButton = (Button) findViewById(R.id.pedometerButton);
        mPedometerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PedometerActivity.class));
            }
        });

        mCompassButton = (Button) findViewById(R.id.compassButton);
        mCompassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CompassActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
