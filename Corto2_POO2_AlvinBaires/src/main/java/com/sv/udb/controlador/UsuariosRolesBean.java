/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.AlumnosFacadeLocal;
import com.sv.udb.ejb.UsuariosRolesFacade;
import com.sv.udb.ejb.UsuariosRolesFacadeLocal;
import com.sv.udb.modelo.Alumnos;
import com.sv.udb.modelo.UsuariosRoles;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Laboratorio
 */
@Named(value = "usuariosRolesBean")
@ViewScoped
public class UsuariosRolesBean implements Serializable{
    @EJB
    private UsuariosRolesFacadeLocal FCDEUsuaRole;    
    private UsuariosRoles objeUsuaRole;
    private List<UsuariosRoles> listUsuaRole;
    private boolean guardar;
   
    
    public boolean isGuardar() {
        return guardar;
    }

    public UsuariosRoles getObjeUsuaRole() {
        return objeUsuaRole;
    }

    public void setObjeUsuaRole(UsuariosRoles objeUsuaRole) {
        this.objeUsuaRole = objeUsuaRole;
    }

    public List<UsuariosRoles> getListUsuaRole() {
        return listUsuaRole;
    }

    public void setListUsuaRole(List<UsuariosRoles> listUsuaRole) {
        this.listUsuaRole = listUsuaRole;
    }

    
    public UsuariosRolesBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeUsuaRole = new UsuariosRoles();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeUsuaRole.setEstaUsuaRole(1);
            this.objeUsuaRole.setFechAltaRole(new Date());
            FCDEUsuaRole.create(this.objeUsuaRole);
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            this.listUsuaRole.add(this.objeUsuaRole);
            this.limpForm();            
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
    }
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance();
        try
        {
            this.objeUsuaRole.setEstaUsuaRole(1);
            this.objeUsuaRole.setFechAltaRole(new Date());
            this.listUsuaRole.remove(this.objeUsuaRole);
            FCDEUsuaRole.edit(this.objeUsuaRole);
            this.listUsuaRole.add(this.objeUsuaRole);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
    }
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance();
        try
        {
            this.objeUsuaRole.setEstaUsuaRole(0);
            this.objeUsuaRole.setFechBajaRole(new Date());
            this.listUsuaRole.remove(this.objeUsuaRole);
            FCDEUsuaRole.edit(this.objeUsuaRole);
            this.listUsuaRole.add(this.objeUsuaRole);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al Eliminar ')");
        }
    }
    
    public void consTodo()
    {
        try
        {
            this.listUsuaRole = FCDEUsuaRole.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance();
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiPara"));
        try
        {
            this.objeUsuaRole = FCDEUsuaRole.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s %s", (this.objeUsuaRole.getCodiUsua().getCodiAlum().getNombAlum()+this.objeUsuaRole.getCodiUsua().getCodiAlum().getApelAlum()), this.objeUsuaRole.getCodiRole().getDescRole()) + "')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }
}
