import edu.ncsu.csc216.question_library.QuestionException;
import edu.ncsu.csc216.question_library.QuestionWriter;

public class CreateTestFile {
   
   public static void main(String[] args) {
      QuestionWriter r = null;
      try {
         r = new QuestionWriter("test.xml");
      } catch (QuestionException e) {
         e.printStackTrace();
      }
      r.makeElementaryQuestion("Easy Question (pick A)", "1",
                               "2", "3", "4", "a",
                               "The first one");
      r.makeElementaryQuestion("Easy Question (pick B)", "1",
                               "2", "3", "4", "b",
                               "The second one");
      r.makeElementaryQuestion("Easy Question (pick C)", "1",
                               "2", "3", "4", "c",
                               "The third one");
      r.makeStandardQuestion("Standard Question (pick A)", "1", "2",
                             "3", "4", "a");
      r.makeStandardQuestion("Standard Question (pick B)", "1", "2",
                             "3", "4", "b");
      r.makeStandardQuestion("Standard Question (pick C)", "1", "2",
                             "3", "4", "c");
      r.makeAdvancedQuestion("Hard Question (Pick A)", "1", "2",
                             "3", "4", "a", "Good job in choosing A!");
      r.makeAdvancedQuestion("Hard Question (Pick B)", "1", "2",
                             "3", "4", "b", "Good job in choosing B!");
      r.makeAdvancedQuestion("Hard Question (Pick C)", "1", "2",
                             "3", "4", "c", "Good job in choosing C!");
      
      try {
         r.marshal();
      } catch (QuestionException e) {
         e.printStackTrace();
      }
   }
   
}
