package com.example.crystalball;

import java.util.Random;

import android.content.Context;

public class CrystallBall {
	private String[] mAnswers;
	private Random mRandom = new Random();
	
	public CrystallBall(Context context){
		mAnswers = context.getResources().getStringArray(R.array.answers);
	}
	
	public String getAnAnswer(){
		return mAnswers[mRandom.nextInt(mAnswers.length)];
	}
}