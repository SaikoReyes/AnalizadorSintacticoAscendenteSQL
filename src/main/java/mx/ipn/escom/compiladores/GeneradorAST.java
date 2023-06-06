/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.ipn.escom.compiladores;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author saiko
 */
public class GeneradorAST {
 
    private final List<Token> tokens;
    private final Stack<Nodo> pila;
    private final Stack<Nodo> pila2;
    public int conteo;
    int numPalabra=0;
    
    public GeneradorAST(List<Token> tokens){
        this.tokens = tokens;
        this.pila = new Stack<>();
        this.pila2 = new Stack<>();
        Collections.reverse(tokens);
        conteo = 0;
    }
    
    public Arbol generarAST(){
        Stack<Nodo> pilaAux;
        pilaAux = new Stack<>();
        
        for(Token t: tokens){
            
            if(t.tipo == TipoToken.EOF){
                continue;
            }
           
            if(t.tipo == TipoToken.COMA){
                
                //if(pila.peek().getValue().tipo==TipoToken.IDENTIFICADOR){
                    Nodo n = new Nodo(t);
                    n.insertarHijo(pila.pop());
                    pila.push(n);
                    System.out.println("Nodo COMA creado");
                    conteo++;
                //}
                
            }
            if(t.tipo == TipoToken.PUNTO){
                if(pila.peek().getValue().tipo==TipoToken.IDENTIFICADOR){
                    Nodo n = new Nodo(t);
                    n.insertarHijo(pila.pop());
                    pila.push(n);
                    System.out.println("Nodo PUNTO creado");
                    conteo++;
                }
            }
            if(t.tipo == TipoToken.ASTERISCO){
                if(pila.peek().getValue().tipo==TipoToken.FROM){
                    Nodo n = new Nodo(t);
                    n.insertarHijo(pila.pop());
                    pila.push(n);
                    System.out.println("Nodo ASTERISCO creado");
                    conteo++;
                }
            }
             if(t.tipo == TipoToken.IDENTIFICADOR){                              
                if(pila.empty()){
                    Nodo n = new Nodo(t);
                    pila.add(n);
                    System.out.println("Nodo ID TERMINAL creado");
                    conteo++;
                }
                else if(pila.peek().getValue().tipo==TipoToken.FROM||
                        pila.peek().getValue().tipo==TipoToken.COMA||
                        pila.peek().getValue().tipo==TipoToken.PUNTO){
                    Nodo n = new Nodo(t);
                for(int i=1; i<=pila.size();i++){    
                    
                    Nodo nodoAux = pila.pop();
                    n.insertarHijo(nodoAux);
                    System.out.println("Nodo ID creado");
                    conteo++;
                    
                }
                pila.push(n);
                }
                
                
             
            }
             
            
            if(t.tipo == TipoToken.FROM){
                if(pila.peek().getValue().tipo==TipoToken.IDENTIFICADOR){
                    Nodo n = new Nodo(t);
                    n.insertarHijo(pila.pop());
                    pila.push(n);
                    System.out.println("Nodo FROM creado");
                    conteo++;
                }
                
            }
            
            if(t.tipo == TipoToken.DISTINCT){
                if(pila.peek().getValue().tipo==TipoToken.IDENTIFICADOR){
                    Nodo n = new Nodo(t);
                    n.insertarHijo(pila.pop());
                    pila.push(n);
                    System.out.println("Nodo DISTINCT creado");
                    conteo++;
                }
            }
            
             if(t.tipo == TipoToken.SELECT){
                if(pila.peek().getValue().tipo==TipoToken.ASTERISCO||
                   pila.peek().getValue().tipo==TipoToken.DISTINCT||
                   pila.peek().getValue().tipo==TipoToken.IDENTIFICADOR){
                    Nodo n = new Nodo(t);
                for(int i=1; i<=pila.size();i++){    
                    
                    Nodo nodoAux = pila.pop();
                    n.insertarHijo(nodoAux);
                    System.out.println("Nodo SELECT creado");
                    conteo++;
                    
                }
                pila.push(n);
                }                              
                
                }
            
            
        }
        
            Nodo nodoAux = pila.pop();
            
        
        
        
        Arbol programa = new Arbol(nodoAux);
        return programa;
    }
}
