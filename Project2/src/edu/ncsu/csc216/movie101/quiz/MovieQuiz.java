package edu.ncsu.csc216.movie101.quiz;

import edu.ncsu.csc216.movie101.question.MovieQuestions;
import edu.ncsu.csc216.movie101.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.QuestionException;
import edu.ncsu.csc216.question_library.QuestionReader;

/**
 * MovieQuiz implements the interface QuizMaster. This
 * class has methods to get the current question text and
 * choices, processes the answer, tracks the number of
 * correct and attempted questions, and asserts whether
 * or not the quiz has more questions.
 * 
 * @author Kaleb
 */
public class MovieQuiz implements QuizMaster {
	/** The movie questions from QuestionReader */
	private MovieQuestions questions;
	/** The question reader for the question file */
	private QuestionReader reader;
	
	/**
	 * Initializes reader and questions data members
	 * @param questions the parameter required for reader
	 * @throws QuestionException if the file name being incorrect 
	 *         or the file structure being improper
	 */
	public MovieQuiz(String questionFile) throws QuestionException {
		reader = new QuestionReader(questionFile);
		questions = new MovieQuestions(reader.getStandardQuestions(),reader.getElementaryQuestions(),reader.getAdvancedQuestions());
	}

	/**
	 * Are there any more questions remaining in this test?
	 * @return true if there are, false if there are not
	 */
	@Override
	public boolean hasMoreQuestions() {
		if(questions.hasMoreQuestions()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get the text for the current question.
	 * @return the current question text
	 * @throws EmptyQuestionListException if there is no current question
	 */
	@Override
	public String getCurrentQuestionText() throws EmptyQuestionListException {
		return questions.getCurrentQuestionText();
	}

	/**
	 * Get the possible answers for the current question
	 * @return the possible answers for the current question -- each answer
	 *         is a separate array element
	 * @throws EmptyQuestionListException if there is no current question
	 */
	@Override
	public String[] getCurrentQuestionChoices() throws EmptyQuestionListException {
		return questions.getCurrentQuestionChoices();
	}

	/**
	 * Process the user's answer to the current question.
	 * @param answer  the user's answer to the question
	 * @return the graded response to the question
	 * @throws EmptyQuestionListException if there is no current question
	 */
	@Override
	public String processAnswer(String answer) throws EmptyQuestionListException {
		return questions.processAnswer(answer);
	}

	/**
	 * How many questions has the user answered correctly?
	 * @return the number of correct answers
	 */
	@Override
	public int getNumCorrectQuestions() {
		return questions.getNumCorrectAnswers();
	}

	/**
	 * How many questions has the user attempted to answer.
	 * @return the number of attempts
	 */
	@Override
	public int getNumAttemptedQuestions() {
		return questions.getNumAttemptQuestions();
	}

}
