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
class EmptyCollectionException extends RuntimeException {

    /**
     * Lanza una excepción cuyo texto es "Coleccion vacia"
     */
    public EmptyCollectionException() {
        super("Coleccion vacia");
    }
    
    /**
     * Lanza una excepción cuyo texto es un String dado como parametro
     * @param mensaje el mensaje que se quiere mostrar al usuario
     */
    public EmptyCollectionException(String mensaje) {
        super(mensaje);
    }
    
}
