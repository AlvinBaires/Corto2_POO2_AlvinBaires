/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.AlumnosFacadeLocal;
import com.sv.udb.ejb.UsuariosFacadeLocal;
import com.sv.udb.modelo.Alumnos;
import com.sv.udb.modelo.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author REGISTRO
 */
@Named(value = "usuariosBean")
@ViewScoped
public class UsuariosBean implements Serializable{
    @EJB
    private UsuariosFacadeLocal FCDEUsua;    
    private Usuarios objeUsua;
    private List<Usuarios> listUsua;
    private boolean guardar;
   

    public boolean isGuardar() {
        return guardar;
    }

    public Usuarios getObjeUsua() {
        return objeUsua;
    }

    public void setObjeUsua(Usuarios objeUsua) {
        this.objeUsua = objeUsua;
    }

    public List<Usuarios> getListUsua() {
        return listUsua;
    }

    public void setListUsua(List<Usuarios> listUsua) {
        this.listUsua = listUsua;
    }
        
    public UsuariosBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeUsua = new Usuarios();
        this.guardar = true;        
    }
       
    
    public void consTodo()
    {
        try
        {
            this.listUsua = FCDEUsua.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
}
