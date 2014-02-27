package com.example.crystalball;

import java.util.Random;

public class CrystallBall {
	private String[] answers = {
			"It is certain",
			"It is decidly so",
			"All signs yes",
			"The starts are not aligned",
			"My reply is no",
			"It is doubtful",
			"Better not to tell you now",
			"Concetrate and ask again",
			"Unable to answer now"
	};
	
	private Random random = new Random();
	
	public String getAnAnswer(){
		return answers[random.nextInt(answers.length)];
	}
}