package com.example.crystalball;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crystalball.ShakeDetector.OnShakeListener;

public class MainActivity extends Activity {
	private CrystallBall mBall;
	private TextView mAnswerLabel;
	private ImageView mBackground;
	private SensorManager mSensorManager;
	private Sensor mAcelerometer;
	private ShakeDetector mShakeDector;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Declare our variables
        mAnswerLabel = (TextView) findViewById(R.id.textView1);
        mBackground = (ImageView) findViewById(R.id.imageView1);
        
        mBall = new CrystallBall(this);
        // Sensor
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAcelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDector = new ShakeDetector(new OnShakeListener() {
			@Override
			public void onShake() {
				handleNewAnswer();
			}
		});
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	mSensorManager.registerListener(mShakeDector, mAcelerometer, SensorManager.SENSOR_DELAY_UI);
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    	mSensorManager.unregisterListener(mShakeDector);
    }
    
    private void animateBackground(){
    	mBackground.setImageResource(R.drawable.ball_animation);
    	AnimationDrawable animation = (AnimationDrawable) mBackground.getDrawable();
    	if(animation.isRunning()){
    		animation.stop();
    	}
    	animation.start();
    }
    
    private void animateAnswer(){
    	AlphaAnimation fadeInAnswer = new AlphaAnimation(0,1);
    	fadeInAnswer.setDuration(2000);
    	fadeInAnswer.setFillAfter(true);
    	mAnswerLabel.setAnimation(fadeInAnswer);
    }
    
    private void playSound(){
    	MediaPlayer player = MediaPlayer.create(this, R.raw.sound);
    	player.start();
    	player.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	private void handleNewAnswer() {
		animateBackground();
		playSound();
		mAnswerLabel.setText(mBall.getAnAnswer());
	}
    
}
