package edu.ncsu.csc216.movie101.quiz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.ncsu.csc216.question_library.QuestionException;

public class MovieQuizTest {
   
   public static String testFile = "test.xml";
   
   @Test(expected = QuestionException.class)
   public void testMovieQuizThrowException() throws Exception {
      MovieQuiz quiz = new MovieQuiz("this.txt"); // invalid file type, should be .xml
      MovieQuiz quiz2 = new MovieQuiz("this"); // no extension
      MovieQuiz quiz3 = new MovieQuiz(".xml"); // no file title
      MovieQuiz quiz4 = new MovieQuiz(""); // empty string
   }
   
   @Test(expected = QuestionException.class)
   public void testMovieQuiz() throws Exception {
      MovieQuiz quiz = new MovieQuiz("this.xml"); // valid file
      MovieQuiz quiz2 = new MovieQuiz("A.xml"); // valid file, with capital letters
      MovieQuiz quiz3 = new MovieQuiz("this file.xml"); // valid file, containing spaces
      MovieQuiz quiz4 = new MovieQuiz("123 456.xml"); // valid file, containing spaces and
                                                      // consisting
                                                      // of numbers
   }
   
   @Test
   public void testHasMoreQuestions() throws Exception {
      int expectedQuestions = 3;
      int numEasyQuestions = 3;
      
      MovieQuiz quiz = new MovieQuiz(testFile);
      
      assertTrue(quiz.hasMoreQuestions());
      
      // answer one standard question incorrectly
      System.out.println(quiz.getCurrentQuestionText());
      quiz.processAnswer("d");
      
      // answer each easy question incorrectly twice
      for (int i = 0; i < numEasyQuestions; i++) {
         System.out.println(quiz.getCurrentQuestionText());
         quiz.processAnswer("d");
         System.out.println(quiz.getCurrentQuestionText());
         quiz.processAnswer("d");
      }
      
      assertFalse(quiz.hasMoreQuestions());
   }
   
   @Test
   public void testGetCurrentQuestionText() throws Exception {
      MovieQuiz quiz = new MovieQuiz(testFile);
      
      assertEquals(quiz.getCurrentQuestionText(), "Standard Question (pick A)"); // expected first
                                                                                 // standard
                                                                                 // question
   }
   
   @Test
   public void testGetCurrentQuestionChoices() throws Exception {
      throw new RuntimeException("not yet implemented");
   }
   
   @Test
   public void testProcessAnswer() throws Exception {
      throw new RuntimeException("not yet implemented");
   }
   
   @Test
   public void testGetNumCorrectQuestions() throws Exception {
      throw new RuntimeException("not yet implemented");
   }
   
   @Test
   public void testGetNumAttemptedQuestions() throws Exception {
      throw new RuntimeException("not yet implemented");
   }
   
}
