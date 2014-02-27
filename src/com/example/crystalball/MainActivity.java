package com.example.crystalball;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private CrystallBall mBall = new CrystallBall();
	private TextView mAnswerLabel;
	private Button mgetAnswerButton;
	private ImageView mBackground;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Declare our variables
        mAnswerLabel = (TextView) findViewById(R.id.textView1);
        mgetAnswerButton = (Button) findViewById(R.id.button1);
        
        mgetAnswerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				animateBackground();
				mAnswerLabel.setText(mBall.getAnAnswer());
			}
		});
    }
    
    private void animateBackground(){
    	mBackground = (ImageView) findViewById(R.id.imageView1);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
