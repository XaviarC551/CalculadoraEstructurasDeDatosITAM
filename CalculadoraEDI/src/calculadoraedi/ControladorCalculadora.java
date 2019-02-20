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
public class ControladorCalculadora extends VistaCalculadora {/*
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
        switch(c){
            case '0': 
                if(posUltimoPunto > posUltimoOperador)
                    exp.setText(exp.getText() + '0');
                else if(posUltimoParentesis > posUltimoOperador){
                    try{ //en el caso de que el parentesis sea lo ultimo que escribio
                    aux = exp.getText().charAt(posUltimoParentesis+1);
                    }catch(Exception e){
                        aux = 1;
                    }
                    if(aux != 0)
                        exp.setText(exp.getText() + '0');
                }else if(posUltimoOperador > -1){
                    try{ //en el caso de que el operador sea lo ultimo que escribio
                    aux = exp.getText().charAt(posUltimoOperador+1);
                    }catch(Exception e){
                        aux = 1;
                    }
                    if(aux != 0)
                        exp.setText(exp.getText() + '0');
                }
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
                exp.setText(exp.getText() + c);  
                break;
            case '(':
                aux = exp.getText().charAt(exp.getText().length()-1);
                if(aux == '.')
                    exp.setText(exp.getText()+"0*(");
                else if(aux>47 && aux<58)
                    exp.setText(exp.getText()+"*(");
                else
                    exp.setText(exp.getText()+'(');
                break;
            case ')':
                aux = exp.getText().charAt(exp.getText().length()-1);
                if(aux == '.')
                    exp.setText(exp.getText()+"0)");
                else if (aux>47 && aux<58)
                    exp.setText(exp.getText()+')');
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
*/
}
