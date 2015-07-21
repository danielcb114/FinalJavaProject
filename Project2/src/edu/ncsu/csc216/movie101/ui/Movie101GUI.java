package edu.ncsu.csc216.movie101.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import edu.ncsu.csc216.movie101.quiz.MovieQuiz;
import edu.ncsu.csc216.movie101.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.QuestionException;

public class Movie101GUI extends JFrame implements ActionListener {
   
   private static final String TITLE = "Movie 101";
   
   // Quiz object
   private MovieQuiz quiz;
   
   // window parameters
   private static final int WIDTH = 600; // 500
   private static final int HEIGHT = 250; // 250
   
   // File
   private static final String file = "test.xml";
   
   // inputs
   private String[] choices;
   private String responseString;
   
   // GUI groups
   JLabel questionLabel;
   ArrayList<JRadioButton> answerButtons = new ArrayList<JRadioButton>();
   
   // answer group
   private static final String EMPTY_RESPONSE = ""; // used for testing
   JLabel responseLabel;
   
   private static final String SUBMIT_ANSWER_TEXT = "Submit Answer";
   // Question controls group
   JButton submitAnswer;
   JButton nextQuestion;
   JButton quit;
   
   private static final String NEXT_QUESTION_TEXT = "Next Question";
   private static final String QUIT_TEXT = "QUIT";
   private static final String CHOICE = "choice"; // for the radio button's action command
   
   private Container masterPane;
   
   JFrame frame;
   
   // text font
   private static final Font FONT = new Font("arial", Font.PLAIN, 15);
   
   public static void main(String[] args) {
      // select file
      
      Movie101GUI gui = new Movie101GUI(file);
   }
   
   public Movie101GUI(String file) {
      // instantiate fields
      try {
         quiz = new MovieQuiz(file);
         
         choices = quiz.getCurrentQuestionChoices();
         
         // create the question box and set the title, add basic buttons
         frame = new JFrame(TITLE);
         frame.setSize(WIDTH, HEIGHT);
         frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
         masterPane = frame.getContentPane();
         frame.setResizable(false);
         
         // instantiate question label
         questionLabel = new JLabel(quiz.getCurrentQuestionText());
         questionLabel.setFont(FONT);
         
         // instantiate response label
         responseString = EMPTY_RESPONSE;
         responseLabel = new JLabel(responseString);
         responseLabel.setFont(FONT);
         
         JPanel answerGroup = new JPanel();
         ButtonGroup answerButtonGroup = new ButtonGroup(); // create parallel button group
         answerGroup.setLayout(new BoxLayout(answerGroup, BoxLayout.Y_AXIS));
         // add to answerGroup
         for (JRadioButton jRadioButton : answerButtons) {
            answerGroup.add(jRadioButton);
            answerButtonGroup.add(jRadioButton);
         }
         answerGroup.setFont(FONT);
         
         // instantiate radio buttons
         answerButtons.clear();
         for (String string : choices) {
            JRadioButton button = new JRadioButton(string);
            button.setActionCommand(CHOICE);
            answerButtons.add(button);
            answerGroup.add(button);
            answerButtonGroup.add(button);
            button.addActionListener(this);
         }
         
         // instantiate buttons
         submitAnswer = new JButton(SUBMIT_ANSWER_TEXT);
         submitAnswer.setActionCommand(SUBMIT_ANSWER_TEXT);
         submitAnswer.addActionListener(this);
         
         nextQuestion = new JButton(NEXT_QUESTION_TEXT);
         nextQuestion.setActionCommand(NEXT_QUESTION_TEXT);
         nextQuestion.addActionListener(this);
         
         quit = new JButton(QUIT_TEXT);
         quit.setActionCommand(QUIT_TEXT);
         quit.addActionListener(this);
         
         // add to containers
         masterPane.add(questionLabel); // add question label
         
         JPanel controlGroup = new JPanel();
         controlGroup.setLayout(new FlowLayout());
         // add to buttonGroup
         controlGroup.add(submitAnswer);
         controlGroup.add(nextQuestion);
         controlGroup.add(quit);
         
         // add groups to master pane
         masterPane.add(answerGroup);
         masterPane.add(controlGroup);
         
         // adjust layout
         
         GroupLayout layout = new GroupLayout(masterPane);
         
         layout.setAutoCreateGaps(true);
         layout.setAutoCreateContainerGaps(true);
         
         layout.setHorizontalGroup(layout.createParallelGroup().addComponent(questionLabel)
                                         .addComponent(answerGroup).addComponent(responseLabel)
                                         .addComponent(controlGroup));
         layout.setVerticalGroup(layout.createSequentialGroup().addComponent(questionLabel)
                                       .addComponent(answerGroup).addComponent(responseLabel)
                                       .addComponent(controlGroup));
         masterPane.setLayout(layout);
         
         // disable appropriate buttons
         nextQuestion.setEnabled(false);
         submitAnswer.setEnabled(false);
         
         frame.setVisible(true);
         
      } catch (QuestionException pst) {
         pst.printStackTrace();
      } catch (EmptyQuestionListException eqle) {
         eqle.printStackTrace();
      }
      
   }
   
   private void quitMessage() {
      frame.setVisible(false);
      JOptionPane.showMessageDialog(null,
                                    String.format("You answer %d questions correctly out of %d attempts",
                                                  0, 0));
   }
   
   @Override
   public void actionPerformed(ActionEvent ae) {
      try {
         
         String s = ae.getActionCommand();
         switch (s) {
            case CHOICE:
               // a radio button is now selected, so enable submit answer
               submitAnswer.setEnabled(true);
               refresh();
               break;
            case NEXT_QUESTION_TEXT:
               // next button
               refresh();
               break;
            case QUIT_TEXT:
               // quit button
               quitMessage();
               System.exit(0);
               break;
            
            case SUBMIT_ANSWER_TEXT:
               // submit answer
               submitAnswer.setEnabled(false);
               System.out.println("Submit");
               System.out.println(quiz.getCurrentQuestionText());
               nextQuestion.setEnabled(true);
               
               // determine radio button
               int index = -1;
               for (int i = 0; i < answerButtons.size(); i++) {
                  if (answerButtons.get(i).isSelected()) {
                     index = i;
                     break;
                  }
               }
               
               switch (index) {
                  case 0:
                     // option 1
                     responseString = quiz.processAnswer("a");
                     printResult();
                     break;
                  case 1:
                     // option 2
                     responseString = quiz.processAnswer("b");
                     printResult();
                     break;
                  case 2:
                     // option 3
                     responseString = quiz.processAnswer("c");
                     printResult();
                     break;
                  case 3:
                     // option 4
                     responseString = quiz.processAnswer("d");
                     printResult();
                     break;
                  default:
                     throw new IllegalStateException("No answer selected");
               }
               // end determination of radio button, and end of nested switch
               break;
            default:
               break;
         }
         
      } catch (EmptyQuestionListException eqle) {
         eqle.printStackTrace();
      }
   }
   
   private void refresh() {
      try {
         questionLabel.setText(quiz.getCurrentQuestionText());
         choices = quiz.getCurrentQuestionChoices();
         
         for (int i = 0; i < answerButtons.size(); i++) {
            answerButtons.get(i).setText(choices[i]);
         }
         
         responseLabel.setText("");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   private void printResult() {
      try {
         System.out.println(responseString);
         responseLabel.setText(responseString);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
