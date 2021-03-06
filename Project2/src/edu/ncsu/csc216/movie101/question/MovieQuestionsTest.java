package edu.ncsu.csc216.movie101.question;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.question_library.AdvancedQuestion;
import edu.ncsu.csc216.question_library.ElementaryQuestion;
import edu.ncsu.csc216.question_library.StandardQuestion;

/**
 * @author dcbrewer
 */
public class MovieQuestionsTest {
   
   /**
    * MovieQuestions field used for testing
    */
   MovieQuestions movieQuestions;
   
   /**
    * Elementary question list used for testing
    */
   List<ElementaryQuestion> listElem;
   
   /**
    * Standard question list used for testing
    */
   List<StandardQuestion> listStd;
   
   /**
    * Advanced question list used for testing
    */
   List<AdvancedQuestion> listAdv;
   
   /**
    * Setup method; fills the MovieQuestions field with question objects
    */
   @Before
   public void setUp() {
      
      listElem = new ArrayList<ElementaryQuestion>();
      
      listStd = new ArrayList<StandardQuestion>();
      
      listAdv = new ArrayList<AdvancedQuestion>();
      
      // add to easy list
      listElem.add(makeEasyQuestion("Easy question 1", "A", "B", "C", "D", "d", "Pick D"));
      listElem.add(makeEasyQuestion("Easy question 2", "A", "B", "C", "D", "c", "Pick C"));
      listElem.add(makeEasyQuestion("Easy question 3", "A", "B", "C", "D", "b", "Pick B"));
      
      // add to standard list
      listStd.add(makeQuestion("Question 1", "A", "B", "C", "D", "d"));
      listStd.add(makeQuestion("Question 2", "A", "B", "C", "D", "c"));
      listStd.add(makeQuestion("Question 3", "A", "B", "C", "D", "b"));
      
      // add to advanced list
      listAdv.add(makeHardQuestion("Question 1", "A", "B", "C", "D", "d", "Good job!"));
      listAdv.add(makeHardQuestion("Question 2", "A", "B", "C", "D", "c", "Good job!"));
      listAdv.add(makeHardQuestion("Question 3", "A", "B", "C", "D", "b", "Good job!"));
      
      // fill out field
      movieQuestions = new MovieQuestions(listStd, listElem, listAdv);
   }
   
   /**
    * Tests the movieQuestions method
    */
   @Test
   public void testMovieQuestions() throws Exception {
      
      List<ElementaryQuestion> listElementary = listElem;
      List<StandardQuestion> listStandard = listStd;
      List<AdvancedQuestion> listAdvanced = listAdv;
      
      MovieQuestions movieQuestionsTest = new MovieQuestions(listStandard, listElem, listAdv);
      
      assertTrue(movieQuestionsTest instanceof MovieQuestions);
   }
   
   /**
    * Tests the hasMoreQuestions method
    */
   @Test
   public void testHasMoreQuestions() throws Exception {
      // should be true
      assertTrue(movieQuestions.hasMoreQuestions());
      // get a standard question wrong
      movieQuestions.processAnswer("a");
      // should be true
      assertTrue(movieQuestions.hasMoreQuestions());
      
      // get 3 easy questions wrong, at 2 attempts each
      for (int i = 0; i < listElem.size(); i++) {
         movieQuestions.processAnswer("a");
         assertTrue(movieQuestions.hasMoreQuestions());
         movieQuestions.processAnswer("a");
         if (i != listElem.size() - 1) {
            assertTrue(movieQuestions.hasMoreQuestions());
         }
      }
      
      // should be false
      assertFalse(movieQuestions.hasMoreQuestions());
   }
   
   /**
    * Tests the getCurrentQuestionText method
    */
   @Test
   public void testGetCurrentQuestionText() throws Exception {
      
      assertEquals(listStd.get(0).getQuestion(), movieQuestions.getCurrentQuestionText());
      movieQuestions.processAnswer("a");// wrong answer to standard
      
      assertEquals(listElem.get(0).getQuestion(), movieQuestions.getCurrentQuestionText());
      movieQuestions.processAnswer("a");// wrong answer to 1st easy, 1st attempt
      
      assertEquals(listElem.get(0).getQuestion(), movieQuestions.getCurrentQuestionText());
      movieQuestions.processAnswer("d"); // right answer to 1st easy, 2nd
      
      assertEquals(listElem.get(1).getQuestion(), movieQuestions.getCurrentQuestionText());
      movieQuestions.processAnswer("c"); // right answer to 2nd easy
      
      assertEquals(listElem.get(2).getQuestion(), movieQuestions.getCurrentQuestionText());
      movieQuestions.processAnswer("b"); // right answer to 3rd easy
      
      assertEquals(listStd.get(1).getQuestion(), movieQuestions.getCurrentQuestionText());
      movieQuestions.processAnswer("c"); // right answer to 2nd standard
      
      assertEquals(listStd.get(2).getQuestion(), movieQuestions.getCurrentQuestionText());
      movieQuestions.processAnswer("b"); // right answer to 3rd standard
      
      movieQuestions.getCurrentQuestionText();
      
      assertEquals(listAdv.get(0).getQuestion(), movieQuestions.getCurrentQuestionText());
      // PROBLEM LIES HERE
      movieQuestions.processAnswer("d"); // right answer to 1st hard
      
      movieQuestions.getCurrentQuestionText();
      
      assertEquals(listAdv.get(1).getQuestion(), movieQuestions.getCurrentQuestionText());
      movieQuestions.processAnswer("c"); // right answer to 2nd hard
      
      assertEquals(listAdv.get(2).getQuestion(), movieQuestions.getCurrentQuestionText());
      movieQuestions.processAnswer("b"); // right answer to 3rd hard
      
   }
   
