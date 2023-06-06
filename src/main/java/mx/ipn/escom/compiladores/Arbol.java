/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.ipn.escom.compiladores;

import java.util.ArrayList;
import java.util.List;
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
        public Arbol(Nodo raiz){
            this.raiz = new ArrayList<>();
            this.raiz.add(raiz);
        }
        
        public Arbol(List<Nodo> raiz){
            this.raiz = raiz;
        }
        public void recorrer(int c){
            Token t = raiz.get(0).getValue();
            TipoToken tt;
            
            if(t.tipo == TipoToken.SELECT){
                System.out.println("-SELECT");
                tt = TipoToken.SELECT;
                List<Nodo> n = raiz;
                
                for(int i = 0; i<=c;i++){
                    int x = i+3;
                    if(n.get(0).getHijos()!=null){
                       n = n.get(0).getHijos();
                    }
                        t = n.get(0).getValue();
                        
                        switch(t.tipo){
                            
                            case ASTERISCO:
                                
                                    if(n.get(0).getHijos().get(0).getValue().tipo == TipoToken.FROM&&tt == TipoToken.SELECT){
                                            System.out.println("- Asterisco");
                                            tt = TipoToken.ASTERISCO;
                                            
                                            break;
                                    }else{
                                        System.out.println("No corresponde a una sentencia SQL valida");
                                        System.out.println("Error en la posición: "+x);
                                        i = c+1;
                                        break;
                                    }
                                
                            case DISTINCT:
                                
                                    if(n.get(0).getHijos().get(0).getValue().tipo == TipoToken.IDENTIFICADOR&&tt == TipoToken.SELECT){
                                        
                                        System.out.println("-DISTINCT");
                                        tt = TipoToken.DISTINCT;
                                        break;
                                    }
                                    else{
                                        System.out.println("No corresponde a una sentencia SQL valida");
                                        System.out.println("Error en la posición: "+x);
                                        i=c+1;
                                        break;
                                    }
                                
                                
                            case IDENTIFICADOR:
                                
                                
                                    if(n.get(0).getHijos()!=null){
                                    if(n.get(0).getHijos().get(0).getValue().tipo == TipoToken.COMA&&(tt == TipoToken.SELECT||tt == TipoToken.FROM||tt==TipoToken.PUNTO||tt==TipoToken.DISTINCT||tt==TipoToken.COMA)){                                        
                                        System.out.println("- ID");
                                        tt=TipoToken.IDENTIFICADOR;
                                        break;
                                    }
                                    if(n.get(0).getHijos().get(0).getValue().tipo == TipoToken.PUNTO&&(tt == TipoToken.SELECT||tt == TipoToken.FROM||tt==TipoToken.COMA||tt == TipoToken.PUNTO||tt==TipoToken.DISTINCT)){
                                        tt=TipoToken.IDENTIFICADOR;
                                        System.out.println("-ID");
                                        break;
                                    }
                                    if(n.get(0).getHijos().get(0).getValue().tipo == TipoToken.FROM&&(tt == TipoToken.COMA||tt==TipoToken.PUNTO)&&xd==true){
                                        tt=TipoToken.IDENTIFICADOR;
                                        System.out.println("-ID");
                                        break;
                                    }
                                    else{
                                        System.out.println("Error en la posición: "+x);
                                        System.out.println("No corresponde a una sentencia SQL valida");
                                        i=c+1;
                                        break;
                                    }
                                }
                                    else{
                                        System.out.println("-ID");
                                        System.out.println("Fin del analisis");
                                        System.out.println("Sentencia correcta");
                                        i=c+1;
                                        break;
                                    }
                                    
                                
                                
                                
                            case FROM:
                                
                                if(n.listIterator(0).hasNext()&&n.get(0).getHijos()!=null){
                                    if(n.get(0).getHijos().get(0).getValue().tipo == TipoToken.IDENTIFICADOR&&tt == TipoToken.ASTERISCO){
                                        xd = false;
                                        tt=TipoToken.FROM;
                                        System.out.println("-FROM");
                                        
                                        
                                        break;
                                    }
                                    if(n.get(0).getHijos().get(0).getValue().tipo == TipoToken.IDENTIFICADOR&&tt==TipoToken.IDENTIFICADOR&&xd==true){
                                        xd = false;
                                        tt=TipoToken.FROM;
                                        System.out.println("-FROM");
                            
                                        break;
                                    

                                    }
                                else{
                                         System.out.println("No corresponde a una sentencia SQL valida");
                                    
                                        System.out.println("Error en la posición: "+ x);
                                    i=c+1;
                                   
                                    break;
                                
                                }
                                }
                                
                            case COMA:
                                
                                //System.out.println("Soy una coma");
                                    if(n.get(0).getHijos().get(0).getValue().tipo == TipoToken.IDENTIFICADOR&&tt==TipoToken.IDENTIFICADOR){
                                        
                                        System.out.println("-COMA");
                                        tt=TipoToken.COMA;
                                        break;
                                        
                                       
                                        
                                       
                                    }else{
                                        System.out.println("No corresponde a una sentencia SQL valida");
                                        int z = x-1;
                                        System.out.println("Error en la posición: "+z);
                                        i=c+1;
                                        break;
                                        
                                    }
                                
                            case PUNTO:
                                
                                    
                                    if(n.get(0).getHijos().get(0).getValue().tipo == TipoToken.IDENTIFICADOR&&tt==TipoToken.IDENTIFICADOR){
                                        
                                        System.out.println("-Punto");
                                        tt=TipoToken.PUNTO;
                                        break;
                                    }else{
                                        System.out.println("No corresponde a una sentencia SQL valida");
                                        System.out.println("Error en la posición: "+x);
                                        i=c+1;
                                        break;
                                    }
                                
                            

                            default:
                                System.out.println("No corresponde a una sentencia SQL valida");
                                System.out.println("Error en la posición: "+ x);
                                i=c+1;
                                break;
                                
                        }
                                                         
                }
            }
            else{
                System.out.println("No corresponde a una sentencia SQL valida");
                System.out.println("Error en la posicion: 1");
            }
        
    }
        
        
    
}
