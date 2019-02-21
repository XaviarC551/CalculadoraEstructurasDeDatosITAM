/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libs;

/**
 *
 * @author Equipo 4 
 */
public class Operador implements ElementoDeExpresion{
    
    private char operador;
    
    
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

    @Override
    public String toString() {
        return "Operador{" + "operador=" + operador + '}';
    }
    
}
