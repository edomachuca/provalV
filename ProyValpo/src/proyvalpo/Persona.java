/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyvalpo;

/**
 *
 * @author Mazhuka
 */
public class Persona {
    private String nombre;
    private String RUT;
    private String sexo;
    private String domicilio;
    private String circuns;
    private String mesa;
    
    public Persona(String nombre,String RUT, String sexo,String domicilio, String circuns,String mesa){
        this.nombre=nombre;
        this.RUT=RUT;
        this.sexo=sexo;
        this.domicilio=domicilio;
        this.circuns=circuns;
        this.mesa=mesa;           
    }
    
    public int atts(){
        return 6;
    }
    public String getDomicilio(){
        return domicilio;
    }
    public String getNombre(){
        return nombre;
    }
    public String getRUT(){
        return RUT;
    }
    public String getSexo(){
        return sexo;
    }
    public String getCir(){
        return circuns;
    }
     public String getMesa(){
        return mesa;
    }
}
