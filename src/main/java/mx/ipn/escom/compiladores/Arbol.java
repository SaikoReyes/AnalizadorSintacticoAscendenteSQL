/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.ipn.escom.compiladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import static mx.ipn.escom.compiladores.TipoToken.ASTERISCO;
import static mx.ipn.escom.compiladores.TipoToken.DISTINCT;
import static mx.ipn.escom.compiladores.TipoToken.IDENTIFICADOR;

/**
 *
 * @author saiko
 */
public class Arbol {
        private final List<Nodo> raiz;
        public String str = "";
        public boolean xd = true;
        Stack<Nodo> pila = new Stack();        
        Nodo nuevaRaiz = null;
        
        
        public Arbol(Nodo raiz){
            this.raiz = new ArrayList<>();
            this.raiz.add(raiz);
        }
        
        public Arbol(List<Nodo> raiz){
            this.raiz = raiz;
        }
        Nodo nodoActual;
        public void recorrer(int c){
            nodoActual = raiz.get(0);
            while(!pila.isEmpty() || nodoActual!=null){
               if(nodoActual!=null){
                   pila.push(nodoActual);
                   System.out.println(nodoActual.getValue().lexema);
                   if(nodoActual.getHijos()==null){
                       break;
                   }                   
                   nodoActual = nodoActual.getHijos().get(0);
               }
                
            }
           
            int posicion=pila.size()-1;
            TipoToken tt;
            nodoActual=pila.pop();
            Token t;
            tt = TipoToken.IDENTIFICADOR;
            if(nodoActual.getValue().tipo == TipoToken.IDENTIFICADOR){
                System.out.println("-ID");
                tt = TipoToken.IDENTIFICADOR;
                nodoActual = nuevaRaiz;
                for(int i = 0; i<=c; i++){
                 if(!pila.isEmpty()){
                     nodoActual = pila.pop();
                 }
                 
                 
                 
                 t = nodoActual.getValue();
                 switch(t.tipo){
                     case IDENTIFICADOR:
                         if((tt == TipoToken.COMA||tt==TipoToken.PUNTO)&&pila.peek().getValue().tipo==TipoToken.FROM){
                             System.out.println("-IDENTIFICADOR");
                             tt=TipoToken.IDENTIFICADOR;
                             posicion--;
                             break;
                         }
                         if((tt == TipoToken.COMA)&&pila.peek().getValue().tipo==TipoToken.DISTINCT){
                             System.out.println("-IDENTIFICADOR");
                             tt=TipoToken.IDENTIFICADOR;
                             posicion--;
                             break;
                         }
                         if((tt == TipoToken.FROM)&&pila.peek().getValue().tipo==TipoToken.COMA){
                             System.out.println("-IDENTIFICADOR");
                             tt=TipoToken.IDENTIFICADOR;
                             posicion--;
                             break;
                         }
                         if((tt == TipoToken.COMA||tt==TipoToken.FROM)&&pila.peek().getValue().tipo==TipoToken.PUNTO){
                             System.out.println("-IDENTIFICADOR");
                             tt=TipoToken.IDENTIFICADOR;
                             posicion--;
                             break;
                         }
                         if((tt == TipoToken.COMA||tt==TipoToken.PUNTO)&&pila.peek().getValue().tipo==TipoToken.SELECT){
                             System.out.println("-IDENTIFICADOR");
                             tt=TipoToken.IDENTIFICADOR;
                             posicion--;
                             break;
                         }
                         if((tt == TipoToken.COMA||tt==TipoToken.PUNTO)&&pila.peek().getValue().tipo==TipoToken.COMA){
                             System.out.println("-IDENTIFICADOR");
                             tt=TipoToken.IDENTIFICADOR;
                             posicion--;
                             break;
                         }
                         else{
                             System.out.println("Error en la posición: "+posicion);
                             System.out.println("No corresponde a una sentencia SQL valida");
                             i=c+1;
                             break;
                         }
                     case ASTERISCO:
                         if((tt == TipoToken.FROM)&&pila.peek().getValue().tipo==TipoToken.SELECT){
                             System.out.println("-ASTERISCO");
                             tt=TipoToken.ASTERISCO;
                             posicion--;
                             break;
                         }
                         else{
                            System.out.println("No corresponde a una sentencia SQL valida");
                            System.out.println("Error en la posición: "+posicion);
                            i = c+1;
                            break;
                         }
                         
                     case DISTINCT:
                         if((tt == TipoToken.IDENTIFICADOR)&&pila.peek().getValue().tipo==TipoToken.SELECT){
                             System.out.println("-DISTINCT");
                             tt=TipoToken.DISTINCT;
                             posicion--;
                             break;
                         }
                         else{
                            System.out.println("No corresponde a una sentencia SQL valida");
                            System.out.println("Error en la posición: "+posicion);
                            i = c+1;
                            break;
                         }
                    case FROM:
                        int pos = posicion-1;
                         if((tt == TipoToken.IDENTIFICADOR)&&pila.peek().getValue().tipo==TipoToken.ASTERISCO){
                             System.out.println("-FROM");
                             tt=TipoToken.FROM;
                             posicion--;
                             break;
                         }
                         if((tt == TipoToken.IDENTIFICADOR)&&pila.peek().getValue().tipo==TipoToken.IDENTIFICADOR){
                             System.out.println("-FROM");
                             tt=TipoToken.FROM;
                             posicion--;
                             break;
                         }
                         
                         else{
                            System.out.println("No corresponde a una sentencia SQL valida");
                            System.out.println("Error en la posición: "+pos);
                            i = c+1;
                            break;
                         }
                    case PUNTO:
                         if((tt == TipoToken.IDENTIFICADOR)&&pila.peek().getValue().tipo==TipoToken.IDENTIFICADOR){
                             System.out.println("-PUNTO");
                             tt=TipoToken.PUNTO;
                             posicion--;
                             break;
                         }
                         else{
                            System.out.println("No corresponde a una sentencia SQL valida");
                            System.out.println("Error en la posición: "+posicion);
                            i = c+1;
                            break;
                         }
                    case COMA:
                         if((tt == TipoToken.IDENTIFICADOR)&&pila.peek().getValue().tipo==TipoToken.IDENTIFICADOR){
                             System.out.println("-COMA");
                             tt=TipoToken.COMA;
                             posicion--;
                             break;
                         }
                         else{
                            System.out.println("No corresponde a una sentencia SQL valida");
                            System.out.println("Error en la posición: "+posicion);
                            i = c+1;
                            break;
                         }
                    case SELECT:
                         if(tt == TipoToken.IDENTIFICADOR||tt == TipoToken.ASTERISCO||tt==TipoToken.DISTINCT){
                             System.out.println("-SELECT");
                             tt=TipoToken.SELECT;
                             posicion--;
                             System.out.println("\nSENTENCIA SQL ACEPTADA");
                             break;
                         }
                         else{
                            i = c+1;                            
                            break;
                         }
                 }
                }
            }
            
            
    }
        
        
    
}
