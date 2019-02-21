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
public class ErrorDeSintaxisException extends RuntimeException{

    /**
     * Lanza una excepción cuyo texto es "Error de sintaxis"
     */
    public ErrorDeSintaxisException() {
        super("Error de sintaxis!");
    }
    
    /**
     * Lanza una excepción cuyo texto es un String dado como parametro
     * @param mensaje el mensaje que se quiere mostrar al usuario
     */
    public ErrorDeSintaxisException(String mensaje) {
        super(mensaje);
    }
    
}
