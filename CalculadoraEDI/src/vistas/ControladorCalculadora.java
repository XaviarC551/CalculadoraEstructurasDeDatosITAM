/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import libs.ErrorDeIndefinicionException;
import libs.ErrorDeSintaxisException;
import libs.ProcesadorDeExpresiones;
//import libs.ProcesadorDeExpresiones;
/**
 *
 * @author CD
 */
public class ControladorCalculadora extends VistaCalculadora {
    //TODO: ProcesadorDeExpresiones
    private final char[] mapaDeBotones=new char[]{
        '0','1','2','3','4','5','6','7','8','9',
        '/','*','+','-','=','.','(',')','n','l',
        'b'
    };
    public ControladorCalculadora(){
        super();        
        for(int i = 0; i<botones.length; i++){
            botones[i].addActionListener(
                    new EscuchaBoton(mapaDeBotones[i]));
            
        }
        this.addKeyListener(new EscuchaTeclado());
        mostrarMensaje("Hola","Mundo!");
    }
    
    private void mostrarMensaje(String mensaje, String titulo){
        JOptionPane.showMessageDialog(this,mensaje,titulo,
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    private boolean esNumero(char c){
        return (c>='0' && c<='9') || c == '.';
    }
    
    private boolean puedeEscribirse(char c){
        return esNumero(c) ||
                (c >= '(' && c<='+') ||
                (c >= '-' && c<='/');
            
    }
    
    private void clear(){
        expresion.setText("");
        resultado.setText("");
    }
    
    private String substr(String str, int a, int b){
        if(b<a)
            return "";
        return str.substring(a, b);
    }
    
    public void procesarBoton(char c){
        String exp=expresion.getText();
        if(this.puedeEscribirse(c)){
            /*if(c == '0'){
                if(!exp.isEmpty() && esNumero(exp.charAt(exp.length()-1)))
                    expresion.setText(exp + c);
            }
            else*/
                expresion.setText(exp + c);
        }
        else{
            switch(c){
                case 'b':
                    if(exp.length() > 0)
                        expresion.setText(exp.substring(0, exp.length()-1));
                    break;
                case 'l':
                    clear();
                    break;
                case 'n':
                    if(!exp.isEmpty()){
                        int k=exp.length();
                        int i=exp.length()-1;
                        if(esNumero(exp.charAt(i))){
                            while(i>=0 && esNumero(exp.charAt(i)))
                                i--;
                            System.out.println(i);
                            expresion.setText(substr(exp,0, i+1)+"(-"+exp.substring(i+1, k)+")");
                        }
                    }
                    break;
                case '=':
                    try{
                        resultado.setText(String.valueOf(ProcesadorDeExpresiones.procesarExpresion(exp)));
                    }
                    catch(Exception e){
                        if(e instanceof ErrorDeSintaxisException)
                            resultado.setText("Error de sintaxis");
                        else if(e instanceof ErrorDeIndefinicionException)
                            resultado.setText("El  resultado es indefinido");
                    }
                    break;
            }
        }
        
    }   
        
    class EscuchaBoton implements ActionListener{
        private char comando;
        
        public EscuchaBoton(char comando){
            this.comando=comando;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            procesarBoton(comando);
        }
    };
    class EscuchaTeclado implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {
            char c=e.getKeyChar();
            if(puedeEscribirse(c)){
                procesarBoton(c);
            }
            else{
                switch(c){
                    // Tecla de "borrar"
                    case KeyEvent.VK_BACK_SPACE:
                        procesarBoton('b');
                        break;
                    case KeyEvent.VK_ENTER:
                        procesarBoton('=');
                        break;
                    case KeyEvent.VK_DELETE:
                        procesarBoton('l');
                        break;
                }
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };
    
    public static void main(String[] args){
         /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaCalculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaCalculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaCalculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCalculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ControladorCalculadora().setVisible(true);
                
            }
        });
    }
    
}
