package edu.ncsu.csc216.movie101.question;

import java.util.List;

import edu.ncsu.csc216.movie101.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.AdvancedQuestion;
import edu.ncsu.csc216.question_library.ElementaryQuestion;
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
   private int numCorretAnswers;
   /** Tracker for the number of user attempts */
   private int numAttemptQuests;
   /** Tracker for the question state of elementary questions */
   private ElementaryQuestionState elemState;
   /** Tracker for the question state of standard questions */
   private StandardQuestionState stdState;
   /** Tracker for the question state of advance questions */
   private AdvanceQuestionState advState;
   /** Tracker for the question state of current question */
   private QuestionState state;
   /** Message for a correct answer */
   public static final String CORRECT = "Correct!";
   /** Message for an incorrect answer */
   public static final String INCORRECT = "Incorrect!";
   /** String to separate the text */
   public static final String SEPARATOR = " ";
   
   /**
    * A constructor that takes three Lists to initialize its nested concrete state classes.
    * 
    * @param standList Used to initialize the standard question state
    * @param elemList Used to initialize the elementary question state
    * @param advList Used to initialize the advance question state
    */
   public MovieQuestions(List<StandardQuestion> standList, List<ElementaryQuestion> elemList,
                         List<AdvancedQuestion> advList) {
      stdState = new StandardQuestionState(standList);
      elemState = new ElementaryQuestionState(elemList);
      advState = new AdvanceQuestionState(advList);
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
   public int getNumCorretAnswers() {
      return numCorretAnswers;
   }
   
   /**
    * Gets the number of user attempts
    * 
    * @return the number of user attempts
    */
   public int getNumAttemptQuests() {
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
      private int numCorrectInRow;
      
      public AdvanceQuestionState(List<AdvancedQuestion> advList) {
         super(List < Question > advList);
         // TODO Auto-generated constructor stub
      }
      
      @Override
      public String processAnswer(String answer) {
         // TODO Auto-generated method stub
         return null;
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
      private int attempts;
      private int numCorrectInRow;
      
      public ElementaryQuestionState(List<ElementaryQuestion> elemList) {
         super(elemList);
         // TODO Auto-generated constructor stub
      }
      
      @Override
      public String processAnswer(String answer) {
         // TODO Auto-generated method stub
         return null;
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
      
      public StandardQuestionState(List<StandardQuestion> standList) {
         super(standList);
         // TODO Auto-generated constructor stub
      }
      
      @Override
      public String processAnswer(String answer) {
         // TODO Auto-generated method stub
         return null;
      }
      
   }
   
}
