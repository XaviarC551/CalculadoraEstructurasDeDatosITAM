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
public class ProcesadorDeExpresiones {
    
    /**
     * Valida si un caracter es un numero o un punto decimal
     * @param c caracter a validar
     * @return <ul>
     *      <li> true si si es un numero o un punto decimal </li>
     *      <li> false si no lo es </li>
     */
    private static boolean isNum(char c){
        return (c >= '0' && c <= '9') || (c == '.');
    }
    
    /**
     * Regresa el correspondiente contrario a una llave, parentesis o llave
     * @param c el caracter a verificar
     * @return regresa el contrario al caracter dado
     */
    private static char getRep(char c){
        char res='(';
        if(c == ']')
            res='[';
        if(c == '}')
            res='{';
        return res;
    }
    
    /**
     * Compara las jerarquias  de dos operadores
     * @param a primer operador a comparar
     * @param b segundo operador a comparar 
     * @return <ul>
     *         <li> un positivo si la jerarquia de a es mayor </li>
     *         <li> 0 si la jerarquia de ambos es igual </li>
     *         <li> un negativo si la jerarquia de b es mayor </li>
     * </ul>
     */
    private static int compararOperador(char a, char b){
        return getJerarquia(a)-getJerarquia(b);
    }
    
    /**
     * Obtiene la jerarquia de un operador dado
     * @param a el operador del que se quiere obtener la jerarquia
     * @return el valor de la jerarquia del operador
     */
    private static int getJerarquia(char a){
        int res = -1;
        if(a == '^')
            res = 3;
        else if(a == '*' || a == '/')
            res = 2;
        else if(a == '+' || a == '-')
            res = 1;        
        else if(a == ')' || a == '(')
            res = 0;
        return res;
    }
    
    /*private static double procesarPostfija(String postfija){
        
        Pila<Double> valores=new PilaA();
        
        
        int i=0;
        String[] arr=postfija.split(" ");
        
        while(i<arr.length){
            if(isNum(arr[i].charAt(0)))
                valores.push(Double.parseDouble(arr[i]));
            else{
                double m=0.0;
                double b=valores.pop();
                double a;
                
                if(valores.isEmpty())
                    a=0.0;
                else
                    a=valores.pop();
                switch(arr[i].charAt(0)){
                    case '+':
                        m = a + b;
                        break;
                    case '-':
                        m = a - b;
                        break;
                    case '/':
                        m = a / b;
                        break;
                    case '*':
                        m = a * b;
                        break;
                    case '^':
                        m = Math.pow(a, b);
                        break;
                        
                }
                valores.push(m);
            }
        }
        return valores.peek();
    }*/
    
    /**
     * Funcion para procesar una expresion postfija
     * @throws ErrorDeIndefinicionException
     * @param invertida la expresion a procesar
     * @return la expresion ya procesada
     */
    public static double procesarPostfija(Pila<ElementoDeExpresion> invertida){
        
        Pila<ElementoDeExpresion> postfija=invertida;
        
        Pila<Double> valores=new PilaA();
        
        
        while(!postfija.isEmpty()){
            ElementoDeExpresion e = postfija.pop();
            if(e instanceof Numero)
                valores.push(e.getDoubleValue());
            else{
                double m=0.0;
                double b=valores.pop();
                double a;
                if(valores.isEmpty())
                    a=0.0;
                else
                    a=valores.pop();
                switch(e.getCharValue()){
                    case '+':
                        m = a + b;
                        break;
                    case '-':
                        m = a - b;
                        break;
                    case '/':
                        // Invalidar la división entre 0
                        if(b==0)
                            throw new ErrorDeIndefinicionException();
                        m = a / b;
                        break;
                    case '*':
                        m = a * b;
                        break;
                    case '^':
                        m = Math.pow(a, b);
                        break;
                        
                }
                valores.push(m);
            }
        }
        return valores.peek();
    }
    
