/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.health.rest;

import com.health.entity.Account;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Amar
 */
@Stateless
@Path("account")
public class AccountFacadeREST extends AbstractFacade<Account> {

    @PersistenceContext(unitName = "HealthcareServerPU")
    private EntityManager em;

    public AccountFacadeREST() {
        super(Account.class);
    }
    
    
    
    @POST
    @Override
    @Path("create")
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Account entity) {
        super.create(entity);
    }

    @PUT
    @Path("update")
    @Consumes({MediaType.APPLICATION_XML})
    @Override
    public void edit(Account entity) {
        super.edit(entity);
    }

    @DELETE
    @Consumes({MediaType.TEXT_PLAIN})
    @Path("delete/{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("search/{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Account find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML})
    public List<Account> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Account> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
