package com.example.crystalball;

import java.util.Random;

import android.content.Context;

public class CrystallBall {
	private String[] answers;
	private Context context;
	private Random random = new Random();
	
	public CrystallBall(Context context){
		this.context = context;
		answers = context.getResources().getStringArray(R.array.answers);
	}
	
	public String getAnAnswer(){
		return answers[random.nextInt(answers.length)];
	}
}