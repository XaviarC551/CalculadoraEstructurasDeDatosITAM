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
public class ErrorDeIndefinicionException extends RuntimeException{
    /**
     * Lanza una excepción cuyo texto es "Error de sintaxis"
     */
    public ErrorDeIndefinicionException() {
        super("Error de sintaxis");
    }
    
    /**
     * Lanza una excepción cuyo texto es un String dado como parametro
     * @param mensaje el mensaje que se quiere mostrar al usuario
     */
    public ErrorDeIndefinicionException(String mensaje) {
        super(mensaje);
    }
}
