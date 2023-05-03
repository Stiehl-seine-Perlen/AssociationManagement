package de.thi.association.controller;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import de.benevolo.entities.association.Association;
import de.thi.association.services.AssociationService;

import java.util.List;

@Path("/association/")
public class AssociationResource {
    // Schnittstelle

    @Inject
    AssociationService associationService;


    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void addAssociation(de.benevolo.entities.association.Association association) {
        // has password, because we like data security
        System.out.println(association);
        associationService.persistAssociation(association);
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Association> getAllEntries() {
        return associationService.getAllAssociations();
    }
}
