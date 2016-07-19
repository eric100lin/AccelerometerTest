package com.upatek.accelerometer_test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.widget.ImageView;

public class AccelerometerPhoneActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private static String TAG = "AccelerometerActivity";

    AnimatedView animatedView = null;
    ShapeDrawable mDrawable = new ShapeDrawable();
    public static int x;
    public static int y;
    private DisplayMetrics mMetrics = new DisplayMetrics();
    private int center_x, center_y, center_z, center_count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        getWindowManager().getDefaultDisplay().getMetrics(mMetrics);

        x= mMetrics.widthPixels/2;
        y = mMetrics.heightPixels/2;
        
        animatedView = new AnimatedView(this);
        setContentView(animatedView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
        	if(center_count<10) {
        		center_count++;
        		center_x = (int)event.values[0];
        		center_y = (int)event.values[1];
        		center_z = (int)event.values[2];
        		return;
        	}
    		
        	int x_exp = (int) (3 * Math.abs(event.values[0]-center_x));
        	int y_exp = (int) (3 * Math.abs(event.values[1]-center_y));
        	
        	x -= (event.values[0]>0) ? x_exp : -x_exp;
        	if (x<=0)	x=0;
        	else if(x>=mMetrics.widthPixels-AnimatedView.width)	x=mMetrics.widthPixels-AnimatedView.width;
        	
            y += (event.values[1]>0) ? y_exp : -y_exp;
          	if (y<=0)	y=0;
        	else if(y>=mMetrics.heightPixels-AnimatedView.height)	y=mMetrics.heightPixels-AnimatedView.height;
            
//            Log.i(TAG, event.values[0]+","+event.values[1]+","+event.values[2]+"; (x_exp,y_exp)" + x_exp+","+y_exp +"; (x,y)" + x+","+y);
        }
    }

    public class AnimatedView extends ImageView {

    	public static final int width = 50;
    	public static final int height = 50;

        public AnimatedView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub

            mDrawable = new ShapeDrawable(new OvalShape());
            mDrawable.getPaint().setColor(0xffffAC23);
            mDrawable.setBounds(x, y, x + width, y + height);

        }

        @Override
        protected void onDraw(Canvas canvas) {

            mDrawable.setBounds(x, y, x + width, y + height);
            mDrawable.draw(canvas);
            invalidate();
        }
    }
}