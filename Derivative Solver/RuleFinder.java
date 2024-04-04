import java.util.Scanner;
public class RuleFinder {
    String full;
    public RuleFinder(String full){ //determines what rule shouls be used based on parentheses
        this.full = full;
        if(!(full.contains("(") || full.contains(")"))){
            Power();
        }
        else{
            char e = 40;
            int count  = 0;
            for(int i = 0; i<full.length();  i++){
                if (full.charAt(i) == e) {
                count++;
                }
            }
            if(count == 1){
                Chain();
            }
            else{
                ProductQuotient();
            }
        }
    }
    public void Power(){ //implements the power rule with instuctions
    Scanner input = new Scanner(System.in);
    Polynomial a = new Polynomial(full);
    Polynomial b = new Polynomial(full);
    b.powerRule();
    full = b.toString();
    System.out.println("This is a power rule problem. These are the easiest to solve.");
    System.out.println("With the power rule, the function Px^n is now (P*n)x^n-1.");
    System.out.println("For example, the expression " + a.getNomialNum(0) + "is now " + b.getNomialNum(0));
    System.out.print("Show answer?(y/n):");
    String inp = input.nextLine();
    if(inp.equals("n") || inp.equals("n ")){
        System.out.println("Ok then.");
    }
    else{
    System.out.println("The answer is:");
    System.out.println(full);
    }
    }
    public void Chain(){ //implements the chain rule with instuctions
    Scanner input = new Scanner(System.in);
    ChainRule a = new ChainRule(full);
    a.solveChain();
    full = a.toString();
    System.out.println("This is a chain rule problem. These are typically easy to solve.");
    System.out.println("With the power rule, the function P(T)^n is now PnT'(T)^n-1");
    System.out.println("For example, the expression 2(2x^2 + 1)^4 is now 8(2x)(2x^2 +1)^3.");
    System.out.print("Show answer?(y/n):");
    String inp = input.nextLine();
    if(inp.equals("n") || inp.equals("n ")){
        System.out.println("Ok then.");
    }
    else{
    System.out.println("The answer is:");
    System.out.println(full);
    }
}
    public void ProductQuotient(){ //implements either product or quotient rule with instructions
    Scanner input = new Scanner(System.in);
    PAndQRule a = new PAndQRule(full);
    PAndQRule b = new PAndQRule(full);
    a.solvePorQ();
    b.solvePorQ();
    b.factor();
    b.simplify();
    full = a.toString();
    String inp;
    if(a.getPQ()){ //if product rule
    System.out.println("This is a product rule problem. \nThese are typically harder to solve.");
    System.out.println("With the product rule, the function (x)*(y) is now xy'+yx'");
    System.out.println("For example, the expression (2x^2 + 1)*(x+1) is now:\n(2x^2 + 1)(1) + (x+1)(4x).");
    System.out.print("Show next Step?(y/n):");
    inp = input.nextLine();
    if(inp.equals("n") || inp.equals("n ")){ //dependent on user input
        a.factor();
        a.simplify();
        System.out.print("Show final answer?(y/n):");
        inp = input.nextLine();
        if(inp.equals("n") || inp.equals("n ")){
            System.out.println("Ok then.");
        }
        else{
            System.out.println("The final answer is:");
            System.out.println(a);
            System.out.println("There may be more to factor, but this program is not intricate \nenough to factor further");
        }
    }
    else{ //if the problem does not need to be factored
    System.out.println("Now we have this:");
    System.out.println(a);
    a.factor();
    if(a.toString().equals(b.toString())){
        System.out.println("Now we simplify.");
    System.out.print("Show final answer?(y/n):");
    inp = input.nextLine();
    if(inp.equals("n") || inp.equals("n ")){
            System.out.println("Ok then.");
        }
        else{
            System.out.println("The final answer is:");
            System.out.println(a);
    }
    }
    else{ //if the problem does need to be factored
    System.out.println("Now we factor.");
    System.out.print("Show factored answer?(y/n):");
    inp = input.nextLine();
        if(inp.equals("n") || inp.equals("n ")){ //dependent on user input
            System.out.println("Ok then.");
            System.out.print("Show final answer?(y/n):");
            inp = input.nextLine();
            if(inp.equals("n") || inp.equals("n ")){
                System.out.println("Ok then.");
            }
            else{
                System.out.print("The final answer is:");
                a.simplify();
                System.out.println(a);
                System.out.println("There may be more to factor, but this program is not intricate \nenough to factor further");
            }
        }
        else{
            System.out.println("The factored answer is:");
            System.out.println(a);
            System.out.print("Show final answer?(y/n):");
            inp = input.nextLine();
            if(inp.equals("n") || inp.equals("n ")){
                System.out.println("Ok then.");
            }
            else{
                System.out.print("The final answer is:");
                a.simplify();
                System.out.println(a);
                System.out.println("There may be more to factor, but this program is not intricate \nenough to factor further");
            
    
            }
            }
        }
    }
    }
    else{ //if quotient rule
    System.out.println("This is a quotient rule problem. \nThese are typically harder to solve.");
    System.out.println("With the quotient rule, the function (x)/(y) is now x'y-xy'/x^2");
    System.out.println("For example, the expression (2x^2 + 1)/(x+1) is now:\n(x + 1)(2x) - (1)(2x^2 + 1) / (x+1)^2.");
    System.out.print("Show next Step?(y/n):");
    inp = input.nextLine();
    if(inp.equals("n") || inp.equals("n ")){ //dependent on user input
        a.simplify();
        System.out.print("Show final answer?(y/n):");
        inp = input.nextLine();
        if(inp.equals("n") || inp.equals("n ")){
            System.out.println("Ok then.");
        }
        else{
            System.out.println("The final answer is:");
            System.out.println(a);
            System.out.println("There may be more to factor, but this program is not intricate \nenough to factor further");
        }
    }
    else{
    System.out.println("Now we have this:");
    System.out.println(a);
    System.out.println("Now we simplify.");
    System.out.print("Show final answer?(y/n):");
    inp = input.nextLine();
    a.simplify();
        if(inp.equals("n") || inp.equals("n ")){
            System.out.println("Ok then.");
        }
        else{
            System.out.println("The final answer is:");
            System.out.println(a);
            System.out.println("There may be more to factor, but this program is not intricate \nenough to factor further");
    }
    }
    }
    }
    
}