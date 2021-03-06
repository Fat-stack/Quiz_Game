package app_game;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class QuestionHandler implements Serializable {

    // ------- PATHS -------
    File questions_SerFilePath = new File("BattleQuiz/game_files/QuestionBank.ser");
    String questions_TextFilePath ="BattleQuiz/game_files/QuestionBank_v2.txt";

    // ------- VARIABLES -------
    String question;
    List<String> options;

    // ------- CONSTRUCTORS -------
    public  QuestionHandler(){}

    public QuestionHandler(String question, List<String> options){
        this.question = question;
        this.options =  options;
    }

    // ------- METHODS -------

    public void resetQuestionListFromTextFile() throws IOException {
        LinkedList<String> linesFromQuestionBank = new LinkedList<>();
        LinkedList<QuestionHandler> questionList = new LinkedList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(questions_TextFilePath));
        bufferedReader.lines().forEach(linesFromQuestionBank::addLast);
        //bufferedReader.lines().forEach(line -> linesFromQuestionBank.addLast(line));

        for(String line : linesFromQuestionBank){
            String question = (line.substring(0, line.indexOf(":")));
            List <String> options = Arrays.asList(line.substring(line.indexOf(":")).split(","));
            options.set(0, options.get(0).replaceFirst(":",""));
            questionList.add(new QuestionHandler(question, options));
        }
        writeQuestionListToSer(questionList);
        System.out.println("Successfully restored questionbank from txt-file.");
    }

    public void writeQuestionListToSer(LinkedList<QuestionHandler> toStore) throws IOException {

        FileOutputStream fileOutput = new FileOutputStream(questions_SerFilePath);
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

        objectOutput.writeObject(toStore);
        objectOutput.flush();
        objectOutput.close();

    }

    public LinkedList<QuestionHandler> readQuestionListFromSer() throws IOException, ClassNotFoundException {

        FileInputStream fileInput = new FileInputStream(questions_SerFilePath);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);


        return (LinkedList<QuestionHandler>) objectInput.readObject();

    }

    public String validateOption(String strOption){
        System.out.println("Is this the correct option? - Answer with 'y' or 'n' ( YES or NO)");
        Scanner scValidate = new Scanner(System.in);

        String validationAnswer = scValidate.next().toLowerCase();

        if(validationAnswer.equals("y") ){
            return "*"+strOption ;
        }else if(validationAnswer.equals("n")){
            return strOption;
        }else{return "Wrong validation input";
        }

    }

    public void addQuestion() throws IOException, ClassNotFoundException {
        LinkedList<QuestionHandler> tempLinkedList = readQuestionListFromSer();

        LinkedList<String> availableOptions = new LinkedList<>();

        Scanner scQuestionInformation = new Scanner(System.in);

        System.out.println("Enter new Question:");
        String questionText = scQuestionInformation.nextLine();
        System.out.println("Enter answer A:");
        String option1 = validateOption(scQuestionInformation.nextLine().toLowerCase().trim());
        System.out.println("Enter answer B:");
        String option2 = validateOption(scQuestionInformation.nextLine().toLowerCase().trim());
        System.out.println("Enter answer C:");
        String option3 = validateOption(scQuestionInformation.nextLine().toLowerCase().trim());
        System.out.println("Enter answer D:");
        String option4 = validateOption(scQuestionInformation.nextLine().toLowerCase().trim());

        // adding answers to list
        availableOptions.add(option1);
        availableOptions.add(option2);
        availableOptions.add(option3);
        availableOptions.add(option4);

        // Add the new question to templinkedlist
        tempLinkedList.add(new QuestionHandler(questionText, availableOptions));

        //Write the modified list back to QuestionBank.ser.
        writeQuestionListToSer(tempLinkedList);

        System.out.println("Question added: " + tempLinkedList.getLast().question + tempLinkedList.getLast().options);
    }

    public void showAllQuestions() throws IOException, ClassNotFoundException {
        LinkedList<QuestionHandler> tempListReadAll = readQuestionListFromSer();
        for (int i = 0; i < tempListReadAll.size(); i++) {
            System.out.println(i +1 + " " +tempListReadAll.get(i).question + " - " + tempListReadAll.get(i).options);
        }
    }

    public void removeQuestion() throws IOException,ClassNotFoundException {
        Scanner scRemove = new Scanner(System.in);
        LinkedList<QuestionHandler> tempList = readQuestionListFromSer();

        System.out.println("Which question do you want to remove? (number from list of questions)");

        try {
            int NumberToRemove = scRemove.nextInt() - 1;
            tempList.remove(NumberToRemove);

            writeQuestionListToSer(tempList);
            System.out.println("Successful with removal of question number  " + (NumberToRemove + 1));
        }catch(Exception e){
            System.out.println("Enter a valid number..\n");
            removeQuestion();
        }

    }

    public void editQuestion() throws IOException, ClassNotFoundException {
        Scanner scEdit = new Scanner(System.in);

        LinkedList<QuestionHandler> tempList = readQuestionListFromSer();
        System.out.println("What question do you want to change (number)?");

        try {
            int numberToEdit = scEdit.nextInt() - 1;
            System.out.println("You are about to edit question nr " + (numberToEdit + 1) + ": " +
                    tempList.get(numberToEdit).question + "? " + tempList.get(numberToEdit).options);


            LinkedList<String> answerList = new LinkedList<>();
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter question:");
            String q = sc.nextLine();
            System.out.println("Enter answer A");
            String answer1 = validateOption(sc.nextLine().toLowerCase().trim());
            System.out.println("Enter answer B");
            String answer2 = validateOption(sc.nextLine().toLowerCase().trim());
            System.out.println("Enter answer C");
            String answer3 = validateOption(sc.nextLine().toLowerCase().trim());
            System.out.println("Enter answer D");
            String answer4 = validateOption(sc.nextLine().toLowerCase().trim());


            answerList.add(answer1);
            answerList.add(answer2);
            answerList.add(answer3);
            answerList.add(answer4);

            //4 Remove previous question at this index.
            tempList.remove(numberToEdit);

            // 5 Add the edited(new) question in the same index-position as the deleted one(old one).
            tempList.add(numberToEdit, new QuestionHandler(q, answerList));

            // 6 Write modified list to ser-file
            System.out.println("Question number " + (numberToEdit + 1) + " is edited to :" + tempList.get(numberToEdit).question + "? " + tempList.get(numberToEdit).options + ".");
            writeQuestionListToSer(tempList);
        }catch (Exception e){
            System.out.println("Enter a valid question number\n");
            editQuestion();
        }
    }

}

