   /**
    * Tests the getCurrentQuestionChoices method
    */
   @Test
   public void testGetCurrentQuestionChoices() throws Exception {
      String[] choices = { "A", "B", "C", "D" };
      
      assertTrue(equalAnswers(choices, movieQuestions.getCurrentQuestionChoices()));
      movieQuestions.processAnswer("a");// wrong answer to standard
      
      assertTrue(equalAnswers(choices, movieQuestions.getCurrentQuestionChoices()));
      movieQuestions.processAnswer("a");// wrong answer to 1st easy, 1st attempt
      
      assertTrue(equalAnswers(choices, movieQuestions.getCurrentQuestionChoices()));
      movieQuestions.processAnswer("d"); // right answer to 1st easy, 2nd
      
      assertTrue(equalAnswers(choices, movieQuestions.getCurrentQuestionChoices()));
      movieQuestions.processAnswer("c"); // right answer to 2nd easy
      
      assertTrue(equalAnswers(choices, movieQuestions.getCurrentQuestionChoices()));
      movieQuestions.processAnswer("b"); // right answer to 3rd easy
      
      assertTrue(equalAnswers(choices, movieQuestions.getCurrentQuestionChoices()));
      movieQuestions.processAnswer("c"); // right answer to 2nd standard
      
      assertTrue(equalAnswers(choices, movieQuestions.getCurrentQuestionChoices()));
      movieQuestions.processAnswer("b"); // right answer to 3rd standard
      
      assertTrue(equalAnswers(choices, movieQuestions.getCurrentQuestionChoices()));
      // PROBLEM LIES HERE
      movieQuestions.processAnswer("d"); // right answer to 1st hard
      
      assertTrue(equalAnswers(choices, movieQuestions.getCurrentQuestionChoices()));
      
      assertTrue(equalAnswers(choices, movieQuestions.getCurrentQuestionChoices()));
      movieQuestions.processAnswer("c"); // right answer to 2nd hard
      
      assertTrue(equalAnswers(choices, movieQuestions.getCurrentQuestionChoices()));
      movieQuestions.processAnswer("b"); // right answer to 3rd hard
   }
   
   /**
    * @throws Tests the processAnswer method
    */
   @Test
   public void testProcessAnswer() throws Exception {
      
      String[] lowerAnswer = new String[10];
      String[] upperAnswer = new String[10];
      
      // test all questions with lowercase
      lowerAnswer[0] = movieQuestions.processAnswer("a"); // wrong answer to standard
      lowerAnswer[1] = movieQuestions.processAnswer("a"); // wrong answer to 1st easy, 1st attempt
      lowerAnswer[2] = movieQuestions.processAnswer("d"); // right answer to 1st easy, 2nd attempt
      lowerAnswer[3] = movieQuestions.processAnswer("c"); // right answer to 2nd easy
      lowerAnswer[4] = movieQuestions.processAnswer("b"); // right answer to 3rd easy
      lowerAnswer[5] = movieQuestions.processAnswer("c"); // right answer to 2nd standard
      lowerAnswer[6] = movieQuestions.processAnswer("b"); // right answer to 3rd standard
      lowerAnswer[7] = movieQuestions.processAnswer("d"); // right answer to 1st hard
      lowerAnswer[8] = movieQuestions.processAnswer("c"); // right answer to 2nd hard
      lowerAnswer[9] = movieQuestions.processAnswer("b"); // right answer to 3rd hard
      
      setUp(); // reset
      
      // test all questions with uppercase
      upperAnswer[0] = movieQuestions.processAnswer("a"); // wrong answer to standard
      upperAnswer[1] = movieQuestions.processAnswer("a"); // wrong answer to 1st easy, 1st attempt
      upperAnswer[2] = movieQuestions.processAnswer("d"); // right answer to 1st easy, 2nd attempt
      upperAnswer[3] = movieQuestions.processAnswer("c"); // right answer to 2nd easy
      upperAnswer[4] = movieQuestions.processAnswer("b"); // right answer to 3rd easy
      upperAnswer[5] = movieQuestions.processAnswer("c"); // right answer to 2nd standard
      upperAnswer[6] = movieQuestions.processAnswer("b"); // right answer to 3rd standard
      upperAnswer[7] = movieQuestions.processAnswer("d"); // right answer to 1st hard
      upperAnswer[8] = movieQuestions.processAnswer("c"); // right answer to 2nd hard
      upperAnswer[9] = movieQuestions.processAnswer("b"); // right answer to 3rd hard
      
      assertTrue(equalAnswers(lowerAnswer, upperAnswer));
   }
   
