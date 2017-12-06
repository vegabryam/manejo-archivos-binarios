/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import modelo.Auto;
import modelo.Persona;

/**
 *
 * @author Estudiante
 */
public class GestionDato {
    
     private List<Persona> personaList;
     private File datosPersona;
     private List<Auto> autoList;
     private File datosAuto;

    public GestionDato(List<Persona> personaList, File datosPersona, List<Auto> autoList, File datosAuto) {
        this.personaList = personaList;
        this.datosPersona = datosPersona;
        this.autoList = autoList;
        this.datosAuto = datosAuto;
    }

    

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    public File getDatosPersona() {
        return datosPersona;
    }

    public void setDatosPersona(File datosPersona) {
        this.datosPersona = datosPersona;
    }
    
    public boolean addPersona(Persona p){
        return this.personaList.add(p);
    }
    public boolean addAuto(Auto a){
        return this.autoList.add(a);
    }
     
    public boolean escribirPersona() throws FileNotFoundException, IOException{
        
          FileOutputStream fichero = new FileOutputStream(datosPersona.getAbsolutePath(),true);
          DataOutputStream objetoSalida = new DataOutputStream(fichero);
          
          for(Persona p:this.personaList){
              objetoSalida.writeUTF(p.getNombre());
              objetoSalida.writeUTF(p.getApellido());
              objetoSalida.writeInt(p.getEdad());
              objetoSalida.writeInt(p.getCodigo());
          }
          objetoSalida.close();
          return true;
          
    }
    
    public boolean escribirAuto() throws FileNotFoundException, IOException{
        
          FileOutputStream fichero = new FileOutputStream(datosAuto.getAbsolutePath(),true);
          DataOutputStream objetoSalida = new DataOutputStream(fichero);
          
          for(Auto a:this.autoList){
              objetoSalida.writeUTF(a.getPlaca());
              objetoSalida.writeUTF(a.getMarca());
              objetoSalida.writeInt(a.getPersona().getCodigo());
          }
          objetoSalida.close();
          return true;
          
    }
    
    public List<Persona> leerPersona() throws FileNotFoundException, IOException{
        List<Persona> personaList=new ArrayList<Persona>();
        try{
        FileInputStream fichero = new FileInputStream(datosPersona.getAbsolutePath());
        DataInputStream objetoSalida = new DataInputStream(fichero);
        String dato;
        while(true){
            String nombre=objetoSalida.readUTF();
            String apellido=objetoSalida.readUTF();
            int edad=objetoSalida.readInt();
            int codigo=objetoSalida.readInt();
            Persona p=new Persona(nombre,apellido,edad,codigo);
            personaList.add(p);
        }
        }
        catch(Exception ex){  
        }
       return personaList;      
    }
    
    public List<Auto> leerAuto() throws FileNotFoundException, IOException{
        List<Auto> autoList=new ArrayList<Auto>();
        try{
        FileInputStream fichero = new FileInputStream(datosAuto.getAbsolutePath());
        DataInputStream objetoSalida = new DataInputStream(fichero);
        String dato;
        while(true){
            String placa=objetoSalida.readUTF();
            String marca=objetoSalida.readUTF();
            int codigo=objetoSalida.readInt();
            Auto a=new Auto(placa,marca,this.buscarPersona(codigo));
            autoList.add(a);
        }
        }
        catch(Exception ex){  
        }
       return autoList;      
    }
    
    public Persona buscarPersona(int codigo){
        for(Persona p:this.personaList){
            if(p.getCodigo()==codigo){
                return p;
            }
        }
    return null;
    }
     
}
