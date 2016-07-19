package com.upatek.accelerometer_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btnStartRotation = (Button) findViewById(R.id.btnStartRotation);
		Button btnStartPhoneMouse = (Button) findViewById(R.id.btnStartPhoneMouse);
		Button btnStartGlassMouse = (Button) findViewById(R.id.btnStartGlassMouse);
//		Button btnStartBouncingBall = (Button) findViewById(R.id.btnStartBouncingBall);
		btnStartRotation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartActivity.this, RotationVectorDemo.class);
				startActivity(intent);
//				finish();
			}
		});
		btnStartPhoneMouse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartActivity.this, AccelerometerPhoneActivity.class);
				startActivity(intent);
//				finish();
			}
		});
		btnStartGlassMouse.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartActivity.this, AccelerometerGlassActivity.class);
				startActivity(intent);
//				finish();
			}
		});
//		btnStartBouncingBall.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(StartActivity.this, BouncingBallActivity.class);
//				startActivity(intent);
////				finish();
//			}
//		});
	}
}
