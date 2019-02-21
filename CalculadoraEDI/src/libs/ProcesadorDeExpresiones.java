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
     * @param invertida la expresion a procesar
     * @return la expresion ya procesada
     */
    private static double procesarPostfija(Pila<ElementoDeExpresion> invertida){
        
        Pila<ElementoDeExpresion> postfija=invertida;
        
        /*
        while(!invertida.isEmpty())
            postfija.push(invertida.pop());*/
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
    private static boolean revisarExpresion(String str){
        boolean res=true;
        PilaA pila=new PilaA<Character>();
        int i=0;
        char c='\0';
        char prev='\0';
        
        boolean dec=false;
        StringBuilder cad=new StringBuilder();
        
        while(i<str.length()&&res){
            c=str.charAt(i);
            if(c=='('||c=='{'||c=='[')
                pila.push(c);
            else if(c==')'||c=='}'||c==']'){
                
                if(getJerarquia(prev) > 0 || prev=='(')
                    res=false;
                
                if(pila.isEmpty())
                    res=false;
                else{
                    if(!pila.peek().equals(getRep(c)))
                        res=false;
                    pila.pop();
                }
            }
            
            // Validar puntos decimales
            if(isNum(c)){
                cad.append(c);
                /*if(c == '.'){
                    if(dec)
                        res=false;
                    else
                        dec=!dec;
                }*/
            }
            else{
                if(cad.length()!=0){
                    try{
                        Double.parseDouble(cad.toString());
                    }
                    catch(Exception e){
                        res=false;
                    }
                    cad.delete(0, cad.length());
                }
                //dec=false;
                if(prev!='\0' && !isNum(prev)){
                    if(c != '-' && c != '+'){
                        if((getJerarquia(c) > 0 && getJerarquia(prev) > 0))
                            res=false;
                    }
                    else if(c=='+'){
                        if((getJerarquia(prev) > 0) || prev=='(')
                            res=false;
                    }
                    else if(getJerarquia(c) > 0 && getJerarquia(prev) > 0 && prev == c){
                        res=false;
                    }
                }
                
            }
            
            i++;
            prev=c;
        }
        return res && pila.isEmpty() && getJerarquia(str.charAt(str.length()-1))<=0;
    }
    
    
    
    /*private static  PilaA<ElementoDeExpresion> convertirPostfija(String cad){
        PilaA<ElementoDeExpresion> op, resp;
        int n, j;       
        char a;        
        String num;
        n = cad.length();                       
        resp = new PilaA<ElementoDeExpresion>();
        op = new PilaA<ElementoDeExpresion>();     
        
        boolean negative=false;
        for(int i = 0; i < n; i++){
            a = cad.charAt(i);            
            if(a == '('  || (op.isEmpty() && !isNum(a))){
                //(!isNum(cad.charAt(i-1)) && cad.charAt(i-1)!=')')
                    op.push(new Operador(a));
            }else if(isNum(a)){
                j = i;
                num = "";
                while(j < n && (isNum(cad.charAt(j)) || cad.charAt(j) == '.')){
                    num+=cad.charAt(j);
                    j++;                 
                }
                
                /*if(i-2>=0){
                    if(cad.charAt(i-1)=='-' && !isNum(cad.charAt(i-2)))
                        resp.push(new Numero(-Double.parseDouble(num)));
                    else
                        resp.push(new Numero(Double.parseDouble(num)));
                }
                else{
                    if(i-1>=0 && cad.charAt(i-1) == '-')
                        resp.push(new Numero(-Double.parseDouble(num)));
                    else*/
        /*            resp.push(new Numero(Double.parseDouble(num)));
                i = j-1;
                negative=false;
            }else if(a == ')'){
                while(op.peek().getCharValue() != '('){
                    resp.push(op.pop());
                }
                op.pop();
            }else{
                
                    if( getJerarquia(a) > getJerarquia(op.peek().getCharValue()))
                        op.push(new Operador(a));
                    else{
                        while(!op.isEmpty() && getJerarquia(a) <= getJerarquia(op.peek().getCharValue())){
                            resp.push(op.pop());
                        }
                        op.push(new Operador(a));
                    }
                
                    
            }
        }        
        if(!op.isEmpty()){
            while(!op.isEmpty())
                resp.push(op.pop());
        }
        return resp;
    }*/
    
    /**
     * Funcion que convierte una expresion a su notacion postfija
     * @param cad la expresion a convertir
     * @return una pila que tenga la expresion en notacion postfija
     */
    private static  PilaA<ElementoDeExpresion> convertirPostfija(String cad){
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
    public static String obtenerNum(String a, int j, int n){
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
     * @param expresion la expresion a procesar
     * @return el numero resultado
     */
    public static double procesarExpresion(String expresion){
        if(!revisarExpresion(expresion))
            throw new ErrorDeSintaxisException();
        return procesarPostfija(convertirPostfija(expresion));
        //return 0.0;
    }
    
    public static void main(String[] args){
        ProcesadorDeExpresiones proc=new ProcesadorDeExpresiones();
        Pila<ElementoDeExpresion> p=proc.convertirPostfija("5/0.55/-0.5");
        //Pila<ElementoDeExpresion> p=new PilaA<ElementoDeExpresion>();
        // 4 5 2 4 ^ 3 - 2 1 1 * + ^ * + 3 -
        /*p.push(new Numero(4));
        p.push(new Numero(5));
        p.push(new Numero(2));
        p.push(new Numero(4));
        p.push(new Operador('^'));
        p.push(new Numero(3));
        p.push(new Operador('-'));
        p.push(new Numero(2));
        p.push(new Numero(1));
        p.push(new Numero(1));
        p.push(new Operador('*'));
        p.push(new Operador('+'));
        p.push(new Operador('^'));
        p.push(new Operador('*'));
        p.push(new Operador('+'));
        p.push(new Numero(3));
        
        
        p.push(new Operador('-'));
        */
        /*
        p.push(new Numero(6));
        p.push(new Numero(2));
        p.push(new Operador('-'));
        p.push(new Numero(5));
        p.push(new Numero(4));
        p.push(new Operador('+'));
        p.push(new Operador('*'));*//*
        */
        while(!p.isEmpty())
            System.out.println(p.pop());
        
        //System.out.println(proc.procesarPostfija("4 5 2 4 ^ 3 - 2 1 1 * + ^ * + 3 -"));
        //System.out.println(proc.revisarExpresion("-(90+2.0*-((-2))*3)"));*/
        //Double.parseDouble("(.");
    }
    
}
