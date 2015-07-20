package edu.ncsu.csc216.movie101.quiz;

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
      
      MovieQuiz quiz = new MovieQuiz(testFile);
      
      assertTrue(quiz.hasMoreQuestions());
      
      for (int i = 0; i < expectedQuestions; i++) {
         assertTrue(quiz.hasMoreQuestions());
         quiz.processAnswer("1");
      }
      
      assertFalse(quiz.hasMoreQuestions());
   }
   
   @Test
   public void testGetCurrentQuestionText() throws Exception {
      MovieQuiz quiz = new MovieQuiz(testFile);
      
      assertTrue(quiz.getCurrentQuestionText().equals("1 (easy)"));
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
