package edu.ncsu.csc216.movie101.question;

import java.util.List;

import edu.ncsu.csc216.question_library.AdvancedQuestion;
import edu.ncsu.csc216.question_library.ElementaryQuestion;
import edu.ncsu.csc216.question_library.Question;
import edu.ncsu.csc216.question_library.StandardQuestion;

public class MovieQuestions {
	private int numCorretAnswers;
	private int numAttemptQuests;
	public static final String CORRECT;
	public static final String INCORRECT;
	public static final String SEPARATOR;
	
	public MovieQuestions(List<StandardQuestion> standList, List<ElementaryQuestion> elemList, List<AdvancedQuestion> advList) {
		
	}
	
	public boolean hasMoreQuestions() {
		return false;
		
	}
	
	public String getCurrentQuestionText() {
		return null;
		
	}
	
	public String[] getCurrentQuestionChoices() {
		return null;
		
	}
	
	public String processAnswer(String answer) {
		return answer;
		
	}

	public int getNumCorretAnswers() {
		return numCorretAnswers;
	}

	public int getNumAttemptQuests() {
		return numAttemptQuests;
	}
	
	public class AdvanceQuestionState extends QuestionState {
		private int numCorrectInRow;

		public AdvanceQuestionState(List<Question> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String processAnswer(String answer) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public class ElementaryQuestionState extends QuestionState {
		private int attempts;
		private int numCorrectInRow;

		public ElementaryQuestionState(List<Question> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String processAnswer(String answer) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public class StandardQuestionState extends QuestionState {

		public StandardQuestionState(List<Question> list) {
			super(list);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String processAnswer(String answer) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
