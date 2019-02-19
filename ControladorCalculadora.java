/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clase8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ControladorCalculadora extends VistaCalculadora{
    public ControladorCalculadora(){
        cambiaSigno.addActionListener(new EscuchadorOperador());
        punto.addActionListener(new EscuchadorOperador());
        div.addActionListener(new EscuchadorOperador());
        resta.addActionListener(new EscuchadorOperador());
        suma.addActionListener(new EscuchadorOperador());     
        borra.addActionListener(new EscuchadorOperador());
        limpiar.addActionListener(new EscuchadorOperador());
        
        pDer.addActionListener(new EscuchadorParentesis());
        pIzq.addActionListener(new EscuchadorParentesis()); 

        num0.addActionListener(new EscuchadorNumero());
        num1.addActionListener(new EscuchadorNumero());
        num2.addActionListener(new EscuchadorNumero());
        num3.addActionListener(new EscuchadorNumero());
        num4.addActionListener(new EscuchadorNumero());
        num5.addActionListener(new EscuchadorNumero());
        num6.addActionListener(new EscuchadorNumero());
        num7.addActionListener(new EscuchadorNumero());
        num8.addActionListener(new EscuchadorNumero());
        num9.addActionListener(new EscuchadorNumero());
        
        igual.addActionListener(new EscuchadorResultado());
    }
    
    private class EscuchadorOperador implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            switch(arg0.getActionCommand().charAt(0)){
                case 'c':   //cambiaSigno
                    
                    break;
                case 'p':      //punto
                    
                    break;
                case 'd':       //division
                    
                    break;
                case 'r':       //resta
                    
                    break;
                case 's':       //suma
                    
                    break;
                case 'b':       //borra uno
                    
                    break;
                case 'l':       //limpiar todo
                    
            }
        }
    }
    
    private class EscuchadorParentesis implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
           if(arg0.getActionCommand().charAt(1)=='D'){  //paréntesis derecho
               
           }
           else{        //paréntesis izquierdo
               
           }
        }
    }
    
    private class EscuchadorNumero implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
           switch(arg0.getActionCommand().charAt(3)){
                case '0':
                   
                   break;
                case '1':
                
                    break;
                case '2':
                   
                   break;
                case '3':
                
                    break;
                case '4':
                   
                   break;
                case '5':
                
                    break;
                case '6':
                   
                   break;
                case '7':
                
                    break;
                case '8':
                   
                   break;
                case '9':

                    
           }      
        }
    }
    
    private class EscuchadorResultado implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
                    
        }
    }
    
}
