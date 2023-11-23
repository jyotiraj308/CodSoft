//Program for guess the number game
import java.util.*;
public class NumberGuessGame {
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        Level player = new Level("Yuvraj",4);
        while(true){
            player.level1();
            System.out.print("\nPlay Again[y or Y for yes]: ");
            String option = input.nextLine();
            System.out.println();
            if(option.length() == 0 || (option.charAt(0)!='y' && option.charAt(0)!='Y')){
                System.out.println("GAME OVER!!!");
                break;      
            } 
        }
    }
    
}
class Level{
    String name;
    int chance;
    Level(String name,int chance){
        this.name = name;
        this.chance = chance;
        System.out.printf("Welcome %s to play the game of guess the number\n",this.name);
    }
    //To get an random number
    public int randomNumber(int max){
        int min = 1;
        Random random = new Random();
        return random.nextInt(max-min+1)+min;
    }
    //Level 1
    public void level1(){
        Scanner input = new Scanner(System.in);
        int guessNumber,number,chanceLeft = -1,countChance =0;
        System.out.println("----------Level1----------");
        System.out.println("\nIn this level you have to guess the number from 1 to 20.");
        System.out.println("If you guess the number in n chance then you will have (chance-n)chance for next level as bonus.\n");
        guessNumber = randomNumber(20);
        do{
            System.out.print("Guess The number: ");
            number = input.nextInt();
            System.out.println();
            countChance++;
            if(number == guessNumber){
                System.out.println("\nHurrah!!! You win level1.");
                chanceLeft = chance-countChance;
                break;
            }
            else if(number>guessNumber){
                System.out.println("Guess number is too high");
                System.out.println("Please guess lower numer");
            }
            else{
                System.out.println("Guess number is too low");
                System.out.println("Please guess higher numer");
            }
        }while(countChance != chance);
         if(chanceLeft == -1){
            System.out.println("\nBetter Luck Next Time");
            System.out.println("Your Score = "+countChance);
            return;
        }
        System.out.println("You are qualified for level 2 with "+chanceLeft+" bonus Chances");
        chance = chance+chanceLeft; // chance incresed by bonus chances
        level2(); //Calling of level2 from level 1 function
    }
    //Level 2
    public void level2(){
        System.out.println("\n\n-------------Level2-----------");
        Scanner input = new Scanner(System.in);
        int guessNumber,number,chanceLeft=-1,countChance=0;
        System.out.println("\nIn Level2 You have to guess the number from 1 to 50");
        System.out.println("Also in this level if you complete this level in n chances then you will have (chance-n) chance for level 3\n");
        guessNumber = randomNumber(50);
        do{
            System.out.print("Guess The number: ");
            number = input.nextInt();
            System.out.println();
            countChance++;
            if(number == guessNumber){
                System.out.println("\nHurrah!!! You win level2.");
                chanceLeft = chance-countChance;
                break;
            }
            else if(number>guessNumber){
                System.out.println("Guess number is too high");
                System.out.println("Please guess lower numer");
            }
            else{
                System.out.println("Guess number is too low");
                System.out.println("Please guess higher numer");
            }
        }while(countChance != chance);
        if(chanceLeft == -1){
            System.out.println("\nBetter Luck Next Time");
            System.out.println("Your Score = "+countChance);
            return;
        }
        System.out.println("You are qualified for level 3 with "+chanceLeft+" chances as bonus.");
        chance = 4 + chanceLeft;//chance is equal to 4+chanceleft till level 2
        level3();//Calling level 3 method from level2() method

    }
    //Level 3
    public void level3(){
        int guessNumber,number,countChance=0;
        Scanner input = new Scanner(System.in);
        System.out.println("\n----------Level 3----------\n");
        System.out.println("In this level guess the number between \"1 to 100\"");
        System.out.println("This is Final level.\nThis level decides the winner so try to take less chance");
        guessNumber = randomNumber(100);
        while(countChance != chance){
            countChance++;
            System.out.print("Guess The Number: ");
            number = input.nextInt();
            System.out.println();
            if(number == guessNumber){
                System.out.println("\nYou are winner of this game.");
                System.out.println("Your Score = "+countChance);
                return;
            }
            else if(number>guessNumber){
                System.out.println("Guessed number is too high.");
                System.out.println("Please guess lower number");
            }
            else{
                System.out.println("Guessed number is too low.");
                System.out.println("Please guess higher number");
            }
        }
        System.out.println("\nYou Lose the match at the last level.");
        System.out.println("Try Again");
        System.out.printf("Your Score = %d\n",countChance);
        
    }
    
}
