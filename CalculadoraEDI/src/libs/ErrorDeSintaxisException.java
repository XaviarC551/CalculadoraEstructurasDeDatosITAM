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
public class ErrorDeSintaxisException extends RuntimeException{

    public ErrorDeSintaxisException() {
        super("Error de sintaxis!");
    }
    
    public ErrorDeSintaxisException(String str) {
        super(str);
    }
    
}
