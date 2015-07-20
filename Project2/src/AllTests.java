import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.ncsu.csc216.movie101.question.MovieQuestionsTest;
import edu.ncsu.csc216.movie101.quiz.MovieQuizTest;

@RunWith(Suite.class)
@SuiteClasses({ MovieQuestionsTest.class, MovieQuizTest.class })
public class AllTests {
   
}
