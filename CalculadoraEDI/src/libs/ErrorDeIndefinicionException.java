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
public class ErrorDeIndefinicionException extends RuntimeException{
    public ErrorDeIndefinicionException() {
        super("Error de sintaxis!");
    }
    
    public ErrorDeIndefinicionException(String str) {
        super(str);
    }
}
