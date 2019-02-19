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
public class Numero extends ElementoDeExpresion{
    private double valor;

    public Numero(double valor) {
        this.valor = valor;
    }
    
    @Override
    public double getDoubleValue() {
        return valor;
    }

    @Override
    public char getCharValue() {
        return '\0';
    }
    
}
