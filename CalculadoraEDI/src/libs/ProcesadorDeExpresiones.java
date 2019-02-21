private  PilaA<ElementoDeExpresion> convertirPostfija(String cad){
        PilaA<ElementoDeExpresion> op, resp;        
        int n, j, i;       
        char a;        
        String num;        
        n = cad.length();                       
        resp = new PilaA<ElementoDeExpresion>();
        op = new PilaA<ElementoDeExpresion>();                
        cad = cad.replace("-(", "-1*(");    
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
            }else if(esNumero(a)){                                           
                num = obtenerNum(cad,i,n);
                resp.push(new Numero(Double.parseDouble(num)));
                i = i + num.length()-1;
            }else if(a == ')'){
                while(op.peek().getCharValue() != '('){
                    resp.push(new Operador(op.pop().getCharValue()));  /// Error aqui 
                }
                op.pop();
            }else{
                if(a == '-' && cad.charAt(i-1) == '('){ // Caso 2*(-2)
                    num = obtenerNum(cad,i,n);
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
        
            /*while(!resp.isEmpty())
                op.push(resp.pop());        */
        return resp;
    }   
    public boolean esNumero(Character a){
        if((int)a >= 48 && (int)a <= 57)
            return true;
        return false;
    }
    
    public String obtenerNum(String a, int j, int n){
        String resp;
        resp = "";
        while(j < n && (esNumero(a.charAt(j)) || a.charAt(j) == '.')){
                    resp+=a.charAt(j);
                    j++;                    
        }
        return resp;
                
    }
