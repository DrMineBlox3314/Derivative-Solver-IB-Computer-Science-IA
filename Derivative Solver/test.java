import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        //initial instructions
        System.out.println("This program is designed to solve any power, chain, product, \nor quotient rule derivative problems.");
        System.out.println("If your problem is a power rule problem, please enter \nthe problem without any parentheses.");
        System.out.println("Otherwise, please use parentheses to separate different  \nexpressions.");
        System.out.println("If your problem is product or quotient rule, use the '*' for  \nproduct rule and '/' for quotient rule.");
        Scanner input = new Scanner(System.in);
        while(true){
        try{ //runs the entire program
        System.out.println("Enter your problem(exit to quit):");
        String cool = input.nextLine();
        if(cool.equals("exit")){
            break;
        }
        RuleFinder c = new RuleFinder(cool);
        }
        catch(Exception e){ //if the user inputs the expression inproperly
            System.out.println("Please input with proper formatting.");
            System.out.println("Enter your problem(exit to quit):");
            String cool = input.nextLine();
            if(cool.equals("exit")){
            break;
            }
           RuleFinder c = new RuleFinder(cool);
        }
        }
    }
}