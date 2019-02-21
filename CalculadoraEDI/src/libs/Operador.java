/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libs;

/**
 *
 * @author Equipo 4 
 * @author Equipo4
 */
public class Operador implements ElementoDeExpresion{
    
    private char operador;
    
    
     /**
     * Es un constructor del Operado que utiliza un
     * @param operador 
     */
    public Operador(char operador) {
        this.operador=operador;
    }
    /**
     * Sirve para obtener el valor decimal
     * @return 0.0
     */
    @Override
    public double getDoubleValue() {
        return 0.0;
    }
    /**
     * Sirve para obtener el valor del caracter
     * @return operador
     */
    @Override
    public char getCharValue() {
        return operador;
    }

    @Override
    public String toString() {
        return "Operador{" + "operador=" + operador + '}';
    }
    
}
