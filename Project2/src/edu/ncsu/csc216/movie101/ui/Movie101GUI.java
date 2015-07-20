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
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Movie101GUI extends JFrame implements ActionListener {
   
   public static final String TITLE = "Movie 101";
   
   // window parameters
   public static final int WIDTH = 600; // 500
   public static final int HEIGHT = 250; // 250
   
   // mock inputs
   public String question = "Which of the following was not directed by the Coen brothers?";
   public String[] answers = { "Blood SImple", "Star Wars", "No Country for Old Men",
                              "Burn After Reading" };
   
   // GUI groups
   JLabel questionLabel;
   ArrayList<JRadioButton> answerButtons = new ArrayList<JRadioButton>();
   
   // answer group
   public static final String EMPTY_RESPONSE = "Correct!"; // used for testing
   JLabel response;
   
   // Question controls group
   JButton submitAnswer;
   public static final String SUBMIT_ANSWER_TEXT = "Submit Answer";
   JButton nextQuestion;
   public static final String NEXT_QUESTION_TEXT = "Next Question";
   JButton quit;
   public static final String QUIT_TEXT = "QUIT";
   
   // text font
   public static final Font FONT = new Font("arial", Font.PLAIN, 15);
   
   public static void main(String[] args) {
      Movie101GUI gui = new Movie101GUI();
   }
   
   public Movie101GUI() {
      // create the question box and set the title, add basic buttons
      JFrame frame = new JFrame(TITLE);
      frame.setSize(WIDTH, HEIGHT);
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      Container masterPane = frame.getContentPane();
      
      frame.setResizable(false);
      
      questionLabel = new JLabel(question); // initialize question label
      questionLabel.setFont(FONT);
      JLabel response = new JLabel(EMPTY_RESPONSE);
      response.setFont(FONT);
      
      // initialize radio buttons
      answerButtons.clear();
      for (String string : answers) {
         answerButtons.add(new JRadioButton(string));
      }
      
      submitAnswer = new JButton(SUBMIT_ANSWER_TEXT);
      
      nextQuestion = new JButton(NEXT_QUESTION_TEXT);
      
      quit = new JButton(QUIT_TEXT);
      
      // add to containers
      masterPane.add(questionLabel); // add question label
      
      JPanel answerGroup = new JPanel();
      ButtonGroup answerButtonGroup = new ButtonGroup(); // create parralel button group
      answerGroup.setLayout(new BoxLayout(answerGroup, BoxLayout.Y_AXIS));
      // add to answerGroup
      for (JRadioButton jRadioButton : answerButtons) {
         answerGroup.add(jRadioButton);
         answerButtonGroup.add(jRadioButton);
      }
      answerGroup.setFont(FONT);
      
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
                                      .addComponent(answerGroup).addComponent(response)
                                      .addComponent(controlGroup));
      layout.setVerticalGroup(layout.createSequentialGroup().addComponent(questionLabel)
                                    .addComponent(answerGroup).addComponent(response)
                                    .addComponent(controlGroup));
      masterPane.setLayout(layout);
      frame.setVisible(true);
      
      // while there are questions left:
      
      // set questionBox to invisible
      
      // set the questionBox to have the correct facts and answer, grey out "next question",
      // "submit answer"
      
      // set questionBox to visible
      
      // upon first choice selection, activate "submit answer"
      
      // if "submit answer", then print processed message, grey out options and submit answer
      
      // if "next question", go to next question, enable all options, disable "submit answer",
      // disable "next question
      
      // if QUIT, print exit message, close program
      
   }
   
   private void questionBox() {
      
   }
   
   private void quitMessage() {
      
   }
   
   @Override
   public void actionPerformed(ActionEvent arg0) {
      
   }
   
}
