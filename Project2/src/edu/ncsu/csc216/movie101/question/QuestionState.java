package edu.ncsu.csc216.movie101.question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ncsu.csc216.movie101.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.Question;
import edu.ncsu.csc216.question_library.QuestionReader;

/**
 * QuestionState is an abstract class that represents state in the FSM. 
 * QuestionState is extended by private inner classes nested inside 
 * MovieQuestions, specifically StandardQuestionState, ElementaryQuestionState, 
 * and AdvancedQuestionState.
 * 
 * @author Kaleb
 */
public abstract class QuestionState {
	/** The list of questions in Question type */
	private List<Question> questions;
	/** The iterator of the list of questions */
	private Iterator<Question> it = questions.iterator();
	/** The current question from questions */
	private Question currentQuestion;
	/** The front indicator of the list */
	public static final int FRONT = 0;
	
	/**
	 * The QuestionState constructor initializes List<Question> questions
	 * @param list the list that is given by MovieQuestions
	 */
	public QuestionState(List<Question> list) {
		questions = list;
		currentQuestion = questions.get(FRONT);
	}
	
	/**
	 * Abstract method to process the user's answer
	 * @param answer the user's answer to the question
	 * @return based on what the type of question is being answered
	 * @throws EmptyQuestionListException if the current question is null
	 */
	public abstract String processAnswer(String answer) throws EmptyQuestionListException;
	
	/**
	 * Asserts whether there are more questions.
	 * @return returns true if there are more, else returns false
	 */
	public boolean hasMoreQuestions() {
		if(currentQuestion != null) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Gets the current question text.
	 * @return returns the current question text
	 * @throws EmptyQuestionListException if the current question is null
	 */
	public String getCurrentQuestionText() throws EmptyQuestionListException {
		if(currentQuestion != null) {
			return currentQuestion.getQuestion();
		} else {
			throw new EmptyQuestionListException();
		}
		
	}
	
	/**
	 * Gets the question choices from the current question.
	 * @return returns a string array of the question choices
	 * @throws EmptyQuestionListException if the current question is null
	 */
	public String[] getCurrentQuestionChoices() throws EmptyQuestionListException {
		if(currentQuestion != null) {
			List<String> choiceList = new ArrayList<String>();
			choiceList.add(currentQuestion.getChoiceA());
			choiceList.add(currentQuestion.getChoiceB());
			choiceList.add(currentQuestion.getChoiceC());
			choiceList.add(currentQuestion.getChoiceD());
			String[] choices = new String[choiceList.size()];
			choiceList.toArray(choices);
			return choices;
		} else {
			throw new EmptyQuestionListException();
		}
		
	}
	
	/**
	 * Get the current question answer
	 * @return returns the question answer
	 * @throws EmptyQuestionListException if the current question is null
	 */
	public String getCurrentQuestionAnswer() throws EmptyQuestionListException {
		if(currentQuestion != null) {
			return currentQuestion.getAnswer();
		} else {
			throw new EmptyQuestionListException();
		}
		
	}
	
	/**
	 * Gets the current question from the list
	 * @return returns the current question
	 * @throws EmptyQuestionListException if the current question is null
	 */
	public Question getCurrentQuestion() throws EmptyQuestionListException {
		if(currentQuestion != null) {
			return currentQuestion;
		} else {
			throw new EmptyQuestionListException();
		}
		
	}
	
	/**
	 * Sets currentQuestion to the next item in the questions list, or null 
	 * if there are no more questions in the list.
	 */
	public void nextQuestion() {
		if(it.hasNext()) {
			currentQuestion = it.next();
		} else {
			currentQuestion = null;
		}
	}

}
