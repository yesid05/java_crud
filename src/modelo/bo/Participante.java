/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.bo;

import java.sql.Date;



/**
 *
 * @author YCA
 */
public class Participante {
    
    private String alias;
    
    private String nombre;
    
    private String apellido;
    
    private String email;
    
    private String celular;
    
    private String contrasena;
    
    private Date fehcaNacimiento;

    public Participante() {
    }

    public Participante(String alias, String nombre, String apellido, String email, String celular, String contrasena, Date fehcaNacimiento) {
        this.alias = alias;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.celular = celular;
        this.contrasena = contrasena;
        this.fehcaNacimiento = fehcaNacimiento;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFehcaNacimiento() {
        return fehcaNacimiento;
    }

    public void setFehcaNacimiento(Date fehcaNacimiento) {
        this.fehcaNacimiento = fehcaNacimiento;
    }
    
    public String[] toArray(){
        return new String[]{alias,nombre,apellido,email,celular,contrasena,fehcaNacimiento.toString()};
    }
    
    @Override
    public String toString() {
        return "Participante{" + "alias=" + alias + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", celular=" + celular + ", contrasena=" + contrasena + ", fehcaNacimiento=" + fehcaNacimiento + '}';
    }
    
    
    
}
