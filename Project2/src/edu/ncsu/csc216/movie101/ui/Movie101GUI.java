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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import edu.ncsu.csc216.movie101.quiz.MovieQuiz;
import edu.ncsu.csc216.movie101.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.QuestionException;

/**
 * The GUI for the movie tutor program. Handles all user interaction, including loading the
 * appropriate question file, the question interface, and printing the user's results.
 * 
 * @author dcbrewer
 */
public class Movie101GUI extends JFrame implements ActionListener {
   
   /**
    * The title of the JFrame containing the app
    */
   private static final String TITLE = "Movie 101";
   
   /**
    * The quiz'z frame
    */
   JFrame frame;
   
   /**
    * The container containing all of the apps primary components
    */
   Container masterPane;
   
   /**
    * Quiz object field
    */
   private MovieQuiz quiz;
   
   /**
    * whether or not the user gets a second try
    */
   boolean secondTry;
   
   /**
    * The width of the app window
    */
   private static final int WIDTH = 600; // 500
   /**
    * The height of the app window
    */
   private static final int HEIGHT = 250; // 250
   
   /**
    * String array holding the choices for each answer choice
    */
   private String[] choices;
   
   /**
    * String containing the response after a user submits a question, e.g., "Great job!"
    */
   private String responseString;
   
   /**
    * The label containing the question text
    */
   
   JLabel questionLabel;
   
   /**
    * The list of radio buttons corresponding to each answer choice
    */
   ArrayList<JRadioButton> answerButtons = new ArrayList<JRadioButton>();
   
   /**
    * The group containing the answer radio buttons
    */
   ButtonGroup answerButtonGroup;
   
   /**
    * The label used for by the action listeners for the radiop buttons
    */
   private static final String CHOICE = "choice"; // for the radio button's action command
   
   /**
    * The string containing an empty response to reset back to after each question.
    */
   private static final String EMPTY_RESPONSE = "";
   
   /**
    * The label containing the question response text, e.g. "Great job!"
    */
   JLabel responseLabel;
   
   /**
    * The text for the submit answer button
    */
   private static final String SUBMIT_ANSWER_TEXT = "Submit Answer";
   
   /**
    * The submitAnswer JButton
    */
   JButton submitAnswer;
   
   /**
    * The text for the nextQuestion button
    */
   private static final String NEXT_QUESTION_TEXT = "Next Question";
   
   /**
    * The nextQuestion JButton
    */
   JButton nextQuestion;
   
   /**
    * The text for the quit button
    */
   private static final String QUIT_TEXT = "QUIT";
   
   /**
    * The quit JButton
    */
   JButton quit;
   
   /**
    * The font for question and response text
    */
   private static final Font FONT = new Font("arial", Font.PLAIN, 15);
   
   /**
    * Initializes program and loads the XML file based on command line arguments, or loads the file
    * based on the GUI.
    * 
    * @param args The filename of the question fole
    */
   public static void main(String[] args) {
      // select file
      
      String fileName = "";
      
      if (args.length == 1) {
         fileName = args[0];
      } else {
         // inspired by Project1's WolfWareGUI filechooser
         JFileChooser fc = new JFileChooser();
         fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
         int returnVal = fc.showOpenDialog(null);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileName = fc.getSelectedFile().getName();
         }
      }
      
      Movie101GUI gui = new Movie101GUI(fileName);
   }
   
   /**
    * THe main GUI file. Handles the creation of all app components.
    * 
    * @param file The XML file containing the questions
    */
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
         answerButtonGroup = new ButtonGroup(); // create parallel button group
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
   
   /**
    * Opens a quit message dialog box with information about how the user did.
    */
   private void quitMessage() {
      frame.setVisible(false);
      JOptionPane.showMessageDialog(null,
                                    String.format("You answer %d questions correctly out of %d attempts",
                                                  quiz.getNumCorrectQuestions(),
                                                  quiz.getNumAttemptedQuestions()));
   }
   
   /**
    * Handles all event processing for the app's buttons
    */
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
               
               // enable and deselect all radio buttons
               for (JRadioButton jRadioButton : answerButtons) {
                  jRadioButton.setEnabled(true);
               }
               
               // disable next question button
               nextQuestion.setEnabled(false);
               
               answerButtonGroup.clearSelection();
               
               refresh();
               break;
            case QUIT_TEXT:
               // quit button
               quitMessage();
               System.exit(0);
               break;
            
            case SUBMIT_ANSWER_TEXT:
               // submit answer
               
               // determine radio button
               int index = -1;
               for (int i = 0; i < answerButtons.size(); i++) {
                  if (answerButtons.get(i).isSelected()) {
                     index = i;
                     break;
                  }
               }
               String preSubmission = quiz.getCurrentQuestionText();
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
               
               if (quiz.hasMoreQuestions()) {
                  secondTry = preSubmission.equals(quiz.getCurrentQuestionText());
               } else {
                  secondTry = false;
               }
               
               // end determination of radio button, and end of nested switch
               if (secondTry) {
                  answerButtonGroup.getSelection().setEnabled(false);
                  answerButtonGroup.clearSelection();
                  submitAnswer.setEnabled(false);
               } else {
                  if (quiz.hasMoreQuestions()) {
                     // handle button enabling/disabling
                     // disable selected radio button
                     answerButtonGroup.getSelection().setEnabled(false);
                     // disable submit answer
                     submitAnswer.setEnabled(false);
                     // disable radio buttons
                     for (JRadioButton jRadioButton : answerButtons) {
                        jRadioButton.setEnabled(false);
                        // disable submit answer
                        submitAnswer.setEnabled(false);
                        nextQuestion.setEnabled(true);
                     }
                  } else {
                     submitAnswer.setEnabled(false);
                     nextQuestion.setEnabled(false);
                  }
               }
               break;
            default:
               break;
         }
         
      } catch (EmptyQuestionListException eqle) {
         eqle.printStackTrace();
      }
   }
   
   /**
    * Updates labels and radio buttons
    */
   private void refresh() {
      try {
         // set the question label
         questionLabel.setText(quiz.getCurrentQuestionText());
         
         // update radio buttons
         choices = quiz.getCurrentQuestionChoices();
         for (int i = 0; i < answerButtons.size(); i++) {
            answerButtons.get(i).setText(choices[i]);
            
         }
         
         responseLabel.setText("");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   /**
    * Sets the response label to have the appropriate response text
    */
   private void printResult() {
      try {
         responseLabel.setText(responseString);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