   /**
    * Tests the getNumCorrectAnswers exception
    */
   @Test
   public void testGetNumCorrectAnswers() throws Exception {
      movieQuestions.processAnswer("a");// wrong answer to standard
      
      movieQuestions.processAnswer("a");// wrong answer to 1st easy, 1st attempt
      
      movieQuestions.processAnswer("d"); // right answer to 1st easy, 2nd
                                         // attempt
      
      movieQuestions.processAnswer("c"); // right answer to 2nd easy
      
      movieQuestions.processAnswer("b"); // right answer to 3rd easy
      
      movieQuestions.processAnswer("c"); // right answer to 2nd standard
      
      movieQuestions.processAnswer("b"); // right answer to 3rd standard
      
      movieQuestions.processAnswer("d"); // right answer to 1st hard
      
      movieQuestions.processAnswer("c"); // right answer to 2nd hard
      
      movieQuestions.processAnswer("b"); // right answer to 3rd hard
      
      assertEquals(8, movieQuestions.getNumCorrectAnswers());
   }
   
   /**
    * @throws Tests the getNumAttemptedQuestions method
    */
   @Test
   public void testGetNumAttemptedQuestions() throws Exception {
      movieQuestions.processAnswer("a");// wrong answer to standard
      
      movieQuestions.processAnswer("a");// wrong answer to 1st easy, 1st attempt
      
      movieQuestions.processAnswer("d"); // right answer to 1st easy, 2nd
      
      movieQuestions.processAnswer("c"); // right answer to 2nd easy
      
      movieQuestions.processAnswer("b"); // right answer to 3rd easy
      
      movieQuestions.processAnswer("c"); // right answer to 2nd standard
      
      movieQuestions.processAnswer("b"); // right answer to 3rd standard
      
      movieQuestions.processAnswer("d"); // right answer to 1st hard
      
      movieQuestions.processAnswer("c"); // right answer to 2nd hard
      
      movieQuestions.processAnswer("b"); // right answer to 3rd hard
      
      assertEquals(10, movieQuestions.getNumAttemptedQuestions());
   }
   
   private ElementaryQuestion makeEasyQuestion(String question, String choiceA, String choiceB,
                                               String choiceC, String choiceD, String answer,
                                               String hint) {
      ElementaryQuestion q = new ElementaryQuestion();
      q.setQuestion(question);
      q.setChoiceA(choiceA);
      q.setChoiceB(choiceB);
      q.setChoiceC(choiceC);
      q.setChoiceD(choiceD);
      q.setAnswer(answer);
      q.setHint(hint);
      return q;
   }
   
   private StandardQuestion makeQuestion(String question, String choiceA, String choiceB,
                                         String choiceC, String choiceD, String answer) {
      StandardQuestion q = new StandardQuestion();
      q.setQuestion(question);
      q.setChoiceA(choiceA);
      q.setChoiceB(choiceB);
      q.setChoiceC(choiceC);
      q.setChoiceD(choiceD);
      q.setAnswer(answer);
      return q;
   }
   
   private AdvancedQuestion makeHardQuestion(String question, String choiceA, String choiceB,
                                             String choiceC, String choiceD, String answer,
                                             String comment) {
      AdvancedQuestion q = new AdvancedQuestion();
      q.setQuestion(question);
      q.setChoiceA(choiceA);
      q.setChoiceB(choiceB);
      q.setChoiceC(choiceC);
      q.setChoiceD(choiceD);
      q.setAnswer(answer);
      q.setComment(comment);
      return q;
   }
   
   private boolean equalAnswers(String[] lowerAnswer, String[] upperAnswer) {
      for (int i = 0; i < upperAnswer.length; i++) {
         if (!(lowerAnswer[i].equals(upperAnswer[i]))) {
            return false;
         }
      }
      return true;
   }
   
}
