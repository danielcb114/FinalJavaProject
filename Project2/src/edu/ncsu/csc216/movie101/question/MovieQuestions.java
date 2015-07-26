package edu.ncsu.csc216.movie101.question;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.movie101.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.AdvancedQuestion;
import edu.ncsu.csc216.question_library.ElementaryQuestion;
import edu.ncsu.csc216.question_library.Question;
import edu.ncsu.csc216.question_library.StandardQuestion;

/**
 * MovieQuestions maintains the questions and determines the order in which they are to be presented
 * based on the user's answers.
 * 
 * @author Kaleb
 *
 */
public class MovieQuestions {
   /** Tracker for the number of user correct answers */
   private int numCorrectAnswers;
   /** Tracker for the number of user attempts */
   private int numAttemptQuests;
   /** Tracker for the question state of elementary questions */
   private ElementaryQuestionState elemState;
   /** List for the elementary questions */
   private List<Question> elemQuestions = new ArrayList<Question>();
   /** Tracker for the question state of standard questions */
   private StandardQuestionState stdState;
   /** List for the standard questions */
   private List<Question> stdQuestions = new ArrayList<Question>();
   /** Tracker for the question state of advance questions */
   private AdvanceQuestionState advState;
   /** List for the advance questions */
   private List<Question> advQuestions = new ArrayList<Question>();
   /** Tracker for the question state of current question */
   private QuestionState state;
   /** Message for a correct answer */
   public static final String CORRECT = "Correct!";
   /** Message for an incorrect answer */
   public static final String INCORRECT = "Incorrect!";
   /** String to separate the text */
   public static final String SEPARATOR = " ";
   /** String to indicate hint */
   public static final String HINT = "Hint: ";
   
   /**
    * A constructor that takes three Lists to initialize its nested concrete state classes.
    * 
    * @param standList Used to initialize the standard question state
    * @param elemList Used to initialize the elementary question state
    * @param advList Used to initialize the advance question state
    */
   public MovieQuestions(List<StandardQuestion> standList, List<ElementaryQuestion> elemList,
                         List<AdvancedQuestion> advList) {
      for (ElementaryQuestion question : elemList) {
         elemQuestions.add(question);
      }
      for (StandardQuestion question : standList) {
         stdQuestions.add(question);
      }
      for (AdvancedQuestion question : advList) {
         advQuestions.add(question);
      }
      stdState = new StandardQuestionState(stdQuestions);
      elemState = new ElementaryQuestionState(elemQuestions);
      advState = new AdvanceQuestionState(advQuestions);
      state = stdState;
   }
   
   /**
    * Asserts whether there are more questions.
    * 
    * @return returns true if there are more, else returns false
    */
   public boolean hasMoreQuestions() {
      return state.hasMoreQuestions();
      
   }
   
   /**
    * Gets the current question text.
    * 
    * @return returns the current question text
    * @throws EmptyQuestionListException if the current question is null
    */
   public String getCurrentQuestionText() throws EmptyQuestionListException {
      return state.getCurrentQuestionText();
      
   }
   
   /**
    * Gets the question choices from the current question.
    * 
    * @return returns a string array of the question choices
    * @throws EmptyQuestionListException if the current question is null
    */
   public String[] getCurrentQuestionChoices() throws EmptyQuestionListException {
      return state.getCurrentQuestionChoices();
      
   }
   
   /**
    * Process the user's answer
    * 
    * @param answer the user's answer to the question
    * @return based on what the type of question is being answered
    * @throws EmptyQuestionListException if the current question is null
    */
   public String processAnswer(String answer) throws EmptyQuestionListException {
      return state.processAnswer(answer);
      
   }
   
   /**
    * Gets the number of user correct answers
    * 
    * @return the number of user correct answers
    */
   public int getNumCorrectAnswers() {
      return numCorrectAnswers;
   }
   
   /**
    * Gets the number of user attempts
    * 
    * @return the number of user attempts
    */
   public int getNumAttemptedQuestions() {
      return numAttemptQuests;
   }
   
   /**
    * AdvanceQuestionState maintains the advance questions and determines if there needs to be a
    * transitions based on the user's answers.
    * 
    * @author Kaleb
    *
    */
   public class AdvanceQuestionState extends QuestionState {
      
