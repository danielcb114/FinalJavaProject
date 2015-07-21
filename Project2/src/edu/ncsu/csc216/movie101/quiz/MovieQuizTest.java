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
      
      System.out.printf("\nBeginning of testHasMoreQuestions console:\n");
      
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
      MovieQuiz quiz = new MovieQuiz(testFile);
      
      String[] choices = quiz.getCurrentQuestionChoices();
      
      for (int i = 0; i < 4; i++) {
         assertEquals(choices[i], Integer.toString(i + 1));
      }
   }
   
   @Test
   public void testProcessAnswer() throws Exception {
      MovieQuiz quiz = new MovieQuiz(testFile);
      
      // answer standard question incorrectly, expected Incorrect!, expected StandardQuestionA
      assertEquals(quiz.getCurrentQuestionText(), "Standard Question (pick A)");
      assertEquals(quiz.processAnswer("b"), "Incorrect!");
      // answer easy question incorrectly, expected Incorrect! and hint, expected EasyQuestionA
      assertEquals(quiz.getCurrentQuestionText(), "Easy Question (pick A)");
      // System.out.println(quiz.processAnswer("c"));
      assertEquals(quiz.processAnswer("c"), "Incorrect! Hint: The first one");
      // answer easy question incorrectly again, expected Incorrect!, expected EasyQuestionA
      assertEquals(quiz.getCurrentQuestionText(), "Easy Question (pick A)");
      assertEquals(quiz.processAnswer("d"), "Incorrect!");
      // answer easy question correctly, expected Correct!, expected EasyQuestionB
      assertEquals(quiz.getCurrentQuestionText(), "Easy Question (pick B)");
      assertEquals(quiz.processAnswer("b"), "Correct!");
      // answer easy question correctly, expected Correct!, expected EasyQuestionC
      assertEquals(quiz.getCurrentQuestionText(), "Easy Question (pick C)");
      assertEquals(quiz.processAnswer("c"), "Correct!");
      // answer standard question correctly, expected Correct!, expected StandardQuestionB
      // FAIL:StandardQuestionA has already been asked earlier, shouldn't be asked again
      assertEquals(quiz.getCurrentQuestionText(), "Standard Question (pick B)");
      assertEquals(quiz.processAnswer("b"), "Correct!");
      
      MovieQuiz quiz2 = new MovieQuiz(testFile); // test again
      
      // Answer standard question correctly, expected Correct!, expected StandarQuestionA
      assertEquals(quiz2.getCurrentQuestionText(), "Standard Question (pick A)");
      assertEquals(quiz2.processAnswer("a"), "Correct!");
      // Answer standard question correctly, expected Correct!, expected StandardQuestionB
      assertEquals(quiz2.getCurrentQuestionText(), "Standard Question (pick B)");
      assertEquals(quiz2.processAnswer("b"), "Correct!");
      // Answer hard question correctly, expected Congratulations, expected HardQuestionA
      assertEquals(quiz2.getCurrentQuestionText(), "Hard Question (Pick A)");
      assertEquals(quiz2.processAnswer("a"), "Correct! Good job in choosing A!");
      // Answer hard question correctly, expected Congratulations, expected HardQuestionB
      assertEquals(quiz2.getCurrentQuestionText(), "Hard Question (Pick B)");
      assertEquals(quiz2.processAnswer("b"), "Correct! Good job in choosing B!");
      // Answer hard question incorrectly, expected Incorrect!, expected HardQuestionC
      assertEquals(quiz2.getCurrentQuestionText(), "Hard Question (Pick C)");
      assertEquals(quiz2.processAnswer("d"), "Incorrect!");
   }
   
   @Test
   public void testGetNumCorrectQuestions() throws Exception {
      MovieQuiz quiz = new MovieQuiz(testFile);
      
      quiz.processAnswer("a");// right
      quiz.processAnswer("d");// wrong
      quiz.processAnswer("a");// right
      quiz.processAnswer("b");// right
      
      assertEquals(quiz.getNumCorrectQuestions(), 3);
   }
   
   @Test
   public void testGetNumAttemptedQuestions() throws Exception {
      MovieQuiz quiz = new MovieQuiz(testFile);
      
      quiz.processAnswer("a");// right
      quiz.processAnswer("d");// wrong
      quiz.processAnswer("a");// right
      quiz.processAnswer("b");// right
      
      assertEquals(quiz.getNumAttemptedQuestions(), 3);
   }
   
}
