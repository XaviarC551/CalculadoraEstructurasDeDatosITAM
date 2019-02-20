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
        int res = 0;
        if(a == '^')
            res = 2;
        else if(a == '*' || a == '/')
            res = 1;
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
    
}
