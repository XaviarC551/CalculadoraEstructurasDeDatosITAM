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
 * @author Equipo 4
 */
public class ControladorCalculadora extends VistaCalculadora {
    //TODO: ProcesadorDeExpresiones
    private final char[] mapaDeBotones=new char[]{
        '0','1','2','3','4','5','6','7','8','9',
        '/','*','+','-','=','.','(',')','n','l',
        'b'
    };
    
    /**
     * Al inicializar el objeto:
     * <ul>
     * <li> se inicializa la vista </li>
     * <li> a los botones de la vista se les da la 
     *      funcionalidad de EscuchaBoton, la cual se encuentra en el código 
     *      de esta misma clase, con el primer simbolo/caracter que representan </li>
     * <li> se iniciliza la lectura del teclado </li>
     * <li> se muestra un mensaje </li>
     * </ul>
     */
    public ControladorCalculadora(){
        super();        
        for(int i = 0; i<botones.length; i++){
            botones[i].addActionListener(
                    new EscuchaBoton(mapaDeBotones[i]));  
        }
        this.addKeyListener(new EscuchaTeclado());
    }
    
    /**
     * Metodo para mostrar un mensaje en la pantalla por medio de un JOptionPane
     * @param mensaje el mensaje que se quiere mostrar
     * @param titulo el titulo que tendra el JOptionPane
     */
    private void mostrarMensaje(String mensaje, String titulo){
        JOptionPane.showMessageDialog(this,mensaje,titulo,
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * El metodo determina si un caracter dado es un numero o un punto decimal
     * @param c // el caracter a probar
     * @return <ul>
     *          <li> true si si es un numero o un punto decimal </li>
     *          <li> false si es un caracter distinto a los especificados </li>
     */
    private boolean esNumero(char c){
        return (c>='0' && c<='9') || c == '.';
    }
    
    /**
     * Metodo auxiliar para verificar que lo que teclee el usuario sean simbolos
     * que pueda procesar la calculadora
     * @param c el caracter que se quiere veridicar
     * @return <ul>
     *          <li> true si si lo puede procesar </li>
     *          <li> false si no lo puede procesar </li>
     * </ul>
     */
    private boolean puedeEscribirse(char c){
        return esNumero(c) ||
                (c >= '(' && c<='+') ||
                (c >= '-' && c<='/');
            
    }
    
    /**
     * Metodo para limpiar la expresion y el resultado
     */
    private void clear(){
        expresion.setText("");
        resultado.setText("");
    }
    
    /**
     * Metodo para obtener una subcadena dada la cadena y limites inferior y superior
     * @param str la cadena de la que se quiere obtener la subcadena
     * @param a   la posicion del primer caracter que se quiere en la cadena
     * @param b   la posicion del primer caracter, despues de la posicion de a,
     *            que no se quiere en la subcadena
     * @see substring
     * @return    
     */
    private String substr(String str, int a, int b){
        String sub = "";
        if(b>a)
            sub = str.substring(a, b);
        return sub;
    }
    
    /**
     * Metodo auxiliar para dar funcionalidad a los botones en funcion del simbolo
     * que representen
     * @param c el caracter que recibe del boton
     * @see puedeEscribirse
     */
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
                            expresion.setText(substr(exp,0, i+1)+
                                    "(-"+exp.substring(i+1, k)+")");
                        }
                    }
                    break;
                case '=':
                    try{
                        // Asegurarse que el usuario no de una entrada vacía a
                        // la calculadora
                        if(exp.length()>0)
                            resultado.setText(String.valueOf(
                                    ProcesadorDeExpresiones.procesarExpresion(
                                            exp)));
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
    
    /**
     * Clase para dar funcionalidad a los botones
     */
    class EscuchaBoton implements ActionListener{
        private char comando;
        
        /**
         * Crea un ActionListener para dar funcionalidad al boton
         * @param comando simbolo que representa al boton segun el mapa de botones
         */
        public EscuchaBoton(char comando){
            this.comando=comando;
        }
        
        /**
         * Metodo para que cuando opriman el boton, se ejecute la funcion dado el
         * simbolo que representa
         * @param  
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            procesarBoton(comando);
        }
    };
    
    /**
     * Clase para que pueda utilizar la calculadora con el uso
     */
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
