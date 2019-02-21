/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libs;

/**
 * 
 * @author Equipo4
 */
public class Numero implements ElementoDeExpresion{
    private double valor;

    /**
     * Se crea un objeto Numero cuyo valor es el numero que representa
     * @param valor 
     */
    public Numero(double valor) {
        this.valor = valor;
    }
    
    /**
     * Funcion que regresa el valor numerico del objeto
     * @return el valor del numero
     */
    @Override
    public double getDoubleValue() {
        return valor;
    }

    /**
     * Funcion que regresa el valor del objeto visto como caracter
     * @return '/0' pues se trata de un numero, no de un operador
     */
    @Override
    public char getCharValue() {
        return '\0';
    }

    /**
     * Funcion para representar la informacion basica del numero
     * @return expresa que pertence a la clave Numero y su valor numerico
     */
    @Override
    public String toString() {
        return "Numero{" + "valor=" + valor + '}';
    }
    
}
