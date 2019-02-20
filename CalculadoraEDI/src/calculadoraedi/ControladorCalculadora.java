/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoraedi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import libs.ProcesadorDeExpresiones;
/**
 *
 * @author CD
 */
public class ControladorCalculadora extends VistaCalculadora {
    //ProcesadorDeExpresiones procesador;
    boolean ultimoEsOperador;
    int posUltimoOperador;
    int posUltimoPunto;
    int posUltimoParentesis;
    
    public ControladorCalculadora(){
        super();
        //procesador = new ProcesadorDeExpresiones();
        ultimoEsOperador = false;
        posUltimoOperador = -1;
        posUltimoPunto = -1;
        posUltimoParentesis = -1;
        for(int i = 0; i<totalBotones; i++)
            botones[i].addActionListener(new EscuchaBoton(botones[i].getText().charAt(0)));
    }
    
    public void procesarBoton(char c){
        char aux;
        int tamanio;
        switch(c){
            case '0': 
                if(posUltimoPunto > posUltimoOperador)
                    exp.setText(exp.getText() + '0');
                else if(posUltimoParentesis > posUltimoOperador){
                    try{ //en el caso de que el parentesis sea lo ultimo que escribio
                    aux = exp.getText().charAt(posUltimoParentesis+1);
                    }catch(Exception e){
                        aux = '1';
                    }
                    if(aux != '0')
                        exp.setText(exp.getText() + '0');
                }else if(posUltimoOperador > -1){
                    try{ //en el caso de que el operador sea lo ultimo que escribio
                    aux = exp.getText().charAt(posUltimoOperador+1);
                    }catch(Exception e){
                        aux = '1';
                    }
                    if(aux != '0')
                        exp.setText(exp.getText() + '0');
                }else if(exp.getText().equals("") || exp.getText().charAt(0) != '0')
                    exp.setText(exp.getText() + '0');
                break;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                try{
                    aux =  exp.getText().charAt( Math.max(posUltimoOperador, posUltimoParentesis)+1);
                } catch(Exception e){
                    aux = '1';
                }
                if( aux == '0' && posUltimoPunto > posUltimoOperador || 
                    aux != '0')
                    exp.setText(exp.getText() + c);  
                break;
            case '(':
                tamanio = exp.getText().length();
                try{ // en caso de que sea lo primero que escriba
                    aux = exp.getText().charAt(tamanio-1);
                }catch(Exception e){
                    aux = '+'; // para que funcione con la parte siguiente
                }
                if(aux == '.'){
                    exp.setText(exp.getText()+"0*(");
                    posUltimoOperador = tamanio + 1;
                    posUltimoParentesis = tamanio + 2;
                }
                else if(aux == ')' || (aux>47 && aux<58) ){
                    exp.setText(exp.getText()+"*(");
                    posUltimoOperador = tamanio;
                    posUltimoParentesis = tamanio + 1;
                }
                else{
                    exp.setText(exp.getText()+'(');
                    posUltimoParentesis = tamanio;
                }
                break;
            case ')':
                tamanio = exp.getText().length();
                try{ // en caso de que sea lo primero que escriba
                    aux = exp.getText().charAt(tamanio-1);
                }catch(Exception e){
                    aux = '+'; // para que funcione con la parte siguiente
                }
                if(aux == '.'){
                    exp.setText(exp.getText()+"0)");
                    posUltimoParentesis = tamanio + 1;
                }
                else if (aux>47 && aux<58 || aux == ')'){
                    exp.setText(exp.getText()+')');
                    posUltimoParentesis = tamanio;
                }
                break;
            default:
                break;
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
                new NotaVista().setVisible(true);
            }
        });
    }
    
}
