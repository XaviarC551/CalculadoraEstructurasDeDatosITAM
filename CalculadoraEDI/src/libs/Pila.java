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
public interface Pila<T> {
    public void push(T valor);
    public T pop();
    public void multiPop(int n);
    public T peek();
    public boolean isEmpty();
}