      /**
       * The constructor for AdvanceQuestoinState
       * 
       * @param advQuestions the list that contains advance questions
       */
      public AdvanceQuestionState(List<Question> advQuestions) {
         super(advQuestions);
      }
      
      /**
       * Processes the user's answer based on an advance question
       * 
       * @param answer the user's answer
       * @throws EmptyQuestionListException if the current question is null
       */
      @Override
      public String processAnswer(String answer) throws EmptyQuestionListException {
         if (answer.equalsIgnoreCase(getCurrentQuestionAnswer())) {
        	numAttemptQuests++;
         	numCorrectAnswers++;
            nextQuestion();
            return CORRECT + SEPARATOR + "Good Job!";
         } else {
        	 numAttemptQuests++;
        	 nextQuestion();
        	 state = stdState;
        	 return INCORRECT;
         }
      }
      
   }
   
   /**
    * ElementaryQuestionState maintains the elementary questions and determines if there needs to be
    * a transitions based on the user's answers.
    * 
    * @author Kaleb
    *
    */
   public class ElementaryQuestionState extends QuestionState {
      /** Tracks the number of attempts on the same question */
      private int attempts;
      /** Tracks the number of correct questions in a row */
      private int numCorrectInRow;
      
      /**
       * The constructor for ElementaryQuestoinState also sets the data members of attempts and
       * numCorrectInRow to zero.
       * 
       * @param elemQuestions the list that contains elementary questions
       */
      public ElementaryQuestionState(List<Question> elemQuestions) {
         super(elemQuestions);
         attempts = 0;
         numCorrectInRow = 0;
      }
      
      /**
       * Processes the user's answer based on an elementary question
       * 
       * @param answer the user's answer
       * @throws EmptyQuestionListException if the current question is null
       */
      @Override
      public String processAnswer(String answer) throws EmptyQuestionListException {
         if (answer.equalsIgnoreCase(getCurrentQuestionAnswer())) {
        	 numAttemptQuests++;
         	 numCorrectAnswers++;
        	 attempts++;
        	 if (numCorrectInRow == 1 && attempts == 1) {
            	numCorrectInRow = 0;
            	nextQuestion();
            	state = stdState;
            } else if (attempts == 1) {
            	attempts = 0;
            	numCorrectInRow++;
            	nextQuestion();
            } else {
            	attempts = 0;
            	nextQuestion();
            }
            return CORRECT;
         } else {
        	numAttemptQuests++;
            attempts++;
            numCorrectInRow = 0;
            if (attempts >= 2) {
               attempts = 0;
               nextQuestion();
               return INCORRECT;
            } else {
            	ElementaryQuestion elem = (ElementaryQuestion) getCurrentQuestion();
                return INCORRECT + SEPARATOR + HINT + elem.getHint();
            }
            
         }
      }
      
   }
   
   /**
    * StandardQuestionState maintains the standard questions and determines if there needs to be a
    * transitions based on the user's answers.
    * 
    * @author Kaleb
    *
    */
   public class StandardQuestionState extends QuestionState {
      /** Tracks the number of correct questions in a row */
      private int numCorrectInRow;
      
      /**
       * The constructor for StandardQuestoinState also sets the data member numCorrectInRow to
       * zero.
       * 
       * @param stdQuestions the list that contains standard questions
       */
      public StandardQuestionState(List<Question> stdQuestions) {
         super(stdQuestions);
         numCorrectInRow = 0;
      }
      
      /**
       * Processes the user's answer based on an standard question
       * 
       * @param answer the user's answer
       * @throws EmptyQuestionListException if the current question is null
       */
      @Override
      public String processAnswer(String answer) throws EmptyQuestionListException {
         if (answer.equalsIgnoreCase(getCurrentQuestionAnswer())) {
        	numAttemptQuests++;
         	numCorrectAnswers++;
            if (numCorrectInRow >= 1) {
            	nextQuestion();
            	state = advState;
            } else {
               nextQuestion();
               numCorrectInRow++;
            }
            return CORRECT;
         } else {
        	 numAttemptQuests++;
        	 nextQuestion();
        	 numCorrectInRow = 0;
        	 state = elemState;
        	 return INCORRECT;
         }
      }
      
   }
   
}