    /**
     * Funcion que revisa si una expresion es valida
     * @param str la expresion a revisar 
     * @return <ul>
     *         <li> true si la expresion es validada </li>
     *         <li> false si la expresion no es valida </li>
     *    </ul>
     */
    public static boolean revisarExpresion(String str){

        boolean res=true;
        
        // Pila para
        PilaA pilaParentesis=new PilaA<Character>();
        
        // Índice de caracter actual
        int i=0;
        // Variable de caracter actual
        char c='\0';
        // Variable de caracter previo
        char prev='\0';
        
        
        boolean dec=false;
        StringBuilder cad=new StringBuilder();
        
        while(i<str.length()&&res){
            c=str.charAt(i);
            
            // Validaciones de paréntesis
            if(c=='(')
                pilaParentesis.push(c);
            else if(c==')'){
                // Revisar que un paréntesis no esté vacío
                if(getJerarquia(prev) > 0 || prev=='(')
                    res=false;
                // Revisar que no hayan cierres de paréntesis adicionales
                if(pilaParentesis.isEmpty())
                    res=false;
                else
                    pilaParentesis.pop();
            }
            
            // Guardar el número previo en un StringBuilder
            if(isNum(c)){
                cad.append(c);                
            }
            else{
                // Revisar que el número previo sea válido
                if(cad.length()!=0){
                    try{
                        Double.parseDouble(cad.toString());
                    }
                    catch(Exception e){
                        res=false;
                    }
                    cad.delete(0, cad.length());
                }
                
                // Validaciones de símbolos
                if(prev!='\0' && !isNum(prev)){
                    // Si el operador actual no es un + o un -
                    if(getJerarquia(c) != 1){
                        // Revisar que no  hayan operadores antes de un operador
                        // de segundo o tercer nivel jerarquico (e.g: **)
                        if((getJerarquia(c) > 0 && getJerarquia(prev) > 0))
                            res=false;
                    }
                    else if(c=='+'){
                        // Revisar que no hayan operadores adyacentes al de suma
                        // (e.g: *+) ni haya un paréntesis antes del signo
                        // (e.g: (+1))
                        if(getJerarquia(prev) > 0 || prev=='(')
                            res=false;
                    }
                    // Revisar que no hayan repeticiones de operadores de resta
                    // (e.g: --)
                    else if(prev == c){
                        res=false;
                    }
                }
                
            }
            
            i++;
            prev=c;
        }
        System.out.println(i);
        // Revisar el último número si no fue procesado en el ciclo
        if(cad.length()!=0){
            try{
                Double.parseDouble(cad.toString());
            }
            catch(Exception e){
                res=false;
            }
        }
        // Revisar que la pila de paréntesis esté vacía y no haya un operador
        // como último caracter.
        return res && pilaParentesis.isEmpty() &&
                getJerarquia(str.charAt(str.length()-1))<=0;
    }
    
    /**
     * Funcion que convierte una expresion a su notacion postfija
     * @param cad la expresion a convertir
     * @return una pila que tenga la expresion en notacion postfija
     */
    public static  PilaA<ElementoDeExpresion> convertirPostfija(String cad){
        PilaA<ElementoDeExpresion> op, resp;        
        int n, j, i;       
        char a;        
        String num;        
        resp = new PilaA<ElementoDeExpresion>();
        op = new PilaA<ElementoDeExpresion>();                
        cad = cad.replace("-(", "-1*(");
        cad = cad.replace(")(", ")*(");
        cad = cad.replace("0(", "0*(");
        cad = cad.replace("1(", "1*(");
        cad = cad.replace("2(", "2*(");
        cad = cad.replace("3(", "3*(");
        cad = cad.replace("4(", "4*(");
        cad = cad.replace("5(", "5*(");
        cad = cad.replace("6(", "6*(");
        cad = cad.replace("7(", "7*(");
        cad = cad.replace("8(", "8*(");
        cad = cad.replace("9(", "9*(");
        n = cad.length();                       
        i=0;
        if(cad.charAt(0) == '-'){ /// Esto es para el caso especial -555....
                num = obtenerNum(cad,1,n);
                resp.push(new Numero(-Double.parseDouble(num)));
                i = num.length() + 1;
        }

        while( i < n){
            a = cad.charAt(i);            
            if(a == '('){
                op.push(new Operador(a));                
            }else if(isNum(a)){                                           
                num = obtenerNum(cad,i,n);
                resp.push(new Numero(Double.parseDouble(num)));
                i = i + num.length()-1;
            }else if(a == ')'){
                while(op.peek().getCharValue() != '('){
                    resp.push(new Operador(op.pop().getCharValue()));
                }                
                op.pop();
            }else{
                if(a == '-' && (getJerarquia(cad.charAt(i-1))>0 || cad.charAt(i-1) == '(')){ // Caso 2*(-2)
                    num = obtenerNum(cad,i+1,n);
                    resp.push(new Numero(-Double.parseDouble(num)));
                    i = i + num.length();                                        
                }
                else if(op.isEmpty())
                    op.push(new Operador(a));
                else if( getJerarquia(a) > getJerarquia(op.peek().getCharValue()))
                        op.push(new Operador(a));
                else{
                    while(!op.isEmpty() && getJerarquia(a) <= getJerarquia(op.peek().getCharValue()) && op.peek().getCharValue() != '('){
                        resp.push(op.pop());
                    }
                    op.push(new Operador(a));
                }
            }
            i++;
        }        
        if(!op.isEmpty()){
            while(!op.isEmpty())
                resp.push(op.pop());
        }
        
            while(!resp.isEmpty())
                op.push(resp.pop());        
        return op;
    }
    
    /**
     * Obtiene un numero de una cadena
     * @param a la cadena de la que se quiere obtener
     * @param j la posicion desde la cual se va a obtener el numero
     * @param n la primera posicion de la que ya no se quiere obtener el numero
     * @return el numero obtenido
     */
    private static String obtenerNum(String a, int j, int n){
        String resp;
        resp = "";
        while(j < n && (isNum(a.charAt(j)) || a.charAt(j) == '.')){
                    resp+=a.charAt(j);
                    j++;                    
        }
        return resp;
                
    }
    
    /**
     * Funcion que procesa la expresion
     * @throws ErrorDeSintaxisException
     * @param expresion la expresion a procesar
     * @return el numero resultado
     */
    public static double procesarExpresion(String expresion){
        if(!revisarExpresion(expresion))
            throw new ErrorDeSintaxisException();
        return procesarPostfija(convertirPostfija(expresion));
        //return 0.0;
    }
    
}
