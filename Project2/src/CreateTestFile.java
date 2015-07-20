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
      r.makeElementaryQuestion("Who directed the Transformers Movies?", "Michael Bay",
                               "M Night Shamalan", "Quentin Tarantino", "Anon", "A",
                               "The first one");
      r.makeStandardQuestion("Who is directing the new Star Wars", "Michael Bay", "Clint Eastwood",
                             "JJ Abrams", "Quentin Tarantino", "C");
      r.makeAdvancedQuestion("Who directed the Shining?", "Stanley Kubrick", "Steven Spielberg",
                             "Michael Bay", "Clint Eastwood", "B", "Good job!");
      
      try {
         r.marshal();
      } catch (QuestionException e) {
         e.printStackTrace();
      }
   }
   
}
