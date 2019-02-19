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
public class PilaA<T> implements Pila<T>{
    private T[] pila;
    private final static int MAX=2000000;
    private int top;
    public PilaA(){
        top=-1;
        pila=(T[]) new Object[MAX];
    }
    public PilaA(int max){
        top=-1;
        pila=(T[]) new Object[max];
    }
    
    private void expandir(){
        T[] newPila = (T[])new Object[pila.length*2];
        int i=0;
        for(T v:pila){
            newPila[i++]=v;
        }
        pila=newPila;
    }
    
    @Override
    public void push(T valor) {
        if(top<pila.length-1)
            pila[++top]=valor;
        else{
            expandir();
            pila[++top]=valor;
        }
    }
    
    @Override
    public T pop(){
        if(!isEmpty())
            return pila[top--];
        throw new EmptyCollectionException();
    }

    @Override
    public T peek() {
        T res=null;
        if(!isEmpty())
            res=pila[top];
        return res;
    }

    @Override
    public boolean isEmpty() {
        return top<0;
    }

    @Override
    public void multiPop(int n) {
        while(!isEmpty()&&n>0){
            pop();
            n--;
        }
        if(!isEmpty())
            return;
        throw new EmptyCollectionException();
    }
    
    
    
    
    
}
