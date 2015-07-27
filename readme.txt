Name 1:Daniel Brewer
ID 1  :200070876
Name 2:Kaleb Wells
ID 2  :200002814

How to run the software:
Open and the Movie101GUI file in the UI package. Then, load the test file "tests.xml" through a command line argument, or via the popup file chooser. Afterwards, you will be able to take the quiz by selecting your answer, and submitting it, then reviewing the solution and clicking "next question" to move on. You may click the quit button at any time to stop taking the quiz. 

BLACKBOX TEST:
Right click "BBTP_dcbrewer_khwells", and open the file with your favorite web browser. This was done because the document contains tables which were too wide for a word document. 


AllTests--------------------------------------------------------------------------
==================================================================================
Simple class to run all of the project's test classes.
==================================================================================

Movie101GUI-----------------------------------------------------------------------
==================================================================================
Complete user interface for the movie tutor program. Consists of 3 interfaces:

File Selection Interface:
If no command line argument is passed into the program, 
it will bring up a file selection menu to allow the user to choose a question file to load from. 

Quiz Interface:
Allows the user to submit answers to questions, view feedback and receive hints based off of the project's specifications. Adjusts the difficulty of questions based off of the question file and the user's performance. 

Performance Report interface:
Simple diaglog box that is shown when the user clicks the "quit" button from the quiz interface that shows the user how many questions they got correct out of their total number of attempts. 
==================================================================================

CreateTestFile--------------------------------------------------------------------
==================================================================================
Responsible for creating the "test.xml" file used for testing. 
Run this class if the XML test file is lost.
==================================================================================

test.xml--------------------------------------------------------------------------
==================================================================================
File containing the following questions:

Note: Answers to all questions go from 1-4

Question List:
Easy Question (Pick A)
Easy Question (Pick B)
Easy Question (Pick C)
Standard Question (Pick A)
Standard Question (Pick B)
Standard Question (Pick C)
Hard Question (Pick A)
Hard Question (Pick B)
Hard Question (Pick C)
==================================================================================

MovieQuestions--------------------------------------------------------------------
==================================================================================
MovieQuestions maintains the questions and determines the order in which they are to be presented
based on the user's answers.
==================================================================================

MovieQuizTest---------------------------------------------------------------------
==================================================================================
Responsible for testing all methods of the MovieQuiz class
==================================================================================

MovieQuestionsTest----------------------------------------------------------------
==================================================================================
Responsible for testing all methods of the MovieQuestions class
==================================================================================

QuestionState---------------------------------------------------------------------
==================================================================================
QuestionState is an abstract class that represents state in the FSM. QuestionState is extended by
private inner classes nested inside MovieQuestions, specifically StandardQuestionState,
ElementaryQuestionState, and AdvancedQuestionState.
==================================================================================

MovieQuiz-------------------------------------------------------------------------
==================================================================================
MovieQuiz implements the interface QuizMaster. This
class has methods to get the current question text and
choices, processes the answer, tracks the number of
correct and attempted questions, and asserts whether
or not the quiz has more questions.
==================================================================================

QuizMaster (interface by Jo Perry)------------------------------------------------
==================================================================================
QuizMaster specifies behaviors required for getting questions
and their possible answers, processing the user's answers, and
keeping track of the number of questions attempted and number
answered correctly.
==================================================================================

EmptyQuestionListException--------------------------------------------------------
==================================================================================
Specifies behaviors required for throwing
the exception if there is no question of the appropriate type to
present. This exception, if thrown, tells the program to run the
quits execution.
==================================================================================
