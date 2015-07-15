package edu.ncsu.csc216.movie101.question;

import java.util.List;

import edu.ncsu.csc216.question_library.Question;

public abstract class QuestionState {
	public static final int FRONT;
	
	public QuestionState(List<Question> list) {
		
	}
	
	public abstract String processAnswer(String answer);
	
	public boolean hasMoreQuestions() {
		return false;
		
	}
	
	public String getCurrentQuestionText() {
		return null;
		
	}
	
	public String[] getCurrentQuestionChoices() {
		return null;
		
	}
	
	public String getCurrentQuestionAnswer() {
		return null;
		
	}
	
	public Question getCurrentQuestion() {
		return null;
		
	}
	
	public void nextQuestion() {
		
	}

}
