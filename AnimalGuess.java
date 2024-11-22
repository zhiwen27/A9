import java.util.Scanner;

public class AnimalGuess{
    public static void main(String[] args) {
        Boolean play = true;
        Scanner sc = new Scanner(System.in);
        while(play){
            System.err.println("Think of an animal.\n" + "I'll try to guess it.\n" + "Is your animal a Mouse?");
            String input = sc.next().toLowerCase();
            if (input.contains("yes")){
                System.err.println("I guessed it!\n" + "Play again?");
                input = sc.next().toLowerCase();
                if (input.contains("no")){
                    play = false;
                }
            }
            else{
                System.err.println("I got it wrong.\n" + "Please help me to learn.\n" + "What was your animal?");
                input = sc.next().toLowerCase();
            }
        }
    }
}