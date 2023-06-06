/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.ipn.escom.compiladores;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saiko
 */
public class Nodo {
    private final Token value;
    private List<Nodo> hijos;

    public Nodo(Token value){
        this.value = value;
    }

    public void insertarHijo(Nodo n){
        if(hijos == null){
            hijos = new ArrayList<>();
            hijos.add(n);
        }
        else{
            hijos.add(0, n);
        }
    }

    public Token getValue(){
        return value;
    }

    public List<Nodo> getHijos(){
        return hijos;
    }
}
