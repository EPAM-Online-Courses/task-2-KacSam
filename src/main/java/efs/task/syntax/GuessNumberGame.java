package efs.task.syntax;


import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {
    //Do not modify main method
    public int M;
    public int L;

    public int randomNumber;

    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        //TODO: Implement the constructor
        try {
            this.M = Integer.parseInt(argument);

            if (M < 1 || M > 400){
                System.out.println(UsefulConstants.WRONG_ARGUMENT);
                throw new java.lang.IllegalArgumentException("out of range");
            }
        }
        catch (NumberFormatException e){
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException(" - not a number");
        }
        this.L = (int)(Math.log(M)/Math.log(2))+1;
        Random random = new Random();
        this.randomNumber= random.nextInt(M) + 1;


    }

    public void play() {
        //TODO: Implement the method that executes the game session
        int currentGuess;
        System.out.println("Zagrajmy. Zgadnij liczbę z zakresu<1," + M + ">");
        Scanner scanNumber = new Scanner(System.in);
        for(currentGuess=0; currentGuess < L; currentGuess++){
            progressBar(currentGuess);
            System.out.println(UsefulConstants.GIVE_ME + " liczbe : ");
            int guess;
            try{
                guess=Integer.parseInt(scanNumber.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println(UsefulConstants.NOT_A_NUMBER);
                continue;
            }
            if(guess<randomNumber){
                System.out.println(UsefulConstants.TO_LESS);
            }
            else if(guess>randomNumber){
                System.out.println(UsefulConstants.TO_MUCH);
            }
            else {
                System.out.println(UsefulConstants.YES);
                break;
            }

        }
        if(currentGuess < L){
            System.out.println(UsefulConstants.CONGRATULATIONS);
        }
        else{
            System.out.println(UsefulConstants.UNFORTUNATELY);
        }

    }

    public void progressBar(int Attempt){
        System.out.println("Twoje proby: [" + "*".repeat(Math.max(1, Attempt+1)) + ".".repeat(Math.max(0, L - Attempt-1)) + "]");
    }
}
