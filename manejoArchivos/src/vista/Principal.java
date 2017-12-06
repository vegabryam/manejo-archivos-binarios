/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.GestionDato;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.Auto;
import modelo.Persona;

/**
 *
 * @author Estudiante
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        File archivo=new File("c:/datos");
        archivo.mkdir();
        
        File archivoLista=new File("c:/datos/ListaPersona.dat");
        archivoLista.createNewFile();
        
        File archivoLista1=new File("c:/datos/ListaAuto.dat");
        archivoLista1.createNewFile();
        
        Persona p=new Persona("Carlos","Perez",18,1);
        Persona p1=new Persona("Juan","Vega",38,2);
        
        Auto a=new Auto("A-25","NISSAN",p);
        Auto a1=new Auto("b-26","NISSAN",p1);
        
        List<Persona> personaList=new ArrayList<Persona>();
        List<Auto> autoList=new ArrayList<Auto>();
        
        GestionDato gD=new GestionDato(personaList,archivoLista,autoList,archivoLista1);
        
        gD.addPersona(p);
        gD.addPersona(p1);
        gD.addAuto(a);
        gD.addAuto(a1);
        
        gD.escribirPersona();
        gD.escribirAuto();
        
        List<Persona> lr=gD.leerPersona();
       
        for(int i=0; i<lr.size();i++)
        {
            System.out.println(lr.get(i).getCodigo()+" "+lr.get(i).getNombre());
        }
        
         List<Auto> la=gD.leerAuto();
         
         System.out.println(la.size());
       
        for(int i=0; i<la.size();i++)
        {
            System.out.println(la.get(i).getPlaca() + " " + la.get(i).getMarca());
        }
    }
    
}
