package app_game;

import java.io.IOException;
public class ThreadQuestions extends Thread
{
    // ------- VARIABLES -------
    GameEngine gameEngine = GameEngine.getInstance();


    // ------- CONSTRUCTORS -------
    public ThreadQuestions() throws IOException, ClassNotFoundException {}

    // ------- METHODS -------
    public void run(){
        try {
            gameEngine.randomizeQuestions();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
