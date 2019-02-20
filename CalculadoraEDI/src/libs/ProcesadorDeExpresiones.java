/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libs;

/**
 *
 * @author priet
 */
public class ProcesadorDeExpresiones {
    private String expresion;
    public ProcesadorDeExpresiones() {
    
    }
    
    private int getJerarquia(char a){
        int res = 1;
        if(a == '^')
            res = 3;
        else if(a == '*' || a == '/')
            res = 2;
        else if(a == ')' || a == '(')
            res = 0;            
        return res;
    }
    
    private int compararOperador(char a, char b){
        return getJerarquia(a)-getJerarquia(b);
    }
    
    private double procesarPostfija(Pila<ElementoDeExpresion> postfija){
        Pila<Double> valores=new PilaA();
        
        while(!postfija.isEmpty()){
            ElementoDeExpresion e = postfija.pop();
            if(e instanceof Numero)
                valores.push(e.getDoubleValue());
            else{
                double m=0.0;
                double b=valores.pop();
                double a=valores.pop();
                switch(e.getCharValue()){
                    case '+':
                        m = a + b;
                        break;
                    case '-':
                        m = a - b;
                        break;
                    case '/':
                        m = a / b;
                        break;
                    case '*':
                        m = a * b;
                        break;
                    case '^':
                        m = Math.pow(a, b);
                        break;
                        
                }
                valores.push(m);
            }
        }
        return valores.peek();
    }
    private  PilaA<ElementoDeExpresion> convertirPostfija(String cad){
        PilaA<ElementoDeExpresion> op, resp;
        int n, j;       
        char a;        
        String num;
        n = cad.length();                       
        resp = new PilaA<ElementoDeExpresion>();
        op = new PilaA<ElementoDeExpresion>();                
        for(int i = 0; i < n; i++){
            a = cad.charAt(i);            
            if(a == '('  || (op.isEmpty() && !isNumber(a))){
                op.push(new Operador(a));                
            }else if(isNumber(a)){
                j = i;
                num = "";
                while(j < n && (isNumber(cad.charAt(j)) || cad.charAt(j) == '.')){
                    num+=cad.charAt(j);
                    j++;                    
                }
                resp.push(new Numero(Double.parseDouble(num)));
                i = j-1;
            }else if(a == ')'){
                while(op.peek().getCharValue() != '('){
                    resp.push(op.pop());
                }
                op.pop();
            }else{
                if( getJerarquia(a) > getJerarquia(op.peek().getCharValue()))
                    op.push(new Operador(a));
                else{
                    while(!op.isEmpty() && getJerarquia(a) <= getJerarquia(op.peek().getCharValue())){
                        resp.push(op.pop());
                    }
                    op.push(new Operador(a));
                }
                    
            }
        }        
        if(!op.isEmpty()){
            while(!op.isEmpty())
                resp.push(op.pop());
        }
        return resp;
    }  
    private boolean isNumber(Character a){
        if((int)a >= 48 && (int)a <= 57)
            return true;
        return false;
    }
    
}
