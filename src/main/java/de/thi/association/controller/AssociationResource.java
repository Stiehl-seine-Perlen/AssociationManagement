package de.thi.association.controller;

import javax.inject.Inject;
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
    @Path("add/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Association addAssociation(Association association) {
        return associationService.persistAssociation(association);
    }

    @GET
    @Path("all/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Association> getAllEntries() {
        return associationService.getAllAssociations();
    }

    @GET
    @Path("{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Association getAssociation(@PathParam("id") Long id){
        return associationService.getAssociationById(id);
    }

    @GET
    @Path("{id}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteAssociation(@PathParam("id") Long id) {
        return associationService.removeAssociation(id);
    }
}
