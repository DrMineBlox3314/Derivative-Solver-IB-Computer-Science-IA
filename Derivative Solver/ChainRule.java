public class ChainRule {
    String full;
    Polynomial out;
    Polynomial in;
    double coe;
    double pow;
    public ChainRule(String full){
        this.full = full;
        //finding the bounds of the parentheses
        int j = 0;
        for(int i=0; i<full.length(); i++){
            if(full.charAt(i) == 40){
                j = i;
                break;
            }
        }
        int k = 0;
        for(int i=j; i<full.length(); i++){
            if(full.charAt(i) == 41){
                k = i;
                break;
            }
        }    
        out = new Polynomial(full.substring(0,j) + "z" + full.substring(k+1,full.length()));
        in = new Polynomial(full.substring(j+1,k));
        coe = out.getCoe(0); //saves 
        pow = out.getPow(0);
    }
    public void solveChain(){ //does the chain rule
        if(pow == 1){ //basically runs the power rule
            distribute();
            Polynomial solve = new Polynomial(full);
            solve.powerRule();
            in = solve;
            full = "(" + solve.toString() + ")";
            }
        else{ //runs chain rule
        out.powerRule();
        coe = out.getCoe(0);
        pow = out.getPow(0);
        Polynomial in2 = new Polynomial(in.toString());
        in.powerRule();
        if(in.getPow(0) == 0){ //if the derivative of the inside is only a coefficient
            out.setNomialCoe(0, out.getCoe(0) * in2.getCoe(0));
        full = out.getNomialCoe(0) + "(" + in2 + ")" + out.getNomialPow(0);
        }
        else if(pow == 1){ //if the derived power is one
            distribute();
            full = "(" + full + ")";
        }
        else{ //otherwise, do normal chain rule
            full = out.getNomialCoe(0) + "(" + in + ")" + "(" + in2 + ")" + out.getNomialPow(0);
        }
        }
    }
    public void setChain(){ //resets the chain with current power and coefficient
        if(pow == 1){
            distribute();
            full = "(" + full + ")";
        }
        else if(pow == 0){
            full = "1";
        }
        else{
        out.setNomialCoe(0,coe);
        out.setNomialPow(0,pow);
        full = out.getNomialCoe(0) + "(" + in + ")" + out.getNomialPow(0);
        }
    }
        
    public void distribute(){ //distributes the outside coefficient on all the inside numbers
        if(pow == 1){
            full = "";
            for(int i = 0; i<in.getListLength(); i++){
                in.setNomialCoe(i, in.getCoe(i) * coe);
                in.setNomialNum(i);
                full += in.getNomialNum(i);

            }
        }
    }
    public String toString(){ //returns the entire string
        return full;
    }
    public double getPow(){ //returns the power of the chain (double)
        return pow;
    }
    public String getPowStr(){ //returns the power of the chain (string)
        return out.getNomialPow(0);
    }
    public void setPow(double pow){ //sets the power of the chain 
        this.pow = pow;
    }
    public String getCoe(){ //returns the coefficient of the chain (string)
        out.setNomialCoe(0, coe);
        return out.getNomialCoe(0);
    }
    public double getChainCoe(){ //returns the coefficient of the chain (double)
        return coe;
    }
    public void setCoe(double coe){ //sets the coefficient of the chain 
        this.coe = coe;
    }
    public String getIn(){ //returns the inside of the string
        return in.toString();
        
    }
    public void setIn(String in){ //sets the inside of the string
        this.in = new Polynomial(in);
        
    }
    public double getInPow(){ //returns the highest power of the inside
        return in.getPow(0);
        
    }
    
}