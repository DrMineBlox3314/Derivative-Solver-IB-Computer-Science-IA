public class Nomial 
{ //class function that separates the individual parts of a number
    String number; //parts
    double coe;
    String variable;
    double pow;
    
    public Nomial(String number) //takes the number and divides it up
    {
     this.number = number;
     String thing = "";
     int j = 0;
     //gets the coefficient
     for(int i = 0; i<number.length(); i++){ 
         char x = number.charAt(i);
         if((x>=65 && x<=90) || (x>=97 && x<=122)){
            j = i;
            break;
         }
        if(x!= 32 ){ //checking for spaces
         thing += x;
        }
     }
     if(thing.equals("") || thing.equals("+")){
         coe = 1;
     }
     else if(thing.equals("-")){
         coe = -1;
     }
     else{
         if(thing.contains("/")){ //if there is a fraction
                int i = thing.indexOf("/");
                coe = Double.parseDouble(thing.substring(0,i)) / Double.parseDouble(thing.substring(i+1,thing.length()));
         } 
         else{
            coe = Double.parseDouble(thing);
         }
     
     if(j==0){
         j=number.length() - 1;
     }
     }
     //gets the variable
     if((65<=number.charAt(j) && number.charAt(j)<=90) || (97<=number.charAt(j) && number.charAt(j)<=122)){
     variable = number.substring(j,j+1);
     //gets the power
     if(number.contains("^")){
         if(number.substring(j+2,number.length()).contains("/")){ //if there is a fraction
                int i = number.substring(number.indexOf("^"),number.length()).indexOf("/") + j + 1;
                pow = Double.parseDouble(number.substring(j+2,i)) / Double.parseDouble(number.substring(i+1,number.length()));
         } 
         else{
        pow = Double.parseDouble(number.substring(j+2,number.length()));
         }
     }
     else{
         pow = 1;
     }
     }
     else{
         variable = "";
         pow = 0;
         
     }
     
    }
}