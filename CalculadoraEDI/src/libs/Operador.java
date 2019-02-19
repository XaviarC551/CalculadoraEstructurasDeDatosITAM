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
public class Operador extends ElementoDeExpresion{
    
    char operador;
    
    public Operador(char operador) {
        this.operador=operador;
    }

    @Override
    public double getDoubleValue() {
        return 0.0;
    }

    @Override
    public char getCharValue() {
        return operador;
    }
}
