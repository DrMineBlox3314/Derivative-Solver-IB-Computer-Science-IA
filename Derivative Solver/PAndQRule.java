import java.util.ArrayList;
public class PAndQRule {
   ChainRule firstChain;
   ChainRule secondChain;
   ChainRule firstDeriv;
   ChainRule secondDeriv;
   ChainRule dividend;
   String full;
   String first;
   String second;
   boolean pq;
   public PAndQRule(String full){ //determines whether the product rule or quotient rule is needed
       this.full = full;
       int i = -1;
       if(full.contains("/")){ //if the problem is quotient rule
            int j = full.indexOf(")");    
               i = full.indexOf("/",j);
               if(i < full.indexOf("(",j)){
               first = full.substring(0, i);
               firstChain = new ChainRule(first);
               second = full.substring(i + 1, full.length());
               secondChain = new ChainRule(second);
               pq = false;
               }
           }
        else{ //if the problem is product rule
            i = full.indexOf("*");
            first = full.substring(0,i);
            second = full.substring(i + 1, full.length());
               firstChain = new ChainRule(first);
               secondChain = new ChainRule(second);
               pq = true;
        }
   }
        public static String addOrMultiply(String one, String two, boolean am){ //method to multiply 2 polynomial functions
            Polynomial One = new Polynomial(one);
            Polynomial Two = new Polynomial(two);
            String thing = "";
            ArrayList<Double> pows = new ArrayList<Double>();
            ArrayList<Double> coes = new ArrayList<Double>();
            if(am){ //multiplies the polynomials
            for(int i = 0; i<One.getListLength(); i++){ //nested for loop to multiply everything together
                for(int j = 0; j<Two.getListLength(); j++){
                    if(!pows.contains((One.getPow(i)) + Two.getPow(j))){
                    pows.add(One.getPow(i) + Two.getPow(j));
                    coes.add(One.getCoe(i) * Two.getCoe(j));
                }
                else{
                    int k = pows.indexOf(One.getPow(i) + Two.getPow(j));
                            coes.set(k, coes.get(k) + One.getCoe(j));
                }
            }
            }
            }
            else{ //adds the polynomials
                for(int i = 0; i<One.getListLength(); i++){
                    for(int j = 0; j<Two.getListLength(); j++){
                    if(One.getPow(i) == Two.getPow(j)){
                        if(!pows.contains(One.getPow(i))){
                            pows.add(One.getPow(i));
                            coes.add(One.getCoe(i) + Two.getCoe(j));
                        }
                        else{
                            int k = pows.indexOf(One.getPow(i));
                            coes.set(k, One.getCoe(i) + Two.getCoe(j));
                        }
                    }
                    else{
                        if(!pows.contains(One.getPow(i))){
                            pows.add(One.getPow(i));
                            coes.add(One.getCoe(i));
                        }
                        if(!pows.contains(Two.getPow(j))){
                            pows.add(Two.getPow(j));
                            coes.add(Two.getCoe(j));
                        }
                    }
                }
            }
            }
            
            for(int i = 0; i<pows.size(); i++){ //puts the new polynomial together
            String c = One.parse(coes.get(i));
            String Var;
            if(!One.getNomialVar(0).equals("")){
            Var = One.getNomialVar(0);
            }
            else{
            Var = Two.getNomialVar(0);    
            }
                if(coes.get(i) > 0 && i == 0){ // if the coefficient is greater than 0 and
            thing = c; // and it is the leading coefficient
            }
            else if(coes.get(i) > 0){ // if the coefficient is greater than 0
            thing += "+ " + c;
            }
            else if(coes.get(i) < 0){ // if the coefficient is less than 0
            thing += "- " + c;
            }
            else{ //if the coefficient is 0
                thing += "";
            }
            if(coes.get(i) != 0){ //as long as the coefficient does not equal 0
            c = One.parse(pows.get(i));
            if(pows.get(i) == 1){ //if the power equals 1
                thing += Var;
            }
            else if(pows.get(i) != 0){ //if the power does not equal 0
                if(pows.get(i)<0){
                    c = "-" + c;
                }
                thing += Var + "^" + c;
            }
            }
            if(i < pows.size() - 1){
            thing += " ";
            }
        }
        return thing; //returns the multiplied polynomial
    }
    public void solvePorQ(){ //function to solve Product or Quotient Rule
        firstDeriv = new ChainRule(firstChain.toString());
        secondDeriv = new ChainRule(secondChain.toString());
        firstDeriv.solveChain();
        secondDeriv.solveChain();
        if(pq){
        full = firstChain.toString() +"*"+ secondDeriv.toString();
        full += " + " + secondChain.toString() +"*"+ firstDeriv.toString();
        }
        else{
        dividend = new ChainRule(secondChain.toString());
        dividend.setPow(dividend.getPow()*2);
        dividend.setCoe(dividend.getChainCoe()*dividend.getChainCoe());
        dividend.setChain();
        full = secondChain.toString() +"*"+ firstDeriv.toString();
        full += " - " + firstChain.toString() +"*"+ secondDeriv.toString();
        full += " / " + dividend.toString();
        }
        if(firstDeriv.getInPow() == 0 && firstDeriv.getPow() == 1 && pq){
            firstDeriv.setIn("1");
        }
        if(secondDeriv.getInPow() == 0 && secondDeriv.getPow() == 1 && pq){
            secondDeriv.setIn("1");
        }
    }
    public void factor(){ //factors out the product answer
        if(!(firstChain.getPow() == 1 || secondChain.getPow() == 1)){ //if both powers are not one
        full = "(" + firstChain.getIn() + ")" + firstDeriv.getPowStr() + "*";
        full += "(" + secondChain.getIn() + ")" + secondDeriv.getPowStr();
        full += "[" + secondDeriv.getCoe() + "(" + secondDeriv.getIn() +")" + "(" + firstChain.getIn() + ")";
        full += " + " + firstDeriv.getCoe() + "(" + firstDeriv.getIn() +")" + "(" + secondChain.getIn() + ")]";
        }
        else if(firstChain.getPow() == 1 && secondChain.getPow() != 1){ //if 1 of the powers are is one
            full = "(" + secondChain.getIn() + ")" + secondDeriv.getPowStr();
            full += "[" + secondDeriv.getCoe() + "(" + secondDeriv.getIn() +")" + "(" + firstChain.getIn() + ")";
            full += " + " + secondChain.getCoe() + firstDeriv.toString() + "(" + secondChain.getIn() + ")]";
            
        }
        else if(secondChain.getPow() == 1 && firstChain.getPow() != 1 ){ //if 1 of the powers are is one
            full = "(" + firstChain.getIn() + ")" + firstDeriv.getPowStr();
            full += "[" + firstChain.getCoe() + secondDeriv.toString() + "(" + firstChain.getIn() + ")";
            full += " + " + firstDeriv.getCoe() + "(" + firstDeriv.getIn() +")" + "(" + secondChain.getIn() + ")]";
            
        }
        else{ //if both powers are one
            simplify();
        }
        }
    public void simplify(){ //simplifies the answer to its final form
        if(pq){ //if product rule
            if(firstChain.getPow() == 1 && secondChain.getPow() == 1){ //if both powers are one
                String coe1 = firstChain.getCoe();
                String coe2 = secondChain.getCoe();
                String part1 = addOrMultiply(coe2,firstChain.getIn(),true);
                String part2 = addOrMultiply(coe1,secondChain.getIn(),true);
                part1 = addOrMultiply(part1,secondDeriv.getIn(),true);
                part2 = addOrMultiply(part2,firstDeriv.getIn(),true);
                full = addOrMultiply(part2,part1,false);
            }
            else{ //otherwise
                String part = full.substring(0,full.indexOf("["));
                String coe1 = firstDeriv.getCoe();
                String coe2 = secondDeriv.getCoe();
                String part1 = addOrMultiply(coe2,firstChain.getIn(),true);
                String part2 = addOrMultiply(coe1,secondChain.getIn(),true);
                part1 = addOrMultiply(part1,secondDeriv.getIn(),true);
                part2 = addOrMultiply(part2,firstDeriv.getIn(),true);
                full = part + "[" + addOrMultiply(part2,part1,false) + "]";
            }
        }
        else{ //if quotient rule
            if(firstChain.getPow() == 1 && secondChain.getPow() != 1){ //if the power of the first chain is one
                double k = dividend.getPow() - secondDeriv.getPow(); //and the second is not
                dividend.setPow(k);
                dividend.setChain();
                secondChain.setPow(k - 1);
                secondChain.setChain();
                String coe1 = firstDeriv.getCoe();
                String coe2 = secondDeriv.getCoe();
                String part1 = addOrMultiply(coe1,firstDeriv.getIn(),true);
                String part2 = addOrMultiply(coe2,firstChain.getIn(),true);
                part1 = addOrMultiply(part1,secondChain.getIn(),true);
                part2 = addOrMultiply(part2, "-1",true);
                full = "(" + addOrMultiply(part2,part1,false) + ")";
                full += "/" + dividend.toString();
            }
            else if(firstChain.getPow() == 1 && secondChain.getPow() == 1){ //if both are one
                String coe1 = firstDeriv.getCoe();
                String coe2 = secondDeriv.getCoe();
                String part1 = addOrMultiply(coe1,firstDeriv.getIn(),true);
                String part2 = addOrMultiply(coe2,secondDeriv.getIn(),true);
                part1 = addOrMultiply(part1,secondChain.getIn(),true);
                part2 = addOrMultiply(part2,firstChain.getIn(),true);
                part2 = addOrMultiply(part2, "-1",true);
                full = "(" + addOrMultiply(part2,part1,false) + ")";
                full += "/" + dividend.toString();
            }
            else{ //if both are not one
                double k = dividend.getPow() - secondDeriv.getPow();
                dividend.setPow(k);
                dividend.setChain();
                secondChain.setPow(k - 1);
                secondChain.setChain();
                secondDeriv.setPow(k - 2);
                secondDeriv.setChain();
                full = secondChain.toString() +"*"+ firstDeriv.toString();
                full += " - " + firstChain.toString() +"*"+ secondDeriv.toString();
                full += " / " + dividend.toString();
                
            }
        }
    }
    public String toString(){ //returns entire string
        return full;
    }
    public boolean getPQ(){ //returns whether the expression is product or quotient rule
        return pq;
    }
}