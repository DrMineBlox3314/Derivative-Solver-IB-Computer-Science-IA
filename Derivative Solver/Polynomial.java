public class Polynomial {
    String poly = "";
    Nomial [] nomials;
    public Polynomial(String poly){
        this.poly = poly; //entire expression
        int c = 1;
        for(int i = 0; i<poly.length(); i++){ //finds how many numbers are in poly
            char j = poly.charAt(i);
            if(j == 45 || j == 43){
            c++;
            }
        }
            if((poly.charAt(0) == 43 || poly.charAt(0) == 45)){
                c--;
            }
            nomials = new Nomial[c];
            int start = 0;
            int end = 0;
            int e = 0;
            if(c == 1){ //for polynomials with only one variable
                Nomial d = new Nomial(poly);
                nomials[e] = d;
            }
            else{
            for(int i = 0; i<poly.length(); i++){ //for polynomials greater than 
                char j = poly.charAt(i); //one variable
                if((j == 45 || j == 43)&& i>0){
                end = i;
                Nomial d = new Nomial(poly.substring(start, end));
                start = end;
                nomials[e] = d; 
                e++;
                }
                if(i == poly.length() - 1){ //gets the last string
                    end = i + 1;
                    Nomial d = new Nomial(poly.substring(start, end));
                    nomials[e] = d;
                }
            }
            }
    }
        public void setNomialCoe(int c, double a){ //sets the given coefficient
            nomials[c].coe = a;
        }
        public String getNomialCoe(int c){ //gets the coefficient (string)
            if(nomials[c].coe>=0){
            return parse(nomials[c].coe);
            }
            return "-" + parse(nomials[c].coe);
        }
        public double getCoe(int c){ //gets the coefficient (double)
            return nomials[c].coe;
        }
        public String getNomialPow(int c){ //gets the power (string)
        if(nomials[c].pow > 0 && (nomials[c].pow != 1)){
            return "^" + parse(nomials[c].pow);
        }
        else if(nomials[c].pow < 0){
            return "^-" + parse(nomials[c].pow);
        }
            return "";
        }
        public void setNomialPow(int c, double a){ //sets the given power
            nomials[c].pow = a;
        }
        public double getPow(int c){ //gets the power (double)
            return nomials[c].pow;
        }
        public int getListLength(){ //gets the list length
            return nomials.length;
        }
        public String getNomialVar(int c){ //gets the given variable
            return nomials[c].variable;
        }
        public String getNomialNum(int c){
            return nomials[c].number;
        }
        public String parse(double num) { //changes any double to int if double is divisible by 1
        if(num < 0){ //(courtesy of stack overflow)
            num += (num*-2);
        }
        if((int) num == num){
        return Integer.toString((int) num);
        }
        return String.valueOf(num); 
        }
        public String toString(){ //returns the entire string
            return poly;
        }
        public void setNomialNum(int c){ //sets the given number
            String i = parse(nomials[c].coe);
            if(nomials[c].coe > 0 && c == 0){ // if the coefficient is greater than 0 and
            nomials[c].number = i; // and it is the leading coefficient
            }
            else if(nomials[c].coe > 0){ // if the coefficient is greater than 0
            nomials[c].number = "+ " + i;
            }
            else if(nomials[c].coe < 0){ // if the coefficient is less than 0
            nomials[c].number = "- " + i;
            }
            else{ //if the coefficient is 0
                nomials[c].number = "";
            }
            if(nomials[c].coe != 0){ //as long as the coefficient does not equal 0
            i = parse(nomials[c].pow);
            if(nomials[c].pow == 1){ //if the power equals 1
                nomials[c].number += nomials[c].variable;
            }
            else if(nomials[c].pow != 0){ //if the power does not equal 0
                if(nomials[c].pow<0){
                    i = "-" + i;
                }
                nomials[c].number += nomials[c].variable + "^" + i;
            }
            }
            if(c < nomials.length - 2){
            nomials[c].number += " ";
            }
        }
        public void powerRule(){ //uses the power rule on the polynomial
            poly = "";
            for(int i = 0; i < getListLength(); i++){
                nomials[i].coe *= nomials[i].pow;
                nomials[i].pow += -1;
                setNomialNum(i);
                poly += nomials[i].number; 
            }
        }
        
    }