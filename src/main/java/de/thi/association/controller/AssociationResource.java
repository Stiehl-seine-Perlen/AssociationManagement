package de.thi.association.controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import de.benevolo.entities.association.Association;
import de.benevolo.entities.association.AssociationRole;
import de.benevolo.entities.association.Membership;
import de.thi.association.services.AssociationService;

import java.util.ArrayList;
import java.util.List;

@Path("/association/")
public class AssociationResource {
    // Schnittstelle

    @Inject
    AssociationService associationService;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Association addAssociation(Association association) {
        association = associationService.persistAssociation(association);
        associationService.initialize(association);
        return association;
    }

    @PUT
    @Path("{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean updateAssociation(@PathParam("id") Long id, Association association) {
        return associationService.updateAssociation(id, association);
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
    public Association getAssociation(@PathParam("id") Long id) {
        return associationService.getAssociationById(id);
    }

    @DELETE
    @Path("{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteAssociation(@PathParam("id") Long id) {
        return associationService.deleteAssociation(id);
    }

    @POST
    @Path("{id}/initialize/")
    @Consumes(MediaType.APPLICATION_JSON)
    public void initializeAssociation(Association association) {
        associationService.initialize(association);
    }

    // TODO: Implement this mock properly
    @GET
    @Path("{id}/members")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Membership> getMembersForAssociation(@PathParam("id") Long id) {
        AssociationRole role = new AssociationRole("Mitglied", "Vordefiniertes Mitglied", false, false, false);
        List<Membership> list = new ArrayList<>();
        list.add(new Membership(1L, 1L, role));
        list.add(new Membership(2L, 2L, role));
        list.add(new Membership(3L, 3L, role));
        list.add(new Membership(4L, 4L, role));
        list.add(new Membership(5L, 5L, role));
        return list;

    }
}
