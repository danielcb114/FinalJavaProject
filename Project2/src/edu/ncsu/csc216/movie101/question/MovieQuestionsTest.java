package edu.ncsu.csc216.movie101.question;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.question_library.AdvancedQuestion;
import edu.ncsu.csc216.question_library.ElementaryQuestion;
import edu.ncsu.csc216.question_library.StandardQuestion;

public class MovieQuestionsTest {
   
   MovieQuestions movieQuestions;
   
   @Before
   public void setUp() {
      List<StandardQuestion> listStandard = new ArrayList<StandardQuestion>();
      List<ElementaryQuestion> listElem = new ArrayList<ElementaryQuestion>();
      List<AdvancedQuestion> listAdv = new ArrayList<AdvancedQuestion>();
      
      MovieQuestions movieQuestions = new MovieQuestions(listStandard, listElem, listAdv);
      
      listStandard.add(new StandardQuestion());
      listElem.add(new ElementaryQuestion());
      listAdv.add(new AdvancedQuestion());
   }
   
   @Test
   public void testMovieQuestions() throws Exception {
      List<StandardQuestion> listStandard = new ArrayList<StandardQuestion>();
      List<ElementaryQuestion> listElem = new ArrayList<ElementaryQuestion>();
      List<AdvancedQuestion> listadv = new ArrayList<AdvancedQuestion>();
      
      MovieQuestions movieQuestionsTest = new MovieQuestions(listStandard, listElem, listadv);
      
      assertTrue(movieQuestionsTest instanceof MovieQuestions);
   }
   
   @Test
   public void testHasMoreQuestions() throws Exception {
      throw new RuntimeException("not yet implemented");
   }
   
   @Test
   public void testGetCurrentQuestionText() throws Exception {
      throw new RuntimeException("not yet implemented");
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
   public void testGetNumCorretAnswers() throws Exception {
      throw new RuntimeException("not yet implemented");
   }
   
   @Test
   public void testGetNumAttemptQuests() throws Exception {
      throw new RuntimeException("not yet implemented");
   }
   
}
